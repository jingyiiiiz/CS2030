class ART implements Test {
    private static final String statusC = "C";
    private static final String statusCT = "CT";
    private static final String statusT = "T";
    private final String status;
    private static final String PREFIX = "A";

    ART(String status) {
        this.status = status;
    }

    public boolean isValid() {
        return (this.status != statusT);
    }

    public boolean isPositive() {
        if (!this.isValid()) {
            return false;
        } else {
            return (this.status == statusCT);
        }
    }

    public String toString() {
        String suffix;
        if (!this.isValid()) {
            suffix = "X";
        } else if (this.isPositive()) {
            suffix = "+";
        } else {
            suffix = "-";
        }
        return PREFIX + suffix;
    }
}
