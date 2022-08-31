package mvcproject.java11.crm.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mvcproject.java11.crm.model.Role;

public class RoleRepository extends AbstractRepository<Role> {

	public void insertRole(Role role) {

		final String query = "INSERT INTO roles (name, description) VALUES  ( ?,?)";

		excuteQueryUpdate(connection -> {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			return statement.executeUpdate();
		});
	}

	public void updateRole(Role role) {

		final String query = "UPDATE roles SET  name=?, description=? WHERE id = ?";

		excuteQueryUpdate(connection -> {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			statement.setInt(3, role.getId());

			return statement.executeUpdate();
		});
	}

	public void deleteRole(int id) {
		final String query = "DELETE FROM roles WHERE id=?";

		excuteQueryUpdate(connection -> {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			return statement.executeUpdate();
		});
	}

	public List<Role> getAllRole() {

		final String query = "SELECT * FROM roles";

		return excuteQuery(connection -> {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();

			List<Role> roles = new ArrayList<>();
			Role role;

			while (res.next()) {
				role = new Role();

				role.setId(res.getInt("id"));
				role.setName(res.getString("name"));
				role.setDescription(res.getString("description"));

				roles.add(role);
			}

			return roles;
		});
	}

	public Role getRoleById(int id) {

		final String query = "SELECT * FROM roles WHERE id=? ";
		return excuteQuerySingle(connection -> {

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();

			Role role = null;
			if (res.next()) {
				role = new Role();
				role.setId(res.getInt("id"));
				role.setName(res.getString("name"));
				role.setDescription(res.getString("description"));

			}

			return role;
		});
	}

	public int getTotalRecordRole(String keyword) {

		final String query = "SELECT COUNT(*) AS total_record  FROM roles WHERE name LIKE '%" + keyword + "%'";

		return excuteQueryInteger(connnection -> {
			PreparedStatement statement = connnection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			if (!resultSet.next())
				return 0;
			return resultSet.getInt("total_record");
		});
	}

	public List<Role> getRoleByKeyWord(String keyword, int index, int limit) {
		StringBuilder query = new StringBuilder("SELECT * FROM roles WHERE name LIKE '%");
		query.append(keyword).append("%' LIMIT ").append(index).append(",").append(limit);

		return excuteQuery(connection -> {
			PreparedStatement statement = connection.prepareStatement(query.toString());
			ResultSet res = statement.executeQuery();

			List<Role> roles = new ArrayList<>();
			Role role;

			while (res.next()) {
				role = new Role();

				role.setId(res.getInt("id"));
				role.setName(res.getString("name"));
				role.setDescription(res.getString("description"));

				roles.add(role);
			}

			return roles;
		});
	}
}
