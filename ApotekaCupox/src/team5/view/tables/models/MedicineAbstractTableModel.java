package team5.view.tables.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import team5.model.Context;
import team5.model.Medicine;


public class MedicineAbstractTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;

	private static Context context=Context.getInstance();
	private List<String> kolone= new ArrayList<String>();
	
	public MedicineAbstractTableModel() {
		kolone.add("id");
		kolone.add("title");
		kolone.add("producer");
		kolone.add("price");
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;//columnIndex >=4;
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
		Medicine m=context.getMedicine().get(rowIndex);
		switch (columnIndex){
			case 0:
				return m.getId();
				
			case 1:
				return m.getTitle();
				
			case 2:
				return m.getProducer();
			case 3:
				return m.getPrice();

				
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
/*
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
		if (columnIndex != 3) {
			return;
		}
		context.getUsers().get(rowIndex).setDeleted((boolean) aValue);
	}
*/

}
