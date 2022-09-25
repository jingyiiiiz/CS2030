class P2 implements Protocol {
    private static final int ISO_DAYS = 3;
    private static final int VACC_MAX = 7;
    private static final int NOT_MAX = 14;

    P2() {
    }

    public String toString() {
        return "P2";
    }

    public Protocol next(Person person, Test test, int daysSince) {
        if (daysSince <= ISO_DAYS) {
            return this;
        } else if (!test.isPositive() ||
                   (person.isVaccinated() && daysSince > VACC_MAX) ||
                   (!person.isVaccinated() && daysSince > NOT_MAX)) {
            return new Discharge("complete");
        } else {
            return this;
        }
    }
}
