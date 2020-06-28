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
import team5.model.ReportItem;

public class ReportAbstractTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;
	private static Context context = Context.getInstance();
	private List<String> kolone = new ArrayList<String>();

	private ReportAbstractTableModel() {
		kolone.add("med_id");
		kolone.add("med_name");
		kolone.add("quantity");
		kolone.add("total_price");
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;// columnIndex >=4;
	}

	@Override
	public int getRowCount() {
		return context.getCurrentReport().size();
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
			return String.class;
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
		int i = 0;
		String medId = null;
		ReportItem r = null;
		for (Entry<String, ReportItem> entry : context.getCurrentReport().entrySet()) {
			if (i != rowIndex) {
				i++;
				continue;
			}
			medId = entry.getKey();
			r = entry.getValue();
			break;
		}

		switch (columnIndex) {
		case 0:
			return medId;

		case 1:
			return r.getTitle();
		case 2:
			return r.getQuantity();

		case 3:
			return r.getTotalPrice();

		}

		return null;
	}

}
