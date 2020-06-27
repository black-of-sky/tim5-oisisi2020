package team5.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import team5.model.User;
import team5.view.tables.TableFactory;;

public class MainView extends JPanel {

	public MainView() {
		// setLayout(new GridBagLayout());
		JPanel toolbar = new Toolbar();// JPanel();
		setLayout(new BorderLayout());
		JButton ab = new JButton("reg");
		toolbar.add(ab);
		ab.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddUser().setVisible(true);

			}
		});
		JPanel tableview = new JPanel();
		tableview.setLayout(new BorderLayout());
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		bottom.setBackground(new Color(255, 210, 181));
		bottom.setLayout(new BorderLayout());
		bottom.setBorder(new EmptyBorder(25, 25, 0, 25));
		tableview.add((new JScrollPane(TableFactory.getTable(User.class))));
		tableview.setBorder(new EmptyBorder(0, 15, 15, 0));
		tableview.setBackground(new Color(255,254,223));
		JSplitPane splitPane=createSplitPane(toolbar, bottom, 0.1, 100, JSplitPane.VERTICAL_SPLIT);

		JPanel tableTitle = new JPanel();
		tableTitle.setBackground(new Color(255,254,223));

		JSplitPane tableSplit = createSplitPane(tableTitle, tableview, 0.1, 85, JSplitPane.VERTICAL_SPLIT);
	

		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(255,254,223));
		JSplitPane splitPaneinner = createSplitPane(tableSplit, sidebar, 0.95, 570, JSplitPane.HORIZONTAL_SPLIT);

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

}
