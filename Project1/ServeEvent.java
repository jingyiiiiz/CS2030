class ServeEvent implements Event {
    private final Customer customer;
    private final int serverId;
    private final double time;

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
        if (currServer.getServerQueue().size() == 0) {
            /*
             * if there is no customers waiting in this counter queue
             * nothing needs to be updated
             */
            return counterQueue;
        } else {
            /*
             * if there are customers in the waiting queue
             * we remove this customer from the queue
             * since as soon as the ServeEvent is finished
             * next customer in the queue is to be served
             */
            ImList<Customer> oldQueue = currServer.getServerQueue();
            ImList<Customer> newQueue = oldQueue.remove(0);
            Server newServer = new Server(this.serverId, currServer.getQmax(), 
                                          currServer.getTillTime(), 
                                          newQueue);
            return new CounterQueue(counterQueue, this.serverId, newServer);
        }
    }

    @Override
    public double timeWaited(CounterQueue counterQueue) {
        /*
         * no increase in timeWaited since this is ServeEvent
         */
        return 0.0;
    }

    @Override
    public int increaseInLeft() {
        /*
         * no increase in number of customer left since this is ArriveEvent
         */
        return 0;
    }
}
