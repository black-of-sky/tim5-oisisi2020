package team5.view.tables.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

import team5.model.Context;
import team5.model.User;
import team5.model.UserType;

public class SoldOrPrescriptedMedicineAbsractTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;

	private Map<String, Integer> data;
	private List<String> kolone = new ArrayList<String>();

	public SoldOrPrescriptedMedicineAbsractTableModel(Map<String, Integer> data2) {
		data = data2;
		kolone.add("med_id");
		kolone.add("quantity");

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public int getRowCount() {
		return data.size();
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

		return String.class;

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		int i = 0;
		for (Entry<String, Integer> a : data.entrySet()) {
			if (i == rowIndex) {
				if (columnIndex == 0)
					return a.getKey();
				return a.getValue();
			}
		}

		return null;
	}

}
