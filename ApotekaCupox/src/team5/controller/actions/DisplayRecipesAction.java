package team5.controller.actions;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.KeyStroke;

import team5.Utils;
import team5.controller.Event;
import team5.view.MainFrame;
import team5.view.SortDialog;

public class DisplayRecipesAction extends AbstractAction {
	public DisplayRecipesAction(boolean selected) {
		putValue(NAME, "");
		// putValue(MNEMONIC_KEY, KeyEvent.VK_Q);
		putValue(SHORT_DESCRIPTION, "Recepti");

		try {
			Image im = ImageIO.read(new File("./resources/icon/recept" + (selected ? " selektovan" : "") + ".png"));
			putValue(LARGE_ICON_KEY, new ImageIcon(im.getScaledInstance(96, 96, Image.SCALE_DEFAULT)));
			// putValue(SMALL_ICON, new ImageIcon(im.getScaledInstance(96, 96,
			// Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		List<JButton> buttons = new ArrayList<>();
		Image im;
		try {
			im = ImageIO.read(new File("./resources/icon/SIFRA.png"));
			buttons.add(getButon(im));
			im = ImageIO.read(new File("./resources/icon/LEKAR.png"));
			buttons.add(getButon(im));
			im = ImageIO.read(new File("./resources/icon/DATUM.png"));
			buttons.add(getButon(im));

		} catch (IOException e) {
			e.printStackTrace();
		}

		SortDialog d = new SortDialog(buttons);
		d.setVisible(true);
		int direction = d.cb.getState() ? 0 : 1;

		JButton clicked = d.getClicked();
		if (clicked == null)
			return;
		Map<String, Integer> map = new HashMap<>();
		map.put("col", buttons.indexOf(clicked));
		map.put("direction", direction);

		MainFrame.getInstance().processEvent(Event.SHOW_RECIPES, map);
	}

	private JButton getButon(Image im) {
		ImageIcon ic = new ImageIcon(im.getScaledInstance(114, 42, Image.SCALE_DEFAULT));
		return Utils.transparentButton(new JButton(ic));
	}
}
