package team5.controller;

import team5.model.Context;
import team5.model.User;
import team5.view.MainFrame;

public class UserController {
	private static UserController instance;

	private UserController() {

	}

	public boolean login(String usernamee, String password) {
		Context context = Context.getInstance();
		for (User user : context.getUsers()) {
			if (user.getUsername().equals(usernamee) && user.getPassword().equals(password)) {
				MainFrame.getInstance().processEvent(Event.LOOGED_IN, user);
				context.setLogged(user);
				context.setLoginAttempts(0);
				return true;

			}
		}
		context.setLoginAttempts(context.getLoginAttempts() + 1);
		if (context.getLoginAttempts() == 3)
			MainFrame.getInstance().processEvent(Event.SHUT_DOWN, null);

		return false;
	}

	public void logout() {
		Context.getInstance().setLogged(null);
		MainFrame.getInstance().processEvent(Event.LOGGED_OUT, null);
	}

	public static UserController getInstance() {
		if(instance==null)
			instance=new UserController();
		return instance;
	}

	public void loginButtonPressed() {
		MainFrame.getInstance().processEvent(Event.LOGIN_PRESSED, null);
		
	}

}
