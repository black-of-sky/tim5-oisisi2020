package team5.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import team5.Utils;
import team5.controller.CartController;
import team5.controller.MedicineController;
import team5.controller.actions.AddMedicineAction;
import team5.controller.actions.AddPerscriptionAction;
import team5.controller.actions.AddUserAction;
import team5.controller.actions.ShowAdditionalInfoAction;
import team5.controller.actions.ShowAllReportsAction;
import team5.controller.actions.ShowProducerReportAction;
import team5.controller.actions.ShowSellerReportAction;
import team5.model.Context;
import team5.model.UserType;

public class Sidebar extends JPanel {
	private JTable table;

	public Sidebar(ViewType viewtype, JTable table) {
		this.table = table;
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.fill = GridBagConstraints.NONE;

		c.weighty = 1;
		c.weightx = 1;

		for (int i = 0; i < 8; ++i) {
			c.gridy = i;
			add(javax.swing.Box.createGlue(), c);
		}
		c.gridy = 8;

		c.anchor = GridBagConstraints.CENTER;
		switch (viewtype) {
		case USERS:
			JButton jb = Utils.transparentButton(new JButton(new AddUserAction()));
			ImageIcon hover = Utils.getImageicon("./resources/icon/registracija selekt.png");
			jb.addMouseListener(new IconChanger((ImageIcon) jb.getIcon(), hover, jb));
			add(jb, c);
			JButton jbShowMOre = Utils.transparentButton(new JButton(new ShowAdditionalInfoAction(table)));
			hover = Utils.getImageicon("./resources/icon/detalji selekt.png");
			jbShowMOre.addMouseListener(new IconChanger((ImageIcon) jbShowMOre.getIcon(), hover, jbShowMOre));
			c.gridy--;
			add(jbShowMOre, c);
			break;
		case MEDICINE:
			JButton jb2 = Utils.transparentButton(new JButton(new AddMedicineAction(false, table)));
			ImageIcon hover2 = Utils.getImageicon("./resources/icon/dodaj selekt.png");

			jb2.addMouseListener(new IconChanger((ImageIcon) jb2.getIcon(), hover2, jb2));
			add(jb2, c);
			c.gridy--;
			if (Context.getInstance().getLogged().getType() != UserType.LEKAR) {
				JButton editButton = Utils.transparentButton(new JButton(new AddMedicineAction(true, table)));
				ImageIcon hoverEdit = Utils.getImageicon("./resources/icon/izmeni lek selekt.png");

				editButton.addMouseListener(new IconChanger((ImageIcon) editButton.getIcon(), hoverEdit, editButton));
				add(editButton, c);

			}

			if (Context.getInstance().getLogged().getType() == UserType.APOTEKAR) {
				JButton addToCart = new JButton("dodaj ga u korpu");// Utils.transparentButton(new JButton("dodaj ga u
																	// korpu"));
				addToCart.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int selected = table.getSelectedRow();
						if (selected == -1) {
							JOptionPane.showMessageDialog(null, "Nijedan lek nije selektovan");
							return;
						}
						if (Context.getInstance().getMedicine().get(selected).isRecipe()) {
							JOptionPane.showMessageDialog(null, "Lek se moze izdavati samo na recept");
							return;
						}
						new AddToCart(1, selected).setVisible(true);
						// 1=lek, selected= koji se dodaji
					}
				});
				ImageIcon hoverEdit = Utils.getImageicon("./resources/icon/izmeni lek selekt.png");

				// addToCart.addMouseListener(new IconChanger((ImageIcon) addToCart.getIcon(),
				// hoverEdit, addToCart));
				c.gridy--;
				add(addToCart, c);
			}

			break;
		case PRESCRIPTION:

			JButton jb3 = Utils.transparentButton(new JButton(Utils.getImageicon("./resources/icon/detalji.png")));
			jb3.addMouseListener(new IconChanger((ImageIcon) jb3.getIcon(),
					Utils.getImageicon("./resources/icon/detalji selekt.png"), jb3));
			jb3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(null, "Nijedan recept nije selektovan");
						return;
					}
					new PrescriptionContent(row).setVisible(true);

				}
			});

			add(jb3, c);

			jb3 = Utils.transparentButton(new JButton(new AddPerscriptionAction()));
			ImageIcon hoverr = Utils.getImageicon("./resources/icon/dodaj selekt.png");

			jb3.addMouseListener(new IconChanger((ImageIcon) jb3.getIcon(), hoverr, jb3));

			c.gridy = 7;
			if (Context.getInstance().getLogged().getType() == UserType.LEKAR)
				add(jb3, c);
			break;
		case CART:
			JButton addMed = Utils.transparentButton(new JButton(Utils.getImageicon("./resources/icon/dodaj lek.png")));
			addMed.addMouseListener(new IconChanger((ImageIcon) addMed.getIcon(),
					Utils.getImageicon("./resources/icon/dodaj lek selekt.png"), addMed));
			addMed.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (MedicineController.getAllNoPerscription().size() == 0) {
						JOptionPane.showMessageDialog(null, "Nema lekova koji se mogu dodati", "Greska",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					new AddToCart(1, -1).setVisible(true);
				}
			});
			add(addMed, c);

			jb3 = new JButton("add recept");
			jb3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					new AddToCart(0, -1).setVisible(true);
				}
			});
			c.gridy = 6;
			add(jb3, c);
			JButton delte = new JButton("izbrisi sve");
			delte.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					CartController.removeAll();
				}
			});
			c.gridy = 5;
			add(delte, c);
			jb3 = new JButton("gotovo");
			jb3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (!CartController.createOrder()) {
						JOptionPane.showMessageDialog(null, "Nijedan lek nije dodat:", "Greska",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					delte.getActionListeners()[0].actionPerformed(null);

				}
			});
			c.gridy = 4;
			add(jb3, c);
			break;
		case REPORTS:
			JButton all = Utils.transparentButton(new JButton(new ShowAllReportsAction()));// full report
			ImageIcon hoverall = Utils.getImageicon("./resources/icon/IZVESTAJ ZA SVE LEKOVE SELEKT.png");
			all.addMouseListener(new IconChanger((ImageIcon) all.getIcon(), hoverall, all));
			add(all, c);

			JButton prod = Utils.transparentButton(new JButton(new ShowProducerReportAction()));// producer report
			ImageIcon hoverProducer = Utils.getImageicon("./resources/icon/ukupna prodaja proizvodjaca selekt.png");
			prod.addMouseListener(new IconChanger((ImageIcon) prod.getIcon(), hoverProducer, prod));
			c.gridy--;
			add(prod, c);

			JButton sell = Utils.transparentButton(new JButton(new ShowSellerReportAction()));// seller report
			ImageIcon sellhov = Utils.getImageicon("./resources/icon/ukupna prodaja apotekara selekt.png");
			sell.addMouseListener(new IconChanger((ImageIcon) sell.getIcon(), sellhov, sell));
			c.gridy--;
			add(sell, c);

			break;

		}
	}
}
