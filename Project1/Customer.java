class Customer {
    private final int id;
    private final double arriveTime;
    private final double serveTime;

    Customer(int id, double arriveTime, double serveTime) {
        this.id = id;
        this.arriveTime = arriveTime;
        this.serveTime = serveTime;
    }

    public int getId() {
        return this.id;
    }

    public double getArriveTime() {
        return this.arriveTime;
    }

    public double getServeTime() {
        return this.serveTime;
    }
}
