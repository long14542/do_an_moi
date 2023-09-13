package javacore21.do_an;
import javacore21.do_an.controllers.loginAndSignup.LoginController;
import javacore21.do_an.controllers.loginAndSignup.SignupController;
import javacore21.do_an.entity.Customer;
import javacore21.do_an.entity.Signup;
import javacore21.do_an.view.LoginSignupView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Signup> userDatabase = new HashMap<>();
        Map<String, Customer> customerMap = new HashMap<>();
            // Thêm dữ liệu người dùng vào userDatabase
        String loggedInUser = null;
        LoginController loginController = new LoginController(userDatabase, loggedInUser, customerMap);

            // Khởi tạo SignupController và truyền loginService vào đó
        SignupController signupController = new SignupController(userDatabase, customerMap);

            // Khởi tạo ForgetPassword và truyền loginService vào đó
        LoginSignupView loginSignupView = new LoginSignupView(loginController, signupController);

            // Gọi hàm SignupOrLogin từ SignupController
        loginSignupView.SignupOrLogin(scanner);


    }
}

