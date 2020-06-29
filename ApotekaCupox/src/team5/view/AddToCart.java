package team5.view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import team5.Utils;
import team5.controller.CartController;
import team5.controller.MedicineController;
import team5.model.Medicine;

public class AddToCart extends JDialog {

	public AddToCart(int option) {// 1 lek, 0 recept
		setModal(true);
		setLocationRelativeTo(null);
		setSize(340, 125 + 50 * option);
		setLayout(new GridLayout(2 + option, 2, 15, 5));// ako je lek 3 reda, recept=>2
		ImageIcon im = null, hover = null;
		try {
			Image ima = ImageIO.read(new File("./resources/icon/dodaj.png"));
			im = new ImageIcon(ima.getScaledInstance(96, 48, Image.SCALE_DEFAULT));
			ima = ImageIO.read(new File("./resources/icon/dodaj selekt.png"));
			hover = new ImageIcon(ima.getScaledInstance(96, 48, Image.SCALE_DEFAULT));
		} catch (Exception e) {
		}
		if (option == 1) {
			JLabel medsLabel = new JLabel("Lek:");
			List<Medicine> meds = MedicineController.getAllNoPerscription();
			JComboBox<Medicine> medsBox = new JComboBox(meds.toArray());

			add(medsLabel);
			add(medsBox);

			JLabel quanLabel = new JLabel("Kolicina: ");
			JTextField quanField = new JTextField();
			add(quanLabel);
			add(quanField);
			JButton add = Utils.transparentButton(new JButton());

			add.addMouseListener(new IconChanger(im, hover, add));
			add.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String quan = quanField.getText();

					if (quan.equals("")) {
						JOptionPane.showMessageDialog(null, "Kolicina nije uneta", "Greska", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						int q = Integer.parseInt(quan);
						if (q <= 0) {
							JOptionPane.showMessageDialog(null, "Kolicina mora biti pozitivna", "Greska",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						CartController.addMedicine((Medicine) medsBox.getSelectedItem(), q);
						AddToCart.this.setVisible(false);

					} catch (NumberFormatException exc) {
						JOptionPane.showMessageDialog(null, "Neispravan format kolicine", "Greska",
								JOptionPane.ERROR_MESSAGE);

					}

				}
			});
			add(Box.createGlue());
			add(add);

		} else {
			JLabel keyLab = new JLabel("Sifra recepta");
			JTextField keyField = new JTextField();

			add(keyLab);
			add(keyField);
			JButton addB = Utils.transparentButton(new JButton());

			addB.addMouseListener(new IconChanger(im, hover, addB));
			add(Box.createGlue());
			add(addB);
			addB.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String key = keyField.getText();

					if (key.equals("")) {
						JOptionPane.showMessageDialog(null, "Sifra recepta nije uneta", "Greska",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (!CartController.addPrescription(key)) {
						JOptionPane.showMessageDialog(null, "Recept ne postoji", "Greska", JOptionPane.ERROR_MESSAGE);

					} else {
						setVisible(false);
					}
				}
			});

		}
	}

}
