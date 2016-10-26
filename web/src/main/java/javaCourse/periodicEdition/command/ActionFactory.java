package javaCourse.periodicEdition.command;

import javax.servlet.http.HttpServletRequest;

import javaCourse.periodicEdition.resource.MessageManager;



/**
 * класс является элементом реализации шаблона Factory Method
 *  извлекает имя команды из запроса 
 *  получает объект, соответствующий команде
 * 
 * @author Vladimir Pliuta
 *
 */
public class ActionFactory {
	private ActionCommand current;

	public ActionCommand defineCommand(HttpServletRequest request) {
		String action = request.getParameter("command");
		if (action == null || action.isEmpty()) {
			return current;
		}

		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
		}
		return current;
	}
}
