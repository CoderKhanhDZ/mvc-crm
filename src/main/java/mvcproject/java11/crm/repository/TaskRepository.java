package mvcproject.java11.crm.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mvcproject.java11.crm.model.Task;

public class TaskRepository extends AbstractRepository<Task> {

	public void updateTask(Task task) {

		final String query = "update tasks set name=?, start_date=?, end_date=?, account_id=?, project_id=?, status_id=? where id = ?;";

		excuteQueryUpdate(connection -> {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, task.getName());
			statement.setDate(2, Date.valueOf(task.getStart_date()));
			statement.setDate(3, Date.valueOf(task.getEnd_date()));
			statement.setInt(4, task.getAccount_id());
			statement.setInt(5, task.getProject_id());
			statement.setInt(6, task.getStatus_id());
			statement.setInt(7, task.getId());

			return statement.executeUpdate();
		});
	}

	public void deleteTask(int id) {
		String query = "delete from tasks where id = ?;";

		excuteQueryUpdate(connection -> {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			return statement.executeUpdate();
		});
	}

	public void insertTask(Task task) {
		String query = "INSERT INTO tasks (name,start_date, end_date, project_id, account_id, status_id) VALUES  (?,?,?,?,?,?);";
		excuteQueryUpdate(connection -> {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, task.getName());
			statement.setDate(2, Date.valueOf(task.getStart_date()));
			statement.setDate(3, Date.valueOf(task.getEnd_date()));
			statement.setInt(4, task.getProject_id());
			statement.setInt(5, task.getAccount_id());
			statement.setInt(6, task.getStatus_id());

			return statement.executeUpdate();
		});
	}

	public List<Task> getAllTask() {

		final String query = "SELECT * FROM tasks";

		return excuteQuery(connection -> {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();

			List<Task> tasks = new ArrayList<>();
			Task task;

			while (res.next()) {
				// id, name, start_date, end_date, account_id, project_id, status_id
				task = new Task();
				task.setId(res.getInt("id"));
				task.setName(res.getString("name"));
				task.setStart_date(getDateFromResultSet("start_date", res));
				task.setEnd_date(getDateFromResultSet("end_date", res));
				task.setAccount_id(res.getInt("account_id"));
				task.setProject_id(res.getInt("project_id"));
				task.setStatus_id(res.getInt("status_id"));
			}

			return tasks;
		});
	}

	public Task getTaskById(int id) {

		String query = "SELECT * FROM tasks WHERE id =? ";

		return excuteQuerySingle(connection -> {

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();

			Task task = null;
			if (res.next()) {
				task = new Task();
				task.setId(res.getInt("id"));
				task.setName(res.getString("name"));
				task.setStart_date(getDateFromResultSet("start_date", res));
				task.setEnd_date(getDateFromResultSet("end_date", res));
				task.setAccount_id(res.getInt("account_id"));
				task.setProject_id(res.getInt("project_id"));
				task.setStatus_id(res.getInt("status_id"));
			}

			return task;
		});
	}

	public List<Task> getTaskByKeyword(String keyword, int index, int record_on_page) {

		StringBuilder query = new StringBuilder("select * from tasks where name like '%");
		query.append(keyword).append("%' limit ").append(index).append(",").append(record_on_page);

		return excuteQuery(connection -> {
			PreparedStatement statement = connection.prepareStatement(query.toString());
			ResultSet res = statement.executeQuery();

			List<Task> tasks = new ArrayList<>();
			Task task;

			while (res.next()) {
				task = new Task();
				task.setId(res.getInt("id"));
				task.setName(res.getString("name"));
				task.setStart_date(getDateFromResultSet("start_date", res));
				task.setEnd_date(getDateFromResultSet("end_date", res));
				task.setAccount_id(res.getInt("account_id"));
				task.setProject_id(res.getInt("project_id"));
				task.setStatus_id(res.getInt("status_id"));

				tasks.add(task);
			}

			return tasks;
		});
	}

	public int getTotalRecordTask(String keyword) {

		final String query = "SELECT COUNT(*) AS total_record  FROM tasks WHERE name LIKE '%" + keyword + "%'";
		return excuteQueryInteger(connnection -> {
			PreparedStatement statement = connnection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.next())
				return 0;
			return resultSet.getInt("total_record");
		});
	}

	private LocalDate getDateFromResultSet(String columnName, ResultSet resultSet) {
		Date time;

		try {
			time = resultSet.getDate(columnName);
			return time == null ? null : time.toLocalDate();
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Task> getTaskByAccountId(int accountId) {
		String query = "SELECT name,start_date,end_date,status_id FROM tasks where account_id = ? ;";

		return excuteQuery(connection -> {
			PreparedStatement statement = connection.prepareStatement(query.toString());
			statement.setInt(1, accountId);
			ResultSet res = statement.executeQuery();

			List<Task> tasks = new ArrayList<>();
			Task task = null;

			while (res.next()) {
				task = new Task();
				task.setName(res.getString("name"));
				task.setStart_date(getDateFromResultSet("start_date", res));
				task.setEnd_date(getDateFromResultSet("end_date", res));
				task.setStatus_id(res.getInt("status_id"));
				tasks.add(task);
			}

			return tasks;
		});
	}

	public List<Task> getTaskByProjectId(int projectId) {
		final String query = "SELECT * FROM tasks where project_id = ? ";
		return excuteQuery(connection -> {
			PreparedStatement statement = connection.prepareStatement(query.toString());
			statement.setInt(1, projectId);
			ResultSet res = statement.executeQuery();

			List<Task> tasks = new ArrayList<>();
			Task task;

			while (res.next()) {
				task = new Task();
				task.setId(res.getInt("id"));
				task.setName(res.getString("name"));
				task.setStart_date(getDateFromResultSet("start_date", res));
				task.setEnd_date(getDateFromResultSet("end_date", res));
				task.setAccount_id(res.getInt("account_id"));
				task.setProject_id(res.getInt("project_id"));
				task.setStatus_id(res.getInt("status_id"));

				tasks.add(task);
			}

			return tasks;
		});
	}

}
