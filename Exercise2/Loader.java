class Loader {
    private final int identifier;
    private final int numOfLoaders;

    Loader(int identifier, int numOfLoaders) {
        this.identifier = identifier;
        this.numOfLoaders = numOfLoaders;
    }

    Loader(int numOfLoaders) {
        this.identifier = 1;
        this.numOfLoaders = numOfLoaders;
    }

    public Loader nextLoader() {
        if (this.identifier + 1 > numOfLoaders) {
            return new Loader(this.numOfLoaders);
        }
        return new Loader(this.identifier + 1, this.numOfLoaders);
    }

    public String toString() {
        return "Loader #" + this.identifier;
    }

    int getIdentifier() {
        return this.identifier;
    }
}
