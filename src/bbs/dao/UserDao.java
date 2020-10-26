package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.User;
import bbs.exception.SQLRuntimeException;

public class UserDao {

	public User select(Connection connection, int userId) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);

			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			List<User> users = toUsers(rs);

			if (users.isEmpty()) {
				return null;
			} else if (2 <= users.size()) {
				throw new IllegalStateException("ユーザーが重複しています");
			} else {
				return users.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public User select(Connection connection, String name, String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE name = ?  AND password = ?";

			ps = connection.prepareStatement(sql);

			ps.setString(1, name);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> users = toUsers(rs);

			if (users.isEmpty()) {
				return null;
			} else if (2 <= users.size()) {
				throw new IllegalStateException("ユーザーが重複しています");
			} else {
				return users.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUsers(ResultSet rs) throws SQLException {

		List<User> users = new ArrayList<User>();
		try {
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setCreatedDate(rs.getTimestamp("created_date"));
				user.setUpdatedDate(rs.getTimestamp("updated_date"));

				users.add(user);
			}
			return users;
		} finally {
			close(rs);
		}
	}

}
