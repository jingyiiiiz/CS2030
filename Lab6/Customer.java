import java.util.function.Supplier;

class Customer {
    private final int id;
    private final double arriveTime;
    private final  Supplier<Double> serveTimeRaw;
    private final double serveTime;

    Customer(int id, double arriveTime, Supplier<Double> serveTimeRaw) {
        this.id = id;
        this.arriveTime = arriveTime;
        this.serveTimeRaw = serveTimeRaw;
        this.serveTime = 0.0;
    }

    Customer(Customer customer) {
        this.id = customer.getId();
        this.arriveTime = customer.getArriveTime();
        this.serveTimeRaw = customer.getServeTimeRaw();
        this.serveTime = customer.serveTimeRaw.get();
    }

    public int getId() {
        return this.id;
    }

    public double getArriveTime() {
        return this.arriveTime;
    }

    public Supplier<Double> getServeTimeRaw() {
        return this.serveTimeRaw;
    }

    public double getServeTime() {
        return this.serveTime;
    }
}
