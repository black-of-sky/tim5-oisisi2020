package team5.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.rmi.CORBA.Util;

import team5.Utils;
import team5.model.Context;
import team5.model.User;
import team5.model.UserType;
import team5.view.MainFrame;
import team5.view.tables.models.UserAbstractTableModel;

public class UserController {
	/*
	 * private static UserController instance;
	 * 
	 * private UserController() {
	 * 
	 * }
	 */

	public static boolean login(String usernamee, String password) {
		Context context = Context.getInstance();
		for (User user : context.getUsers()) {
			if (user.getUsername().equals(usernamee) && user.getPassword().equals(password)) {
				context.setLogged(user);
				context.setLoginAttempts(0);
				MainFrame.getInstance().processEvent(Event.LOOGED_IN, user);
				return true;

			}
		}
		context.setLoginAttempts(context.getLoginAttempts() + 1);
		if (context.getLoginAttempts() == 3)
			MainFrame.getInstance().processEvent(Event.SHUT_DOWN, null);

		return false;
	}

	public static void logout() {
		Context.getInstance().setLogged(null);
		MainFrame.getInstance().processEvent(Event.LOGGED_OUT, null);
	}

	/*
	 * public static UserController getInstance() { if (instance == null) instance =
	 * new UserController(); return instance; }
	 */
	public static void loginButtonPressed() {
		MainFrame.getInstance().processEvent(Event.LOGIN_PRESSED, null);

	}

	public static boolean checkUsername(String username) {
		for (User u : Context.getInstance().getUsers()) {
			if (u.getUsername().equals(username)) {
				return false;

			}
		}
		return true;
	}

	public static void register(User user) {
		Context.getInstance().getUsers().add(user);
		int row = Context.getInstance().getUsers().size() - 1;
		UserAbstractTableModel.getInstance().fireTableRowsInserted(row, row);
		Utils.saveMeToFilePlease(Context.getInstance().getUsers(), "./users.data");
	}

	public static String[] getAllPharmacist() {
		List<String> a = Context.getInstance().getUsers().stream().filter(u -> u.getType() == UserType.APOTEKAR)
				.map(u -> u.getUsername()).distinct().collect(Collectors.toList());
		return a.toArray(new String[0]);
	}
}
