package team5.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import team5.Utils;
import team5.controller.RecipeController;
import team5.model.Context;
import team5.model.Recipe;
import team5.view.tables.TableFactory;

public class RecipeContent extends JDialog {
	private int index;
	private JLabel price;
	public RecipeContent(int index) {
		setModal(true);
		this.index=index;
		setSize(500, 500);
		JComponent up = new JScrollPane(TableFactory.getTable(Recipe.class, index));
		JPanel down = new JPanel();
		price = new JLabel(("ukupno: " + RecipeController.getInstance().getTotalPrice(index)));
		down.add(price);

		add(Utils.createSplitPane(up, down, 1, 400, 0));
		JButton addb=new JButton("dodaj");
		addb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RecipeController.getInstance().addMedicine("nemacka medecina",4);
				updateRecipePrice();
			}
		});
		down.add(addb);
	}

	public void updateRecipePrice() {
		price.setText(""+RecipeController.getInstance().getTotalPrice(index));
	}
}
