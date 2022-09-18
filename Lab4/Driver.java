interface Driver {
    public String toString();

    public int getWaitingTime();

    public float getFare(Request request);

    public Service getService(Request request);

    public String getLicense();

    public String getType();

    public float getSecondFare(Request request);

    public Service getSecondService(Request request);
}
