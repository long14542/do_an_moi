package javacore21.do_an.controllers.loginAndSignup;
import javacore21.do_an.entity.Customer;
import javacore21.do_an.entity.Signup;
import javacore21.do_an.service.CustomerService;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupController {
    private Map<String, Signup> userDatabase;
    private Map<String, Customer> customerMap;
    private CustomerService customerService;

    public SignupController(Map<String, Signup> userDatabase, Map<String, Customer> customerMap) {
        this.userDatabase = userDatabase;
        this.customerMap = customerMap;
        this.customerService = new CustomerService(customerMap);
    }

    public Map<String, Signup> getUserDatabase() {
        return userDatabase;
    }


    private Signup registerUser(Scanner scanner) {
        System.out.println("Enter signup username: ");
        String usernameSignup = scanner.nextLine();

        System.out.println("Enter signup password: ");
        String passwordSignup = scanner.nextLine();

        System.out.println("Enter signup email: ");
        String emailSignup = scanner.nextLine();

        // Thêm vai trò mặc định là "customer" khi đăng ký
        String role = "customer";

        return new Signup(usernameSignup, passwordSignup, emailSignup, role);
    }

    public Signup registerNewUser(Scanner scanner) {
        Signup newUser = null; // Khai báo newUser ở ngoài vòng lặp
        boolean continueSignup = true; // Biến kiểm tra xem có tiếp tục đăng ký không

        while (continueSignup) {
            newUser = registerUser(scanner);

            boolean isValid = true; // Biến kiểm tra xem thông tin có hợp lệ không

            if (isValidEmail(newUser.getEmailSignup())) {
                System.out.println("Invalid email. Please enter again.");
                isValid = false; // Thông tin không hợp lệ, không cần tiếp tục vòng lặp
            }

            if (isValidPassword(newUser.getPasswordSignup())) {
                System.out.println("Invalid password. Please try again.");
                isValid = false; // Thông tin không hợp lệ, không cần tiếp tục vòng lặp
            }

            if (isDuplicate(newUser)) {
                System.out.println("Username or email already exists. Please try again.");
                isValid = false; // Thông tin không hợp lệ, không cần tiếp tục vòng lặp
            }

            if (isValid) {
                if (newUser.getUsernameSignup().equals("long123") && newUser.getPasswordSignup().equals("Long123-")) {
                    newUser.setRole("admin");
                } else {
                    newUser.setRole("customer");
                }
                userDatabase.put(newUser.getUsernameSignup(), newUser);
                System.out.println("Signup Success!");
                Customer customer = customerService.customerInfo(scanner, newUser);
                System.out.println("Customer information saved.");
                new Customer(customer.getFullName(), customer.getAddress(), customer.getPhoneNumber(), newUser.getEmailSignup(), customer.getTicket(), customer.getFlight());
            }

            System.out.println("Do you want to continue signup? (yes/no)");
            String choice = scanner.nextLine();

            if (!choice.equalsIgnoreCase("yes")) {
                continueSignup = false;
            }
        }
        return newUser;
    }

    private boolean isDuplicate(Signup newUser) {
        return userDatabase.containsKey(newUser.getUsernameSignup()) ||
                userDatabase.values().stream().anyMatch(user -> user.getEmailSignup().equals(newUser.getEmailSignup()));
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[.,-_;])(?=.*[a-zA-Z0-9]).{7,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return !matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
    }
}








    /*public Signup signupAccount(Scanner scanner, Signup signup) {
        ForgetPassword forgetPassword = new ForgetPassword();
        LoginService loginService = new LoginService();
        ChangeUsername newUsername = new ChangeUsername();
        ChangeEmail changeEmail = new ChangeEmail();
        LoginSignupView signupView = new LoginSignupView();

        do {
            System.out.println("Enter username to signup: ");
            String usernameSignup = scanner.nextLine();
            System.out.println("Enter password to signup: ");
            String passwordSignup = scanner.nextLine();
            System.out.println("Enter your email to signup: ");
            String emailSignup = scanner.nextLine();
            if ()
                //(!usernameSignup.equals(loginService.getValidUsername()) && !emailSignup.equals(forgetPassword.validEmail) &&
                    passwordSignup.equals(checkPassword(signup)))
                return new Signup(usernameSignup, passwordSignup, emailSignup);
            else{
                System.out.println("username and email exist, enter again");
            }
        } while (true);
    }

    public String checkPassword(Signup signup) {
        do {
            String password = signup.getPasswordSignup();

            if (isValidPassword(password)) {
                return password;
            } else {
                System.out.println("Invalid password, please enter again");
            }
        } while (true);
    }
*/




