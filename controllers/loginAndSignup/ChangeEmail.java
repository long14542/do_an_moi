package javacore21.do_an.controllers.loginAndSignup;

import javacore21.do_an.entity.Signup;

import java.util.Map;
import java.util.Scanner;

public class ChangeEmail {
    private LoginController loginController;

    public ChangeEmail(LoginController loginController) {
        this.loginController = loginController;
    }

    public void newEmail(Scanner scanner){
        if (loginController.getLoggedInUser() == null) {
            System.out.println("Bạn chưa đăng nhập. Vui lòng đăng nhập trước.");
            return;
        }

        Map<String, Signup> userDatabase = loginController.getUserDatabase();
        Signup user = userDatabase.get(loginController.getLoggedInUser());
        do {
            System.out.println("Nhập email mới: ");
            String newEmail = scanner.nextLine();
            if (SignupController.isValidEmail(newEmail)){
                System.out.println("Invalid email, enter again.");
                continue;
            }
            user.setEmailSignup(newEmail);
            userDatabase.put(loginController.getLoggedInUser(), user);
            System.out.println("Email changed successfully.");
            break;
        } while (true);
    }
}
