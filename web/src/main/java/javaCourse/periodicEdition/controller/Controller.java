package javaCourse.periodicEdition.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaCourse.periodicEdition.command.ActionCommand;
import javaCourse.periodicEdition.command.ActionFactory;
import javaCourse.periodicEdition.resource.ConfigurationManager;
import javaCourse.periodicEdition.resource.MessageManager;

/**
 * контроллер запросов единственный сервлет в программе
 * 
 * обрабатывает только post запросы
 * 
 * @author Vladimir Pliuta
 *
 */
@WebServlet(name = "Servlet", urlPatterns = "/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = null;
		RequestContent requestContent = new RequestContent();
		requestContent.extractValue(request);
		
		ActionFactory client = new ActionFactory();
		ActionCommand command = client.defineCommand(requestContent);
		page = command.execute(requestContent);

		if (page != null) {
			request = requestContent.updateRequest(request);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} else {
			page = ConfigurationManager.getProperty("page.error");
			request.getSession().setAttribute("error", MessageManager.getProperty("message.nullpage"));
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}
}
