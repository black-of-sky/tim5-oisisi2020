package team5.controller.actions;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import team5.controller.ReportsController;
import team5.model.Context;
import team5.model.User;
import team5.model.UserType;
import team5.view.MoreInfoAboutUser;

public class ShowAdditionalInfoAction extends AbstractAction {
	private JTable table;

	public ShowAdditionalInfoAction(JTable t) {
		putValue(NAME, "");
		table = t;
		putValue(SHORT_DESCRIPTION, "Prikazi detalje");

		try {
			Image im = ImageIO.read(new File("./resources/icon/detalji.png"));
			putValue(LARGE_ICON_KEY, new ImageIcon(im.getScaledInstance(96, 48, Image.SCALE_DEFAULT)));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int r = table.getSelectedRow();
		if (r == -1 || Context.getInstance().getUsers().get(r).getType() == UserType.ADMINISTRATOR) {
			JOptionPane.showMessageDialog(null, "Selektujte aptekara ili lekara");
			return;
		}
		User u = Context.getInstance().getUsers().get(r);
		Map<String, Integer> data = ReportsController.getStaticticForUser(u);
		new MoreInfoAboutUser(u, data).setVisible(true);
	}

}
