public class BigCruise extends Cruise {
    private static final int LENGTH_PER_LOADER = 40;
    private static final  int PASSENGER_PER_MINUTE = 50;

    BigCruise(String identifier, int arrivalTime, int length, int numPassengers) {
        super(
            identifier, 
            arrivalTime, 
            (int)Math.ceil((double)length / LENGTH_PER_LOADER),
            (int)Math.ceil((double)numPassengers / PASSENGER_PER_MINUTE)
            );
    }
}
