import java.lang.Math;

class ShareARide implements Service {
    private static final int CENTS_PER_KM = 50;
    private static final int SURCHARGE = 500;
    private static final int SUR_START = 600;
    private static final int SUR_END = 900;
    
    ShareARide() {
    }

    public String toString() {
        return "ShareARide";
    }

    public int computeFare(int distance, int passengers, int time) {
        int baseFee = distance * CENTS_PER_KM;
        int withSurcharge = baseFee + SURCHARGE;
        if (time >= SUR_START && time <= SUR_END) {
            return (int) Math.floor(withSurcharge / passengers);
        } else {
            return (int) Math.floor(baseFee / passengers);
        }
    }
}