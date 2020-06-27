package team5.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import team5.Utils;
import team5.controller.MedicineController;
import team5.controller.UserController;
import team5.model.Medicine;
import team5.model.User;
import team5.model.UserType;

public class AddMedicine extends JDialog {
	private ImageIcon iconHover, icon;
	private static MedicineController medicineController = MedicineController.getInstance();

	public AddMedicine() {
		super();
		setSize(500, 250);
		setMinimumSize(new Dimension(500, 250));
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

		JLabel idlabel = new JLabel("Sifra:");
		JTextField idField = new JTextField();

		JLabel proLabel = new JLabel("Proizvodjac :");
		JTextField proField = new JTextField();

		JLabel priceLabel = new JLabel("Cena :");
		JTextField priceField = new JTextField();

		JLabel nameLabel = new JLabel("Ime:");
		JTextField nameField = new JTextField();

		JLabel recipelab = new JLabel("Na recept :");
		JCheckBox recipeBox = new JCheckBox();

		JButton insert = Utils.transparentButton(new JButton());
		panel = new JPanel(new GridLayout(6, 2, 15, 5));

		panel.add(idlabel);
		panel.add(idField);

		panel.add(nameLabel);
		panel.add(nameField);

		panel.add(proLabel);
		panel.add(proField);

		panel.add(recipelab);
		panel.add(recipeBox);

		panel.add(priceLabel);
		panel.add(priceField);

		panel.add(new JLabel());
		panel.add(insert);

		panel.setBackground(new Color(255, 139, 104));
		/*
		 * usernameLabel.setBackground(new Color(255,139,104));
		 * passwordLabel.setBackground(new Color(255,139,104));
		 * nameLabel.setBackground(new Color(255,139,104)); lNameLabel.setBackground(new
		 * Color(255,139,104)); typeLabel.setBackground(new Color(255,139,104));
		 */
		insert.addMouseListener(new IconChanger(icon, iconHover, insert));
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserController userController = UserController.getInstance();
				String error = "";
				String name = nameField.getText().trim();
				String id = idField.getText().trim();
				String price = priceField.getText().trim();
				String manuf = proField.getText().trim();

				boolean recipe = recipeBox.isSelected();

				if (name.equals(""))
					error += "Ime leka nije uneto\r\n";
				if (id.equals(""))
					error += "Sifra leka nije uneta\r\n";
				if (price.equals(""))
					error += "Cena leka nije uneta\r\n";
				Float pricef = null;
				try {
					pricef = Float.parseFloat(price);
				} catch (NumberFormatException ex) {
					error += "Cena nije u ispravnom formatu\r\n";
				}
				if (manuf.equals(""))
					error += "Proizvodjac leka nije unet\r\n";
				if (!medicineController.checkId(id))
					error += "Sifra leka vec postoji\r\n";
				if (error.equals("")) {
					medicineController.insert(new Medicine(id, name, manuf, recipe, pricef));
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
