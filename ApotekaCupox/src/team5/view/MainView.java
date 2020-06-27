package team5.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.EmptyBorder;

import team5.Utils;
import team5.controller.actions.AddMedicineAction;
import team5.controller.actions.AddUserAction;
import team5.model.Medicine;
import team5.model.User;
import team5.view.tables.TableFactory;;

public class MainView extends JPanel {

	public MainView(ViewType viewtype, int colSort, int direction) {
		// setLayout(new GridBagLayout());
		JPanel toolbar = new Toolbar();// JPanel();
		setLayout(new BorderLayout());
		JPanel tableview = new JPanel();
		tableview.setLayout(new BorderLayout());
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		bottom.setBackground(new Color(255, 210, 181));
		bottom.setLayout(new BorderLayout());
		bottom.setBorder(new EmptyBorder(25, 25, 0, 25));
		JTable table = null;
		switch (viewtype) {
		case MEDICINE:
			table=TableFactory.getTable(Medicine.class);
			break;
		case USERS:
			table=TableFactory.getTable(User.class);
			break;
		default:
			break;
		}
		SortOrder ord=direction==1?SortOrder.ASCENDING:SortOrder.DESCENDING;
		tableview.add((new JScrollPane(table)));
		DefaultRowSorter sorter = ((DefaultRowSorter) table.getRowSorter());
		ArrayList list = new ArrayList();
		list.add(new RowSorter.SortKey(colSort, ord));
		sorter.setSortKeys(list);
		sorter.sort();
		tableview.setBorder(new EmptyBorder(0, 15, 15, 0));
		tableview.setBackground(new Color(255, 254, 223));
		JSplitPane splitPane = createSplitPane(toolbar, bottom, 0.1, 100, JSplitPane.VERTICAL_SPLIT);

		JPanel tableTitle = new JPanel();
		tableTitle.setBackground(new Color(255, 254, 223));

		JSplitPane tableSplit = createSplitPane(tableTitle, tableview, 0.1, 85, JSplitPane.VERTICAL_SPLIT);

		JPanel sidebar = makeSidebar(viewtype);
		sidebar.setBackground(new Color(255, 254, 223));

		JSplitPane splitPaneinner = createSplitPane(tableSplit, sidebar, 0.95, -1, JSplitPane.HORIZONTAL_SPLIT);

		bottom.add(splitPaneinner);

		add(splitPane);

	}

	private JSplitPane createSplitPane(Component up, Component down, double weight, int location, int orientation) {
		JSplitPane sp = new JSplitPane(orientation, up, down);
		sp.setDividerLocation(location);
		sp.setResizeWeight(weight);
		sp.setEnabled(false);
		sp.setDividerSize(0);
		sp.setBorder(null);
		return sp;

	}

	private JPanel makeSidebar(ViewType viewtype) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.fill = GridBagConstraints.NONE;

		c.weighty = 1;
		c.weightx = 1;

		for (int i = 0; i < 8; ++i) {
			c.gridy = i;
			panel.add(javax.swing.Box.createGlue(), c);
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
			panel.add(jb, c);
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
			panel.add(jb2, c);
			break;

		default:
			break;
		}

		return panel;
	}

}
