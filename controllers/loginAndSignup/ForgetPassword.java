package javacore21.do_an.controllers.loginAndSignup;

import javacore21.do_an.entity.Signup;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgetPassword {
    private LoginController loginController;

    public ForgetPassword(LoginController loginController) {
        this.loginController = loginController;
    }

    public void checkEmail(Scanner scanner) {
        Map<String, Signup> userDatabase = loginController.getUserDatabase();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        Signup user = userDatabase.get(username);

        while (user == null || !username.equals(user.getUsernameSignup())) {
            System.out.println("Account does not exist or username does not match.");
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            user = userDatabase.get(username);
        }

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        while (!isValidEmail(email) || !email.equals(user.getEmailSignup())) {
            System.out.println("Invalid Email or email does not match.");
            System.out.print("Enter email: ");
            email = scanner.nextLine();
        }

        System.out.println("Please change your password");
        String newPassword = scanner.nextLine();

        while (SignupController.isValidPassword(newPassword)) {

            System.out.println("Invalid Password. Please enter a valid password.");
            System.out.println("Please change your password");
            newPassword = scanner.nextLine();
        }

        user.setPasswordSignup(newPassword);
        userDatabase.put(username, user);
        System.out.println("Password changed successfully.");
    }

    public boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}



