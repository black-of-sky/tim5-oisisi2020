package team5.view;

import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import team5.Utils;
import team5.model.User;
import team5.model.UserType;
import team5.view.tables.TableFactory;

public class MoreInfoAboutUser extends JDialog {

	public MoreInfoAboutUser(User u, Map<String, Integer> data) {
		setModal(true);
		setSize(500, 500);
		JComponent up = new JScrollPane(TableFactory.getUserDetailsTable(data));
		JPanel down = new JPanel();
		if (u.getType() == UserType.APOTEKAR)
			setTitle("Prodati lekovi");
		else
			setTitle("Prepisani lekovi");
		setLocationRelativeTo(null);
		int total=0;
		for(Integer q:data.values())
			total+=q;
		if (u.getType() == UserType.APOTEKAR)
			down.add(new JLabel("Apotekar "+u.getUsername()+" je ukupno prodao "+total+" lekova" ));
		else
			down.add(new JLabel("Lekar "+u.getUsername()+" je ukupno prepisao  "+total+" lekova" ));
		add(Utils.createSplitPane(up, down, 1, 350, 0));
	}

}
