package team5.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import team5.Utils;
import team5.controller.Event;
import team5.controller.actions.DisplayCartAction;
import team5.controller.actions.DisplayMedicineAction;
import team5.controller.actions.DisplayRecipesAction;
import team5.controller.actions.DisplayReportsAction;
import team5.controller.actions.DisplayUsersAction;
import team5.controller.actions.LogoutAction;
import team5.model.Context;
import team5.model.User;
import team5.model.UserType;

public class Toolbar extends JPanel {

	public Toolbar(ViewType view) {
		setBackground(new Color(255, 139, 104));
		User u = Context.getInstance().getLogged();
		Image logo = null;
		try {
			logo = ImageIO.read(new File("./resources/icon/logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon im = new ImageIcon(logo.getScaledInstance(96, 96, Image.SCALE_DEFAULT));
		JPanel logoPanel = new JPanel();
		JPanel items = new JPanel();
		logoPanel.setBackground(new Color(255, 139, 104));
		items.setBackground(new Color(255, 139, 104));
		JButton l = Utils.transparentButton(new JButton(im));
		logoPanel.add(l);
		l.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().processEvent(Event.SHOW_HOME, null);
			}
		});

		items.add(Utils.transparentButton(new JButton(new DisplayMedicineAction(view == ViewType.MEDICINE))));
		if (u.getType() != UserType.APOTEKAR)
			items.add(Utils.transparentButton(new JButton(new DisplayRecipesAction(view == ViewType.PRESCRIPTION))));

		switch (u.getType()) {
		case ADMINISTRATOR:
			items.add(Utils.transparentButton(new JButton(new DisplayUsersAction(view == ViewType.USERS))));
			break;
		case APOTEKAR:
			items.add(Utils.transparentButton(new JButton(new DisplayCartAction(view == ViewType.CART))));
			break;
		}

		if (u.getType() == UserType.ADMINISTRATOR)
			items.add(Utils.transparentButton(new JButton(new DisplayReportsAction(view == ViewType.REPORTS))));
		items.add(Utils.transparentButton(new JButton(new LogoutAction())));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

		add(Utils.createSplitPane(logoPanel, items, 0, 100, 1), c);
	}

}
