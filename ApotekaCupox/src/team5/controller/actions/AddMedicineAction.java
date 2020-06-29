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
import javax.swing.JTable;
import javax.swing.KeyStroke;

import team5.view.AddMedicine;
import team5.view.tables.GenericTable;
import team5.view.tables.models.MedicineAbstractTableModel;

public class AddMedicineAction extends AbstractAction {
	private boolean edit;
	private JTable table;

	public AddMedicineAction(boolean edit, JTable table) {
		this.edit = edit;
		this.table = table;

		putValue(NAME, "");
		putValue(SHORT_DESCRIPTION, edit ? "Izmeni" : "Dodaj" + " lek");

		try {
			String path = "./resources/icon/" + (edit ? "izmeni lek" : "dodaj") + ".png";
			Image im = ImageIO.read(new File(path));
			putValue(LARGE_ICON_KEY, new ImageIcon(im.getScaledInstance(96, 48, Image.SCALE_DEFAULT)));

		} catch (IOException e) {
			e.printStackTrace();
		}

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!edit)
			new AddMedicine(-1).setVisible(true);
		else {
			int selected = table.getSelectedRow();
			if (selected < 0) {
				JOptionPane.showMessageDialog(null, "Nijedan lek nije selektovan","Greska",JOptionPane.ERROR_MESSAGE);

				return;
			}
			new AddMedicine(selected).setVisible(true);
		}
	}
}
