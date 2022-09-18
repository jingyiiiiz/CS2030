import java.util.Comparator;

class BookingComp implements Comparator<FinalBooking> {
    public int compare(FinalBooking first, FinalBooking second) {
        float difference = first.getFare() - second.getFare();
        if (difference > 0) {
            return 1;
        } else if (difference < 0) {
            return -1;
        } else {
            if (first.getWaitingTime() > second.getWaitingTime()) {
                return 1;
            } else if (first.getWaitingTime() < second.getWaitingTime()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}