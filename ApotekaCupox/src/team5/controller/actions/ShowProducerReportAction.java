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

import team5.controller.ReportsController;
import team5.model.Context;
import team5.view.MainView;
import team5.view.ReportInputView;

public class ShowProducerReportAction extends AbstractAction {
	public ShowProducerReportAction() {
		putValue(NAME, "");
		putValue(SHORT_DESCRIPTION, "Prikazi sve");

		try {
			Image im = ImageIO.read(new File("./resources/icon/ukupna prodaja proizvodjaca.png"));
			putValue(LARGE_ICON_KEY, new ImageIcon(im.getScaledInstance(96, 48, Image.SCALE_DEFAULT)));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(Context.getInstance().getMedicine().size()==0) {
			JOptionPane.showMessageDialog(null, "Nema lekova", "Greska", JOptionPane.ERROR_MESSAGE);
			return;
		}
		ReportInputView rv = new ReportInputView(0);
		rv.setVisible(true);
		ReportsController.getByProducer(rv.getText());
		Context.getInstance().setReportFor(" za proizvodjaca: " + rv.getText());
		MainView.getActiveInstance().updateReportInfo();
	}
}
