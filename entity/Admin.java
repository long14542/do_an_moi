package javacore21.do_an.entity;

import javacore21.do_an.controllers.loginAndSignup.SignupController;

import java.util.Map;

public class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public void viewRegistrationInfo(SignupController signupController) {
        Map registrationInfo = signupController.getUserDatabase();
        if (registrationInfo != null) {
            System.out.println("Registration Info:" + registrationInfo);
            // Hiển thị các thông tin khác nếu cần
        } else {
            System.out.println("No registration info available.");
        }
    }
}
