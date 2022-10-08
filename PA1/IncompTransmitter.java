class IncompTransmitter extends Host implements Incomplete {

    IncompTransmitter(String id, ImList<Term> connecting, ImList<Term> connected) {
        super(id, connecting, connected);
    }

    @Override
    public String toString() {
        String result = "";
        Term first = this.getConnecting().get(0);
        result = result + first.toString();
        result = result + " >--snd--> ";
        result = result + this.getId();
        return result;
    }

    public IncompPager rcv() {
        Term first = this.getConnecting().get(0);
        String id = first.getId();
        return new IncompPager(id, this);
    }
}
