class Server {   
    private final String name;
    private final double serveTill;

    Server(String name, double serveTill) {
        this.name = name;
        this.serveTill = serveTill;
    }

    String getName() {
        return this.name;
    }

    double getServeTill() {
        return serveTill;
    }
}
