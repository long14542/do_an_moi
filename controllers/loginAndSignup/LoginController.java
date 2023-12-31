package javacore21.do_an.controllers.loginAndSignup;
import javacore21.do_an.entity.Customer;

import javacore21.do_an.entity.Signup;

import javacore21.do_an.service.FlightService;
import javacore21.do_an.service.TicketService;
import javacore21.do_an.view.LoginSignupView;
import java.util.Map;
import java.util.Scanner;

public class LoginController {
    private Map<String, Signup> userDatabase;
    private Map<String, Customer> customerMap;
    private String loggedInUser;
    TicketService ticketService;
    private Customer customer;
    FlightService flightService;

    public LoginController(Map<String, Signup> userDatabase, Map<String, Customer> customerMap, String loggedInUser, TicketService ticketService, Customer customer, FlightService flightService) {
        this.userDatabase = userDatabase;
        this.customerMap = customerMap;
        this.loggedInUser = loggedInUser;
        this.ticketService = ticketService;
        this.customer = customer;
        this.flightService = flightService;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public Map<String, Signup> getUserDatabase() {
        return userDatabase;
    }
    SignupController signupController = new SignupController(userDatabase, customerMap);
    ChangeUsername changeUsername = new ChangeUsername(this);
    ChangeEmail changeEmail = new ChangeEmail(this);
    ChangePass changePass = new ChangePass(this);
    LoginSignupView loginSignupView = new LoginSignupView(this, signupController);
    ForgetPassword forgetPassword = new ForgetPassword(this);

    public void login(Scanner scanner) {
        boolean continueMenu = true;

        do {
            System.out.println("Enter username: ");
            String usernameLogin = scanner.nextLine();

            System.out.println("Enter password: ");
            String passwordLogin = scanner.nextLine();

            if (isValidLogin(usernameLogin, passwordLogin)) {
                loggedInUser = usernameLogin;
                int choose;
                do {
                    try {
                        loginSignupView.loginSuccess();
                        choose = Integer.parseInt(scanner.nextLine());
                        switch (choose) {
                            case 1 -> changeUsername.changeUsername(scanner);
                            case 2 -> changeEmail.newEmail(scanner);
                            case 3 -> changePass.changePassword(scanner);
                            case 4 -> {
                                logout(scanner);
                                continueMenu = false;
                            }
                            case 5 -> {
                                ticketService.selectTicketType(scanner);
                                ticketService.bookTicket(customer, scanner);
                                //flightService.selectFlight(scanner);
                                //flightService.addFlight(customer, scanner);
                            }
                            default -> System.out.println("Invalid choose. Please enter again.");
                        }

                        if (continueMenu) {
                            System.out.println("Do you want to continue? (yes/no)");
                            String choice = scanner.nextLine();
                            if (!choice.equalsIgnoreCase("yes")) {
                                continueMenu = false; // Người dùng không muốn tiếp tục, dừng vòng lặp
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid choose. Please enter again.");
                    }
                } while (continueMenu);

            } else {
                int choose;
                try {
                    loginSignupView.loginFailed();
                    choose = Integer.parseInt(scanner.nextLine());
                    switch (choose) {
                        case 1 -> System.out.println("Login again");
                        case 2 ->
                                forgetPassword.checkEmail(scanner);
                        case 3 -> {
                            signupController.registerNewUser(scanner);
                            System.out.println("Đăng ký lại thành công!");
                        }
                        default -> System.out.println("Invalid choose. Please enter again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choose. Please enter again.");
                }
            }
        } while (true);
    }


    public void logout(Scanner scanner) {
        loggedInUser = null;
        loginSignupView.SignupOrLogin(scanner);
        System.out.println("Logout success");
    }

    private boolean isValidLogin(String username, String password) {
        Signup user = userDatabase.get(username);
        return user != null && user.getPasswordSignup().equals(password);
    }
}


