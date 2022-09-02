class Server {   
    private final String name;
    private final double serveTill;
    private final int currentIndex;
    private final boolean canServe;

    Server(String name, double serveTill, int currentIndex, boolean canServe) {
        this.name = name;
        this.serveTill = serveTill;
        this.currentIndex = currentIndex;
        this.canServe = canServe;
    }

    Server checkCustomer(Customer customer) {
        Server newServer;
        if (this.serveTill <= customer.getArrivalTime()) {
            newServer = new Server(
                    this.name,
                    customer.getArrivalTime() + customer.getServeTime(),
                    customer.getIndex(),
                    true);
        }
        else {
            newServer = new Server(
                    this.name,
                    this.serveTill,
                    customer.getIndex(),
                    false);
        }
        return newServer;
    }

    @Override
    public String toString() {
        if (this.canServe == true) {
            return "customer " + this.currentIndex + " served by " + this.name;
        }
        else {
            return "customer " + this.currentIndex + " left";
        }
    }
    
}
