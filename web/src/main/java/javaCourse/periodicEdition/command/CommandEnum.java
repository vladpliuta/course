package javaCourse.periodicEdition.command;

/**
 * Enam - repository of commands
 * 
 * @author Vladimir Pliuta
 *
 */
public enum CommandEnum {

	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	},
	PERIODICEDITIONSADMIN {
		{
			this.command = new PeriodicEditionsAdminCommand();
		}
	},
	PERIODICEDITIONSUSER {
		{
			this.command = new PeriodicEditionsUserCommand();
		}
	},
	PERIODICEDITIONDELETE {
		{
			this.command = new PeriodicEditionDeleteCommand();
		}
	},
	PERIODICEDITIONCREATE {
		{
			this.command = new PeriodicEditionCreateCommand();
		}
	},
	SUBSCRIPTIONCREATE {
		{
			this.command = new SubscriptionCreateCommand();
		}
	},
	USERSLIST {
		{
			this.command = new UsersListCommand();
		}
	};
	
	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}

}
