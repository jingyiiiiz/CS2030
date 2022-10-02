import java.util.Comparator;

class EventComp implements Comparator<Event> {
    public int compare(Event first, Event second) {
        double difference = first.getTime() - second.getTime();
        // first priority is time
        if (difference > 0) {
            return 1;
        } else if (difference < 0) {
            return -1;
        } else {
            // when times are equal, customer that arrives earlier (with a smaller index)
            // has the priority
            return first.getIndex() - second.getIndex();
        }
    }
}
