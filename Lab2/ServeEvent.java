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

    @Override
    public String toString() {
        return String.format("customer %d served by %s", customer.getIndex(), server.getName());
    }
}
