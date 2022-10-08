class CompTransmitter extends Transmitter implements Completed {

    CompTransmitter(String id, ImList<Term> connecting, ImList<Term> connected) {
        super(id, connecting, connected);
    }

    @Override
    public String toString() {
        Term newest = this.getConnected().get(this.getConnected().size() - 1);
        String result = "";
        result = result + newest.toString();
        result = result + " >--ack--> ";
        result = result + this.getId();
        return result;
    }

}
