package HW01.dbservice.dataset;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "user_password", nullable = false)
    private String password;

    public UsersDataSet() {
    }

    public UsersDataSet(Long id, String login, String email, String password) {
        setId(id);
        setLogin(login);
        setEmail(email);
        setPassword(password);
    }

    public UsersDataSet(String login, String email, String password) {
        setId(-1L);
        setLogin(login);
        setEmail(email);
        setPassword(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(Long id) {
        this.id = id;
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
