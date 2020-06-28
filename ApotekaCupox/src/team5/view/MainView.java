package team5.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import javax.swing.JLabel;
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
import team5.model.Bill;
import team5.model.Medicine;
import team5.model.Prescription;
import team5.model.User;
import team5.view.tables.TableFactory;;

public class MainView extends JPanel {
	private static MainView activeInstance;
	JLabel price;

	public MainView(ViewType viewtype, int colSort, int direction) {
		activeInstance = this;
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
		JTable table = getTable(viewtype);
		SortOrder ord = direction == 1 ? SortOrder.ASCENDING : SortOrder.DESCENDING;
		JScrollPane jsp = new JScrollPane(table == null ? new JPanel() : table);
		if (viewtype == viewtype.NONE)
			jsp.setBorder(null); // da ne crta okvi oko tabele ako ona ne postoji
		tableview.add(jsp);
		if (viewtype != viewtype.NONE) {
			DefaultRowSorter sorter = ((DefaultRowSorter) table.getRowSorter());
			ArrayList list = new ArrayList();
			list.add(new RowSorter.SortKey(colSort, ord));
			sorter.setSortKeys(list);
			sorter.sort();
		}
		tableview.setBorder(new EmptyBorder(0, 15, 15, 0));
		tableview.setBackground(new Color(255, 254, 223));
		JSplitPane splitPane = Utils.createSplitPane(toolbar, bottom, 0, 100, JSplitPane.VERTICAL_SPLIT);

		JPanel tableTitle = new JPanel();
		tableTitle.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.anchor = GridBagConstraints.BASELINE_LEADING;
		c2.gridy = 0;
		c2.weighty = 2;
		c2.weightx = 1;
		if(viewtype!=ViewType.NONE) {
			
			ImageIcon ic=Utils.getImageForTable(viewtype);
			tableTitle.add(new JLabel(ic),c2);
		}
		if(viewtype==ViewType.CART) {
			c2.anchor = GridBagConstraints.CENTER;
			c2.gridx = 1;
			c2.gridy = 0;
			c2.weighty = 1;
			tableTitle.add(price,c2);
			c2.gridy = 1;
			c2.weighty = 1;
			tableTitle.add(new JLabel("ovde ce ici korisnik, popust...."),c2);
			
		}
		tableTitle.setBackground(new Color(255, 254, 223));

		JSplitPane tableSplit = Utils.createSplitPane(tableTitle, tableview, 0, 85, JSplitPane.VERTICAL_SPLIT);

		JPanel sidebar = new Sidebar(viewtype, table);
		sidebar.setBackground(new Color(255, 254, 223));

		JSplitPane splitPaneinner = Utils.createSplitPane(tableSplit, sidebar, 1, -1, JSplitPane.HORIZONTAL_SPLIT);

		bottom.add(splitPaneinner);

		add(splitPane);
		revalidate();
		repaint();

	}

	private JTable getTable(ViewType viewtype) {
		JTable table = null;
		switch (viewtype) {
		case MEDICINE:
			table = TableFactory.getTable(Medicine.class);
			break;
		case USERS:
			table = TableFactory.getTable(User.class);
			break;
		case RECIPES:
			table = TableFactory.getTable(Prescription.class);
			break;
		case CART:
			price = new JLabel("Ukupno: 0");
			price.setFont(new Font("arial",Font.PLAIN,25));
			table = TableFactory.getTable(Bill.class);
			break;

		}
		return table;
	}

	public static MainView getActiveInstance() {
		return activeInstance;
	}

	public void updateTotalPrice(float f) {
		price.setText("Ukupno: "+f);
		repaint();
	}

}
