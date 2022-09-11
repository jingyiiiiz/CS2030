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

        for (int j = 1; j <= numOfLoadersRequired; j++) {
            Service service = new Service(loader, current, currentTime);
            System.out.println(service);
            if (j % numOfLoaders == 0 || j == numOfLoadersRequired) {
                currentTime = currentTime + current.getServiceTime();
            }
            loader = loader.nextLoader();
        }
    }
}
