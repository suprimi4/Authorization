package HW01.dbservice.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    private Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execUpdate(String update) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(update);
        statement.close();
    }

    public <T> T execQuery(String query, ResultHandler<T> handler) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
        ResultSet resulstSet = statement.getResultSet();
        T value = handler.handle(resulstSet);
        resulstSet.close();
        statement.close();
        return value;
    }
}
