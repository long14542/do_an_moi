package javacore21.do_an.service;

import javacore21.do_an.entity.Customer;
import javacore21.do_an.entity.Flight;
import javacore21.do_an.entity.FlightName;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class FlightService {
    private Map<String, Flight> flights;
    private TicketService ticketService;

    public static int autoID = 0;

    public FlightService(Map<String, Flight> flights, TicketService ticketService) {
        this.flights = flights;
        this.ticketService = ticketService;


    }

    public List<FlightName> getAvailableFlights() {
        // Trả về danh sách các chuyến bay từ enum FlightName
        return Arrays.asList(FlightName.values());
    }
    public FlightName selectFlight(Customer customer, Scanner scanner) {
        List<FlightName> availableFlights = getAvailableFlights();

        System.out.println("Danh sách các chuyến bay:");
        for (int i = 0; i < availableFlights.size(); i++) {
            System.out.println((i) + ". " + availableFlights.get(i));
        }

        int choice;
        while (true) {
            System.out.print("Chọn chuyến bay (0-9): ");
            String input = scanner.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                continue; // Nếu không phải số nguyên, tiếp tục vòng lặp
            }

            if (choice >= 0 && choice < availableFlights.size()) {
                FlightName selectedFlightName = availableFlights.get(choice);
                String flightID = generateFlightID(selectedFlightName);
                Flight flight = flights.get(flightID);

                if (flightID == null) {
                    System.out.println("ID trùng.");
                    continue; // ID trùng, tiếp tục vòng lặp
                }

                if (!flights.containsKey(flightID)) {
                    System.out.println("Chuyến bay không tồn tại.");
                    continue; // Chuyến bay không tồn tại, tiếp tục vòng lặp
                }

                int availableSeat = ticketService.getAvailableSeat(flight);
                if (availableSeat == -1) {
                    System.out.println("Không còn chỗ ngồi trống cho chuyến bay này.");
                    continue; // Không còn chỗ ngồi trống, tiếp tục vòng lặp
                }

                String time = null; // Bạn cần cung cấp thời gian từ đâu đó

                int availableGate = ThreadLocalRandom.current().nextInt(1, 21); // Random số cổng từ 1 đến 20

                flight = new Flight(flightID, selectedFlightName, availableSeat, time, availableGate);
                flights.put(flight.getFlightID(), flight);
                customer.setFlight(flight);
                System.out.println("Add flight successfully.");

                return selectedFlightName; // Khi đã chọn và cập nhật chuyến bay thành công, trả về true và thoát khỏi hàm
            } else {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
        // Trả về false nếu không thực hiện thành công
    }


    public String generateFlightID(FlightName flightName) {
        // Ví dụ: Chuyến bay HANOI_TPHCM có flightID là "HANOI_TPHCM_1"
        return flightName.name() + "_" + (++autoID);
    }
}
