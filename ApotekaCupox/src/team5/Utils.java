package team5;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Insets;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JSplitPane;
import javax.swing.RowFilter;

import team5.model.Context;
import team5.model.Prescription;
import team5.model.UserType;
import team5.view.ViewType;
import team5.view.tables.models.MedicineAbstractTableModel;
import team5.view.tables.models.PrescriptionAbstractTableModel;
import team5.view.tables.models.TellMeIfYouAreDeleted;

public class Utils {
	public static JButton transparentButton(JButton jb) {
		jb.setBackground(new Color(0, 0, 0, 0));
		jb.setBorder(null);
		jb.setMargin(new Insets(0, 0, 0, 0));
		jb.setContentAreaFilled(false);
		return jb;
	}

	public static JSplitPane createSplitPane(Component up, Component down, double weight, int location,
			int orientation) {
		JSplitPane sp = new JSplitPane(orientation, up, down);
		sp.setDividerLocation(location);
		sp.setResizeWeight(weight);
		sp.setEnabled(false);
		sp.setDividerSize(0);
		sp.setBorder(null);
		return sp;
	}

	public static ImageIcon getImageForTable(ViewType viewtype) {
		String path = "./resources/icon/logo.png";
		switch (viewtype) {
		case CART:
			// TODO ikonice

			break;
		case USERS:

			break;
		case PRESCRIPTION:

			break;
		case MEDICINE:

			break;
		default:
			break;
			//return null;
		}

		Image im;
		try {
			im = ImageIO.read(new File(path));
			return new ImageIcon(im.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static ImageIcon getImageicon(String path) {
		ImageIcon icon = null;
		try {
			Image im = ImageIO.read(new File(path));
			icon = new ImageIcon(im.getScaledInstance(96, 48, Image.SCALE_DEFAULT));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return icon;
	}

	public static RowFilter isDeletedFilter() {
		return new RowFilter<Object, Object>() {// kod sa stackoverflowa xD
			public boolean include(Entry<? extends Object, ? extends Object> entry) {
				if (entry.getModel() instanceof TellMeIfYouAreDeleted) {// ako je neka od tabela gde moze da se brise
					TellMeIfYouAreDeleted t = (TellMeIfYouAreDeleted) entry.getModel();
					// adminu se sve prikaze, ostalima samo ako nije izbriano
					return Context.getInstance().getLogged().getType() == UserType.ADMINISTRATOR
							|| !t.areYouDeleted((int) entry.getIdentifier());
				}
				return true;// u tabelema gde nema brisanja svima se sve prikaze
			}
		};
	}

	public static RowFilter stringFiltering(int columnId, String text) {
		return RowFilter.regexFilter("(?i)" + text, columnId);// (?i) radi case insensitive regex matching
	}

	public static void saveMeToFilePlease(Object what, String where) {
		File f = new File(where);
		ObjectOutputStream objOutStream = null;
		try {
			objOutStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
			objOutStream.writeObject(what);
		} catch (IOException e1) {
			// e1.printStackTrace();
		} finally {

			try {
				if (objOutStream != null)
					objOutStream.close();
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
	}

	public static Object loadMeFromFilePlease(String where) {
		File f = new File(where);
		ObjectInputStream objInStream = null;
		Object ret = null;
		try {
			objInStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			ret = objInStream.readObject();
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			try {
				if (objInStream != null)
					objInStream.close();
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		return ret;
	}

	// getIdentifier za default row filter vrati indeks reda
	public static RowFilter<Object, Object> minMaxFilter(int col, float min, float max) {
		return new RowFilter<Object, Object>() {// kod sa stackoverflowa xD
			public boolean include(Entry<? extends Object, ? extends Object> entry) {
				if (entry.getModel() instanceof MedicineAbstractTableModel) {// ovo filtriranej ide samo za lekove
					MedicineAbstractTableModel t = (MedicineAbstractTableModel) entry.getModel();
					float price = (float) t.getValueAt((int) entry.getIdentifier(), col);
					return price >= min && price <= max; // ako je u [min, max] true, u suprotnom false
				}
				return true;// do ovoga nece nikad doci jer ce se ovo koristiti samo za lekove XD ali po
							// defaultu se red vidi, skrzo nebitno xd
			}
		};

	}

	// getIdentifier za default row filter vrati indeks reda
	public static RowFilter<Object, Object> prescriptionContainsMedicineFilter(String medId) {
		return new RowFilter<Object, Object>() {// kod sa stackoverflowa xD
			public boolean include(Entry<? extends Object, ? extends Object> entry) {
				if (entry.getModel() instanceof PrescriptionAbstractTableModel) {// ovo filtriranej ide samo za recepte
					// MedicineAbstractTableModel t = (MedicineAbstractTableModel) entry.getModel();
					// ovde ne moye preko modela jer tabela ne prikayuje lekove dok se ne stinse
					// details
					Prescription p = Context.getInstance().getPrescriptions().get((int) entry.getIdentifier());
					for (java.util.Map.Entry<String, Integer> m : p.getQuantity().entrySet()) {
						if (m.getKey().equals(medId))
							return true;// ako ima ovaj lek na recpetu vrati true
					}
				}
				return false;// ako nema false
			}
		};

	}

	public static void setLogo(JDialog c) {
		try {
			Image icon = ImageIO.read(new File("./resources/icon/logo.png"));
			c.setIconImage(icon);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
