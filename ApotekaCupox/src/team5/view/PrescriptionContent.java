package team5.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import team5.Utils;
import team5.controller.RecipeController;
import team5.model.Context;
import team5.model.Medicine;
import team5.model.Prescription;
import team5.view.tables.TableFactory;

public class PrescriptionContent extends JDialog {
	private int index;
	private JLabel price;

	public PrescriptionContent(int index) {
		setModal(true);
		this.index = index;
		setSize(500, 500);
		JComponent up = new JScrollPane(TableFactory.getTable(Prescription.class, index));
		JPanel down = new JPanel();
		price = new JLabel(("Ukupna cena: " + RecipeController.getTotalPrice(index)));
		down.add(price);
		setLocationRelativeTo(null);

		if (index == -1) {
			// podaci o novom receptu

			ImageIcon icon = null;
			ImageIcon iconHover = null;
			try {
				icon = new ImageIcon(ImageIO.read(new File("./resources/icon/unesi recept.png")));
				iconHover = new ImageIcon(ImageIO.read(new File("./resources/icon/unesi recept selekt.png")));

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			JButton createRecipe = Utils.transparentButton(new JButton());
			createRecipe.addMouseListener(new IconChanger(icon, iconHover, createRecipe));
			JLabel jmbgLabel = new JLabel("JMBG pacijenta:");
			JTextField jmbgField = new JTextField();

			down = new JPanel(new GridLayout(3, 2, 15, 5));
			down.add(Box.createGlue());
			down.add(price);
			down.setBorder(new EmptyBorder(10, 10, 10, 10));
			down.add(jmbgLabel);
			down.add(jmbgField);

			try {

				icon = new ImageIcon(ImageIO.read(new File("./resources/icon/dodaj lek.png")));
				iconHover = new ImageIcon(ImageIO.read(new File("./resources/icon/dodaj lek selekt.png")));

			} catch (IOException e1) {
				e1.printStackTrace();
			}

			JButton addMedicineBtn = Utils.transparentButton(new JButton());
			addMedicineBtn.addMouseListener(new IconChanger(icon, iconHover, addMedicineBtn));
			addMedicineBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new AddMedicineToPrescription().setVisible(true);
					updateRecipePrice();
				}
			});

			down.add(addMedicineBtn);
			down.add(createRecipe);

			down.setBackground(new Color(255, 139, 104));

			createRecipe.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String error = "";

					String jmbg = jmbgField.getText().trim();

					if (jmbg.equals(""))
						error += "Jmbg pacijenta nije unet\r\n";

					else if (Context.getInstance().getRecipeBeingCreated().getQuantity().size() == 0)
						error += "Nije dodat nijedan lek\r\n";

					if (error.equals("")) {
						RecipeController.create(jmbg);
						setVisible(false);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, error, "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			});

		}
		add(Utils.createSplitPane(up, down, 1, index == -1 ? 350 : 400, 0));
	}

	public void updateRecipePrice() {
		price.setText("Ukupna cena:" + RecipeController.getTotalPrice(index));
	}
}
