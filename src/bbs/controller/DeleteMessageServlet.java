package bbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.exception.NoRowsUpdatedRuntimeException;
import bbs.service.MessageService;

@WebServlet(urlPatterns = { "/deleteMessage" })
public class DeleteMessageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		int messageId = Integer.parseInt(request.getParameter("messageId"));
		request.setAttribute("messageId", messageId);
		request.getRequestDispatcher("Delete.jsp").forward(request, response);
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

    	int messageId = Integer.parseInt(request.getParameter("messageId"));

    	try {
    		new MessageService().delete(messageId);
		} catch (NoRowsUpdatedRuntimeException e) {
			response.sendRedirect("./Error.jsp" );
			return;
		}
        response.sendRedirect("./top" );
    }

}
