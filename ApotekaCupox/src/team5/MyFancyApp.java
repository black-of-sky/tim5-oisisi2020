package team5;

import team5.view.MainFrame;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class MyFancyApp {
	public static void main(String[] args) {
		//TODO BOJE ZA JOPTION PANE
		UIManager.put("OptionPane.background", new ColorUIResource(150, 10, 0));
		UIManager.put("Panel.background", new ColorUIResource(255, 0, 0));
		
		MainFrame mf = MainFrame.getInstance();
		mf.setSize(800, 600);
		mf.setLocationRelativeTo(null);
		mf.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
		mf.setVisible(true);
	}
}
