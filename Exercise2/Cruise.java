class Cruise implements Comparable<Cruise> {
    private final String identifier;
    private final int arrivalTime;
    private final int numOfLoaders;
    private final int serviceTime;
    private static final int numMinInHr = 60;

    Cruise(String identifier, int arrivalTime, int numOfLoaders, int serviceTime) {
        this.identifier = identifier;
        this.arrivalTime = arrivalTime;
        this.numOfLoaders = numOfLoaders;
        this.serviceTime = serviceTime;
    }

    public String toString() {
        return this.identifier + String.format("@%04d", this.arrivalTime);
    }

    String getIdentifier() {
        return this.identifier;
    }

    int getServiceTime() {
        return this.serviceTime;
    }

    int getArrivalTime() {
        return (int) (arrivalTime / 100) * numMinInHr + arrivalTime % 100;
    }

    int getNumOfLoadersRequired() {
        return this.numOfLoaders;
    }

    @Override
    public int compareTo(Cruise cr) {
        return this.arrivalTime - cr.arrivalTime;
    }
}
