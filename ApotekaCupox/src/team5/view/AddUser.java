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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import team5.Utils;
import team5.controller.UserController;
import team5.model.User;
import team5.model.UserType;

public class AddUser extends JDialog {
	private ImageIcon iconHover, icon;
	public AddUser() {
		super();
		setSize(500, 250);
		setMinimumSize(new Dimension(500,250));
		setLocationRelativeTo(null);
		setModal(true);
		
		try {
			icon = new ImageIcon(ImageIO.read(new File("./resources/icon/napravi nalog.png")));
			iconHover = new ImageIcon(ImageIO.read(new File("./resources/icon/napravi nalog selekt.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// setLayout(new GridBagLayout());
		JPanel panel = new JPanel();

		JLabel usernameLabel = new JLabel("Korisnicko ime:");
		JTextField usernameField = new JTextField();

		JLabel passwordLabel = new JLabel("Lozinka :");
		JPasswordField passwordField = new JPasswordField();

		JLabel passwordLabel2 = new JLabel("Ponovljena lozinka :");
		JPasswordField passwordField2 = new JPasswordField();

		JLabel nameLabel = new JLabel("Ime:");
		JTextField nameField = new JTextField();

		JLabel lNameLabel = new JLabel("Prezime :");
		JTextField lnameField = new JTextField();

		JLabel typeLabel = new JLabel("Tip:");
		JComboBox<String> typeBox = new JComboBox<String>(new String[] { "Lekar", "Administrator", "Apotekar" });

		JButton register = Utils.transparentButton(new JButton());
		panel = new JPanel(new GridLayout(7, 2, 15, 5));

		panel.add(usernameLabel);
		panel.add(usernameField);
		panel.add(passwordLabel);
		panel.add(passwordField);

		panel.add(passwordLabel2);
		panel.add(passwordField2);

		panel.add(nameLabel);
		panel.add(nameField);

		panel.add(lNameLabel);
		panel.add(lnameField);

		panel.add(typeLabel);
		panel.add(typeBox);

		panel.add(new JLabel());
		panel.add(register);

		panel.setBackground(new Color(255, 139, 104));
		/*
		 * usernameLabel.setBackground(new Color(255,139,104));
		 * passwordLabel.setBackground(new Color(255,139,104));
		 * nameLabel.setBackground(new Color(255,139,104)); lNameLabel.setBackground(new
		 * Color(255,139,104)); typeLabel.setBackground(new Color(255,139,104));
		 */
		register.addMouseListener(new IconChanger(icon, iconHover, register));
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String error = "";
				String username = usernameField.getText().trim();
				String password = passwordField.getText();
				String type = (String) typeBox.getSelectedItem();
				UserType userType = type.equals("Lekar") ? UserType.LEKAR
						: type.equals("Apotekar") ? UserType.APOTEKAR : UserType.ADMINISTRATOR;
				String lastName = lnameField.getText().trim();
				String firstName = nameField.getText().trim();
				if (username.equals(""))
					error += "Korisnicko ime nije uneto\r\n";
				if (password.equals(""))
					error += "Lozinka nije uneta\r\n";
				if (!password.equals(passwordField2.getText()))
					error += "Lozinke se ne poklapaju\r\n";
				if (firstName.equals(""))
					error += "Ime nije uneto\r\n";
				if (lastName.equals(""))
					error += "Prezime nije uneto\r\n";
				if (!UserController.checkUsername(username))
					error += "Korisnicko ime vec posotji\r\n";
				if (error.equals("")) {
					UserController.register(new User(username, password, firstName, lastName, userType));
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
