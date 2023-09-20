package javacore21.do_an.entity;

public class Flight {
    private String flightID;
    private FlightName flightName;
    private int seatNumber;
    private String time;
    private int gate;

    public Flight(String flightID, FlightName flightName, int seatNumber, String time, int gate) {
        this.flightID = flightID;
        this.flightName = flightName;
        this.seatNumber = seatNumber;
        this.time = time;
        this.gate = gate;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public FlightName getFlightName() {
        return flightName;
    }

    public void setFlightName(FlightName flightName) {
        this.flightName = flightName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getGate() {
        return gate;
    }

    public void setGate(int gate) {
        this.gate = gate;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightID='" + flightID + '\'' +
                ", flightName='" + flightName + '\'' +
                ", seatNumber=" + seatNumber +
                ", time='" + time + '\'' +
                '}';
    }
}
