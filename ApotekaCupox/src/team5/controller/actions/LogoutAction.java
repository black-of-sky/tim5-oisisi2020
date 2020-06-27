package team5.controller.actions;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import team5.controller.UserController;

public class LogoutAction extends AbstractAction {
	public LogoutAction() {
		putValue(NAME, "");
		//putValue(MNEMONIC_KEY, KeyEvent.VK_Q);
		putValue(SHORT_DESCRIPTION, "Odjavi se");
		
		try {
			Image im=ImageIO.read(new File("./resources/icon/odjava.png"));
			
			putValue(SMALL_ICON, new ImageIcon(im.getScaledInstance(96, 96, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		UserController.getInstance().logout();
	}
}
