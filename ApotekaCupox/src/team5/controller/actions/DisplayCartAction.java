package team5.controller.actions;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.KeyStroke;

import team5.Utils;
import team5.controller.Event;
import team5.view.MainFrame;
import team5.view.SortDialog;

public class DisplayCartAction extends AbstractAction {
	public DisplayCartAction() {
		putValue(NAME, "");
		// putValue(MNEMONIC_KEY, KeyEvent.VK_Q);
		putValue(SHORT_DESCRIPTION, "Korpa");

		try {
			Image im = ImageIO.read(new File("./resources/icon/korisnici.png"));
			putValue(LARGE_ICON_KEY, new ImageIcon(im.getScaledInstance(96, 96, Image.SCALE_DEFAULT)));
			// putValue(SMALL_ICON, new ImageIcon(im.getScaledInstance(96, 96,
			// Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	
		MainFrame.getInstance().processEvent(Event.SHOW_CART, null);

	}


}
