package javaCourse.periodicEdition.command;


import javaCourse.periodicEdition.controller.RequestContent;
import javaCourse.periodicEdition.resource.ConfigurationManager;



/**
 * command out of the system and the destruction of session
 * 
 * @author Vladimir Pliuta
 *
 */
public class LogoutCommand implements ActionCommand {

	@Override
	public String execute(RequestContent requestContent) {
		String page = ConfigurationManager.getProperty("page.index");
		requestContent.sessionInvalidate();
		return page;
	}
}
