package HW01.dbservice;

import HW01.dbservice.dao.UsersDAO;
import HW01.dbservice.dataset.UsersDataSet;
import HW01.exceptions.DBException;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getPostgresConnection();
    }

    private Connection getPostgresConnection() {

        final String url = "jdbc:postgresql://localhost:5432/Auth";
        final String name = "postgres";
        final String password = "qwerty12356";
        try {
            return DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public UsersDataSet getUser(String name) throws DBException {
        try {
            return new UsersDAO(connection).getUserByName(name);

        } catch (SQLException e) {
            throw new DBException(e);
        }


    }

    public List<UsersDataSet> getAllUsers() throws SQLException {
        UsersDAO dao = new UsersDAO(connection);
        return dao.getAllUsers();
    }

    public long addUser(String name, String email, String password) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.createTable();
            dao.addUser(name, email, password);
            connection.commit();
            return dao.getUserId(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void cleanUp() throws DBException {
        UsersDAO dao = new UsersDAO(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

}



