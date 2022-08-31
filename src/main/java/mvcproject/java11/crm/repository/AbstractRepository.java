package mvcproject.java11.crm.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvcproject.java11.crm.exception.DatabaseNotFoundException;
import mvcproject.java11.crm.jdbc.MySqlConnection;

public class AbstractRepository<T> {
	
	public List<T> excuteQuery(JdbcExcute<List<T>> processor){
		try(Connection connection = MySqlConnection.getConnection()){
			return processor.processQuery(connection);
		}catch (SQLException e) {
			throw new DatabaseNotFoundException(e.getMessage());
		}
	}
	
	public T excuteQuerySingle(JdbcExcute<T> processor){
		try(Connection connection = MySqlConnection.getConnection()){
			return processor.processQuery(connection);
		}catch (SQLException e) {
			throw new DatabaseNotFoundException(e.getMessage());
		}
	}
	
	public void excuteQueryUpdate(JdbcExcute<Integer> processor){
		try(Connection connection = MySqlConnection.getConnection()){
			 processor.processQuery(connection);
		}catch (SQLException e) {
			throw new DatabaseNotFoundException(e.getMessage());
		}
	}
	
	public Integer excuteQueryInteger(JdbcExcute<Integer> processor){
		try(Connection connection = MySqlConnection.getConnection()){
			return processor.processQuery(connection);
		}catch (SQLException e) {
			throw new DatabaseNotFoundException(e.getMessage());
		}
	}
	
	public Boolean existedBy(JdbcExcute<Boolean> processor){
		try(Connection connection = MySqlConnection.getConnection()){
			return processor.processQuery(connection);
		}catch (SQLException e) {
			throw new DatabaseNotFoundException(e.getMessage());
		}
	}

}
