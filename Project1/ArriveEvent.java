class ArriveEvent implements Event {
    private final Customer customer;
    private final double time;

    ArriveEvent(Customer customer) {
        this.customer = customer;
        this.time = customer.getArriveTime();
    }

    @Override
    public String toString() {
        return String.format("%.3f %d arrives", this.time, this.customer.getId());
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
        int directServe = counterQueue.getDirectlyServe(this.customer);
        if (directServe == 0) {
            /*
             * in this case, the customer cannot be directly served
             * proceed to check whether can be inserted into counter queues
             */
            int waitNum = counterQueue.getWaitNum(this.customer);
            if (waitNum == 0) {
                /*
                 * in this case, the customer cannot get into counter queues
                 * so the customer just left
                 */
                return new LeaveEvent(this.customer);
            } else {
                /*
                 * in this case, the customer can get into one of the queues
                 * waitNum is the corresponding counter the customer gets into
                 */
                return new WaitEvent(this.customer, waitNum);
            }
        } else {
            /*
             * in this case, the customer can be directly served
             * proceed to be served by returning a serve event
             */
            return new ServeEvent(this.customer, directServe, this.customer.getArriveTime());
        }
    }

    @Override
    public CounterQueue updateCounterQueue(CounterQueue counterQueue) {
        int directServe = counterQueue.getDirectlyServe(this.customer);
        if (directServe == 0) {
            /*
             * in this case, the customer cannot be directly served
             * proceed to check whether can be inserted into counter queues
             */
            int waitNum = counterQueue.getWaitNum(this.customer);
            if (waitNum == 0) {
                /*
                 * in this case, the customer cannot get into counter queues and left
                 * no need to update counter queue
                 */
                return counterQueue;
            } else {
                /*
                 * in this case, the customer get into one counter queue
                 * update the counter queue such that the customer is now in the queue
                 */
                Server oldServer = counterQueue.getListOfServers().get(waitNum - 1);
                ImList<Customer> oldQueue = oldServer.getServerQueue();
                Server newServer = new Server(waitNum, oldServer.getQmax(),
                                              oldServer.getTillTime(), 
                                              oldQueue.add(this.customer));
                return new CounterQueue(counterQueue, waitNum, newServer);
            }
        } else {
            /*
             * in this case, the custoemr is directly served
             * thus need to change the "served untill" time in the corresponding server
             */
            Server oldServer = counterQueue.getListOfServers().get(directServe - 1);
            Server newServer = new Server(directServe, oldServer.getQmax(), 
                                          this.customer.getArriveTime() + 
                                          this.customer.getServeTime(), 
                                          oldServer.getServerQueue());
            return new CounterQueue(counterQueue, directServe, newServer);
        }
    }

    @Override
    public double timeWaited(CounterQueue counterQueue) {
        /*
         * no increase in timeWaited since this is ArriveEvent
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