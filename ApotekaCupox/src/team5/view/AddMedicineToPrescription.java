package team5.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import team5.Utils;
import team5.controller.MedicineController;
import team5.controller.PrescriptionController;
import team5.model.Medicine;

public class AddMedicineToPrescription extends JDialog {
	private ImageIcon iconHover, icon;

	public AddMedicineToPrescription() {
		Utils.setLogo(this);
		
		setSize(300, 140);
		setMinimumSize(new Dimension(300, 140));
		setLocationRelativeTo(null);
		setModal(true);

		try {
			icon = new ImageIcon(ImageIO.read(new File("./resources/icon/dodaj lek.png")));
			iconHover = new ImageIcon(ImageIO.read(new File("./resources/icon/dodaj lek selekt.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// setLayout(new GridBagLayout());
		JPanel panel = new JPanel();

		JLabel idlabel = new JLabel("Sifra leka:");
		List<Medicine> meds = MedicineController.getAll();
		JComboBox<Medicine> idField = new JComboBox(meds.toArray());

		JLabel priceLabel = new JLabel("Kolicina :");
		JTextField priceField = new JTextField();

		JButton insert = Utils.transparentButton(new JButton());
		panel = new JPanel(new GridLayout(3, 2, 15, 5));

		panel.add(idlabel);
		panel.add(idField);

		panel.add(priceLabel);
		panel.add(priceField);

		panel.add(new JLabel());
		panel.add(insert);

		panel.setBackground(new Color(255, 139, 104));

		insert.addMouseListener(new IconChanger(icon, iconHover, insert));
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String error = "";

				String id = ((Medicine) idField.getSelectedItem()).getId();
				String price = priceField.getText().trim();

				if (price.equals(""))
					error += "Cena leka nije uneta\r\n";
				int pricef = 0;
				try {
					pricef = Integer.parseInt(price);
					if (pricef <= 0) {
						error += "Kolicina mora biti pzitivna\r\n";
					}
				} catch (NumberFormatException ex) {
					error += "Kolicina nije u ispravnom formatu\r\n";
				}
				Medicine med = MedicineController.getById(id);
				if (med == null || med.isDeleted())
					error += "Pogresna sifra leka\r\n";

				if (error.equals("")) {
					PrescriptionController.addMedicine(id, pricef);

					setVisible(false);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, error, "Greska", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		add(panel);
	}

}
