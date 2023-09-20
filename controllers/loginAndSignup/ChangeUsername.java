package javacore21.do_an.controllers.loginAndSignup;
import javacore21.do_an.entity.Signup;

import java.util.Map;
import java.util.Scanner;

public class ChangeUsername {
    private LoginController loginController;

    public ChangeUsername(LoginController loginController) {
        this.loginController = loginController;
    }

    public void changeUsername(Scanner scanner) {

        if (loginController.getLoggedInUser() == null) {
            System.out.println("You are not login, please login first.");
            return;
        }

        System.out.println("Enter new username: ");
        String newUsername = scanner.nextLine();

        Map<String, Signup> userDatabase = loginController.getUserDatabase();
        if (loginController.getUserDatabase().containsKey(newUsername)) {
            System.out.println("Username already exists, please enter again.");
        } else {
            Signup user = userDatabase.get(loginController.getLoggedInUser());
            userDatabase.remove(loginController.getLoggedInUser());
            user.setUsernameSignup(newUsername);
            userDatabase.put(newUsername, user);
            loginController.setLoggedInUser(newUsername);
            System.out.println("Changed username successfully.");
        }
    }
}
