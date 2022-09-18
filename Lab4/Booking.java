class Booking implements Comparable<Booking> {
    private final Driver driver;
    private final Request request;
    private static final float CONVERSION = 100;

    Booking(Driver driver, Request request) {
        this.driver = driver;
        this.request = request;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public Request getRequest() {
        return this.request;
    }

    public int getWaitingTime() {
        return this.getDriver().getWaitingTime();
    }

    public float getFare() {
        return this.getDriver().getFare(this.getRequest());
    }

    public Service getService() {
        return this.getDriver().getService(this.getRequest());
    }

    public float getSecondFare() {
        return this.getDriver().getSecondFare(this.getRequest());
    }

    public Service getSecondService() {
        return this.getDriver().getSecondService(this.getRequest());
    }

    @Override
    public int compareTo(Booking other) {
        if (this.getFare() > other.getFare()) {
            return 1;
        } else if (this.getFare() < other.getFare()) {
            return -1;
        } else {
            if (this.getWaitingTime() > other.getWaitingTime()) {
                return 1;
            } else if (this.getWaitingTime() < other.getWaitingTime()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("$%.2f using %s (%s)", this.getFare(), 
        this.getDriver(), this.getService());
    }
}
