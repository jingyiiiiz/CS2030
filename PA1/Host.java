abstract class Host {
    private final String id;
    private final ImList<Term> connecting;
    private final ImList<Term> connected;

    Host(String id) {
        this.id = id;
        this.connecting = new ImList<Term>();
        this.connected = new ImList<Term>();
    }

    Host(String id, ImList<Term> connecting, ImList<Term> connected) {
        this.id = id;
        this.connecting = connecting;
        this.connected = connected;
    }

    public String toString() {
        return this.id;
    }

    public String getId() {
        return this.id;
    };

    public ImList<Term> getConnecting() {
        return this.connecting;
    };

    public ImList<Term> getConnected() {
        return connected;
    }

    public boolean equals(Host other) {
        return this.getId().equals(other.getId());
    }

    public void broadcast() {
        if (this.connected.size() == 0) {
            System.out.println();
        } else {
            for (int i = 0; i < this.getConnected().size(); i++) {
                Term currTerm = this.getConnected().get(i);
                String thisId = currTerm.getId();
                thisId = thisId + ":beep";
                System.out.println(thisId);
            }
        }
    }
}
