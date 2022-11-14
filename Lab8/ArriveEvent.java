class ArriveEvent implements Event {
    private final Customer customer;
    private final double time;
    private static final String TYPE = "Arrive";

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
        int directServe = counterQueue.getDirectlyServebyHumans(this.customer);
        if (directServe != 0) {
            return new ServeEvent(this.customer, 
                                  directServe, this.customer.getArriveTime(), 
                                  counterQueue.getNumHuman()); 
        } else {
            int directRobot = counterQueue.getDirectlyServeByRobot(this.customer);
            if (directRobot != 0) {
                return new ServeEvent(this.customer, 
                                      directRobot, this.customer.getArriveTime(), 
                                      counterQueue.getNumHuman());
            } else {
                int waitNum = counterQueue.getWaitNumbyHumans(this.customer);
                if (waitNum != 0) {
                    return new WaitEvent(this.customer, waitNum, counterQueue.getNumHuman());
                } else {
                    boolean waitRobot = counterQueue.getWaitByRobot(this.customer);
                    if (waitRobot == true) {
                        return new WaitEvent(this.customer, counterQueue.getNumHuman() + 1, 
                        counterQueue.getNumHuman());
                    } else {
                        return new LeaveEvent(this.customer);
                    }
                }
            }
        }  
    }

    @Override
    public CounterQueue updateCounterQueue(CounterQueue counterQueue) {
        int directServe = counterQueue.getDirectlyServebyHumans(this.customer);
        if (directServe != 0) {
            return counterQueue;
        } else {
            int directRobot = counterQueue.getDirectlyServeByRobot(this.customer);
            if (directRobot != 0) {
                return counterQueue;
            } else {
                int waitNum = counterQueue.getWaitNumbyHumans(this.customer);
                if (waitNum != 0) {
                    Human oldServer = counterQueue.getListOfServers().get(waitNum - 1);
                    ImList<Customer> oldQueue = oldServer.getServerQueue();
                    Human newServer = new Human(waitNum, oldServer.getQmax(),
                                                oldServer.getTillTime(), 
                                                oldQueue.add(this.customer));
                    return new CounterQueue(counterQueue, waitNum, newServer);
                } else {
                    boolean waitRobot = counterQueue.getWaitByRobot(this.customer);
                    if (waitRobot == true) {
                        ImList<Customer> shared = counterQueue.getSharedQueue();
                        shared = shared.add(this.customer);
                        return new CounterQueue(counterQueue, shared);
                    } else {
                        return counterQueue;
                    }
                }
            }
        }
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
        return 0;
    }

    @Override
    public Customer getCustomer() {
        return this.customer;
    }
}
