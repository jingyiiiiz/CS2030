class Human implements Server {
    private static final String TYPE_HUMAN = "Human";
    private final int id;
    private final int qmax;
    private final ImList<Customer> serverQueue;
    private final double tillTime;

    Human(int id, int qmax) {
        this.id = id;
        this.qmax = qmax;
        this.serverQueue = new ImList<Customer>();
        this.tillTime = 0.0;
    }

    Human(int id, int qmax, double tillTime, ImList<Customer> serverQueue) {
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

    public String toString() {
        return String.format("%d", this.id);
    }

    public String getType() {
        return TYPE_HUMAN;
    }

    // only human has
    public ImList<Customer> getServerQueue() {
        return this.serverQueue;
    }

    // only human has
    public boolean canDirectlyServe(double time) {
        if (time >= this.tillTime && this.serverQueue.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    // only human has
    public boolean canQueue(double time) {
        if (this.serverQueue.size() == this.qmax) {
            return false;
        } else {
            return true;
        }
    }
}
