class CounterQueue {
    private final ImList<Server> listOfServers;
    private final int qmax;

    /*
     * for initiation of the very first counterQueue
     */
    CounterQueue(ImList<Server> listOfServers, int qmax) {
        this.listOfServers = listOfServers;
        this.qmax = qmax;
    }

    /*
     * for initiation of subsequent counterQueue that is updated from previous ones
     */
    CounterQueue(CounterQueue counterQueue, int serverId, Server server) {
        ImList<Server> newList = new ImList<Server>();
        ImList<Server> oldList = counterQueue.getListOfServers();
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
        this.qmax = counterQueue.getQmax();
    }
    
    public ImList<Server> getListOfServers() {
        return this.listOfServers;
    }

    public int getQmax() {
        return this.qmax;
    }

    public int getDirectlyServe(Customer customer) {
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

    public int getWaitNum(Customer customer) {
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

}
