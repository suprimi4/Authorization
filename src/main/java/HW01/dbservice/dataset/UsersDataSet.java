package HW01.dbservice.dataset;

public class UsersDataSet {
    private final Long id;
    private final String login;
    private final String email;
    private final String password;

    public UsersDataSet(Long id, String login, String email, String password) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
    }


    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", login=" + login +
                ", email=" + email +
                ", password=" + password +
                '}';
    }

}
