package team5.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import team5.Utils;
import team5.controller.CartController;
import team5.controller.ReportsController;
import team5.controller.actions.DisplaySearchAction;
import team5.model.Bill;
import team5.model.Context;
import team5.model.Medicine;
import team5.model.Prescription;
import team5.model.ReportItem;
import team5.model.User;
import team5.view.tables.TableFactory;;

public class MainView extends JPanel {
	private static MainView activeInstance;
	private JLabel price;// za isvestaj je ovo kolicina lekova, ya korpu cena
	private JLabel discount;// ya izvestaj je profit, za korpu popust
	private JTextField field;
	private JLabel reportInfo;
	private JTable table;

	public MainView(ViewType viewtype, int colSort, int direction) {
		activeInstance = this;
		JPanel toolbar = new Toolbar(viewtype);// JPanel();
		setLayout(new BorderLayout());
		JPanel tableview = new JPanel();
		tableview.setLayout(new BorderLayout());
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		bottom.setBackground(new Color(255, 210, 181));
		bottom.setBorder(new EmptyBorder(25, 25, 0, 25));
		JTable table = getTable(viewtype);
		this.table = table;
		SortOrder ord = direction == 1 ? SortOrder.ASCENDING : SortOrder.DESCENDING;
		JScrollPane jsp = new JScrollPane(table == null ? new HomePageImageDrawer() : table);
		if (viewtype == viewtype.NONE)
			jsp.setBorder(null); // da ne crta okvi oko tabele ako ona ne postoji
		tableview.add(jsp);
		if (viewtype != viewtype.NONE) {
			DefaultRowSorter sorter = ((DefaultRowSorter) table.getRowSorter());
			ArrayList list = new ArrayList();
			list.add(new RowSorter.SortKey(colSort, ord));
			sorter.setSortKeys(list);
			sorter.sort();
			filterTable(null);
		}
		tableview.setBorder(new EmptyBorder(0, 15, 15, 0));
		tableview.setBackground(new Color(255, 254, 223));
		JSplitPane splitPane = Utils.createSplitPane(toolbar, bottom, 0, 100, JSplitPane.VERTICAL_SPLIT);
	if (viewtype == ViewType.NONE) {
			bottom.setLayout(new BorderLayout());
			bottom.add(new HomePageImageDrawer(), BorderLayout.CENTER);
			add(splitPane, BorderLayout.CENTER);
			return;
		}
		JPanel tableTitle = new JPanel();
		tableTitle.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.anchor = GridBagConstraints.BASELINE_LEADING;
		c2.gridy = 0;
		c2.weighty = 1;
		c2.weightx = 1;
		if (viewtype != ViewType.NONE) {
			if (viewtype == viewtype.REPORTS)
				c2.gridheight = 2;// za reports imamo dva reda, pa mora da logo zauzme oba
			ImageIcon ic = Utils.getImageForTable(viewtype);
			tableTitle.setBorder(new EmptyBorder(5, 10, 5, 10));
			tableTitle.add(new JLabel(ic), c2);
		}
		if (viewtype == ViewType.CART) {
			c2.fill = GridBagConstraints.BOTH;
			c2.anchor = GridBagConstraints.CENTER;
			c2.gridx = 1;
			c2.gridy = 0;
			c2.gridwidth = 5;
			tableTitle.add(getCartPanel(), c2);

		} else if (viewtype == ViewType.REPORTS) {
			c2.gridheight = 1;
			c2.fill = GridBagConstraints.BOTH;
			c2.anchor = GridBagConstraints.CENTER;
			reportInfo = new JLabel();
			discount = new JLabel();
			price = new JLabel();
			updateReportInfo();
			c2.gridwidth = 2;
			c2.gridx = 1;
			tableTitle.add(reportInfo, c2);
			c2.fill = GridBagConstraints.NONE;
			c2.gridwidth = 1;
			c2.gridx = 1;
			c2.gridy = 1;
			tableTitle.add(price, c2);
			c2.gridx = 2;
			tableTitle.add(discount, c2);
		} else if (viewtype != ViewType.NONE && viewtype != ViewType.USERS) {// znaci se osim none reports,users i cart
																				// ima pretragu
			c2.gridy = 0;
			c2.gridx = 1;
			c2.fill = GridBagConstraints.BOTH;
			JButton serc = Utils.transparentButton(new JButton(new DisplaySearchAction(viewtype)));
			ImageIcon iconHover = Utils.getImageicon("./resources/icon/pretraga selekt.png");
			serc.addMouseListener(new IconChanger((ImageIcon) serc.getIcon(), iconHover, serc));
			tableTitle.add(serc, c2);
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
		case PRESCRIPTION:
			table = TableFactory.getTable(Prescription.class);
			break;
		case CART:
			price = new JLabel("Ukupno: 0");
			price.setFont(new Font("arial", Font.PLAIN, 25));
			table = TableFactory.getTable(Bill.class);
			updateTotalPrice();
			break;
		case REPORTS:
			table = TableFactory.getTable(ReportItem.class);
			break;
		}
		return table;
	}

	private JPanel getCartPanel() {
		JPanel ret = new JPanel();
		ret.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.BASELINE_LEADING;
		c2.weighty = 1;
		c2.weightx = 1;
		ret.add(new JLabel("Kupac: "), c2);
		c2.gridx = 1;
		field = new JTextField();
		c2.fill = GridBagConstraints.HORIZONTAL;
		ret.add(field, c2);
		c2.gridx = 0;
		c2.gridy = 1;
		discount = new JLabel("Popust: 0% ");
		ret.add(discount, c2);
		field.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				calcDiscount(field.getText());

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				calcDiscount(field.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				calcDiscount(field.getText());
			}
		});
		c2.gridx = 1;
		c2.gridy = 1;
		ret.add(price, c2);
		for (Component c : ret.getComponents()) {
			c.setFont(new Font("arial", Font.PLAIN, 25));
		}
		return ret;
	}

	private float calcDiscount(String user) {
		updateTotalPrice();

		if (user == null || user.equals("")) {
			discount.setText("Popust: 0%");
			return 0;
		} else {
			int di = CartController.calculateDiscount(user);
			discount.setText("Popust: " + di + "%");
			return di;
		}
	}

	public static MainView getActiveInstance() {
		return activeInstance;
	}

	public void updateTotalPrice() {
		if (price == null)
			price = new JLabel();
		if (field == null)
			field = new JTextField();

		float f = CartController.getTotalPrice();
		price.setText("Ukupno: " + f * (100 - CartController.calculateDiscount(field.getText())) / 100);
		repaint();
	}

	public void reset() {
		price.setText("Ukupno: 0.0");
		field.setText("");
		repaint();
	}

	public String getBuyer() {
		return field.getText();
	}

	public void updateReportInfo() {
		String text = Context.getInstance().getReportFor();
		if (!text.equals("")) {
			Font f = new Font("arial", Font.PLAIN, 25);
			price.setFont(f);
			discount.setFont(f);
			reportInfo.setFont(f);
			reportInfo.setText("Izvestaj" + text);
			price.setText("Kolicina:" + ReportsController.getTotalQuantity());
			discount.setText("Zarada: " + ReportsController.getTotalProfit());
		}

	}

	public void filterTable(RowFilter<Object, Object> filter) {
		DefaultRowSorter sorter = ((DefaultRowSorter) table.getRowSorter());
		List<RowFilter<Object, Object>> f = new LinkedList<>();
		f.add(Utils.isDeletedFilter());
		if (filter != null)
			f.add(filter);
		sorter.setRowFilter(RowFilter.andFilter(f));
	}

}
