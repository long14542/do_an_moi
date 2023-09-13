package javacore21.do_an.entity;

public class Login {
    private String usernameLogin;
    private String passwordLogin;
    private String recentEmail;

    public Login(String usernameLogin, String passwordLogin, String recentEmail) {
        this.usernameLogin = usernameLogin;
        this.passwordLogin = passwordLogin;
        this.recentEmail = recentEmail;
    }

    public String getUsernameLogin() {
        return usernameLogin;
    }

    public String setUsernameLogin(String usernameLogin) {
        this.usernameLogin = usernameLogin;
        return usernameLogin;
    }

    public String getPasswordLogin() {
        return passwordLogin;
    }

    public String setPasswordLogin(String passwordLogin) {
        this.passwordLogin = passwordLogin;
        return passwordLogin;
    }

    public String getRecentEmail() {
        return recentEmail;
    }

    public void setRecentEmail(String recentEmail) {
        this.recentEmail = recentEmail;
    }

    @Override
    public String toString() {
        return "Login{" +
                "usernameLogin='" + usernameLogin + '\'' +
                ", passwordLogin='" + passwordLogin + '\'' +
                ", emailLogin+'" + recentEmail + '\'' +
                '}';
    }
}
