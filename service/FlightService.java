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
    public FlightName selectFlight(Customer customer) {
        List<FlightName> availableFlights = getAvailableFlights();

        // Kiểm tra xem map có rỗng hay không
        //boolean isFlightsMapEmpty = flights.isEmpty();
        //System.out.println("Map 'flights' có rỗng không: " + isFlightsMapEmpty);

// In ra thông tin về các chuyến bay trong map
        //System.out.println("Danh sách các chuyến bay trong map:");
        //for (Map.Entry<String, Flight> entry : flights.entrySet()) {
            //String flightID = entry.getKey();
            //Flight flight = entry.getValue();
            //System.out.println("FlightID: " + flightID + ", Flight: " + flight);
        //}

// Kiểm tra số lượng chuyến bay trong map
        int numberOfFlights = flights.size();
        System.out.println("Số lượng chuyến bay trong map: " + numberOfFlights);


        int choice;
        while (true) {
            System.out.print("Chọn chuyến bay (0-9): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            try {
                choice = Integer.parseInt(input);
                System.out.println(input);
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
                flight.setSeatNumber(ticketService.getAvailableSeat(flight)); // hoặc setSeatNumber(availableSeat) nếu bạn muốn có giá trị ghế ngẫu nhiên
                //flight.setTime(flight.getTime()); // Giả sử bạn muốn giữ giá trị thời gian của chuyến bay
                flight.setGate(ThreadLocalRandom.current().nextInt(1, 21)); // Random số cổng từ 1 đến 20
                flight.setFlightID(generateFlightID(selectedFlightName));
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
