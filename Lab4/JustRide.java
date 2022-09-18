class JustRide implements Service {
    private static final int CENTS_PER_KM = 22;
    private static final int SURCHARGE = 500;
    private static final int PEAK_START = 600;
    private static final int PEAK_END = 900;
    private static final int BOOKING_FEE = 0;

    JustRide() {
    }

    public String toString() {
        return "JustRide";
    }

    public int computeFare(int distance, int passengers, int time) {
        int baseFee = distance * CENTS_PER_KM;
        int withSurcharge = baseFee + SURCHARGE;
        if (time >= PEAK_START && time <= PEAK_END) {
            return withSurcharge;
        } else {
            return baseFee;
        }
    }
}
