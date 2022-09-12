import java.util.Currency;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        Server server = new Server(name, 0.0);
        int index = 0;
        PQ<Event> pq = new PQ<Event>(new EventComp());

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            double serviceTime = sc.nextDouble();
            index++;
            Customer currCustomer = new Customer(arrivalTime, serviceTime, index);
            ArriveEvent arriveEvent = new ArriveEvent(currCustomer, server);
            pq = pq.add(arriveEvent);
        }

        double currTime = 0.0;

        while (! pq.isEmpty()) {
            Pair<Event, PQ<Event>> pr = pq.poll();
            Event currEvent = pr.first();
            pq = pr.second();

            System.out.println(currEvent);

            if (currEvent.getFinalTime() >= currTime) {
                currTime = currEvent.getFinalTime();
            }

            Event subsequentEvent = currEvent.nextEvent(currTime);

            if (subsequentEvent == currEvent) {
                continue;
            } else {
                pq = pq.add(subsequentEvent);
            }
        }

        sc.close();
    }
}
