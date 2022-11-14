class DoneEvent implements Event {
    private final Customer customer;
    private final int serverId;
    private final double time;
    private final int numHuman;
    private static final String TYPE_HUMAN = "HumanDone";
    private static final String TYPE_SELFCHECK = "SelfCheckDone";
    
    DoneEvent(Customer customer, int serverId, double time, int numHuman) {
        this.customer = customer;
        this.serverId = serverId;
        this.time = time;
        this.numHuman = numHuman;
    }

    @Override
    public String toString() {
        if (this.serverId <= this.numHuman) {
            return String.format("%.3f %d done serving by %d", this.time, 
                                 this.customer.getId(), this.serverId);
        } else {
            return String.format("%.3f %d done serving by self-check %d", 
                                 this.time, this.customer.getId(), this.serverId);
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
    public CounterQueue updateCounterQueue(CounterQueue counterQueue) {
        if (this.serverId <= this.numHuman) {
            return counterQueue;
        } else if (counterQueue.getSharedQueue().size() > 0) {
            ImList<Customer> newShared = counterQueue.getSharedQueue().remove(0);
            return new CounterQueue(counterQueue, newShared);
        } else {
            return counterQueue;
        }
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

    @Override
    public Event updateCustomer(CounterQueue counterQueue) {
        return this;
    }

    @Override
    public String getEventType(CounterQueue counterQueue) {
        if (this.serverId <= counterQueue.getNumHuman()) {
            return TYPE_HUMAN;
        } else {
            return TYPE_SELFCHECK;
        }
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
