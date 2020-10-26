package bbs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.beans.Message;
import bbs.service.MessageService;

@WebServlet(urlPatterns = { "/top" })
public class TopServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("/Top.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String searchWord = request.getParameter("searchWord");

		List<Message> messages = new MessageService().select(searchWord);
		request.setAttribute("messages", messages);
		request.getRequestDispatcher("/Top.jsp").forward(request, response);
	}

}