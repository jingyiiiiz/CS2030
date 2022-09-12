import java.util.Comparator;

class EventComp implements Comparator<Event> {
    public int compare(Event first, Event second) {
        double difference = first.getTime() - second.getTime();
        if (difference > 0) {
            return 1;
        } else if (difference < 0) {
            return -1;
        } else {
            return first.getIndex() - second.getIndex();
        }
    }
}