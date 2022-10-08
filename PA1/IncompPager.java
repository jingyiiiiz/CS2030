class IncompPager extends Term implements ToAck {
    private final Host sndHost;

    IncompPager(String id, Host sndHost) {
        super(id);
        this.sndHost = sndHost;
    }

    @Override
    public String toString() {
        String result = "";
        result = result + this.sndHost.toString();
        result = result + " >--rcv--> ";
        result = result + this.getId();
        return result;
    }

    public CompTransmitter ack() {
        String id = this.sndHost.getId();
        ImList<Term> connected = this.sndHost.getConnected().add(this);
        ImList<Term> connecting = this.sndHost.getConnecting().remove(0);
        return new CompTransmitter(id, connecting, connected);
    }
}
