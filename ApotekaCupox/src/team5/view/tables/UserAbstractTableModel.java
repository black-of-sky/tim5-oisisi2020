package team5.view.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import team5.model.Context;
import team5.model.User;


public class UserAbstractTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;

	public static String kolonaDugme = "DUGME";
	public static String kolonaCheck = "CHECK";
	private static Context context=Context.getInstance();
	private List<String> kolone= new ArrayList<String>();
	
	public UserAbstractTableModel() {
		kolone.add("Korisnicko ime");
		kolone.add("Ime");
		kolone.add("Prezime");
		kolone.add("Tipn");
		kolone.add("Izbrisan");
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex >=4;
	}

	@Override
	public int getRowCount() {
		return context.getUsers().size();
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
		case 3:
			return String.class;
		case 4:
			return Boolean.class;
		default:
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user=context.getUsers().get(rowIndex);
		switch (columnIndex){
			case 0:
				return user.getUsername();
				
			case 1:
				return user.getfName();
				
			case 2:
				return user.getlName();
			case 3:
				return user.getType().toString();
			case 4:
				return user.isDeleted();
				
		}
		/*if (columnIndex < 4)
			return BazaIgraca.getInstance().getValueAt(rowIndex, columnIndex);
		else if (columnIndex == 4) {
			JButton btn = new JButton("" + rowIndex);
			return btn;
		} else if (columnIndex == 5) {
			return koJeOtkacen.get(rowIndex);
		}*/
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
		if (columnIndex != 3) {
			return;
		}
		context.getUsers().get(rowIndex).setDeleted((boolean) aValue);
	}


}
