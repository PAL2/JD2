package by.hotel.command;

import by.hotel.command.admin.*;
import by.hotel.command.client.*;
import by.hotel.command.user.LoginCommand;
import by.hotel.command.user.LogoutCommand;
import by.hotel.command.user.RegCommand;

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
	ORDER {
		{
			this.command = new OrderCommand();
		}
	},
	CHOOSEROOM {
		{
			this.command = new ChooseRoomCommand();
		}
	},
	BILL {
		{
			this.command = new BillCommand();
		}
	},
	MYACCOUNTS {
		{
			this.command = new MyAccountsCommand();
		}
	},
	REG {
		{
			this.command = new RegCommand();
		}
	},
	REJECT {
		{
			this.command = new RejectCommand();
		}
	},
	ALLBOOKING {
		{
			this.command = new AllBookingCommand();
		}
	},
	NEWBOOKING {
		{
			this.command = new NewBookingCommand();
		}
	},
	DELETEBOOKING {
		{
			this.command = new DeleteBookingCommand();
		}
	},
	ALLUSER {
		{
			this.command = new AllUserCommand();
		}
	},
	ALLROOM {
		{
			this.command = new AllRoomCommand();
		}
	},
	ALLACCOUNT {
		{
			this.command = new AllAccountCommand();
		}
	},
	MYBOOKING {
		{
			this.command = new MyBookingCommand();
		}
	},
	UNPAIDACCOUNT{
		{
			this.command = new UnpaidAccountCommand();
		}
	},
	PAY{
		{
			this.command = new PayCommand();
		}
	},
	REFUSE{
		{
			this.command = new RefuseCommand();
		}
	},
	GOORDER{
		{
			this.command = new GoOrderCommand();
		}
	};
	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}

}
