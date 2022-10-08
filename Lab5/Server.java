class Server {
    private final int id;
    private final int qmax;
    private final ImList<Customer> serverQueue;
    private final double tillTime;

    Server(int id, int qmax) {
        this.id = id;
        this.qmax = qmax;
        this.serverQueue = new ImList<Customer>();
        this.tillTime = 0.0;
    }

    Server(int id, int qmax, double tillTime, ImList<Customer> serverQueue) {
        this.id = id;
        this.qmax = qmax;
        this.tillTime = tillTime;
        this.serverQueue = serverQueue;
    }

    public int getId() {
        return this.id;
    }

    public int getQmax() {
        return this.qmax;
    }

    public double getTillTime() {
        return this.tillTime;
    }

    public ImList<Customer> getServerQueue() {
        return this.serverQueue;
    }

    public String toString() {
        return String.format("%d", this.id);
    }

    public boolean canDirectlyServe(double time) {
        if (time >= this.tillTime && this.serverQueue.size() == 0) {
            /*
             * if there is no customers in the waiting queue
             * and the arrival time is later than the server finishes serving
             * the incoming customer can be directly served
             */
            return true;
        } else {
            /*
             * otherwise the customer cannot be directly served
             */
            return false;
        }
    }

    public boolean canQueue(double time) {
        if (this.serverQueue.size() == this.qmax) {
            /*
             * if the waiting queue is full
             * no new customers can be taken in
             */
            return false;
        } else {
            /*
             * otherwise, the customer can get into the waiting queue
             */
            return true;
        }
    }
}
