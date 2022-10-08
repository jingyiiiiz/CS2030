class Pager extends Term {
    
    Pager(String id) {
        super(id);
    }

    public IncompTransmitter snd(Completed host) {
        ImList<Term> connecting = host.getConnecting();
        connecting = connecting.add(this);
        return new IncompTransmitter(host.getId(), connecting, host.getConnected());
    }

}
