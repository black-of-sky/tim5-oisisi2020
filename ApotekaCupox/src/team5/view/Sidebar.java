package team5.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import team5.Utils;
import team5.controller.actions.AddMedicineAction;
import team5.controller.actions.AddUserAction;

public class Sidebar extends JPanel {
	private JTable table;
	public Sidebar(ViewType viewtype,JTable table) {
		this.table=table;
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.fill = GridBagConstraints.NONE;

		c.weighty = 1;
		c.weightx = 1;

		for (int i = 0; i < 8; ++i) {
			c.gridy = i;
			add(javax.swing.Box.createGlue(), c);
		}
		c.gridy = 8;

		c.anchor = GridBagConstraints.CENTER;
		switch (viewtype) {
		case USERS:
			JButton jb = Utils.transparentButton(new JButton(new AddUserAction()));
			ImageIcon hover = null;
			try {
				Image im = ImageIO.read(new File("./resources/icon/registracija selekt.png"));
				hover = new ImageIcon(im.getScaledInstance(96, 48, Image.SCALE_DEFAULT));
			} catch (IOException e) {
				e.printStackTrace();
			}

			jb.addMouseListener(new IconChanger((ImageIcon) jb.getIcon(), hover, jb));
			add(jb, c);
			break;
		case MEDICINE:
			JButton jb2 = Utils.transparentButton(new JButton(new AddMedicineAction()));
			ImageIcon hover2 = null;
			try {
				Image im = ImageIO.read(new File("./resources/icon/dodaj selekt.png"));
				hover2 = new ImageIcon(im.getScaledInstance(96, 48, Image.SCALE_DEFAULT));
			} catch (IOException e) {
				e.printStackTrace();
			}

			jb2.addMouseListener(new IconChanger((ImageIcon) jb2.getIcon(), hover2, jb2));
			add(jb2, c);
			break;
		case RECIPES:
			/*JButton jb2 = Utils.transparentButton(new JButton(new AddMedicineAction()));
			ImageIcon hover2 = null;
			try {
				Image im = ImageIO.read(new File("./resources/icon/dodaj selekt.png"));
				hover2 = new ImageIcon(im.getScaledInstance(96, 48, Image.SCALE_DEFAULT));
			} catch (IOException e) {
				e.printStackTrace();
			}

			jb2.addMouseListener(new IconChanger((ImageIcon) jb2.getIcon(), hover2, jb2));
			add(jb2, c);
			break;*/
		}
	}
}
