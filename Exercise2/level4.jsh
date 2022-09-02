void serveCruises(List<Cruise> cruises, int numOfLoaders) {
    int currentTime = 0;
    int numOfCruises = cruises.size();
    Loader loader = new Loader(numOfLoaders);

    for (int i = 0; i < numOfCruises; i++) {
        Cruise current = cruises.get(i);
        int numOfLoadersRequired = current.getNumOfLoadersRequired();

        if (current.getArrivalTime() > currentTime) {
            currentTime = current.getArrivalTime();
        }

        for (int j = 0; j < numOfLoadersRequired; j++) {
            new Service(loader, current, currentTime);
            loader = loader.nextLoader();
            if (j == numOfLoadersRequired - 1) {
                currentTime = currentTime + current.getServiceTime();
            }
        }
    }
}
