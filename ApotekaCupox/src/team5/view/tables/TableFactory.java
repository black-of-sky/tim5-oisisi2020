package team5.view.tables;

import javax.swing.JTable;

import team5.model.Medicine;
import team5.model.User;
import team5.view.tables.models.MedicineAbstractTableModel;
import team5.view.tables.models.UserAbstractTableModel;

public class TableFactory {
	private TableFactory() {
	}

	public static JTable getTable(Class<?> c) {
		JTable ret=new GenericTable();
		if (c == User.class) {
			ret.setModel(new UserAbstractTableModel());
		}
		else if (c == Medicine.class) {
			ret.setModel(new MedicineAbstractTableModel());
		}
		return ret;
	}

}
