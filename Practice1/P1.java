class P1 implements Protocol {
    
    P1() {
    }

    public String toString() {
        return "P1";
    }

    public Protocol next(Person person, Test test, int daysSince) {
        if (!test.isPositive()) {
            return new Discharge("follow up with doctor");
        } else {
            return this;
        }
    }
}
