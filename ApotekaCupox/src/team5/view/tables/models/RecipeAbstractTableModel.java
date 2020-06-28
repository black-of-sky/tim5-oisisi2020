package team5.view.tables.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import team5.controller.RecipeController;
import team5.model.Context;
import team5.model.Recipe;
import team5.model.UserType;

public class RecipeAbstractTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;

	private static Context context = Context.getInstance();
	private List<String> kolone = new ArrayList<String>();

	private RecipeAbstractTableModel() {
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
		return false;// columnIndex >=4;
	}

	@Override
	public int getRowCount() {
		return context.getRecipes().size();
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
		Recipe r = context.getRecipes().get(rowIndex);
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
			return RecipeController.getInstance().getTotalPrice(rowIndex);
		case 5:
			return r.isRemoved();

		}

		return null;
	}

	private static RecipeAbstractTableModel instance;

	public static RecipeAbstractTableModel getInstance() {
		if (instance == null)
			instance = new RecipeAbstractTableModel();
		return instance;
	}

}
