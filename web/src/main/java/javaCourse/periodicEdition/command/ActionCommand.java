package javaCourse.periodicEdition.command;

import javaCourse.periodicEdition.controller.RequestContent;

/**
 * command interface
 * 
 * @author Vladimir Pliuta
 *
 */
public interface ActionCommand {

	String execute(RequestContent requestContent);

}
