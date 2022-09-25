class ContactCase extends Case {
    ContactCase(Person person, Test test, Case theCase) {
        super(person, new ImList<Test>().add(test), 
              1, theCase.getLineage(), veryFirst(person, test));
    }

    static Protocol veryFirst(Person person, Test test) {
        if (test.isPositive()) {
            if (person.isHighRisk()) {
                return new P1();
            } else {
                return new P2();
            }
        } else {
            return new P3();
        }
    }
}
