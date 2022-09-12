class ServeEvent implements Event {
    private final Customer customer;
    private final Server server;

    ServeEvent(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
    }

    public Server updateServer() {
        return new Server(this.server.getName(), this.customer.getArrivalTime()
                        + this.customer.getServeTime());
    }

    // get time
    public double getTime() {
        return customer.getArrivalTime();
    }

    // get final time
    public double getFinalTime() {
        return this.customer.getArrivalTime() + this.customer.getServeTime();
    }

    public Event nextEvent(double currTime) {
        return new DoneEvent(this.customer, this.server);
    }

    public Server getServer() {
        return this.server;
    }

    // get index
    public int getIndex() {
        return this.customer.getIndex();
    }

    @Override
    public String toString() {
        return String.format("%.1f customer %d served by %s", this.getTime(),
        customer.getIndex(), server.getName());
    }
}
