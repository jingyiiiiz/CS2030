import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        Server server = new Server(name, 0.0, 0, true);
        int index = 0;

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            double serviceTime = sc.nextDouble();
            index++;
            Customer currCustomer = new Customer(arrivalTime, serviceTime, index);
            server = server.checkCustomer(currCustomer);
            System.out.println(server.toString());
        }

        sc.close();
    }
}
