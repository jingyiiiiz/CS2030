class ArriveEvent implements Event {
    private final Customer customer;
    private final Server server;
    
    ArriveEvent(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
    }

    public Event nextEvent() {
        if (customer.getArrivalTime() >= server.getServeTill()) {
            return new ServeEvent(new Customer(this.customer.getArrivalTime(), 
                                  this.customer.getServeTime(), this.customer.getIndex()),
                                  new Server(this.server.getName(), this.server.getServeTill()));
        } else {
            return new LeaveEvent(this.customer, this.server);
        }
    }

    public Server updateServer() {
        return new Server(this.server.getName(), this.server.getServeTill()
                         + this.customer.getServeTime());
    }

    @Override
    public String toString() {
        return String.format("customer %d arrives", customer.getIndex());
    }
}
