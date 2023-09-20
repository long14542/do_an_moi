package javacore21.do_an.view;

import javacore21.do_an.controllers.loginAndSignup.LoginController;
import javacore21.do_an.controllers.loginAndSignup.SignupController;
import java.util.Scanner;

public class LoginSignupView {
    private LoginController loginController;
    private SignupController signupController;

    public LoginSignupView(LoginController loginController, SignupController signupController) {
        this.loginController = loginController;
        this.signupController = signupController;
    }

    public void SignupOrLogin(Scanner scanner){
            int choose = 0;
            do {
                System.out.println("1- Login");
                System.out.println("2- Signup");
                try {
                    System.out.println("Please enter a number to choose: ");
                    choose = Integer.parseInt(scanner.nextLine());
                    if (choose < 1 || choose > 2) {
                        System.out.println("Invalid input. Please try again!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please try again!");
                }
            } while (choose < 1 || choose > 2);
            switch (choose){
                case 1: loginController.login(scanner);
                break;
                case 2: signupController.registerNewUser(scanner);
                        loginController.login(scanner);
                        break;
            }
        }

        public void loginSuccess() {
            System.out.println("Login success!");
            System.out.println("1 - Change Username");
            System.out.println("2 - Change Email");
            System.out.println("3 - Change Password");
            System.out.println("4 - Logout");
            System.out.println("5- Buy ticket");
            System.out.println("Please enter a number to choose: ");
        }
        public void loginFailed(){
            System.out.println("1 - Login again");
            System.out.println("2 - Forget Passsword");
            System.out.println("3- Signup account");
            System.out.println("Please enter a number to choose: ");
    }
}
