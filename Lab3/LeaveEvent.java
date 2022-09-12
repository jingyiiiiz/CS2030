class LeaveEvent implements Event {
    private final Customer customer;
    private final Server server;

    LeaveEvent(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
    }

    public Server updateServer() {
        return new Server(this.server.getName(), this.server.getServeTill());
    }

    public Event nextEvent(double currTime) {
        return this;
    }

    public Server getServer() {
        return this.server;
    }

    // get time
    public double getTime() {
        return this.customer.getArrivalTime();
    }

    // get final time
    public double getFinalTime() {
        return this.customer.getArrivalTime();
    }

    // get index
    public int getIndex() {
        return this.customer.getIndex();
    }

    @Override
    public String toString() {
        return String.format("%.1f customer %d leaves", this.getTime(),
        this.customer.getIndex());
    }
}
