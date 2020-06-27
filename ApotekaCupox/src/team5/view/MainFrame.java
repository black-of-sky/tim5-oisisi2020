package team5.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import team5.controller.Event;
import team5.model.Medicine;
import team5.model.User;
import team5.view.tables.TableFactory;

public class MainFrame extends JFrame {
	private static MainFrame instance;

	private MainFrame() {
		setLayout(new BorderLayout());
		getContentPane().add(new LandingView(PanelType.LANDING),BorderLayout.CENTER);
		 //add(new JScrollPane(TableFactory.getTable(User.class)));
		 //add(new JScrollPane(TableFactory.getTable(Medicine.class)));
		//add(new MainView(),BorderLayout.CENTER);
		
	}

	public static MainFrame getInstance() {
		if (instance == null)
			instance = new MainFrame();
		return instance;
	}

	public void processEvent(Event e, Object o) {
		switch (e) {
		case LOGIN_PRESSED:
			getContentPane().removeAll();
			getContentPane().add(new LandingView(PanelType.LOGIN),BorderLayout.CENTER);

			break;
		case LOOGED_IN:
			getContentPane().removeAll();
			add(new MainView(ViewType.USERS),BorderLayout.CENTER);

			break;
		case SHUT_DOWN:
			System.exit(1);
			break;
		case LOGGED_OUT:
			getContentPane().removeAll();
			getContentPane().add(new LandingView(PanelType.LANDING),BorderLayout.CENTER);
			break;
		case SHOW_MEDICINE:
			getContentPane().removeAll();
			add(new MainView(ViewType.MEDICINE),BorderLayout.CENTER);
			break;
		case SHOW_USERS:
			getContentPane().removeAll();
			add(new MainView(ViewType.USERS),BorderLayout.CENTER);
			
			break;
	
		default:
			break;
		}
		revalidate();		
		repaint();
		
	}
}
