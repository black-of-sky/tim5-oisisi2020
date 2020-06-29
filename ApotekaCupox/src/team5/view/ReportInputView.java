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
import javax.swing.JTextField;

import team5.Utils;
import team5.controller.MedicineController;
import team5.controller.UserController;
import team5.model.Medicine;

public class ReportInputView extends JDialog {
	private JComboBox<String> box = null;// combo box za ime prodavca ili proizvodjaca

	public ReportInputView(int option) {// 0=proizvodjac 1=apotekar
		super();
		setSize(500, 250);
		setMinimumSize(new Dimension(500, 250));
		setLocationRelativeTo(null);
		setModal(true);

		ImageIcon icon = null;
		ImageIcon iconHover = null;
		try {
			icon = new ImageIcon(ImageIO.read(new File("./resources/icon/generisi.png")));
			iconHover = new ImageIcon(ImageIO.read(new File("./resources/icon/generisi selekt.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// setLayout(new GridBagLayout());
		JPanel panel = new JPanel();

		JLabel label = new JLabel(option == 0 ? "Proizvodjac" : "Apotekar");
		if (option == 0)
			box = new JComboBox<>(MedicineController.getAllProducers());
		else
			box = new JComboBox<>(UserController.getAllPharmacist());

		JButton insert = Utils.transparentButton(new JButton());
		panel = new JPanel(new GridLayout(2, 2, 15, 5));

		panel.add(label);
		panel.add(box);

		panel.add(new JLabel());
		panel.add(insert);

		panel.setBackground(new Color(255, 139, 104));

		insert.addMouseListener(new IconChanger(icon, iconHover, insert));
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();

			}
		});

		add(panel);
	}

	public String getText() {
		return (String) box.getSelectedItem();

	}
}
