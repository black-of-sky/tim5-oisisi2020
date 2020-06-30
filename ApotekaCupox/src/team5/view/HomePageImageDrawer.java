package team5.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class HomePageImageDrawer extends JPanel {

	private static Image background;

	public HomePageImageDrawer() {
		setSize(500, 500);
		if (background == null) {
			File f = new File("./resources/img/homepage.png");
			try {
				background = ImageIO.read(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Dimension d = getSize();
		g.drawImage(background.getScaledInstance(d.width, d.height, Image.SCALE_FAST), 0, 0, null);
	}
}
