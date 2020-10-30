package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.Message;
import bbs.exception.NoRowsUpdatedRuntimeException;
import bbs.exception.SQLRuntimeException;

public class MessageDao {

    public void insert(Connection connection, Message message) {

        PreparedStatement ps = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO messages ( ");
            sql.append("    id, ");
            sql.append("    text, ");
            sql.append("    created_date, ");
            sql.append("    updated_date ");
            sql.append(") VALUES ( ");
            sql.append("    ?, ");
            sql.append("    ?, ");
            sql.append("    CURRENT_TIMESTAMP, ");
            sql.append("    CURRENT_TIMESTAMP ");
            sql.append(")");

            ps = connection.prepareStatement(sql.toString());

            ps.setInt(1, message.getId());
            ps.setString(2, message.getText());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        } finally {
            close(ps);
        }
    }

    public void delete(Connection connection, int messageId) {

        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM messages WHERE id = ?";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, messageId);
            int count = ps.executeUpdate();

            if (count == 0) {
                throw new NoRowsUpdatedRuntimeException();
            }

        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        } finally {
            close(ps);
        }
    }

	public List<Message> select(Connection connection, String searchWord) {
			PreparedStatement ps = null;
			try {
				String sql = "SELECT * FROM messages WHERE text LIKE ?";

				ps = connection.prepareStatement(sql);
				ps.setString(1, "%" + searchWord + "%");

				ResultSet rs = ps.executeQuery();
				List<Message> messages = toMessages(rs);

				if (messages.isEmpty()) {
					return null;
				} else {
					return messages;
				}
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {
				close(ps);
			}
		}

	public Message select(Connection connection, int messageId) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM messages WHERE id = ?";

			ps = connection.prepareStatement(sql);

			ps.setInt(1, messageId);

			ResultSet rs = ps.executeQuery();
			List<Message> messages = toMessages(rs);

			if (messages.isEmpty()) {
				return null;
			} else if (2 <= messages.size()) {
				throw new IllegalStateException("メッセージが重複しています。");
			} else {
				return messages.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, Message message) {

        PreparedStatement ps = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE messages SET ");
            sql.append("    text = ?, ");
            sql.append("    updated_date = CURRENT_TIMESTAMP ");
            sql.append("WHERE id = ?");

            ps = connection.prepareStatement(sql.toString());

            ps.setString(1, message.getText());
            ps.setInt(2, message.getId());

            int count = ps.executeUpdate();
            if (count == 0) {
                throw new NoRowsUpdatedRuntimeException();
            }
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        } finally {
            close(ps);
        }
    }

	private List<Message> toMessages(ResultSet rs) throws SQLException {

		List<Message> messages = new ArrayList<Message>();
		try {
			while (rs.next()) {
				Message message = new Message();
				message.setId(rs.getInt("id"));
				message.setText(rs.getString("text"));
				message.setCreatedDate(rs.getTimestamp("created_date"));
				message.setUpdatedDate(rs.getTimestamp("updated_date"));

				messages.add(message);
			}
			return messages;
		} finally {
			close(rs);
		}
	}
	}


