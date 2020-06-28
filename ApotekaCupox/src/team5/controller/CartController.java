package team5.controller;

import java.util.LinkedList;
import java.util.Map.Entry;

import team5.model.BillItem;
import team5.model.Context;
import team5.model.Medicine;
import team5.model.Prescription;
import team5.view.MainView;
import team5.view.tables.models.CartAbstactTableModel;

public class CartController {

	public static void addMedicine(Medicine med, int q, String recId) {// Dodaje lek u korpu
		BillItem b = checkIfAlreadyInCart(med.getId());// ako vec ima povecava kolicinu
		if (b != null) {
			b.setQuantity(b.getQuantity() + q);
			b.setTotalPrice(b.getQuantity() * med.getPrice());
			int r = Context.getInstance().getCurrentCart().indexOf(b);
			CartAbstactTableModel.getInstance().fireTableRowsUpdated(r, r);
			MainView.getActiveInstance().updateTotalPrice(CartController.getTotalPrice());
			return;
		}

		// ako ga nema pravi nov
		b = new BillItem(med.getId(), recId, Context.getInstance().getLogged().getUsername(), q, q * med.getPrice());
		Context.getInstance().getCurrentCart().add(b);
		int r = Context.getInstance().getCurrentCart().size() - 1;
		CartAbstactTableModel.getInstance().fireTableRowsInserted(r, r);
		MainView.getActiveInstance().updateTotalPrice(CartController.getTotalPrice());
	}

	private static float getTotalPrice() {
		float ret = 0;
		for (BillItem i : Context.getInstance().getCurrentCart())
			ret += i.getTotalPrice();
		return ret;
	}

	public static void addMedicine(Medicine med, int q) {// ako se dodaje lek, pozove se ogrnja f-ja sa "" za idRecepta
		addMedicine(med, q, "");
	}

	private static BillItem checkIfAlreadyInCart(String medId) {
		for (BillItem b : Context.getInstance().getCurrentCart()) {
			if (b.getMedicineId().equals(medId)) {

				return b;
			}
		}

		return null;
	}

	public static boolean addPrescription(String id) {
		for (Prescription p : Context.getInstance().getPrescription()) {
			if (p.isRemoved() || !id.equals(p.getId() + ""))// kastuje se int na string sa +""
				continue;
			for (Entry<String, Integer> pair : p.getQuantity().entrySet()) {
				Medicine med = MedicineController.getById(pair.getKey());
				if (med != null && !med.isDeleted()) {
					addMedicine(med, pair.getValue(), id);

				}
			}
			return true;
		}
		return false;
	}

	public static void removeAll() {
		Context.getInstance().setCurrentCart(new LinkedList<BillItem>());
		MainView.getActiveInstance().updateTotalPrice(CartController.getTotalPrice());
		CartAbstactTableModel.getInstance().fireTableDataChanged();

		
	}

}
