package team5.controller;

import java.util.stream.Collectors;

import team5.model.Context;
import team5.model.Medicine;
import team5.view.tables.models.MedicineAbstractTableModel;

public class MedicineController {
	private static MedicineController instance;

	private MedicineController() {

	}

	public static MedicineController getInstance() {
		if (instance == null)
			instance = new MedicineController();
		return instance;
	}

	public boolean checkId(String id) {
		for (Medicine med : Context.getInstance().getMedicine()) {
			if (med.getId().equals(id)) {
				return false;

			}
		}
		return true;
	}

	public void insert(Medicine med) {
		Context.getInstance().getMedicine().add(med);
		int row = Context.getInstance().getMedicine().size() - 1;
		MedicineAbstractTableModel.getInstance().fireTableRowsInserted(row, row);
	}

	public Medicine getById(String key) {
		return Context.getInstance().getMedicine().stream().filter(val -> val.getId().equals(key))
				.collect(Collectors.toList()).get(0);

	}

}
