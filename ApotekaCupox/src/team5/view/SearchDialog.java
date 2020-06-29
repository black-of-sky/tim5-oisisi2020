package team5.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import team5.model.Context;
import team5.model.Medicine;

public class SearchDialog extends JDialog {
	private boolean closed = true; // da li je zatvoren ovaj prozor tako sto je sisnuto X, ako jeste onda je
									// odustao od pretrage, pretpostavimo da jeste, ako klikne na pretrazi promenim
									// ovo
	private int selectedOption;// indeks opcije kioja je selektovana u combo boxu
	private String option;// ime te opcije
	private float min, max;// ako se trazila cena onda ovo postavimo
	private String searchTerm;// u suprotnom ovo

	public SearchDialog(String[] data) {
		super();
		setSize(300, 140);
		setLocationRelativeTo(null);
		setModal(true);
		//setResizable(false);

		ImageIcon icon = null;
		ImageIcon iconHover = null;
		try {
			icon = new ImageIcon(ImageIO.read(new File("./resources/icon/dodaj lek.png")));
			iconHover = new ImageIcon(ImageIO.read(new File("./resources/icon/dodaj lek selekt.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JPanel panel = new JPanel();

		JLabel atrLabel = new JLabel("Atribut:");
		JComboBox<String> atrBox = new JComboBox(data);

		JLabel stringlab = new JLabel("Vrednost :");
		JTextField stringVal = new JTextField();

		JLabel lekLab = new JLabel("Lek :");
		List<Medicine> meds = MedicineController.getAll();
		JComboBox lekBox = new JComboBox(meds.toArray());

		JButton insert = Utils.transparentButton(new JButton());

		JLabel minLab = new JLabel("Od :");
		JTextField minField = new JTextField();
		JLabel maxLab = new JLabel("Do :");
		JTextField maxField = new JTextField();

		atrBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.add(atrLabel);// ovo se dodaju u ova slucaja
				panel.add(atrBox);
				if (atrBox.getSelectedItem().equals("Lek")) {

					panel.add(lekLab);
					panel.add(lekBox);
					setSize(300, 150);
					if (meds.size() == 0) {
						JOptionPane.showMessageDialog(null, "Nema lekova", "Greska", JOptionPane.ERROR_MESSAGE);
						atrBox.setSelectedIndex(0);// prebacimo sa lekova na nesto drugo
						actionPerformed(e);//i pozovemo opet ovu metodu da iscrta nesto drugo xD
						return;
					}

				} else if (atrBox.getSelectedItem().equals("Cena")) {
					setSize(300, 200);

					panel.setLayout(new GridLayout(4, 2, 15, 5));
					panel.add(minLab);
					panel.add(minField);
					panel.add(maxLab);
					panel.add(maxField);
				} else {

					setSize(300, 150);

					panel.setLayout(new GridLayout(3, 2, 15, 5));
					panel.add(stringlab);
					panel.add(stringVal);

				}

				panel.add(new JLabel());// i ovo uvek ide
				panel.add(insert);
				panel.revalidate();
				panel.repaint();
			}
		});

		panel.setBackground(new Color(255, 139, 104));

		insert.addMouseListener(new IconChanger(icon, iconHover, insert));
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (atrBox.getSelectedItem().equals("Cena")) {
					String error = "";

					String minVal = minField.getText().trim();
					String maxVal = maxField.getText().trim();

					float priceMin = Float.MIN_VALUE, priceMax = Float.MAX_VALUE; // minimalna i max vrdnost za float
																					// tip
					if (minVal.equals("") && maxVal.equals(""))
						error += "Mora se uneti bar jedna vrednost\r\n";
					if (!minVal.equals("")) {
						try {
							priceMin = Float.parseFloat(minVal);
							if (priceMin <= 0) {
								error += "Minimalna vrednost mora biti pozitivna\r\n";
							}
						} catch (NumberFormatException ex) {
							error += "Minimalna vrednost nije u ispravnom formatu\r\n";
						}
					}
					if (!maxVal.equals("")) {
						try {
							priceMax = Float.parseFloat(maxVal);
							if (priceMax <= 0) {
								error += "Maksimalna vrednost mora biti pozitivna\r\n";
							}
						} catch (NumberFormatException ex) {
							error += "Maksimalna vrednost nije u ispravnom formatu\r\n";
						}
					}
					if (priceMin > priceMax)
						error += "Minimalna cena ne moze biti veca od maksimalne\r\n";
					if (!error.equals("")) {

						JOptionPane.showMessageDialog(null, error, "Greska", JOptionPane.ERROR_MESSAGE);
						return;
					}
					min = priceMin;/// ako je sve ok postavimo vrenosti
					max = priceMax;
				} else if (atrBox.getSelectedItem().equals("Lek")) {
					searchTerm = meds.get(lekBox.getSelectedIndex()).getId();// sifar leka za koji se traze recepti
				} else// ako trazi text neki onda ovo postavljamo
					searchTerm = stringVal.getText().trim();
				
				selectedOption=atrBox.getSelectedIndex();
				closed = false;// menjamo ovo
				setVisible(false);// iskljic window

			}
		});

		add(panel);
		atrBox.getActionListeners()[0].actionPerformed(null);// like a boss xD
	}

	// geteri za polja
	public boolean isClosed() {
		return closed;
	}

	public int getSelectedOption() {
		return selectedOption;
	}

	public String getOption() {
		return option;
	}

	public float getMin() {
		return min;
	}

	public float getMax() {
		return max;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

}
