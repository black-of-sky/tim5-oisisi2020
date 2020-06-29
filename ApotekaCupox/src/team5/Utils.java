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
import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;

import team5.model.Context;
import team5.model.UserType;
import team5.view.ViewType;
import team5.view.tables.models.TellMeIfYouAreDeleted;

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
		String path = "./resources/icon/logo.png";
		switch (viewtype) {
		case CART:
			// TODO ikonice

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

	public static ImageIcon getImageicon(String path) {
		ImageIcon icon = null;
		try {
			Image im = ImageIO.read(new File(path));
			icon = new ImageIcon(im.getScaledInstance(96, 48, Image.SCALE_DEFAULT));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return icon;
	}

	public static RowFilter isDeletedFilter() {
		return new RowFilter<Object, Object>() {// kod sa stackoverflowa xD
			public boolean include(Entry<? extends Object, ? extends Object> entry) {
				if (entry.getModel() instanceof TellMeIfYouAreDeleted) {// ako je neka od tabela gde moze da se brise
					TellMeIfYouAreDeleted t = (TellMeIfYouAreDeleted) entry.getModel();
					// adminu se sve prikaze, ostalima samo ako nije izbriano
					return Context.getInstance().getLogged().getType() == UserType.ADMINISTRATOR
							|| !t.areYouDeleted((int) entry.getIdentifier());
				}
				return true;// u tabelema gde nema brisanja svima se sve prikaze
			}
		};
	}

}
