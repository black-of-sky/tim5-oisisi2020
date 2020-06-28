package team5.view.tables.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

import team5.controller.MedicineController;
import team5.model.BillItem;
import team5.model.Context;
import team5.model.Medicine;
import team5.model.Prescription;

public class CartAbstactTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;
	private static CartAbstactTableModel instance;
	private static Context context = Context.getInstance();
	private List<String> kolone = new ArrayList<String>();

	private CartAbstactTableModel() {
		kolone.add("med_id");
		kolone.add("med_price");
		kolone.add("quantity");
		kolone.add("total_price");
		instance = this;
		context.setCurrentCart(new LinkedList<BillItem>());
	}

	public static CartAbstactTableModel getInstance() {
		if(instance==null)
			instance=new CartAbstactTableModel();
		return instance;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;// columnIndex >=4;
	}

	@Override
	public int getRowCount() {
		return context.getCurrentCart().size();
	}

	@Override
	public int getColumnCount() {
		return kolone.size();
	}

	@Override
	public String getColumnName(int column) {
		return kolone.get(column);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return Float.class;
		case 2:
			return Integer.class;
		case 3:
			return Float.class;
		case 4:
		default:
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		List<BillItem> r = context.getCurrentCart();
		BillItem row = context.getCurrentCart().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return row.getMedicineId();

		case 1:
			return row.getTotalPrice() / row.getQuantity();
		case 2:
			return row.getQuantity();

		case 3:
			return row.getTotalPrice();

		}

		return null;
	}

}
