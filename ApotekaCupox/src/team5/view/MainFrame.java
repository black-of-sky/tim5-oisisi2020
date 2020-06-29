package team5.view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import team5.controller.Event;

public class MainFrame extends JFrame {
	private static MainFrame instance;

	private MainFrame() {
		setTitle("Apoteka Cupox");
		try {
			Image icon = ImageIO.read(new File("./resources/icon/logo.png"));
			setIconImage(icon);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		setLayout(new BorderLayout());
		getContentPane().add(new LandingView(PanelType.LANDING), BorderLayout.CENTER);
		// add(new JScrollPane(TableFactory.getTable(User.class)));
		// add(new JScrollPane(TableFactory.getTable(Medicine.class)));
		// add(new MainView(),BorderLayout.CENTER);

	}

	public static MainFrame getInstance() {
		if (instance == null)
			instance = new MainFrame();
		return instance;
	}

	public void processEvent(Event e, Object o) {

		getContentPane().removeAll();

		switch (e) {
		case LOGIN_PRESSED:
			getContentPane().add(new LandingView(PanelType.LOGIN), BorderLayout.CENTER);

			break;
		case LOOGED_IN:
			add(new MainView(ViewType.NONE, 0, 0), BorderLayout.CENTER);

			break;
		case SHUT_DOWN:
			System.exit(1);
			break;
		case LOGGED_OUT:
			getContentPane().add(new LandingView(PanelType.LANDING), BorderLayout.CENTER);
			break;
		case SHOW_MEDICINE:
			Map<String, Integer> map = (Map<String, Integer>) o;

			add(new MainView(ViewType.MEDICINE, map.get("col"), map.get("direction")), BorderLayout.CENTER);
			break;
		case SHOW_USERS:
			Map<String, Integer> map2 = (Map<String, Integer>) o;
			add(new MainView(ViewType.USERS, map2.get("col"), map2.get("direction")), BorderLayout.CENTER);

			break;
		case SHOW_RECIPES:
			Map<String, Integer> map3 = (Map<String, Integer>) o;
			add(new MainView(ViewType.RECIPES, map3.get("col"), map3.get("direction")), BorderLayout.CENTER);

			break;
		case SHOW_CART:
			add(new MainView(ViewType.CART, 0, 0), BorderLayout.CENTER);

			break;
		case SHOW_REPORTS:
			add(new MainView(ViewType.REPORTS, 0, 0), BorderLayout.CENTER);

			break;
		default:
			break;
		}
		revalidate();
		repaint();

	}
}
