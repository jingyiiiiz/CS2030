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

    @Override
    public String toString() {
        return String.format("customer %d leaves", this.customer.getIndex());
    }
}
