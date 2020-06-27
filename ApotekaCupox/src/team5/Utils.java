package team5;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JSplitPane;

public class Utils {
	public static JButton transparentButton(JButton jb) {
		jb.setBackground(new Color(0, 0, 0, 0));
		jb.setBorder(null);
		jb.setMargin(new Insets(0, 0, 0, 0));
		jb.setContentAreaFilled(false);
		return jb;
	}
	
	public static JSplitPane createSplitPane(Component up, Component down, double weight, int location, int orientation) {
		JSplitPane sp = new JSplitPane(orientation, up, down);
		sp.setDividerLocation(location);
		sp.setResizeWeight(weight);
		sp.setEnabled(false);
		sp.setDividerSize(0);
		sp.setBorder(null);
		return sp;

	}

}
