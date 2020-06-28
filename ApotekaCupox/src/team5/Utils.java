package team5;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSplitPane;

import team5.view.ViewType;

public class Utils {
	public static JButton transparentButton(JButton jb) {
		jb.setBackground(new Color(0, 0, 0, 0));
		jb.setBorder(null);
		jb.setMargin(new Insets(0, 0, 0, 0));
		jb.setContentAreaFilled(false);
		return jb;
	}

	public static JSplitPane createSplitPane(Component up, Component down, double weight, int location,
			int orientation) {
		JSplitPane sp = new JSplitPane(orientation, up, down);
		sp.setDividerLocation(location);
		sp.setResizeWeight(weight);
		sp.setEnabled(false);
		sp.setDividerSize(0);
		sp.setBorder(null);
		return sp;
	}

	public static ImageIcon getImageForTable(ViewType viewtype) {
		String path="./resources/icon/logo.png";
		switch (viewtype) {
		case CART:
			//TODO ikonice

			break;
		case USERS:

			break;
		case RECIPES:

			break;
		case MEDICINE:

			break;
		default:
			return null;
		}

		Image im;
		try {
			im = ImageIO.read(new File(path));
			return new ImageIcon(im.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
