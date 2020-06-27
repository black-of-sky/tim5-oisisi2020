package team5.model;

import java.util.Date;
import java.util.Map;

public class Recipe {
	private int id;
	private String doctor, jmbg;
	private Date date;
	private Map<String, Integer> quantity;
	private boolean removed;
	private float price;

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Recipe(int id, String doctor, String jmbg, Date date, Map<String, Integer> quantity) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.jmbg = jmbg;
		removed = false;
		this.date = date;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Map<String, Integer> getQuantity() {
		return quantity;
	}

	public void setQuantity(Map<String, Integer> quantity) {
		this.quantity = quantity;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

}
