package javacore21.do_an.service;

import javacore21.do_an.entity.*;

import java.util.Map;
import java.util.UUID;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TicketService {
    private Map<String, Flight> flights;
    private Map<String, Ticket> tickets;
    private Random random = new Random();

    public TicketService(Map<String, Flight> flights, Map<String, Ticket> tickets) {
        this.flights = flights;
        this.tickets = tickets;
    }

    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    public TicketType selectTicketType(Scanner scanner) {
        System.out.println("Chọn loại vé:");
        for (TicketType type : TicketType.values()) {
            System.out.println(type.name());
        }

        String input = scanner.nextLine().toUpperCase();
        try {
            return TicketType.valueOf(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Loại vé không hợp lệ. Vui lòng chọn lại.");
            return selectTicketType(scanner);
        }
    }

    public boolean bookTicket(Customer customer, Scanner scanner) {
        FlightService flightService = new FlightService(flights, this);
        FlightName selectedFlightName = flightService.selectFlight(customer, scanner);
        String flightID = flightService.generateFlightID(selectedFlightName);
        // Sử dụng generateFlightID thay vì generateUniqueID
        Flight flight = flights.get(flightID);
        if (flight == null) {
            System.out.println("Chuyến bay không tồn tại.");
            return false;
        }
        TicketType selectedTicketType = selectTicketType(scanner); // Chọn loại vé
        if (selectedTicketType == null) {
            return false;
        }

        int availableSeat = getAvailableSeat(flight);
        if (availableSeat == -1) {
            System.out.println("Không còn chỗ ngồi trống cho chuyến bay này.");
            return false;
        }

        String ticketID = generateUniqueID();
        if (ticketID == null) {
            return false; // Không còn cổng hoặc ID vé trùng
        }

        Ticket ticket = new Ticket(ticketID, selectedTicketType);
        tickets.put(ticket.getTicketID(), ticket);

        flight.setSeatNumber(flight.getSeatNumber() - 1); // Giảm số lượng ghế trống
        customer.setTicket(ticket);

        System.out.println("Đặt vé thành công. Mã vé của bạn là: " + ticket.getTicketID());
        return true;
    }

    int getAvailableSeat(Flight flight) {
        int seat;
        do {
            seat = random.nextInt(200) + 1; // Tạo số ngẫu nhiên trong phạm vi từ 1 đến 200
        } while (!isSeatAvailable(flight, seat));

        return seat;
    }

    private boolean isSeatAvailable(Flight flight, int seat) {
        return seat > 0 && seat <= flight.getSeatNumber();
    }

    public String generateUniqueID() {
        String uniqueID = UUID.randomUUID().toString();
        while (tickets.containsKey(uniqueID) ) {
            uniqueID = UUID.randomUUID().toString();
        }
        return uniqueID;
    }
}



