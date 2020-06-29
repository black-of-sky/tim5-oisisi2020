package team5.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;

import team5.Utils;

public class Prescription implements Serializable{
	private int id;
	private String doctor, jmbg;
	private Date date;
	private LinkedHashMap<String, Integer> quantity;
	private boolean removed;
	private float price;

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Prescription(int id, String doctor, String jmbg, Date date, LinkedHashMap<String, Integer> quantity) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.jmbg = jmbg;
		removed = false;
		this.date = date;
		this.quantity = quantity != null ? quantity : new LinkedHashMap<String, Integer>();
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

	public LinkedHashMap<String, Integer> getQuantity() {
		return quantity;
	}

	public void setQuantity(LinkedHashMap<String, Integer> quantity) {
		this.quantity = quantity;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
		Utils.saveMeToFilePlease(Context.getInstance().getPrescriptions(), "./data/prescriptions.data");
	}

}
