package com.hotel.command;

import com.hotel.command.user.LoginCommand;
import com.hotel.command.user.LogoutCommand;
import com.hotel.command.user.RegCommand;
import com.hotel.command.admin.*;
import com.hotel.command.client.*;

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
