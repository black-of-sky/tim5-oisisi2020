package team5.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LandingView extends JPanel {
	private Image background;

	public LandingView() {
		try {
			background = ImageIO.read(new File("./resources/img/landing.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		
		c.gridx=0;
		c.gridy=0;
		c.weighty = 1;
		c.weightx = 1;
		c.anchor=GridBagConstraints.CENTER;
		c.fill=GridBagConstraints.BOTH;
		for(int i=0;i<20;i++) {
			c.gridx++;
			c.gridy++;
			add(javax.swing.Box.createGlue(),c);
		}
		c.gridy=16;
		c.gridx=7;
		add(new JButton("LET ME INNNN"),c);
		
		
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Dimension d=getSize();
		
		g.drawImage(background.getScaledInstance(d.width, d.height,Image.SCALE_SMOOTH ), 0, 0, null);
	}

}
