package team5.controller;

import java.util.List;
import java.util.stream.Collectors;

import team5.model.Context;
import team5.model.Medicine;
import team5.view.tables.models.MedicineAbstractTableModel;

public class MedicineController {
	// private static MedicineController instance;

	private MedicineController() {

	}

	/*
	 * public static MedicineController getInstance() { if (instance == null)
	 * instance = new MedicineController(); return instance; }
	 */

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
		return Context.getInstance().getMedicine().stream().filter(m -> !m.isDeleted() && !m.isRecipe())
				.collect(Collectors.toList());
	}

	public static List<Medicine> getAll() {
		return Context.getInstance().getMedicine().stream().filter(m -> !m.isDeleted()).collect(Collectors.toList());
	}

	public static String[] getAllProducers() {
		return Context.getInstance().getMedicine().stream().map(med -> med.getProducer()).distinct()
				.collect(Collectors.toList()).toArray(new String[0]);
	}

	public static void edit(Medicine medicine) {
		Medicine m = Context.getInstance().getMedicine().stream().filter(med -> med.getId().equals(medicine.getId()))
				.collect(Collectors.toList()).get(0);
		m.setPrice(medicine.getPrice());
		m.setProducer(medicine.getProducer());
		m.setRecipe(medicine.isRecipe());
		m.setTitle(medicine.getTitle());
		int row = Context.getInstance().getMedicine().indexOf(m);
		MedicineAbstractTableModel.getInstance().fireTableRowsUpdated(row, row);

	}

}
