class CounterQueue {
    private final ImList<Human> listOfServers;
    private final ImList<SelfCheck> listOfSelfCheck;
    private final ImList<Customer> sharedQueue;
    private final int qmax;
    private final int numHuman;
    private final int numSelfCheck;

    CounterQueue(ImList<Human> listOfServers, ImList<SelfCheck> listOfSelfCheck, 
                  ImList<Customer> sharedQueue, int qmax, 
                  int numHuman, int numSelfCheck) {
        this.listOfServers = listOfServers;
        this.listOfSelfCheck = listOfSelfCheck;
        this.sharedQueue = sharedQueue;
        this.qmax = qmax;
        this.numHuman = numHuman;
        this.numSelfCheck = numSelfCheck;
    }

    CounterQueue(CounterQueue counterQueue, int serverId, Human server) {
        ImList<Human> newList = new ImList<Human>();
        ImList<Human> oldList = counterQueue.getListOfServers();
        for (int i = 0; i < oldList.size(); i++) {
            if (i + 1 == serverId) {
                // noting the id of the server that needs to be updated
                newList = newList.add(server);
                // change the server that needs to be updated
            } else {
                newList = newList.add(oldList.get(i));
            }
        }
        this.listOfServers = newList;
        this.listOfSelfCheck = counterQueue.getListOfSelfChecks();
        this.sharedQueue = counterQueue.getSharedQueue();
        this.qmax = counterQueue.getQmax();
        this.numHuman = counterQueue.getNumHuman();
        this.numSelfCheck = counterQueue.getNumSelfCheck();
    }

    CounterQueue(CounterQueue counterQueue, SelfCheck selfCheck, int serverId) {
        this.listOfServers = counterQueue.getListOfServers();
        ImList<SelfCheck> newList = new ImList<SelfCheck>();
        ImList<SelfCheck> oldList = counterQueue.getListOfSelfChecks();
        int actualId = serverId - counterQueue.getNumHuman();
        for (int i = 0; i < oldList.size(); i++) {
            if (i + 1 == actualId) {
                newList = newList.add(selfCheck);
            } else {
                newList = newList.add(oldList.get(i));
            }
        }
        this.listOfSelfCheck = newList;
        this.sharedQueue = counterQueue.getSharedQueue();
        this.qmax = counterQueue.getQmax();
        this.numHuman = counterQueue.getNumHuman();
        this.numSelfCheck = counterQueue.getNumSelfCheck();
    }

    CounterQueue(CounterQueue counterQueue, ImList<Customer> shared) {
        this.listOfServers = counterQueue.getListOfServers();
        this.listOfSelfCheck = counterQueue.getListOfSelfChecks();
        this.sharedQueue = shared;
        this.qmax = counterQueue.getQmax();
        this.numHuman = counterQueue.getNumHuman();
        this.numSelfCheck = counterQueue.getNumSelfCheck();
    }
    
    public ImList<Human> getListOfServers() {
        return this.listOfServers;
    }

    public ImList<SelfCheck> getListOfSelfChecks() {
        return this.listOfSelfCheck;
    }

    public ImList<Customer> getSharedQueue() {
        return this.sharedQueue;
    }

    public int getQmax() {
        return this.qmax;
    }

    public int getNumHuman() {
        return this.numHuman;
    }

    public int getNumSelfCheck() {
        return this.numSelfCheck;
    }

    public int getDirectlyServebyHumans(Customer customer) {
        int result = 0;
        // by default, no suitable server
        for (int i = 0; i < this.listOfServers.size(); i++) {
            if (listOfServers.get(i).canDirectlyServe(customer.getArriveTime())) {
                // the first available server gotten that can directly serve the customer
                result = i + 1;
                break;
                // break the cycle since no need to check others
            } else {
                continue;
                // if not, continue finding
            }
        }
        return result;
    }

    public int getWaitNumbyHumans(Customer customer) {
        int result = 0;
        // by default, no suitable server
        for (int i = 0; i < this.listOfServers.size(); i++) {
            if (listOfServers.get(i).canQueue(customer.getArriveTime())) {
                // the first available server gotten that has an available queue
                result = i + 1;
                break;
                // break the cycle since no need to check others
            } else {
                continue;
                // if not, continue finding
            }
        }
        return result;
    }

    public int getDirectlyServeByRobot(Customer customer) {
        int result = 0;
        if (this.sharedQueue.size() > 0) {
            return result;
        } else {
            for (int i = 0; i < this.numSelfCheck; i++) {
                if (listOfSelfCheck.get(i).getTillTime() <= customer.getArriveTime()) {
                    result = listOfSelfCheck.get(i).getId();
                    break;
                } else {
                    continue;
                }
            }
            return result;
        }
    }

    public boolean getWaitByRobot(Customer customer) {
        if (this.sharedQueue.size() == qmax) {
            return false;
        } else if (this.numSelfCheck == 0) {
            return false;
        } else {
            return true;
        }
    }

    public double getMinServe() {
        double result = Double.MAX_VALUE;
        double theTime;
        for (int i = 0; i < this.numSelfCheck; i++) {
            theTime = this.listOfSelfCheck.get(i).getTillTime();
            if (theTime < result) {
                result = theTime;
            }
        }
        return result;
    }

    public int getMinId() {
        double result = Double.MAX_VALUE;
        int minId = 0;
        double theTime;
        for (int i = 0; i < this.numSelfCheck; i++) {
            theTime = this.listOfSelfCheck.get(i).getTillTime();
            if (theTime < result) {
                result = theTime;
                minId = this.numHuman + 1 + i;
            }
        }
        return minId;
    }

}
