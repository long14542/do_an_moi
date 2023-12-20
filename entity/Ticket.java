package javacore21.do_an.entity;

public class Ticket {
    private String ticketID;
    private double ticketPrice;
    private TicketType ticketType;

    public Ticket(String ticketID, TicketType ticketType) {
        this.ticketID = ticketID;
        this.ticketType = ticketType;
        this.ticketPrice = calculateTicketPrice(ticketType);
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
        this.ticketPrice = calculateTicketPrice(ticketType);
    }

    // Phương thức tính giá vé dựa trên loại vé
    public double calculateTicketPrice(TicketType ticketType) {
        switch (ticketType) {
            case FIRST_CLASS:
                return 1000.0;
            case BUSINESS_CLASS:
                return 800.0;
            case PREMIUM_CLASS:
                return 600.0;
            case ECONOMY_CLASS:
                return 400.0;
            default:
                return 0.0; // Trả về giá 0 nếu loại vé không hợp lệ
        }
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID='" + ticketID + '\'' +
                ", ticketPrice=" + ticketPrice +
                ", ticketType=" + ticketType +
                '}';
    }
}

