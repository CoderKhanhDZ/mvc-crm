package mvcproject.java11.crm.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mvcproject.java11.crm.model.Account;
import mvcproject.java11.crm.model.Project;

public class ProjectRepository extends AbstractRepository<Project> {

	public int updateProject(Project project) {

		final String query = "update projects set  name=?, start_date=? , end_date=? where id = ?;";

		return excuteQueryUpdate(connection -> {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, project.getName());
			statement.setDate(2, Date.valueOf(project.getStart_date()));
			statement.setDate(3, Date.valueOf(project.getEnd_date()));
			statement.setInt(4, project.getId());

			return statement.executeUpdate();
		});
	}

	public int deleteProject(int id) {
		final String query = "delete from projects where id = ?;";

		return excuteQueryUpdate(connection -> {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, id);

			return statement.executeUpdate();
		});
	}

	public int insertProject(Project project) {
		final String query = "INSERT INTO projects (name, start_date, end_date) VALUES  (?,?,?)";

		return excuteQueryUpdate(connection -> {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, project.getName());
			statement.setDate(2, Date.valueOf(project.getStart_date()));
			statement.setDate(3, Date.valueOf(project.getEnd_date()));

			return statement.executeUpdate();
		});
	}

	public List<Project> getAllProject() {
		final String query = "SELECT * FROM projects;";

		return excuteQuery(connection -> {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();

			List<Project> projects = new ArrayList<>();
			Project project;

			while (res.next()) {
				project = new Project();

				project.setId(res.getInt("id"));
				project.setName(res.getString("name"));
				project.setStart_date(getDateFromResultSet("start_date", res));
				project.setEnd_date(getDateFromResultSet("end_date", res));

				projects.add(project);
			}

			return projects;
		});
	}

	public Project getProjectById(int id) {
		final String query = "SELECT * FROM projects WHERE id=? ;";

		return excuteQuerySingle(connection -> {

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();

			Project project = null;
			if (res.next()) {
				project = new Project();

				project.setId(res.getInt("id"));
				project.setName(res.getString("name"));
				project.setStart_date(getDateFromResultSet("start_date", res));
				project.setEnd_date(getDateFromResultSet("end_date", res));
			}

			return project;
		});
	}

	public List<Project> getProjectByKeyword(String keyword, int index, int limit) {

		StringBuilder query = new StringBuilder("select * from projects where name like '%");
		query.append(keyword).append("%' limit ").append(index).append(",").append(limit);

		return excuteQuery(connection -> {
			PreparedStatement statement = connection.prepareStatement(query.toString());
			ResultSet res = statement.executeQuery();

			List<Project> projects = new ArrayList<>();
			Project project;

			while (res.next()) {
				
				project = new Project();
				
				project.setId(res.getInt("id"));
				project.setName(res.getString("name"));
				project.setStart_date(getDateFromResultSet("start_date", res));
				project.setEnd_date(getDateFromResultSet("end_date", res));

				projects.add(project);
			}

			return projects;
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

	public int getTotalRecordProject(String keyword) {

		final String query = "SELECT COUNT(*) AS total_record  FROM projects WHERE name LIKE '%" + keyword + "%'";

		return excuteQueryUpdate(connection -> {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			if(!resultSet.next()) return 0;
			return resultSet.getInt("total_record");
		});
	}
}
