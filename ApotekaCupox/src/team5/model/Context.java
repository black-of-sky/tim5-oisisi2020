package team5.model;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.rmi.CORBA.Util;

import team5.Utils;

public class Context {

	private static Context instance = null;
	private List<User> users;
	private List<Medicine> medicine;
	private User logged = null;
	private int loginAttempts = 0;

	private List<Prescription> prescriptions;

	private Prescription recipeBeingCreated;
	private List<BillItem> currentCart; // trenutna korpa

	private List<Bill> bills; // svi racuni ikada

	private Map<String, ReportItem> currentReport;

	private String reportFor = ""; // kojo se izvestaj trenutnog prikazyuje (ovo se prikazuje iznad tabele)

	private Context() {
		users = (List<User>) Utils.loadMeFromFilePlease("./users.data");
		if (users == null) {
			users = new LinkedList<User>();
			User user = new User("admin", "admin", "admin", "admin", UserType.ADMINISTRATOR);
			users.add(user);
		/*	User user2 = new User("dummy", "dummy", "dummy", "dummy", UserType.LEKAR);
			users.add(user2);
			User user3 = new User("asd", "asd", "asd", "asd", UserType.APOTEKAR);
			users.add(user3);*/
		}

		medicine = (List<Medicine>) Utils.loadMeFromFilePlease("./medicine.data");
		if (medicine == null) {
			medicine = new LinkedList<Medicine>();
			// medicine.add(new Medicine("nemacka medecina", "je najbolja", "na svetu",
			// false, 22));
			// medicine.add(new Medicine("ubice ga", " bili", "ko zeca", false, 22.145f));

		}
		prescriptions = (List<Prescription>) Utils.loadMeFromFilePlease("./prescriptions.data");
		if (prescriptions == null) {
			prescriptions = new LinkedList<Prescription>();
			/*
			 * LinkedHashMap<String, Integer> ma = new LinkedHashMap<>(); ma.put("ubice ga",
			 * 12); ma.put("nemacka medecina", 3); Prescription r = new Prescription(1,
			 * "aasd", "0120135", new Date(), ma); prescriptions.add(r);
			 */

		}
		bills = (List<Bill>) Utils.loadMeFromFilePlease("./bills.data");
		if (bills == null)
			bills = new LinkedList<>();

		reset();
	}

	public void reset() {
		// inicijalizuj vrednosti, ako se neko izloguje pa se uloguje novi da ne ostane
		// nesto sto je prethodni gledao
		// ova 2 su samo bitna posto se ne restratuju kad se promeni kartica
		currentReport = new LinkedHashMap<>();
		recipeBeingCreated = new Prescription(0, null, null, null, null);
		reportFor = "";
		currentCart = new LinkedList<>();

	}

	public Prescription getRecipeBeingCreated() {
		return recipeBeingCreated;
	}

	public void setPrescriptionBeingCreated(Prescription prescriptionBeingCreated) {
		this.recipeBeingCreated = prescriptionBeingCreated;
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

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public List<BillItem> getCurrentCart() {
		return currentCart;
	}

	public void setCurrentCart(List<BillItem> currentCart) {
		this.currentCart = currentCart;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public void setRecipeBeingCreated(Prescription recipeBeingCreated) {
		this.recipeBeingCreated = recipeBeingCreated;
	}

	public Map<String, ReportItem> getCurrentReport() {
		return currentReport;
	}

	public void setCurrentReport(Map<String, ReportItem> currentReport) {
		this.currentReport = currentReport;
	}

	public String getReportFor() {
		return reportFor;
	}

	public void setReportFor(String reportFor) {
		this.reportFor = reportFor;
	}

}
