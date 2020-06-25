package team5.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import team5.view.tables.UsersTable;

public class MainFrame extends JFrame {
	public MainFrame() {
		
		add(new LandingView());
		//add(new JScrollPane(new UsersTable()));
		
	}
	
}
