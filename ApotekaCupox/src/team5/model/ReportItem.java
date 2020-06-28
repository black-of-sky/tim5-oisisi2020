package team5.model;

//kolona u izvestaju
public class ReportItem {
	private float totalPrice;// kompletan profit za taj lek
	private String title;// ime leka, id ne treba posto ce se ovo dodavati u mapu gde je id kljuc
	private int quantity; // kolicina

	public ReportItem(float totalPrice, String title, int quantity) {
		super();
		this.totalPrice = totalPrice;
		this.title = title;
		this.quantity = quantity;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
