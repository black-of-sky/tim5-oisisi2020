package team5.controller.actions;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import team5.controller.ReportsController;
import team5.controller.UserController;
import team5.model.Context;
import team5.view.AddMedicine;
import team5.view.MainView;
import team5.view.ReportInputView;

public class ShowSellerReportAction extends AbstractAction {
	public ShowSellerReportAction() {
		putValue(NAME, "");
		putValue(SHORT_DESCRIPTION, "Prikazi sve");

		try {
			Image im = ImageIO.read(new File("./resources/icon/ukupna prodaja apotekara.png"));
			putValue(LARGE_ICON_KEY, new ImageIcon(im.getScaledInstance(96, 48, Image.SCALE_DEFAULT)));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (UserController.getAllPharmacist().length == 0) {
			JOptionPane.showMessageDialog(null, "Nema apotekara", "Greska", JOptionPane.ERROR_MESSAGE);
			return;
		}
		ReportInputView rv = new ReportInputView(1);
		rv.setVisible(true);
		ReportsController.getBySeller(rv.getText());
		Context.getInstance().setReportFor(" o prodaji apotekara " + rv.getText());
		MainView.getActiveInstance().updateReportInfo();
	}
}
