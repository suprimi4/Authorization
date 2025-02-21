package HW01.dbservice;

import HW01.dbservice.dao.UsersDAO;
import HW01.dbservice.dataset.UsersDataSet;
import HW01.exceptions.DBException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBService {
    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getPostgresConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    private Configuration getPostgresConfiguration() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(UsersDataSet.class);
        return configuration;

    }

    public UsersDataSet getUser(String name) throws DBException {
        try (Session session = sessionFactory.openSession()) {
            UsersDAO dao = new UsersDAO(session);
            return dao.getUserByName(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<UsersDataSet> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            UsersDAO dao = new UsersDAO(session);
            return dao.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return java.util.Collections.emptyList();
    }

    public long addUser(String name, String email, String password) throws DBException {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            UsersDAO dao = new UsersDAO(session);
            dao.addUser(name, email, password);
            transaction.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return 0;
    }

}



