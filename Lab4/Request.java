class Request {
    private final int distance;
    private final int passengers;
    private final int time;

    Request(int distance, int passengers, int time) {
        this.distance = distance;
        this.passengers = passengers;
        this.time = time;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getPassengers() {
        return this.passengers;
    }

    public int getTime() {
        return this.time;
    }

    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs",
                this.getDistance(), this.getPassengers(), this.getTime());
    }

    public int computeFare(Service service) {
        return service.computeFare(this.getDistance(), this.getPassengers(), 
                this.getTime());
    }
}
