class WaitEvent implements Event {
    private final Customer customer;
    private final int serverId;
    private final double time;

    WaitEvent(Customer customer, int serverId) {
        this.customer = customer;
        this.serverId = serverId;
        this.time = customer.getArriveTime();
    }

    @Override
    public String toString() {
        return String.format("%.3f %d waits at %d", this.time, 
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
         * next event is the ServeEvent
         * time of the ServeEvent is the time that the server is busy until
         */
        Server currServer = counterQueue.getListOfServers().get(serverId - 1);
        double time = currServer.getTillTime();
        return new ServeEvent(this.customer, serverId, time);
    }

    @Override
    public CounterQueue updateCounterQueue(CounterQueue counterQueue) {
        /*
         * when the serving time of the next ServeEvent is confirmed
         * the time that the server is busy till due to the new ServeEvent 
         * can be determined
         */
        Server oldServer = counterQueue.getListOfServers().get(serverId - 1);
        double newTime = oldServer.getTillTime() + this.customer.getServeTime();
        /*
         * update the time that the server is busy till accordingly
         */
        Server newServer = new Server(this.serverId, oldServer.getQmax(), 
                                      newTime, oldServer.getServerQueue());
        return new CounterQueue(counterQueue, serverId, newServer);
    }

    @Override
    public double timeWaited(CounterQueue counterQueue) {
        /*
         * there is an increase in total waiting time because it is a WaitEvent
         * the time that the customer gets to be served is equal to the time 
         * that the server is busy untill
         * the waiting time is the difference between the time that the customer
         * gets to be served
         * and the time that the customer arrives
         */
        Server oldServer = counterQueue.getListOfServers().get(serverId - 1);
        return oldServer.getTillTime() - this.customer.getArriveTime();
    }

    @Override
    public int increaseInLeft() {
        /*
         * no increase in number of customer left since this is WaitEvent
         */
        return 0;
    }
}
