package team5.view;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import team5.Utils;
import team5.controller.actions.DisplayMedicineAction;
import team5.controller.actions.DisplayUsersAction;
import team5.controller.actions.LogoutAction;
import team5.model.Context;
import team5.model.User;

public class Toolbar extends JPanel{

	public Toolbar() {
		setBackground(new Color(255,139,104));
		User u=Context.getInstance().getLogged();
		Image logo=null;
		try {
			logo = ImageIO.read(new File("./resources/icon/logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon im= new ImageIcon(logo.getScaledInstance(96, 96, Image.SCALE_DEFAULT));
		
		add(new JLabel(im));
		
		switch(u.getType()) {
		case ADMINISTRATOR:
			add(Utils.transparentButton(new JButton(new DisplayUsersAction())));
			break;
		case LEKAR:
			break;
			
		case APOTEKAR:
			break;
		}
		add(Utils.transparentButton(new JButton(new DisplayMedicineAction())));
		add(Utils.transparentButton(new JButton(new LogoutAction())));
	}	
	
}
