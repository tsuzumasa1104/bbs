package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;

import bbs.beans.User;
import bbs.dao.UserDao;
import bbs.utils.CipherUtil;

public class UserService {

	public User select(int userId) {

		Connection connection = null;
		try {

			connection = getConnection();
			User user = new UserDao().select(connection, userId);
			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public User select(String name, String password) {
		Connection connection = null;
		try {

			connection = getConnection();
			String encPassword = CipherUtil.encrypt(password);
			System.out.println(encPassword);
			User user = new UserDao().select(connection, name, encPassword);
			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}

	}

}
