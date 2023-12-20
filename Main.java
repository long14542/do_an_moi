package javacore21.do_an;
import javacore21.do_an.controllers.loginAndSignup.LoginController;
import javacore21.do_an.controllers.loginAndSignup.SignupController;
import javacore21.do_an.entity.Customer;
import javacore21.do_an.entity.Flight;
import javacore21.do_an.entity.Signup;
import javacore21.do_an.entity.Ticket;
import javacore21.do_an.service.CustomerService;
import javacore21.do_an.service.FlightService;
import javacore21.do_an.service.MapCheck;
import javacore21.do_an.service.TicketService;
import javacore21.do_an.view.LoginSignupView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Signup> userDatabase = new HashMap<>();
        Map<String, Customer> customerMap = new HashMap<>();
        Map<String, Ticket> ticketMap = new HashMap<>();
        Map<String, Flight> flightMap = new HashMap<>();
        Customer customer = null;
        String loggedInUser = null;
        TicketService ticketService = new TicketService(flightMap, ticketMap);
        FlightService flightService = new FlightService(flightMap, ticketService);

        SignupController signupController = new SignupController(userDatabase, customerMap);
        Signup signup = signupController.registerNewUser(scanner);

        CustomerService customerService = new CustomerService(customerMap);

        LoginController loginController = new LoginController(userDatabase, customerMap, loggedInUser, ticketService, customer, flightService);
        LoginSignupView loginSignupView = new LoginSignupView(loginController, signupController);

        boolean continueSignup = true; // Thêm biến để kiểm soát việc tiếp tục đăng ký

        while (continueSignup) {
            loginSignupView.SignupOrLogin(scanner);
            customer = customerService.customerInfo(scanner, signup);
            //ticketService.selectTicketType(scanner);
            ticketService.bookTicket(customer, scanner);
            //MapCheck mapCheck = new MapCheck();
            //mapCheck.displayFlightMapInfo(flightMap);
            flightService.selectFlight(customer);
            //flightService.addFlight(customer, scanner);

            // Hỏi người dùng có muốn tiếp tục đăng ký hay không
            System.out.print("Do you want to continue signup? (yes/no): ");
            String choice = scanner.nextLine().toLowerCase();
            continueSignup = choice.equals("yes");
        }
    }
}

