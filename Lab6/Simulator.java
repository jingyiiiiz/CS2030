import java.util.Currency;
import java.util.function.Supplier;

class Simulator {
    private final ImList<Customer> customers;
    private final ImList<Server> servers;
    private final int qmax;
    private final Supplier<Double> restTimes;

    Simulator(int numOfServers, int qmax, 
              ImList<Pair<Double,Supplier<Double>>> inputTimes,
              Supplier<Double> restTimes) {
        /*
         * initialise the list of customers
         * by iterating through the list of timings
         */
        ImList<Customer> customerList = new ImList<Customer>();
        for (int i = 0; i < inputTimes.size(); i++) {
            Pair<Double,Supplier<Double>> times = inputTimes.get(i);
            customerList = customerList.add(new Customer(i + 1, times.first(), 
                                            times.second()));
        }
        this.customers = customerList;
        /*
         * initialise the list of servers
         * by adding servers one by one into the list
         */
        ImList<Server> serverList = new ImList<Server>();
        for (int i = 0; i < numOfServers; i++) {
            serverList = serverList.add(new Server(i + 1, qmax));
        }
        this.servers = serverList;
        this.qmax = qmax;
        this.restTimes = restTimes;
    }

    public String simulate() {
        String result = "";
        double totalWaitingTime = 0.0;
        int customerLeft = 0;
        ImList<Event> events = new ImList<Event>();
        PQ<Event> pq = new PQ<Event>(new EventComp());
        CounterQueue counterQueue = new CounterQueue(this.servers, qmax);
        // initialise the counter queue
        for (int i = 0; i < this.customers.size(); i++) {
            // adding each ArriveEvent into the priority queue
            Customer currCustomer = this.customers.get(i);
            ArriveEvent currArriveEvent = new ArriveEvent(currCustomer);
            pq = pq.add(currArriveEvent);
        }

        while (! pq.isEmpty()) {
            Pair<Event, PQ<Event>> pr = pq.poll();
            Event currEvent = pr.first();
            pq = pr.second();

            if (currEvent.getEventType(counterQueue) == "Serve") {
                Event newServeEvent = currEvent.updateCustomer(counterQueue);
                Server theServer =
                       counterQueue.getListOfServers().get(currEvent.getServerId() - 1);
                pq = pq.add(newServeEvent);
                continue;
            } 

            events = events.add(currEvent);
            totalWaitingTime = totalWaitingTime + currEvent.timeWaited(counterQueue);
            customerLeft = customerLeft + currEvent.increaseInLeft();

            Event subsequentEvent = currEvent.nextEvent(counterQueue);
            if (currEvent.getEventType(counterQueue) == "Done") {
                Server currServer =
                       counterQueue.getListOfServers().get(currEvent.getServerId() - 1);
                double restForServer = restTimes.get();
                if (restForServer > 0) {
                    Server newServer = new Server(currServer.getId(), currServer.getQmax(), 
                            currEvent.getTime() + restForServer, 
                            currServer.getServerQueue());
                    counterQueue = new CounterQueue(counterQueue, newServer.getId(), 
                            newServer); 
                } 
            }
            counterQueue = currEvent.updateCounterQueue(counterQueue);

            /*
             * this is to check whether the event has subsequent events
             * for LeaveEvent and DoneEvent, there is no subsequent events
             * so themselves will be returned
             * and the if condition checks this case 
             * and do not add the identical event back into the priority queue
             */

            if (subsequentEvent == currEvent) {
                continue;
            } else {
                pq = pq.add(subsequentEvent);
            }
        }

        for (int i = 0; i < events.size(); i++) {
            result = result + events.get(i).toString();
            result = result + "\n";
        }

        int customerServed = this.customers.size() - customerLeft;
        double avgTime = totalWaitingTime / customerServed;

        String lastLine = String.format("[%.3f %d %d]", avgTime, customerServed, customerLeft);
        result = result + lastLine;

        return result;
    }

    

}
