
class ServeEvent implements Event {
    private final Customer customer;
    private final int serverId;
    private final double time;
    private final boolean updated;
    private final boolean withServe;
    private final int numHuman;
    private static final String TYPE = "Serve";
    private static final String TYPE_UPDATE = "Serve_Updated";

    ServeEvent(Customer customer, int serverId, double time, int numHuman) {
        this.customer = customer;
        this.serverId = serverId;
        this.time = time;
        this.updated = false;
        this.withServe = false;
        this.numHuman = numHuman;
    }

    ServeEvent(Customer customer, int serverId, double time, boolean updated, int numHuman) {
        this.customer = customer;
        this.serverId = serverId;
        this.time = time;
        this.updated = updated;
        this.withServe = false;
        this.numHuman = numHuman;
    }

    ServeEvent(Customer customer, int serverId, double time, 
               boolean updated, boolean withServe, int numHuman) {
        this.customer = customer;
        this.serverId = serverId;
        this.time = time;
        this.updated = updated;
        this.withServe = withServe;
        this.numHuman = numHuman;
    }

    @Override
    public String toString() {
        if (this.serverId <= numHuman) {
            return String.format("%.3f %d serves by %d", this.time, 
                             this.customer.getId(), this.serverId);
        } else {
            return String.format("%.3f %d serves by self-check %d", 
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

    @Override
    public Event nextEvent(CounterQueue counterQueue) {
        double endTime = this.time + this.customer.getServeTime();
        return new DoneEvent(this.customer, this.serverId, endTime, this.numHuman);
    }

    @Override
    public CounterQueue updateCounterQueue(CounterQueue counterQueue) {
        if (this.serverId <= counterQueue.getNumHuman()) {
            Human currServer = counterQueue.getListOfServers().get(this.serverId - 1);
            if (currServer.getServerQueue().size() > 0) {
                ImList<Customer> queue = currServer.getServerQueue();
                queue = queue.remove(0);
                Human newServer = new Human(this.serverId, currServer.getQmax(),
                                            this.time + this.customer.getServeTime(),
                                            queue);
                return new CounterQueue(counterQueue, serverId, newServer);
            } else {
                Human newServer = new Human(this.serverId, currServer.getQmax(),
                                            this.time + this.customer.getServeTime(),
                                            currServer.getServerQueue());
                return new CounterQueue(counterQueue, serverId, newServer);
            }
        } else {
            ImList<Customer> sharedQueue = counterQueue.getSharedQueue();
            for (int i = 0; i < sharedQueue.size(); i++) {
                Customer custInQueue = sharedQueue.get(i);
                if (this.customer.getId() == custInQueue.getId()) {
                    sharedQueue = sharedQueue.remove(i);
                }
            }
            counterQueue = new CounterQueue(counterQueue, sharedQueue);
            SelfCheck currSelfCheck = counterQueue
                                      .getListOfSelfChecks()
                                      .get(this.serverId - 1 - counterQueue.getNumHuman());
            SelfCheck newSelfCheck = new SelfCheck(serverId, currSelfCheck.getQmax(),
                                                        currSelfCheck.getSmallestId(),
                                                        this.time 
                                                        + this.customer.getServeTime());
            return new CounterQueue(counterQueue, newSelfCheck, serverId);
        }
    }

    @Override
    public double timeWaited(CounterQueue counterQueue) {
        return this.time - this.customer.getArriveTime();
    }

    @Override
    public int increaseInLeft() {
        return 0;
    }

    @Override
    public Event updateCustomer(CounterQueue counterQueue) {
        if (this.serverId <= counterQueue.getNumHuman()) {
            Human currServer = counterQueue.getListOfServers().get(this.serverId - 1);
            double newTime = currServer.getTillTime();
            if (newTime > this.time) {
                return new ServeEvent(this.customer, this.serverId, newTime, false, this.numHuman);
            } else if (this.updated == false) {
                return new ServeEvent(this.customer, this.serverId, 
                                      this.time, true, false, this.numHuman);
            } else {
                Customer customerWithServe = new Customer(this.customer);
                return new ServeEvent(customerWithServe, this.serverId, this.time, 
                                      true, true, this.numHuman);
            }
        } else {
            ImList<SelfCheck> listOfSelfCheck = counterQueue.getListOfSelfChecks();
            SelfCheck currSelfCheck = listOfSelfCheck.get(this.serverId 
                                                          - counterQueue.getNumHuman() - 1);
            double newTime = currSelfCheck.getTillTime();
            if (newTime > this.time) {
                return new ServeEvent(this.customer, this.serverId, newTime, false, this.numHuman);
            } else {
                Customer customerWithServe = new Customer(this.customer);
                return new ServeEvent(customerWithServe, this.serverId, this.time, 
                                      true, true, this.numHuman);
            }
        }    
    }

    @Override
    public String getEventType(CounterQueue counterQueue) {
        if (this.serverId <= counterQueue.getNumHuman()) {
            ImList<Human> listOfServers = counterQueue.getListOfServers();
            // counterQueue.getListOfServers().get(this.serverId - 1);
            Human currServer = listOfServers.get(this.serverId - 1);
            double newTime = currServer.getTillTime();
            if (newTime > this.time) {
                return TYPE;
            } else if (this.updated == false) {
                return TYPE;
            } else if (this.withServe == false) {
                return TYPE;
            } else {
                return TYPE_UPDATE;
            }
        } else {
            ImList<SelfCheck> listOfSelfCheck = counterQueue.getListOfSelfChecks();
            SelfCheck currSelfCheck = listOfSelfCheck.get(this.serverId 
                                                          - counterQueue.getNumHuman() - 1);
            double newTime = currSelfCheck.getTillTime();
            if (newTime > this.time) {
                return TYPE;
            } else if (this.updated == false) {
                return TYPE;
            } else if (this.withServe == false) {
                return TYPE;
            } else {
                return TYPE_UPDATE;
            }
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
