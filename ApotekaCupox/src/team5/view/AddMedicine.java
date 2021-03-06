package team5.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
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
import team5.model.Context;
import team5.model.Medicine;
import team5.model.User;
import team5.model.UserType;

public class AddMedicine extends JDialog {
	private ImageIcon iconHover, icon;
	private Medicine med;

	public AddMedicine(int selected) {
		super();
		Utils.setLogo(this);
		setSize(500, 250);
		setMinimumSize(new Dimension(500, 250));
		setLocationRelativeTo(null);
		setModal(true);

		try {
			String path = selected != -1 ? "izmeni lek" : "dodaj lek";
			icon = new ImageIcon(ImageIO.read(new File("./resources/icon/" + path + ".png")));
			iconHover = new ImageIcon(ImageIO.read(new File("./resources/icon/" + path + " selekt.png")));
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
		if (selected != -1) {// ako je edit, onda popunimo siftu i zakljucamo
			// i ostala polja popunimo ali ih ostavimo slobodnim za izmenu
			med = Context.getInstance().getMedicine().get(selected);
			idField.setText(med.getId());
			idField.setEnabled(false);
			proField.setText(med.getProducer());
			priceField.setText("" + med.getPrice());// cast na string
			nameField.setText(med.getTitle());
			recipeBox.setSelected(med.isRecipe());
		}

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

				String error = "";
				String name = nameField.getText().trim();
				String id = idField.getText().trim();
				String price = priceField.getText().trim();
				String manuf = proField.getText().trim();

				boolean recipe = recipeBox.isSelected();
				Medicine med = AddMedicine.this.med;
				if (name.equals(""))
					if (selected == -1)// ako je seleced>=0 onda editujemo pa moze prazan jer ostaje prethodna vrenost
						error += "Ime leka nije uneto\r\n";
					else// edit mod=> ostaje staro ime ako je sada uneto prazno
						name = med.getTitle();

				if (id.equals(""))
					error += "Sifra leka nije uneta\r\n";
				if (price.equals(""))
					if (selected == -1)// ako je seleced>=0 onda editujemo pa moze prazan jer ostaje prethodna
										// vrenost
						error += "Cena leka nije uneta\r\n";
					else// edit mod=> ostaje staro ime ako je sada uneto prazno
						price = med.getPrice() + "";
				Float pricef = null;
				try {
					pricef = Float.parseFloat(price);
					if (pricef <= 0)
						error += "Cena mora biti pozitivna\r\n";
				} catch (NumberFormatException ex) {
					error += "Cena nije u ispravnom formatu\r\n";
				}
				if (manuf.equals(""))
					if (selected == -1)// ako je seleced>=0 onda editujemo pa moze prazan jer ostaje prethodna
										// vrenost
						error += "Proizvodjac leka nije unet\r\n";
					else// edit mod=> ostaje staro ime ako je sada uneto prazno
						manuf = med.getProducer();

				if (selected == -1 && !MedicineController.checkId(id))
					error += "Sifra leka vec postoji\r\n";
				if (error.equals("")) {
					if (selected == -1)
						MedicineController.insert(new Medicine(id, name, manuf, recipe, pricef));
					else
						MedicineController.edit(new Medicine(id, name, manuf, recipe, pricef));
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
