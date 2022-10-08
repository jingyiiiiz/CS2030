class ServeEvent implements Event {
    private final Customer customer;
    private final int serverId;
    private final double time;
    private static final String TYPE = "Serve";

    ServeEvent(Customer customer, int serverId, double time) {
        this.customer = customer;
        this.serverId = serverId;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d serves by %d", this.time, 
                             this.customer.getId(), this.serverId);
    }

    @Override
    public double getTime() {
        return this.time;
    }

    @Override
    public int getIndex() {
        return this.customer.getId();
    }

    @Override
    public Event nextEvent(CounterQueue counterQueue) {
        /*
         * next event is the DoneEvent
         * thus time of DoneEvent is starting time of Serve + amount of ServeTime needed
         */
        double endTime = this.time + this.customer.getServeTime();
        return new DoneEvent(this.customer, this.serverId, endTime);
    }

    @Override
    public CounterQueue updateCounterQueue(CounterQueue counterQueue) {
        Server currServer = counterQueue.getListOfServers().get(this.serverId - 1);
        
        Server newServer = new Server(this.serverId, currServer.getQmax(),
                                        this.time + this.customer.getServeTime(),
                                        currServer.getServerQueue());
        /*
         * if there is no customers waiting in this counter queue
         * nothing needs to be updated
         */
        return new CounterQueue(counterQueue, serverId, newServer);
    }

    @Override
    public double timeWaited(CounterQueue counterQueue) {
        return this.time - this.customer.getArriveTime();
    }

    @Override
    public int increaseInLeft() {
        /*
         * no increase in number of customer left since this is ArriveEvent
         */
        return 0;
    }

    @Override
    public Event updateCustomer(CounterQueue counterQueue) {
        Customer customerWithServe = new Customer(this.customer);
        return new ServeEvent(customerWithServe, this.serverId, this.time);
    }

    @Override
    public String getEventType() {
        return TYPE;
    }

    @Override
    public int getServerId() {
        return this.serverId;
    }

    @Override
    public Customer getCustomer() {
        return this.customer;
    }
}
