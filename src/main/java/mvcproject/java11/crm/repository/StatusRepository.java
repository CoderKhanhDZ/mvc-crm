package mvcproject.java11.crm.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mvcproject.java11.crm.model.Status;

public class StatusRepository extends AbstractRepository<Status> {

	public List<Status> getAllStatus() {
		final String query = "SELECT * FROM status";
		return excuteQuery(connection -> {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();

			List<Status> statuss = new ArrayList<>();
			Status status;

			while (res.next()) {
				status = new Status();
				status.setId(res.getInt("id"));
				status.setName(res.getString("name"));
				statuss.add(status);
			}

			return statuss;
		});
	}

	public Status getStatusById(int id) {
		final String query = "SELECT * FROM status WHERE id=? ;";

		return excuteQuerySingle(connection -> {

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();

			Status status = null;
			if (res.next()) {
				status = new Status();

				status.setId(res.getInt("id"));
				status.setName(res.getString("name"));
			}

			return status;
		});
	}
}
