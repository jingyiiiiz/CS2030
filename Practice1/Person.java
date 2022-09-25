class Person {
    private final String id;
    private final String vaccStatus;
    private final int risk;
    private static final int BENCHMARK_RISK = 8;

    Person(String id, String vaccStatus, int risk) {
        this.id = id;
        this.vaccStatus = vaccStatus;
        this.risk = risk;
    }

    public boolean isVaccinated() {
        return (this.vaccStatus.length() >= 2);
    }

    public boolean isHighRisk() {
        return (this.risk >= BENCHMARK_RISK);
    }

    public String toString() {
        String riskIndicator;
        if (this.isHighRisk()) {
            riskIndicator = "HIGH";
        } else {
            riskIndicator = "LOW";
        }
        return String.format("%s/%s/%s", this.id, this.vaccStatus, riskIndicator);
    }
}
