void findBestBooking(Request request, List<Driver> driversList) {
    ImList<FinalBooking> newList = new ImList<FinalBooking>();

    for (Driver driver : driversList) {
        Booking currBooking = new Booking(driver, request);

        newList = newList.add(new FinalBooking(currBooking.getFare(), 
            driver, currBooking.getService()));
        
        newList = newList.add(new FinalBooking(currBooking.getSecondFare(), 
            driver, currBooking.getSecondService()));
    }

    newList = newList.sort(new BookingComp());
    
    for (FinalBooking booking : newList) {
        System.out.println(booking);
    }
}