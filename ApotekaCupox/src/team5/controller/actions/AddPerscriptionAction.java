package team5.controller.actions;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import team5.model.Context;
import team5.view.PrescriptionContent;

public class AddPerscriptionAction extends AbstractAction{

	public AddPerscriptionAction() {
		putValue(NAME, "");
		putValue(SHORT_DESCRIPTION, "Dodaj recpet");

		try {
			Image im = ImageIO.read(new File("./resources/icon/dodaj.png"));
			putValue(LARGE_ICON_KEY, new ImageIcon(im.getScaledInstance(96, 48, Image.SCALE_DEFAULT)));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(Context.getInstance().getMedicine().stream().filter(med->!med.isDeleted()).collect(Collectors.toList()).size()==0) {
			JOptionPane.showMessageDialog(null, "Nema lekova", "Greska", JOptionPane.ERROR_MESSAGE);
			return;
		}
		new PrescriptionContent(-1).setVisible(true);
	}
}
