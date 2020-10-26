package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bbs.beans.Message;
import bbs.dao.MessageDao;

public class MessageService {

    public void insert(Message message) {

        Connection connection = null;
        try {

            connection = getConnection();
            new MessageDao().insert(connection, message);
            commit(connection);
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

    public List<Message> select(String searchWord) {
        Connection connection = null;
        try {
            connection = getConnection();

            List<Message> messages;
            if (StringUtils.isBlank(searchWord)) {
            	messages = new MessageDao().select(connection, "");
        	} else {
        		messages = new MessageDao().select(connection, searchWord);
        	}

            commit(connection);

            return messages;
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

    public Message select(int messageId) {
        Connection connection = null;
        try {
            connection = getConnection();
            Message message =
            		new MessageDao().select(connection, messageId);
            commit(connection);

            return message;
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

	public void delete(int messageId) {

        Connection connection = null;
        try {
            connection = getConnection();
            new MessageDao().delete(connection, messageId);
            commit(connection);

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

	public void update(Message message) {

        Connection connection = null;
        try {

            connection = getConnection();
            new MessageDao().update(connection, message);
            commit(connection);

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
