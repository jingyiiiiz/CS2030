/*
 * Interface Event for 5 types of events:
 * ArriveEvent, WaitEvent, ServeEvent, DoneEvent, LeaveEvent
 */

interface Event {
    public String toString();
    // this is for string conversion, i.e., description of each event

    public double getTime();
    // this is to get the timestamp for the event, so that their priorities can be compared

    public Event nextEvent(CounterQueue counterQueue);
    // this is to call the next event, depending on the property of the current event

    public int getIndex();
    // this is to get index of customer, for priority comparison in EventComp

    public CounterQueue updateCounterQueue(CounterQueue counterQueue);
    // this is to update the counterqueues

    public double timeWaited(CounterQueue counterQueue);
    // this is for WaitEvent, to increase the waiting time of the customer that has waited

    public int increaseInLeft();
    // this is for LeaveEvent, to update the number of customers left without being served

    public Event updateCustomer(CounterQueue counterQueue);

    public String getEventType();

    public int getServerId();

    public Customer getCustomer();
}