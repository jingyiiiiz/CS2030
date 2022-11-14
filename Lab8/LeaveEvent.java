class LeaveEvent implements Event {
    private final Customer customer;
    private final double time;
    private static final String TYPE = "Leave";

    LeaveEvent(Customer customer) {
        this.customer = customer;
        this.time = customer.getArriveTime();
    }

    @Override
    public String toString() {
        return String.format("%.3f %d leaves", this.time, this.customer.getId());
    }

    @Override
    public double getTime() {
        return this.time;
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

    @Override
    public int getIndex() {
        return this.customer.getId();
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
         * no increase in timeWaited since this is LeaveEvent
         */
        return 0.0;
    }

    @Override
    public int increaseInLeft() {
        /*
         * increase in number of customer left since this is LeaveEvent
         */
        return 1;
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
        return 0;
    }

    @Override
    public Customer getCustomer() {
        return this.customer;
    }
}
