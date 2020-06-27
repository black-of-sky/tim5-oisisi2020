package team5.view.tables.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

import team5.controller.MedicineController;
import team5.model.Context;
import team5.model.Medicine;
import team5.model.Recipe;

public class MedicineInRecipe extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;
	private static MedicineInRecipe activeInstance;
	private static Context context = Context.getInstance();
	private List<String> kolone = new ArrayList<String>();
	private int index;

	public MedicineInRecipe(int index) {
		kolone.add("med_id");
		this.index = index;
		kolone.add("med_price");
		kolone.add("quantity");
		kolone.add("total_price");
		activeInstance = this;
		if (index == -1)
			context.setRecipeBeingCreated(new Recipe(-1, null, null, null, null));
	}

	public static MedicineInRecipe getActiveInstance() {
		return activeInstance;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;// columnIndex >=4;
	}

	@Override
	public int getRowCount() {
		if (index != -1)
			return context.getRecipes().get(index).getQuantity().size();
		return context.getRecipeBeingCreated().getQuantity().size();
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
		LinkedHashMap<String, Integer> r ;
		if(index!=-1)
		 r = context.getRecipes().get(index).getQuantity();
		else 
			r = context.getRecipeBeingCreated().getQuantity();
		int i = 0;
		Medicine med = null;
		Integer value = 0;
		for (Entry<String, Integer> entry : r.entrySet()) {
			if (i != rowIndex) {
				i++;
				continue;
			}

			String key = entry.getKey();
			med = MedicineController.getInstance().getById(key);
			value = entry.getValue();
			break;
		}
		switch (columnIndex) {
		case 0:
			return med.getId();

		case 1:
			return med.getPrice();
		case 2:
			return value;

		case 3:
			return value * med.getPrice();

		}
		/*
		 * if (columnIndex < 4) return BazaIgraca.getInstance().getValueAt(rowIndex,
		 * columnIndex); else if (columnIndex == 4) { JButton btn = new JButton("" +
		 * rowIndex); return btn; } else if (columnIndex == 5) { return
		 * koJeOtkacen.get(rowIndex); }
		 */
		return null;
	}

}
