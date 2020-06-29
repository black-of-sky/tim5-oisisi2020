package team5.view.tables;

import java.awt.Component;
import java.util.Map;

import javax.swing.JTable;

import team5.model.Bill;
import team5.model.Medicine;
import team5.model.Prescription;
import team5.model.ReportItem;
import team5.model.User;
import team5.view.tables.models.CartAbstactTableModel;
import team5.view.tables.models.MedicineAbstractTableModel;
import team5.view.tables.models.MedicineInPrescriptionAbstactTableModel;
import team5.view.tables.models.PrescriptionAbstractTableModel;
import team5.view.tables.models.ReportAbstractTableModel;
import team5.view.tables.models.SoldOrPrescriptedMedicineAbsractTableModel;
import team5.view.tables.models.UserAbstractTableModel;

public class TableFactory {
	private TableFactory() {
	}

	public static JTable getTable(Class<?> c) {
		JTable ret = new GenericTable();
		if (c == User.class) {
			ret.setModel(UserAbstractTableModel.getInstance());
		} else if (c == Medicine.class) {
			ret.setModel(MedicineAbstractTableModel.getInstance());
		} else if (c == Prescription.class) {
			ret.setModel(PrescriptionAbstractTableModel.getInstance());
		} else if (c == Bill.class) {
			ret.setModel(CartAbstactTableModel.getInstance());
		} else if (c == ReportItem.class)
			ret.setModel(ReportAbstractTableModel.getInstance());
		return ret;
	}

	public static JTable getTable(Class<?> c, int index) {
		JTable ret = new GenericTable();

		if (c == Prescription.class) {
			ret.setModel(new MedicineInPrescriptionAbstactTableModel(index));

		}
		return ret;
	}

	public static JTable getUserDetailsTable(Map<String, Integer> data) {
		JTable ret = new GenericTable();
		ret.setModel(new SoldOrPrescriptedMedicineAbsractTableModel(data));
		return ret;
	}

}
