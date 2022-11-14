class SelfCheck implements Server {
    private static final String TYPE_SELFCHECK = "SelfCheck";
    private final int id;
    private final int qmax;
    private final int smallestId;
    private final double tillTime;

    SelfCheck(int id, int qmax, int smallestId) {
        this.id = id;
        this.qmax = qmax;
        this.smallestId = smallestId;
        this.tillTime = 0.0;
    }

    SelfCheck(int id, int qmax, int smallestId, double tillTime) {
        this.id = id;
        this.qmax = qmax;
        this.smallestId = smallestId;
        this.tillTime = tillTime;
    }

    public int getId() {
        return this.id;
    }

    public int getSmallestId() {
        return this.smallestId;
    }

    public int getQmax() {
        return this.qmax;
    }

    public double getTillTime() {
        return this.tillTime;
    }

    public String toString() {
        return String.format("%d", this.id);
    }

    public String getType() {
        return TYPE_SELFCHECK;
    }

    // only selfcheck has
    public String smallToString() {
        return String.format("%d", this.smallestId);
    }
}
