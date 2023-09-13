package javacore21.do_an.entity;

public class Flight {
    private String flightID;
    private String takeOfLocation;
    private String landingLocation;
    private int seatNumber;

    public Flight(String flightID, String takeOfLocation, String landingLocation, int seatNumber) {
        this.flightID = flightID;
        this.takeOfLocation = takeOfLocation;
        this.landingLocation = landingLocation;
        this.seatNumber = seatNumber;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getTakeOfLocation() {
        return takeOfLocation;
    }

    public void setTakeOfLocation(String takeOfLocation) {
        this.takeOfLocation = takeOfLocation;
    }

    public String getLandingLocation() {
        return landingLocation;
    }

    public void setLandingLocation(String landingLocation) {
        this.landingLocation = landingLocation;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightID='" + flightID + '\'' +
                ", takeOfLocation='" + takeOfLocation + '\'' +
                ", landingLocation='" + landingLocation + '\'' +
                ", seatNumber=" + seatNumber +
                '}';
    }
}
