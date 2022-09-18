class NormalCab implements Driver {
    private final String license;
    private final int waitingTime;
    private static final float CONVERSION = 100;

    NormalCab(String license, int waitingTime) {
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
        return String.format("%s (%d mins away) %s", this.getLicense(),
                this.getWaitingTime(), "NormalCab");
    }

    public float getFare(Request request) {
        if (request.computeFare(new JustRide()) < 
            request.computeFare(new TakeACab())) {
            return request.computeFare(new JustRide()) / CONVERSION;
        } else {
            return request.computeFare(new TakeACab()) / CONVERSION;
        }
    }

    public Service getService(Request request) {
        if (request.computeFare(new JustRide()) < 
            request.computeFare(new TakeACab())) {
            return new JustRide();
        } else {
            return new TakeACab();
        }
    }

    public String getType() {
        return "NormalCab";
    }

    public float getSecondFare(Request request) {
        if (request.computeFare(new JustRide()) < 
            request.computeFare(new TakeACab())) {
            return request.computeFare(new TakeACab()) / CONVERSION;
        } else {
            return request.computeFare(new JustRide()) / CONVERSION;
        }
    }

    public Service getSecondService(Request request) {
        if (request.computeFare(new JustRide()) < 
            request.computeFare(new TakeACab())) {
            return new TakeACab();
        } else {
            return new JustRide();
        }
    }
}
