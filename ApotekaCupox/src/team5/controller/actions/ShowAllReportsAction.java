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

import team5.controller.ReportsController;
import team5.model.Context;
import team5.view.AddMedicine;
import team5.view.MainView;

public class ShowAllReportsAction extends AbstractAction {
	public ShowAllReportsAction() {
		putValue(NAME, "");
		putValue(SHORT_DESCRIPTION, "Prikazi sve");

		try {
			Image im = ImageIO.read(new File("./resources/icon/IZVESTAJ ZA SVE LEKOVE.png"));
			putValue(LARGE_ICON_KEY, new ImageIcon(im.getScaledInstance(96, 48, Image.SCALE_DEFAULT)));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ReportsController.getAll();
		Context.getInstance().setReportFor(" o ukupnoj prodaji svih lekova");
		MainView.getActiveInstance().updateReportInfo();
	}
}
