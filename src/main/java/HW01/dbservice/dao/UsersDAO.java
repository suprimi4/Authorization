package HW01.dbservice.dao;

import HW01.dbservice.dataset.UsersDataSet;
import HW01.dbservice.executor.Executor;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet getUserByName(String value) throws SQLException {
        String hql = "from UsersDataSet u where u.login = :login";
        Query<UsersDataSet> query = session.createQuery(hql, UsersDataSet.class);
        query.setParameter("login", value);
        UsersDataSet dataSet = query.uniqueResult();
        if (dataSet == null) {
            return new UsersDataSet(-1L, "", "", "");
        }
        return dataSet;
    }

    public List<UsersDataSet> getAllUsers() throws SQLException {
        return session.createQuery("from UsersDataSet", UsersDataSet.class).list();
    }

    public void addUser(String name, String email, String password) throws SQLException {
        session.save(new UsersDataSet(name, email, password));
    }


}
