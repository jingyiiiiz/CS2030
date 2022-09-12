interface Event {
    public String toString();
    
    public Server updateServer();

    public Event nextEvent(double currTime);

    public double getTime();

    public double getFinalTime();

    public int getIndex();

    public Server getServer();
}
