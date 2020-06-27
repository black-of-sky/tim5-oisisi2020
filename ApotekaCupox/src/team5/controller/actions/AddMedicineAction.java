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

import team5.view.AddMedicine;

public class AddMedicineAction extends AbstractAction {
	public AddMedicineAction() {
		putValue(NAME, "");
		putValue(SHORT_DESCRIPTION, "Dodaj lek");
		
		try {
			Image im=ImageIO.read(new File("./resources/icon/dodaj.png"));
			putValue(LARGE_ICON_KEY, new ImageIcon(im.getScaledInstance(96, 48, Image.SCALE_DEFAULT)));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new AddMedicine().setVisible(true);
	}
}
