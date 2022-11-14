import java.util.Currency;
import java.util.function.Supplier;

class Simulator {
    private final ImList<Customer> customers;
    private final ImList<Human> servers;
    private final ImList<SelfCheck> selfChecks;
    private final int qmax;
    private final Supplier<Double> restTimes;
    private final int numOfServers;
    private final int numOfSelfChecks;

    Simulator(int numOfServers, int numOfSelfChecks,
              int qmax, 
              ImList<Pair<Double,Supplier<Double>>> inputTimes,
              Supplier<Double> restTimes) {
        ImList<Customer> customerList = new ImList<Customer>();
        for (int i = 0; i < inputTimes.size(); i++) {
            Pair<Double,Supplier<Double>> times = inputTimes.get(i);
            customerList = customerList.add(new Customer(i + 1, times.first(), 
                                            times.second()));
        }
        this.customers = customerList;
        ImList<Human> serverList = new ImList<Human>();
        for (int i = 0; i < numOfServers; i++) {
            serverList = serverList.add(new Human(i + 1, qmax));
        }
        this.servers = serverList;
        ImList<SelfCheck> selfCheckList = new ImList<SelfCheck>();
        for (int j = 0; j < numOfSelfChecks; j++) {
            selfCheckList = selfCheckList.add(
                            new SelfCheck(j + 1 + numOfServers, qmax, 
                                          numOfSelfChecks + 1));
        }
        this.selfChecks = selfCheckList;
        this.qmax = qmax;
        this.restTimes = restTimes;
        this.numOfSelfChecks = numOfSelfChecks;
        this.numOfServers = numOfServers;
    }

    public String simulate() {
        String result = "";
        double totalWaitingTime = 0.0;
        int customerLeft = 0;
        ImList<Event> events = new ImList<Event>();
        PQ<Event> pq = new PQ<Event>(new EventComp());
        ImList<Customer> sharedQueue = new ImList<Customer>();
        CounterQueue counterQueue = new CounterQueue(this.servers, this.selfChecks,
                                                     sharedQueue, qmax, 
                                                     this.numOfServers, 
                                                     this.numOfSelfChecks);
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
                pq = pq.add(newServeEvent);
                continue;
            } 

            events = events.add(currEvent);
            totalWaitingTime = totalWaitingTime + currEvent.timeWaited(counterQueue);
            customerLeft = customerLeft + currEvent.increaseInLeft();

            Event subsequentEvent = currEvent.nextEvent(counterQueue);
            if (currEvent.getEventType(counterQueue) == "HumanDone") {
                Human currServer =
                       counterQueue.getListOfServers().get(currEvent.getServerId() - 1);
                double restForServer = restTimes.get();
                if (restForServer > 0) {
                    Human newServer = new Human(currServer.getId(), currServer.getQmax(), 
                            currEvent.getTime() + restForServer, 
                            currServer.getServerQueue());
                    counterQueue = new CounterQueue(counterQueue, newServer.getId(), 
                            newServer); 
                } 
            } else if (currEvent.getEventType(counterQueue) == "SelfCheckDone") {
                ImList<Customer> custQueue = counterQueue.getSharedQueue();
                if (custQueue.size() > 0) {
                    Customer toServe = custQueue.get(0);
                    Event newNextServe = new ServeEvent(toServe, 
                                                        currEvent.getServerId(),
                                                        currEvent.getTime(),
                                                        this.numOfServers);
                    pq = pq.add(newNextServe);
                }
            }
            counterQueue = currEvent.updateCounterQueue(counterQueue);

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
