class Service {
    private final Loader loader;
    private final Cruise cruise;
    private final int timeOfService;
    private static final int numMinInHr = 60;

    Service(Loader loader, Cruise cruise, int timeOfService) {
        this.loader = loader;
        this.cruise = cruise;
        this.timeOfService = timeOfService;
    }

    public String toString() {
        int standardTime = (int) (this.timeOfService / numMinInHr) * 100 +
                           this.timeOfService % numMinInHr;
        return String.format("%04d : %s serving %s", standardTime, 
               this.loader, this.cruise);
    }
}
