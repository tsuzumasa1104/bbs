package bbs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import bbs.beans.Message;
import bbs.exception.NoRowsUpdatedRuntimeException;
import bbs.service.MessageService;

@WebServlet(urlPatterns = { "/editMessage/*" })
public class EditMessageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String[] path = request.getPathInfo().split("/");

		int messageId = Integer.parseInt(path[1]);
		Message message = new MessageService().select(messageId);
		request.setAttribute("message", message);
		request.getRequestDispatcher("/Edit.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		List<String> errorMessages = new ArrayList<String>();

        Message message = getMessage(request);
        if ( ! isValidForm(message, errorMessages)) {
        	request.setAttribute("errorMessages", errorMessages);
        	request.setAttribute("message", message);
            request.getRequestDispatcher("/Message.jsp").forward(request, response);
            return;
        }

        new MessageService().update(message);
        response.sendRedirect("/bbs/top");

		try {
    		new MessageService().update(message);
		} catch (NoRowsUpdatedRuntimeException e) {
			response.sendRedirect("/bbs/Error.jsp" );
			return;
		}

	}

	private Message getMessage(HttpServletRequest request) throws IOException, ServletException {

		int messageId = Integer.parseInt(request.getParameter("messageId"));
		String text = request.getParameter("text");
		Message message = new Message();
		message.setId(messageId);
		message.setText(text);

        return message;
    }

    private boolean isValidForm(Message message, List<String> errorMessages) {

    	String text = message.getText();

        if (StringUtils.isBlank(text)) {
            errorMessages.add("本文を入力してください");
        } else if(100 < text.length() ) {
        	errorMessages.add("本文は100文字以下で入力してください");
        }

        if (errorMessages.size() != 0) {
            return false;
        }

        return true;
    }


}