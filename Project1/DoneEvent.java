class DoneEvent implements Event {
    private final Customer customer;
    private final int serverId;
    private final double time;
    
    DoneEvent(Customer customer, int serverId, double time) {
        this.customer = customer;
        this.serverId = serverId;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d done serving by %d", this.time, 
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

    /*
     * no subsequent events take place for this customer
     * thus returns itself
     * this will cause the next identical event to be eliminated in Main
     */
    @Override
    public Event nextEvent(CounterQueue counterQueue) {
        return this;
    }

    /*
     * no effect on counter queues
     */
    @Override
    public CounterQueue updateCounterQueue(CounterQueue counterQueue) {
        return counterQueue;
    }

    @Override
    public double timeWaited(CounterQueue counterQueue) {
        /*
         * no increase in timeWaited since this is DoneEvent
         */
        return 0.0;
    }

    @Override
    public int increaseInLeft() {
        /*
         * no increase in number of customer left since this is DoneEvent
         */
        return 0;
    }
}
