abstract class Term {
    private final String id;

    Term(String id) {
        this.id = id;
    }

    public String toString() {
        return this.id;
    }

    public String getId() {
        return this.id;
    }

    public boolean equals(Term other) {
        return this.getId().equals(other.getId());
    }
}
