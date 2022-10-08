class Transmitter extends Host implements Completed{
    
    Transmitter(String id) {
        super(id);
    }

    Transmitter(String id, ImList<Term> connecting, ImList<Term> connected) {
        super(id, connecting, connected);
    }

}
