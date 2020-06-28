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
import team5.model.Bill;
import team5.model.Medicine;
import team5.model.Prescription;
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
			table = TableFactory.getTable(Medicine.class);
			break;
		case USERS:
			table = TableFactory.getTable(User.class);
			break;
		case RECIPES:
			table = TableFactory.getTable(Prescription.class);
			break;
		case CART:
			table = TableFactory.getTable(Bill.class);
			break;

		}
		SortOrder ord = direction == 1 ? SortOrder.ASCENDING : SortOrder.DESCENDING;
		JScrollPane jsp=new JScrollPane(table == null ? new JPanel() : table);
		if(viewtype == viewtype.NONE)
			jsp.setBorder(null); //da ne crta okvi oko tabele ako ona ne postoji
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
		tableTitle.setBackground(new Color(255, 254, 223));

		JSplitPane tableSplit = Utils.createSplitPane(tableTitle, tableview, 0, 85, JSplitPane.VERTICAL_SPLIT);

		JPanel sidebar = new Sidebar(viewtype, table);
		sidebar.setBackground(new Color(255, 254, 223));

		JSplitPane splitPaneinner = Utils.createSplitPane(tableSplit, sidebar, 0.95, -1, JSplitPane.HORIZONTAL_SPLIT);

		bottom.add(splitPaneinner);

		add(splitPane);

	}

}
