package team5.model;

import java.io.Serializable;

import team5.Utils;

public class User implements Serializable {
	private String username, password, fName, lName;
	private UserType type;
	private boolean deleted = false;

	public User(String username, String password, String fName, String lName, UserType type) {
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		if (getUsername().equals("admin"))
			return;// za admina ne damo da mu se iskljuci nalog kako bi uvek postojao bar jedan
					// koji moze da udje xD
		this.deleted = deleted;
		Utils.saveMeToFilePlease(Context.getInstance().getUsers(), "./users.data");
	}

}
