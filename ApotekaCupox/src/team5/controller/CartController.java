package team5.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import team5.Utils;
import team5.model.Bill;
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
			MainView.getActiveInstance().updateTotalPrice();
			return;
		}

		// ako ga nema pravi nov
		b = new BillItem(med.getId(), recId, q, q * med.getPrice());
		Context.getInstance().getCurrentCart().add(b);
		int r = Context.getInstance().getCurrentCart().size() - 1;
		CartAbstactTableModel.getInstance().fireTableRowsInserted(r, r);
		MainView.getActiveInstance().updateTotalPrice();
	}

	public static float getTotalPrice() {
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
		for (Prescription p : Context.getInstance().getPrescriptions()) {
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
		MainView.getActiveInstance().reset();
		CartAbstactTableModel.getInstance().fireTableDataChanged();

	}

	public static int calculateDiscount(String user) {
		if (user.equals(""))
			return 0;
		float spent = 0;
		for (Bill billy : Context.getInstance().getBills().stream().filter(b -> b.getBuyer().equals(user))
				.collect(Collectors.toList())) {
			// popusti idu ovako:
			// 5% ako se prijavio(samo mu apotekar uneo ime), 10% ako je OVOG MESECA
			// potrosio > 1000, 20% vise od 5k

			Date d = new Date();
			int year = d.getYear();
			int month = d.getMonth();
			if (billy.getDate().getYear() == year && billy.getDate().getMonth() == month) {
				spent += billy.getFee();
			}

		}
		return spent >= 5000 ? 25 : spent >= 1000 ? 10 : 5;
	}

	public static boolean createOrder() {

		List<BillItem> meds = Context.getInstance().getCurrentCart();
		if (meds.size() < 1)
			return false;
		Bill b = new Bill();
		String buyer = MainView.getActiveInstance().getBuyer();
		b.setBuyer(buyer);
		b.setDate(new Date());
		b.setDiscountPerc(buyer.equals("") ? 0 : calculateDiscount(buyer));
		b.setFee((float) (getTotalPrice() * (100.0 - b.getDiscountPerc()) / 100));
		b.setItems(meds);
		b.setSoldBy(Context.getInstance().getLogged().getUsername());
		Context.getInstance().getBills().add(b);
		Context.getInstance().setCurrentCart(new LinkedList<>());
		Utils.saveMeToFilePlease(Context.getInstance().getBills(), "./bills.data");

		return true;
	}

}
