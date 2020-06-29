package team5.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import team5.Utils;
import team5.model.Context;
import team5.model.Medicine;
import team5.model.Prescription;
import team5.model.User;
import team5.view.MainFrame;
import team5.view.tables.models.MedicineAbstractTableModel;
import team5.view.tables.models.MedicineInPrescriptionAbstactTableModel;
import team5.view.tables.models.PrescriptionAbstractTableModel;
import team5.view.tables.models.UserAbstractTableModel;

public class PrescriptionController {
	//private static RecipeController instance;

	private PrescriptionController() {

	}

/*	public static RecipeController getInstance() {
		if (instance == null)
			instance = new RecipeController();
		return instance;
	}*/

	public static void create(String jmbg) {
		Prescription rec = Context.getInstance().getRecipeBeingCreated();
		rec.setDate(new Date());
		rec.setDoctor(Context.getInstance().getLogged().getUsername());
		rec.setJmbg(jmbg);
		rec.setId(Context.getInstance().getPrescriptions().size());
		Context.getInstance().getPrescriptions().add(rec);
		int row = Context.getInstance().getPrescriptions().size() - 1;
		PrescriptionAbstractTableModel.getInstance().fireTableRowsInserted(row, row);
		Utils.saveMeToFilePlease(Context.getInstance().getPrescriptions(), "./prescriptions.data");
	}

	public static float getTotalPrice(int index) {
		float total = 0;
		Set<Entry<String, Integer>> set;
		if (index != -1)
			set = Context.getInstance().getPrescriptions().get(index).getQuantity().entrySet();
		else
			set = Context.getInstance().getRecipeBeingCreated().getQuantity().entrySet();

		for (Entry<String, Integer> entry : set) {
			Medicine med = MedicineController.getById(entry.getKey());
			if (!med.isDeleted())
				total += entry.getValue() * med.getPrice();
		}
		return total;
	}

	public static void addMedicine(String string, int i) {
		LinkedHashMap<String, Integer> map = Context.getInstance().getRecipeBeingCreated().getQuantity();
		if (map.containsKey(string))
			map.put(string, map.get(string) + i);
		else
			map.put(string, i);
		MedicineInPrescriptionAbstactTableModel.getActiveInstance().fireTableDataChanged();

	}

}
