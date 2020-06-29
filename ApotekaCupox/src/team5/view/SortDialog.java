package team5.view;

import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import team5.Utils;

public class SortDialog extends JDialog {
	private JButton clicked = null;
	public Checkbox cb;

	public SortDialog(List<JButton> buttons) {
		setTitle("Sortiraj po koloni");
		Utils.setLogo(this);
		setModal(true);
		setSize(500, buttons.size() * 75);
		setLocationRelativeTo(null);
		setLayout(new GridBagLayout());

		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.anchor = GridBagConstraints.BASELINE_LEADING;
		c2.gridy = 0;
		c2.weighty = 1;
		c2.anchor = GridBagConstraints.CENTER;
		c2.weightx = 1;

		for (JButton c : buttons) {
			add(c, c2);
			c2.gridy++;

			c.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					clicked = c;
					SortDialog.this.setVisible(false);
				}
			});
		}
		c2.gridx++;
		c2.gridy = 0;
		c2.gridheight = buttons.size();
		cb = new Checkbox("OPADAJUCI REDOSLED?");
		cb.setFont(new Font("arial", Font.PLAIN, 20));
		cb.setSize(114, 42);
		add(cb, c2);
	}

	public JButton getClicked() {
		return clicked;
	}

}
