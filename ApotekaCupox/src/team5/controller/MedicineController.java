package team5.controller;

import java.util.List;
import java.util.stream.Collectors;

import team5.model.Context;
import team5.model.Medicine;
import team5.view.tables.models.MedicineAbstractTableModel;

public class MedicineController {
	//private static MedicineController instance;

	private MedicineController() {

	}

	/*public static MedicineController getInstance() {
		if (instance == null)
			instance = new MedicineController();
		return instance;
	}*/

	public static boolean checkId(String id) {
		for (Medicine med : Context.getInstance().getMedicine()) {
			if (med.getId().equals(id)) {
				return false;

			}
		}
		return true;
	}

	public static void insert(Medicine med) {
		Context.getInstance().getMedicine().add(med);
		int row = Context.getInstance().getMedicine().size() - 1;
		MedicineAbstractTableModel.getInstance().fireTableRowsInserted(row, row);
	}

	public static Medicine getById(String key) {
		List<Medicine> a = Context.getInstance().getMedicine().stream().filter(val -> val.getId().equals(key))
				.collect(Collectors.toList());
		return a.size() > 0 ? a.get(0) : null;

	}

	public static List<Medicine> getAllNoPerscription() {
		return Context.getInstance().getMedicine().stream().filter(m->!m.isDeleted()&&!m.isRecipe()).collect(Collectors.toList());
	}

}
