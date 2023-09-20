package javacore21.do_an.controllers.loginAndSignup;

import javacore21.do_an.entity.Signup;

import java.util.Map;
import java.util.Scanner;

public class ChangePass {
    private LoginController loginController;

    public ChangePass(LoginController loginController) {
        this.loginController = loginController;
    }

    public void changePassword(Scanner scanner) {
        if (loginController.getLoggedInUser() == null) {
            System.out.println("Bạn chưa đăng nhập. Vui lòng đăng nhập trước.");
            return;
        }

        Map<String, Signup> userDatabase = loginController.getUserDatabase();
        Signup user = userDatabase.get(loginController.getLoggedInUser());
        do {
            System.out.println("Nhập mật khẩu mới: ");
            String newPassword = scanner.nextLine();
            if (!SignupController.isValidPassword(newPassword)){
                System.out.println("Invalid password, enter again.");
                continue;
            }
            user.setPasswordSignup(newPassword);
            userDatabase.put(loginController.getLoggedInUser(), user);
            System.out.println("Thay đổi mật khẩu thành công.");
        } while (true);
    }
}
