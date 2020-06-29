package team5.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Bill implements Serializable {
	private String buyer,soldBy;// //kupacsoldBy-apotekar koji ga je prodao;
	private Date date;
	private int discountPerc;//popust, procenat
	private float fee; // kolik je platio, uradunat popust
	
	
	private List<BillItem> items;


	public String getBuyer() {
		return buyer;
	}


	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getDiscountPerc() {
		return discountPerc;
	}


	public void setDiscountPerc(int discountPerc) {
		this.discountPerc = discountPerc;
	}


	public float getFee() {
		return fee;
	}


	public void setFee(float fee) {
		this.fee = fee;
	}


	public List<BillItem> getItems() {
		return items;
	}


	public void setItems(List<BillItem> items) {
		this.items = items;
	}


	public String getSoldBy() {
		return soldBy;
	}


	public void setSoldBy(String soldBy) {
		this.soldBy = soldBy;
	}
	
	
}
