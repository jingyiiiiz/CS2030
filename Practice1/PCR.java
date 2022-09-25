class PCR implements Test {
    private final String status;
    private static final String PREFIX = "P";

    PCR(String status) {
        this.status = status;
    }

    public boolean isValid() {
        return true;
    }

    public boolean isPositive() {
        if (this.status == "alpha" || this.status == "beta" ||
            this.status == "delta" || this.status == "omicron") {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        String suffix;
        if (this.isPositive()) {
            suffix = "+";
        } else {
            suffix = "-";
        }
        return PREFIX + suffix;
    }
}
