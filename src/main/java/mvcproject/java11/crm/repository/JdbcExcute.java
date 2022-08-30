package mvcproject.java11.crm.repository;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface JdbcExcute<T> {

	T processQuery(Connection connection) throws SQLException;
}
