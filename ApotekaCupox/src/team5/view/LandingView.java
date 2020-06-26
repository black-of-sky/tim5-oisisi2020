package team5.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.midi.ControllerEventListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import team5.controller.UserController;

public class LandingView extends JPanel {
	private Image background, icon;
	
	public LandingView(PanelType type) {
		try {
			File f=null;
			switch (type) {
			case LANDING:
				f=new File("./resources/img/landing.png");
				break;

			default:
				f=new File("./resources/img/loginView.png");;
			}
			
			background = ImageIO.read(f);
			icon = ImageIO.read(new File("./resources/icon/login.png"));

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
			c.gridheight=2;
			c.gridwidth=4;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 2;
			displayLoginForm(c);
		}
	}
	private void displayLoginForm(GridBagConstraints c) {

		JLabel usernameLabel = new JLabel("Korisnicko ime:");
		JTextField usernameField = new JTextField();
        
        JLabel passLabel= new JLabel("Lozinka :");
        JPasswordField passField = new JPasswordField();

 
        JButton login = new JButton("Uloguj se");
        JPanel panel = new JPanel(new GridLayout(3, 2,20,10));

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passField);
        JLabel error = new JLabel();
        error.setForeground(new Color(255,0,0));
        panel.add(error);
        panel.add(login);
        panel.setBackground(new Color(0,0,0,0));
		add(panel, c);

	}
	private void displayButton(GridBagConstraints c) {
		JButton jb = new JButton();
		jb.setBackground(new Color(0, 0, 0, 0));
		jb.setBorder(null);
		jb.setPreferredSize(new Dimension(144, 42));
		jb.setMargin(new Insets(0, 0, 0, 0));
		jb.setContentAreaFilled(false);
		jb.setIcon(new ImageIcon(icon));
		add(jb, c);
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserController.getInstance().loginButtonPressed();
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
