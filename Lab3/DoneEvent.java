class DoneEvent implements Event {
    private final Customer customer;
    private final Server server;

    DoneEvent(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
    }

    public Server updateServer() {
        return new Server(this.server.getName(), this.server.getServeTill());
    }

    public Event nextEvent(double currTime) {
        return this;
    }

    // get time
    public double getTime() {
        return this.customer.getArrivalTime() + this.customer.getServeTime();
    }

    // get finaltime
    public double getFinalTime() {
        return this.customer.getArrivalTime() + this.customer.getServeTime();
    }

    // get index
    public int getIndex() {
        return this.customer.getIndex();
    }

    public Server getServer() {
        return this.server;
    }

    @Override
    public String toString() {
        return String.format("%.1f customer %d done serving by %s", 
        this.getTime(), this.customer.getIndex(),
        this.server.getName());
    }
}
