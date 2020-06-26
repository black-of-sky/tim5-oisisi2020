package team5.model;

import java.util.LinkedList;
import java.util.List;

public class Context{
	
	private static Context instance=null;
	private List<User> users;
	private List<Medicine> medicine;
	private User logged=null;
	private int loginAttempts=0;
	
	
	private Context() {
		users=new LinkedList<User>();
		User user=new User("admin", "admin","admin" , "admin", UserType.ADMINISTRATOR);
		users.add(user);
		
		User user2=new User("dummy", "dummy","dummy" , "dummy", UserType.LEKAR);
		users.add(user2);
		
		User user3=new User("asd", "asd","asd" , "asd", UserType.APOTEKAR);
		users.add(user3);	
		//*****
		
		medicine=new LinkedList<Medicine>();
		medicine.add(new Medicine("poyy","lek", "pro", false, 22));
	}
	
	public static Context getInstance() {
		if (instance==null) {
			instance=new Context();
		}
		return instance;
	}
	public static void setInstance(Context instance) {
		Context.instance = instance;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public User getLogged() {
		return logged;
	}
	public void setLogged(User logged) {
		this.logged = logged;
	}
	public int getLoginAttempts() {
		return loginAttempts;
	}
	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public List<Medicine> getMedicine() {
		return medicine;
	}

	public void setMedicine(List<Medicine> medicine) {
		this.medicine = medicine;
	}
	
	
}
