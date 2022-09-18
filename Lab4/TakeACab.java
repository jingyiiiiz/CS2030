class TakeACab implements Service {
    private static final int CENTS_PER_KM = 33;
    private static final int BOOKING_FEE = 200;

    public String toString() {
        return "TakeACab";
    }

    public int computeFare(int distance, int passengers, int time) {
        return distance * CENTS_PER_KM + BOOKING_FEE;
    }
}
