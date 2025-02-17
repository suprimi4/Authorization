package HW01.model;

public class UserProfile {

    private final String login;
    private final String email;
    private final String password;


    public UserProfile(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEmpty() {
        return login.isEmpty() && email.isEmpty() && password.isEmpty();
    }
}
