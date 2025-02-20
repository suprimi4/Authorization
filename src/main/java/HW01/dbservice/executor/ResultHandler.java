package HW01.dbservice.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}

