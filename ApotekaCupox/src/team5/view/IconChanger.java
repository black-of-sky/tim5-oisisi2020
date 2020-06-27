package team5.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IconChanger implements MouseListener {

	private ImageIcon icon, iconHover;
	private JButton button;

	public IconChanger(ImageIcon icon, ImageIcon iconHover, JButton button) {
		super();
		button.setIcon(icon);
		this.icon = icon;
		this.iconHover = iconHover;
		this.button = button;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		button.setIcon(icon);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		button.setIcon(iconHover);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
}