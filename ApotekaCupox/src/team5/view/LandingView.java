package team5.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import team5.Utils;
import team5.controller.UserController;

public class LandingView extends JPanel {
	private Image background;
	private ImageIcon icon, iconHover;

	public LandingView(PanelType type) {
		try {
			File f = null;
			switch (type) {
			case LANDING:
				f = new File("./resources/img/landing.png");
				icon = new ImageIcon(ImageIO.read(new File("./resources/icon/login.png")));
				iconHover = new ImageIcon(ImageIO.read(new File("./resources/icon/login_hover.png")));
				break;

			default:
				f = new File("./resources/img/loginView.png");
				icon = new ImageIcon(ImageIO.read(new File("./resources/icon/login2.png")));
				iconHover = new ImageIcon(ImageIO.read(new File("./resources/icon/login2_hover.png")));
			}

			background = ImageIO.read(f);

		} catch (IOException e) {
			e.printStackTrace();
		}
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1;
		c.weightx = 1;
		c.anchor = GridBagConstraints.CENTER;

		for (int i = 0; i < 20; i++) {
			c.gridx++;
			c.gridy++;
			add(javax.swing.Box.createGlue(), c);
		}

		// add(new JButton("LET ME INNNN"),c);
		if (type == PanelType.LANDING) {
			c.gridy = 16;
			c.fill = GridBagConstraints.NONE;
			c.gridx = 7;
			displayButton(c);
		} else {
			c.gridy = 10;
			c.gridheight = 2;
			c.gridwidth = 4;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 2;
			displayLoginForm(c);
		}
	}

	private void displayLoginForm(GridBagConstraints c) {

		JLabel usernameLabel = new JLabel("Korisnicko ime:");
		JTextField usernameField = new JTextField();
		usernameField.setPreferredSize(new Dimension(150, 30));
		JLabel passLabel = new JLabel("Lozinka :");
		JPasswordField passField = new JPasswordField();
		passField.setPreferredSize(new Dimension(150, 30));

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.anchor = GridBagConstraints.BASELINE_LEADING;
		c2.gridy = 0;
		c2.weighty = 1;
		c2.weightx = 1;
		// c2.anchor = GridBagConstraints.CENTER;

		panel.add(usernameLabel, c2);
		c2.gridx = 1;

		// TODO Srediti boje i font
		panel.add(usernameField, c2);
		passLabel.setFont(new Font("arial", Font.BOLD, 25));
		usernameLabel.setFont(new Font("arial", Font.BOLD, 25));
		usernameLabel.setForeground(new Color(118, 190, 189));
		passLabel.setForeground(new Color(118, 190, 189));
		c2.gridx = 0;
		c2.gridy = 1;
		// c.fill = GridBagConstraints.BOTH;
		panel.add(passLabel, c2);
		c2.gridx = 1;
		c2.gridy = 1;

		// c.fill = GridBagConstraints.BOTH;
		panel.add(passField, c2);
		JLabel error = new JLabel("");
		error.setForeground(new Color(255, 0, 0));
		error.setFont(new Font("arial", Font.BOLD, 18));
		error.setBackground(new Color(0, 0, 0, 0));

		c2.gridx = 0;

		c2.gridy = 2;
		c2.anchor = GridBagConstraints.WEST;
		panel.add(error, c2);

		JButton jb = new JButton();
		jb.setBorder(null);

		jb.setMargin(new Insets(0, 0, 0, 0));
		jb.setBackground(new Color(255, 210, 181));// iz nekog razloga ne radi lepo kad je providno, tako da stavljam
													// boju pozzdine
		
		c2.anchor = GridBagConstraints.BASELINE_LEADING;
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!UserController.login(usernameField.getText().trim(), passField.getText())) {
					error.setText("Pogresni kredencijali");
					MainFrame.getInstance().repaint();
				}
			}
		});

		jb.setIcon(icon);
		jb.addMouseListener(new IconChanger(icon, iconHover, jb));
		c2.gridx = 1;
		c2.gridy = 2;
		panel.add(jb, c2);
		panel.setBackground(new Color(0, 0, 0, 0));
		add(panel, c);

	}

	private void displayButton(GridBagConstraints c) {
		JButton jb = new JButton();
		Utils.transparentButton(jb);
		jb.setPreferredSize(new Dimension(144, 42));
		
		jb.setIcon(icon);
		add(jb, c);
		jb.addMouseListener(new IconChanger(icon, iconHover, jb));
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserController.loginButtonPressed();

			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Dimension d = getSize();

		g.drawImage(background.getScaledInstance(d.width, d.height, Image.SCALE_FAST), 0, 0, null);
	}

}

enum PanelType {
	LOGIN, LANDING
}

