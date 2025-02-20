package HW01.dbservice.dao;

import HW01.dbservice.dataset.UsersDataSet;
import HW01.dbservice.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private Executor executor;

    public UsersDAO(Connection connection) {
        executor = new Executor(connection);
    }

    public long getUserId(String name) throws SQLException {
        return executor.execQuery("select * from users where login='" + name + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }


    public UsersDataSet get(long id) throws SQLException {
        return executor.execQuery("select * from users where id=" + id, result -> {
            result.next();
            return new UsersDataSet(result.getLong(1), result.getString(2),result.getString(3),result.getString(4));
        });
    }


    public UsersDataSet getUserByName(String value) throws SQLException {
        return executor.execQuery("select * from users where login =" + value, resultSet -> {
                    resultSet.next();
                    return new UsersDataSet(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                }
        );
    }

    public List<UsersDataSet> getAllUsers() throws SQLException {
        List<UsersDataSet> list = new ArrayList<>();
        return executor.execQuery("select * from users", resultSet -> {

            while (resultSet.next()) {
                long id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String email = resultSet.getString(3);
                String password = resultSet.getString(4);
                list.add(new UsersDataSet(id, login, email, password));
            }
            return list;
        });
    }

    public void addUser(String name, String email, String password) throws SQLException {
        executor.execUpdate("insert into users (login, email, user_password) values ('" + name + "', '" + email + "', '" + password + "');");


    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id SERIAL PRIMARY KEY,login varchar(20),email varchar(20),user_password varchar (30));");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }


}
