class ArriveEvent implements Event {
    private final Customer customer;
    private final Server server;
    
    ArriveEvent(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
    }

    public Event nextEvent(double currTime) {
        if (this.customer.getArrivalTime() >= currTime) {
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

    public Server getServer() {
        return this.server;
    }

    @Override
    public String toString() {
        return String.format("%.1f customer %d arrives", this.getTime(), customer.getIndex());
    }
}
