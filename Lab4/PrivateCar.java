class PrivateCar implements Driver {
    private final String license;
    private final int waitingTime;
    private static final float CONVERSION = 100;

    PrivateCar(String license, int waitingTime) {
        this.license = license;
        this.waitingTime = waitingTime;
    }

    public String getLicense() {
        return this.license;
    }

    public int getWaitingTime() {
        return this.waitingTime;
    }

    public String toString() {
        return String.format("%s (%d mins away) %s", 
        this.getLicense(), this.getWaitingTime(), "PrivateCar");
    }

    public float getFare(Request request) {
        if (request.computeFare(new JustRide()) < 
            request.computeFare(new ShareARide())) {
            return request.computeFare(new JustRide()) / CONVERSION;
        } else {
            return request.computeFare(new ShareARide()) / CONVERSION;
        }
    }

    public Service getService(Request request) {
        if (request.computeFare(new JustRide()) < 
            request.computeFare(new ShareARide())) {
            return new JustRide();
        } else {
            return new ShareARide();
        }
    }

    public String getType() {
        return "PrivateCar";
    }

    public float getSecondFare(Request request) {
        if (request.computeFare(new JustRide()) < 
            request.computeFare(new ShareARide())) {
            return request.computeFare(new ShareARide()) / CONVERSION;
        } else {
            return request.computeFare(new JustRide()) / CONVERSION;
        }
    }

    public Service getSecondService(Request request) {
        if (request.computeFare(new JustRide()) < 
            request.computeFare(new ShareARide())) {
            return new ShareARide();
        } else {
            return new JustRide();
        }
    }
}