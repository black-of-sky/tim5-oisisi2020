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
import javax.swing.RowFilter;

import team5.Utils;
import team5.view.MainView;
import team5.view.SearchDialog;
import team5.view.ViewType;

public class DisplaySearchAction extends AbstractAction {
	private ViewType type;

	public DisplaySearchAction(ViewType type) {
		this.type = type;
		putValue(SHORT_DESCRIPTION, "Pretraga");

		try {
			Image im = ImageIO.read(new File("./resources/icon/pretraga.png"));
			putValue(LARGE_ICON_KEY, new ImageIcon(im.getScaledInstance(96, 48, Image.SCALE_DEFAULT)));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] data;
		if (type == ViewType.MEDICINE) // lekovi
			data = new String[] { "Sifra", "Ime", "Proizvodjac", "Cena" };// bas ovaj redosled je u modelu pa se moze
																			// koristiti indeks, jupii
		else
			data = new String[] { "Sifra", "Lekar", "JMBG", "Lek" };
		SearchDialog d = new SearchDialog(data);
		d.setVisible(true);
		if (d.isClosed())// stisnuo x => oudstao od pretrage
			return;
		RowFilter<Object, Object> filter = null;
		if (type == ViewType.MEDICINE) {
			if (d.getSelectedOption() < 3) // <3 znaci nije selektovana cena
				filter = Utils.stringFiltering(d.getSelectedOption(), d.getSearchTerm());
			else
				filter = Utils.minMaxFilter(3, d.getMin(), d.getMax());
		} else { // recepti
			if (d.getSelectedOption() < 3)
				filter = Utils.stringFiltering(d.getSelectedOption(), d.getSearchTerm());
			else
				filter = Utils.prescriptionContainsMedicineFilter(d.getSearchTerm());
		}
		MainView.getActiveInstance().filterTable(filter);
	}

}
