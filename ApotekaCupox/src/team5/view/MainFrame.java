package team5.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import team5.controller.Event;
import team5.view.tables.UsersTable;

public class MainFrame extends JFrame {
	private static MainFrame instance;
	private MainFrame() {
		
		add(new LandingView());
		//add(new JScrollPane(new UsersTable()));
		
	}
	public static MainFrame getInstance() {
		if (instance==null)
			instance=new MainFrame();
		return instance;
	}
	
	public void processEvent(Event e,Object o) {
		//TODO obrada dogadjaja
	}
	
}
