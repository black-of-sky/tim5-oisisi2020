package team5;

import team5.view.MainFrame;

public class MyFancyApp {
	public static void main(String[] args) {
		MainFrame mf= MainFrame.getInstance();
		mf.setSize(800,600);
		mf.setVisible(true);
	}
}
