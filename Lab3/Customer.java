class Customer {
    private final double arrivalTime;
    private final double serveTime;
    private final int index;

    Customer(double arrivalTime, double serveTime, int index) {
        this.arrivalTime = arrivalTime;
        this.serveTime = serveTime;
        this.index = index;
    }

    double getArrivalTime() {
        return this.arrivalTime;
    }

    double getServeTime() {
        return this.serveTime;
    }

    int getIndex() {
        return this.index;
    }
}
