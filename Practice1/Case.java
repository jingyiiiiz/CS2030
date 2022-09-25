class Case {
    private final Person person;
    private final ImList<Test> listOfTests;
    private final int daysSince;
    private final ImList<Case> lineage;
    private final Protocol protocol;

    Case(Person person, PCR test) {
        this.person = person;
        this.listOfTests = new ImList<Test>().add(test);
        this.protocol = veryFirst(person, test);
        this.lineage = new ImList<>();
        if (test.isPositive()) {
            this.daysSince = 1;
        } else {
            this.daysSince = 0;
        }
    }

    Case(Person person, ImList<Test> listOfTests, 
         int daysSince, ImList<Case> lineage, Protocol protocol) {
        this.person = person;
        this.listOfTests = listOfTests;
        this.daysSince = daysSince;
        this.lineage = lineage;
        this.protocol = protocol;
    }

    static Protocol veryFirst(Person person, PCR test) {
        if (test.isPositive()) {
            if (person.isHighRisk()) {
                return new P1();
            } else {
                return new P2();
            }
        } else {
            return new Discharge("follow up with doctor");
        }
    }

    public Protocol getCurrentProtocol() {
        return this.protocol;
    }

    public Person getPerson() {
        return this.person;
    }

    public boolean p1Taken() {
        return (this.person.isHighRisk());
    }

    public ImList<Case> getLineage() {
        return lineage.add(this);
    }

    public ImList<Test> getTestHistory() {
        return this.listOfTests;
    }

    public Case test(Test test) {
        if (!test.isValid()) {
            return this;
        } else {
            Protocol newProtocol = protocol.next(person, test, this.daysSince + 1);
            return new Case(person, listOfTests.add(test), daysSince + 1, 
                            this.lineage, newProtocol);
        }
    }

    public String toString() {
        return String.format("%s %s %s", this.person, this.listOfTests, this.protocol);
    }

}
