import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        Server server = new Server(name, 0.0);
        int index = 0;

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            double serviceTime = sc.nextDouble();
            index++;
            Customer currCustomer = new Customer(arrivalTime, serviceTime, index);
            ArriveEvent arriveEvent = new ArriveEvent(currCustomer, server);
            System.out.println(arriveEvent);
            Event nextEvent = arriveEvent.nextEvent();
            System.out.println(nextEvent);
            server = nextEvent.updateServer();
        }

        sc.close();
    }
}
