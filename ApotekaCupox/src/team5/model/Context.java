package team5.model;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class Context {

	private static Context instance = null;
	private List<User> users;
	private List<Medicine> medicine;
	private User logged = null;
	private int loginAttempts = 0;

	private List<Recipe> recipes;

	private Recipe recipeBeingCreated = new Recipe(0, null, null, null, null);

	public Recipe getRecipeBeingCreated() {
		return recipeBeingCreated;
	}

	public void setRecipeBeingCreated(Recipe recipeBeingCreated) {
		this.recipeBeingCreated = recipeBeingCreated;
	}

	private Context() {
		users = new LinkedList<User>();
		User user = new User("admin", "admin", "admin", "admin", UserType.ADMINISTRATOR);
		users.add(user);

		User user2 = new User("dummy", "dummy", "dummy", "dummy", UserType.LEKAR);
		users.add(user2);

		User user3 = new User("asd", "asd", "asd", "asd", UserType.APOTEKAR);
		users.add(user3);
		// *****

		medicine = new LinkedList<Medicine>();
		medicine.add(new Medicine("nemacka medecina", "je najbolja", "na svetu", false, 22));
		medicine.add(new Medicine("ubice ga", " bili", "ko zeca", false, 22.145f));
		recipes = new LinkedList<Recipe>();
		LinkedHashMap<String, Integer> ma = new LinkedHashMap<>();
		ma.put("ubice ga", 12);
		ma.put("nemacka medecina", 3);
		Recipe r = new Recipe(1, "aasd", "0120135", new Date(), ma);
		recipes.add(r);

	}

	public static Context getInstance() {
		if (instance == null) {
			instance = new Context();
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

	public List<Recipe> getRecipes() {
		return recipes;
	}

}
