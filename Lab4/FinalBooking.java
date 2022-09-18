class FinalBooking {
    private final float fare;
    private final Driver driver;
    private final Service service;

    FinalBooking(float fare, Driver driver, Service service) {
        this.fare = fare;
        this.driver = driver;
        this.service = service;
    }

    public float getFare() {
        return this.fare;
    }

    public int getWaitingTime() {
        return this.driver.getWaitingTime();
    }

    @Override
    public String toString() {
        return String.format("$%.2f using %s (%s)", this.getFare(), 
        this.driver, this.service);
    }
}
