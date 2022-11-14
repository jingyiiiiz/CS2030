class WaitEvent implements Event {
    private final Customer customer;
    private final int serverId;
    private final double time;
    private static final String TYPE = "Wait";
    private final int numHuman;

    WaitEvent(Customer customer, int serverId, int numHuman) {
        this.customer = customer;
        this.serverId = serverId;
        this.time = customer.getArriveTime();
        this.numHuman = numHuman;
    }

    @Override
    public String toString() {
        if (this.serverId <= this.numHuman) {
            return String.format("%.3f %d waits at %d", this.time, 
                             this.customer.getId(), this.serverId);
        } else {
            return String.format("%.3f %d waits at self-check %d", this.time,
                                 this.customer.getId(), this.serverId);
        }    
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
        if (this.serverId <= counterQueue.getNumHuman()) {
            Human currServer = counterQueue.getListOfServers().get(serverId - 1);
            double time = currServer.getTillTime();
            return new ServeEvent(this.customer, serverId, time, counterQueue.getNumHuman());
        } else {
            return this;
        }   
    }

    @Override
    public CounterQueue updateCounterQueue(CounterQueue counterQueue) {
        return counterQueue;
    }

    @Override
    public double timeWaited(CounterQueue counterQueue) {
        return 0.0;
    }

    @Override
    public int increaseInLeft() {
        return 0;
    }

    @Override
    public Event updateCustomer(CounterQueue counterQueue) {
        return this;
    }

    @Override
    public String getEventType(CounterQueue counterQueue) {
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
