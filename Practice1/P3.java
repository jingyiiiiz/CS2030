class P3 implements Protocol {
    private static final int MONITOR = 5;
    
    P3() {
    }

    public String toString() {
        return "P3";
    }

    public Protocol next(Person person, Test test, int daysSince) {
        if (test.isPositive()) {
            if (person.isHighRisk()) {
                return new P1();
            } else {
                return new P2();
            }
        } else if (daysSince <= MONITOR) {
            return this;
        } else {
            return new Discharge("complete");
        }
    }
}