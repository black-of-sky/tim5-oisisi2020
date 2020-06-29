package team5.view.tables.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import team5.controller.RecipeController;
import team5.model.Context;
import team5.model.Prescription;
import team5.model.UserType;

public class PrescriptionAbstractTableModel extends AbstractTableModel implements TellMeIfYouAreDeleted{

	private static final long serialVersionUID = 2710362894062472488L;

	private static Context context = Context.getInstance();
	private List<String> kolone = new ArrayList<String>();

	private PrescriptionAbstractTableModel() {
		kolone.add("rec_id");
		kolone.add("rec_doc");
		kolone.add("rec_jmbg");
		kolone.add("rec_date");
		kolone.add("rec_price");
		if (Context.getInstance().getLogged().getType() == UserType.ADMINISTRATOR)
			kolone.add("removed");
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 5;// columnIndex >=4;
	}

	@Override
	public int getRowCount() {
		return context.getPrescription().size();
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
			return String.class;
		case 4:
			return Float.class;
		case 5:
			return Boolean.class;
		default:
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Prescription r = context.getPrescription().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return r.getId();

		case 1:
			return r.getDoctor();

		case 2:
			return r.getJmbg();
		case 3:
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");

			return f.format(r.getDate());

		case 4:
			return RecipeController.getTotalPrice(rowIndex);
		case 5:
			return r.isRemoved();

		}

		return null;
	}

	private static PrescriptionAbstractTableModel instance;

	public static PrescriptionAbstractTableModel getInstance() {
		if (instance == null)
			instance = new PrescriptionAbstractTableModel();
		return instance;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
		if (columnIndex != 5) {
			return;
		}
		context.getPrescription().get(rowIndex).setRemoved((boolean) aValue);
	}

	@Override
	public boolean areYouDeleted(int row) {
		return context.getPrescription().get(row).isRemoved();
	}

}
