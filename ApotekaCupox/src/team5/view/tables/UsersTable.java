package team5.view.tables;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import team5.view.tables.models.UserAbstractTableModel;

public class UsersTable extends JTable {

	private static final long serialVersionUID = 8900651367165240112L;

	public UsersTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(false);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(UserAbstractTableModel.getInstance());
		// new ButtonColumnIgraci(this, 4);
		setRowHeight(40);
		// setShowHorizontalLines(false);
		// setShowVerticalLines(false);
		// setGridColor( new Color(182,64,14));
		setShowGrid(false);
		setIntercellSpacing(new Dimension(0, 0));
		getTableHeader().setBackground(new Color(182, 64, 14));
		getTableHeader().setForeground(new Color(255, 255, 255));


	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		JComponent c = (JComponent) super.prepareRenderer(renderer, row, column);
		MatteBorder border = new MatteBorder(0, 0, 10, 0, new Color(182, 64, 14));

		c.setBorder(border);
		if (isRowSelected(row)) {
			c.setBackground(Color.LIGHT_GRAY);
		} else {
			if ((column + 1) % 2 != 0) {
				c.setBackground(new Color(250, 210, 181));
			} else {
				c.setBackground(new Color(250, 139, 104));
			}
			// c.setBackground(Color.WHITE);
		}
		return c;
	}

	
}
