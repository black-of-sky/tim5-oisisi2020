package team5.view.tables.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import team5.model.Context;
import team5.model.Medicine;
import team5.model.UserType;

public class MedicineAbstractTableModel extends AbstractTableModel implements TellMeIfYouAreDeleted {

	private static final long serialVersionUID = 2710362894062472488L;

	private static Context context = Context.getInstance();
	private List<String> kolone = new ArrayList<String>();

	private MedicineAbstractTableModel() {
		kolone.add("med_id");
		kolone.add("med_title");
		kolone.add("med_producer");
		kolone.add("med_price");
		kolone.add("med_recipe");
		if (Context.getInstance().getLogged().getType() == UserType.ADMINISTRATOR)
			kolone.add("removed");
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 5;// columnIndex >=4;
	}

	@Override
	public int getRowCount() {
		return context.getMedicine().size();
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
		case 1:
		case 2:
			return String.class;
		case 3:
			return Float.class;
		case 4:
		case 5:

			return Boolean.class;
		default:
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Medicine m = context.getMedicine().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return m.getId();

		case 1:
			return m.getTitle();

		case 2:
			return m.getProducer();
		case 3:
			return m.getPrice();
		case 4:
			return m.isRecipe();
		case 5:
			return m.isDeleted();

		}
		/*
		 * if (columnIndex < 4) return BazaIgraca.getInstance().getValueAt(rowIndex,
		 * columnIndex); else if (columnIndex == 4) { JButton btn = new JButton("" +
		 * rowIndex); return btn; } else if (columnIndex == 5) { return
		 * koJeOtkacen.get(rowIndex); }
		 */
		return null;
	}
	/*
	 * @Override public void setValueAt(Object aValue, int rowIndex, int
	 * columnIndex) { super.setValueAt(aValue, rowIndex, columnIndex); if
	 * (columnIndex != 3) { return; }
	 * context.getUsers().get(rowIndex).setDeleted((boolean) aValue); }
	 */

	private static MedicineAbstractTableModel instance;

	public static MedicineAbstractTableModel getInstance() {
		if (instance == null)
			instance = new MedicineAbstractTableModel();
		return instance;
	}

	@Override
	public boolean areYouDeleted(int row) {
		return context.getMedicine().get(row).isDeleted();
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
		if (columnIndex != 5) {
			return;
		}
		context.getMedicine().get(rowIndex).setDeleted((boolean) aValue);
	}

}
