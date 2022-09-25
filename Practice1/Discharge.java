class Discharge implements Protocol {
    private final String text;

    Discharge(String text) {
        this.text = text;
    }

    @Override
    public Protocol next(Person person, Test test, int daysSince) {
        if (test.isPositive()) {
            return new Discharge("seek medical attention");
        } else {
            return this;
        }
    }

    public String toString() {
        return String.format("Discharged: %s", this.text);
    }
}