package team5.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.html.parser.Entity;

import team5.model.Bill;
import team5.model.BillItem;
import team5.model.Context;
import team5.model.Medicine;
import team5.model.Prescription;
import team5.model.ReportItem;
import team5.model.User;
import team5.model.UserType;
import team5.view.tables.models.ReportAbstractTableModel;

public class ReportsController {
	private ReportsController() {

	}

	private static LinkedHashMap<String, ReportItem> getReport(String producer, String seller) {
		// producer = ko je prizveo lek, ako je null onda nam ttrebaju svi proizvodjaci
		// seller = ko je prodao, ako je null ona nije bitno ko je prodao
		LinkedHashMap<String, ReportItem> ret = new LinkedHashMap<String, ReportItem>();
		for (Bill b : Context.getInstance().getBills()) {// svi racuni
			if (seller != null && !b.getSoldBy().equals(seller)) // ako je bitan prodavac, i ovaj racun nije izdao
																	// onaj kog trazimo
				continue;// preskocimo racun

			for (BillItem bi : b.getItems()) {// svaka stavka sa racuna
				String medId = bi.getMedicineId();// koji je lek
				Medicine med = MedicineController.getById(medId);
				if (producer != null && !med.getProducer().equals(producer))// ako je bitan proizvodjac i ako nije onaj
																			// kog trazimo
					continue;// cepamo dalje
				float profit = (float) (bi.getTotalPrice() * (1.0 - b.getDiscountPerc() / 100f));// cena umanjena za
																									// popust
				if (ret.containsKey(medId)) {// ako je naisao na taj lek
					ReportItem rep = ret.get(medId);
					rep.setTotalPrice(rep.getTotalPrice() + profit);// saberi sa stavkom trenutnog racuna
					rep.setTotalPrice(rep.getQuantity() + bi.getQuantity());// i povecaj kolicinu
					// ne mora put, posto je vec u mapi
				} else {// ako ga nema
					// napravi novi
					// i dodaj u mapu
					// zarada, ime leka, kolicina
					ReportItem rep = new ReportItem(profit, med.getTitle(), bi.getQuantity());
					ret.put(medId, rep); // u suprotnom samo dodaj ovo sa trenutnog
				}
			}
		}
		Context.getInstance().setCurrentReport(ret);
		ReportAbstractTableModel.getInstance().fireTableDataChanged();
		return ret;
	}

	public static LinkedHashMap<String, ReportItem> getAll() {
		return getReport(null, null);// nije bitan ni proizvodjac ni prodavac
	}

	public static LinkedHashMap<String, ReportItem> getBySeller(String seller) {
		return getReport(null, seller);// bitan prodavac
	}

	public static LinkedHashMap<String, ReportItem> getByProducer(String prod) {
		return getReport(prod, null);// bitan prizvodjac
	}

	public static float getTotalProfit() {
		float f = 0;
		for (ReportItem r : Context.getInstance().getCurrentReport().values()) {
			f += r.getTotalPrice();
		}
		return f;
	}

	public static int getTotalQuantity() {
		int total = 0;
		for (ReportItem r : Context.getInstance().getCurrentReport().values()) {
			total += r.getQuantity();
		}

		return total;
	}

	public static Map<String, Integer> getStaticticForUser(User u) {
		Map<String, Integer> ret = new HashMap<String, Integer>();
		if (u.getType() == UserType.LEKAR) {
			for (Prescription p : Context.getInstance().getPrescriptions()) {
				if (p.getDoctor().equals(u.getUsername())) {
					for (Entry<String, Integer> a : p.getQuantity().entrySet()) {
						int q = a.getValue();
						if (ret.containsKey(a.getKey()))
							ret.put(a.getKey(), q + ret.get(a.getKey()));
						else
							ret.put(a.getKey(), q);
					}
				}
			}
		} else {
			for (Bill bill : Context.getInstance().getBills()) {
				if (bill.getSoldBy().equals(u.getUsername())) {
					for (BillItem a : bill.getItems()) {
						if (ret.containsKey(a.getMedicineId()))
							ret.put(a.getMedicineId(), a.getQuantity() + ret.get(a.getMedicineId()));
						else
							ret.put(a.getMedicineId(), a.getQuantity());
					}
				}
			}
		}

		return ret;
	}
}
