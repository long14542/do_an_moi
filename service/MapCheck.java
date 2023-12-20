package javacore21.do_an.service;

import javacore21.do_an.entity.Flight;

import java.util.Map;

public class MapCheck {

        public void displayFlightMapInfo(Map<String, Flight> flights) {
            // Kiểm tra xem map có rỗng hay không
            boolean isFlightsMapEmpty = flights.isEmpty();
            System.out.println("Map 'flights' có rỗng không: " + isFlightsMapEmpty);

            // In ra thông tin về các chuyến bay trong map
            System.out.println("Danh sách các chuyến bay trong map:");
            for (Map.Entry<String, Flight> entry : flights.entrySet()) {
                String flightID = entry.getKey();
                Flight flight = entry.getValue();
                System.out.println("FlightID: " + flightID + ", Flight: " + flight);
            }

            // Kiểm tra số lượng chuyến bay trong map
            int numberOfFlights = flights.size();
            System.out.println("Số lượng chuyến bay trong map: " + numberOfFlights);
        }
}
