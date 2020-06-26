package team5.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import team5.controller.Event;
import team5.view.tables.UsersTable;

public class MainFrame extends JFrame {
	private static MainFrame instance;

	private MainFrame() {
		setLayout(new BorderLayout());
		getContentPane().add(new LandingView(PanelType.LANDING),BorderLayout.CENTER);
		// add(new JScrollPane(new UsersTable()));
		
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
			revalidate();		
			repaint();
			break;
		case LOOGED_IN:
			getContentPane().removeAll();
			revalidate();		
			repaint();
			break;
		case SHUT_DOWN:
			System.exit(1);
			break;
		default:
			break;
		}
		
	}
}
