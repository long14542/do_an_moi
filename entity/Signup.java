package javacore21.do_an.entity;

public class Signup {

        private String usernameSignup;
        private String passwordSignup;
        private String emailSignup;
        private String role;


        public Signup(String usernameSignup, String passwordSignup, String emailSignup, String role) {
            this.usernameSignup = usernameSignup;
            this.passwordSignup = passwordSignup;
            this.emailSignup = emailSignup;
            this.role = role;
        }

        public String getUsernameSignup() {
            return usernameSignup;
        }

        public void setUsernameSignup(String usernameSignup) {
            this.usernameSignup = usernameSignup;
        }

        public String getPasswordSignup() {
            return passwordSignup;
        }

        public void setPasswordSignup(String passwordSignup) {
            this.passwordSignup = passwordSignup;
        }

        public String getEmailSignup() {
            return emailSignup;
        }

        public void setEmailSignup(String emailSignup) {
            this.emailSignup = emailSignup;
        }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
        public String toString() {
            return "Signup{" +
                    "usernameSignup='" + usernameSignup + '\'' +
                    ", passwordSignup='" + passwordSignup + '\'' +
                    ", emailSignup='" + emailSignup + '\'' +
                    '}';
        }
    }

