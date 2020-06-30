package team5;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import team5.model.Context;
import team5.view.MainFrame;

public class MyFancyApp {
	public static void main(String[] args) {
		//TODO BOJE ZA JOPTION PANE
		UIManager.put("OptionPane.background", new ColorUIResource(new Color(255, 254, 223)));
		UIManager.put("Panel.background", new ColorUIResource(new Color(255, 254, 223)));
		Context.getInstance();//da ucita podatke iz fajlova
		MainFrame mf = MainFrame.getInstance();
		mf.setSize(800, 600);
		mf.setLocationRelativeTo(null);
		mf.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
		mf.setVisible(true);
	}
}
