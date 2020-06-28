package team5.model;

public class BillItem {
	private String medicineId, prescription, soldBy;// prescription=id recepta (ako je dodato sa recepta),
													// soldBy-apotekar koji ga je prodao
	private int quantity;
	private float totalPrice;

	public BillItem(String medicineId, String prescription, String soldBy, int quantity, float totalPrice) {
		super();
		this.medicineId = medicineId;
		this.prescription = prescription;
		this.soldBy = soldBy;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public String getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getSoldBy() {
		return soldBy;
	}

	public void setSoldBy(String soldBy) {
		this.soldBy = soldBy;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

}
