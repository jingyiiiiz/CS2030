public class SmallCruise extends Cruise {
    private static final int NUM_LOADER = 1;
    private static final int SERVE_TIME = 30;

    SmallCruise(String identification, int arrivalTime) {
        super(identification, arrivalTime,
        NUM_LOADER, SERVE_TIME);
    }
}
