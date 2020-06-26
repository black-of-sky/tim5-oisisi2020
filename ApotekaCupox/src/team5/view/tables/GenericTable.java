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

public class GenericTable extends JTable {

	private static final long serialVersionUID = 8900651367165240112L;

	public GenericTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(false);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setRowHeight(40);
		setShowGrid(false);
		setIntercellSpacing(new Dimension(0, 0));
		getTableHeader().setBackground( new Color(182, 64, 14));
		getTableHeader().setForeground( new Color(255, 255, 255));
		getTableHeader().setDefaultRenderer(new MyCoolTableHeaderRenderer(this));
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		JComponent c = (JComponent) super.prepareRenderer(renderer, row, column);
		MatteBorder border = new MatteBorder(0, 0, 10, 0, new Color(182, 64, 14));

		c.setBorder(border);
		if (isRowSelected(row)) {
			c.setBackground(new Color(255,255,204));
		} else {
			if ((column + 1) % 2 != 0) {
				c.setBackground(new Color(250, 210, 181));
			} else {
				c.setBackground(new Color(250, 139, 104));
			}
			
		}
		return c;
	}
	
	private class MyCoolTableHeaderRenderer implements TableCellRenderer {

		DefaultTableCellRenderer renderer;

		public MyCoolTableHeaderRenderer(JTable table) {
			renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
			renderer.setHorizontalAlignment(JLabel.CENTER);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {
			Component c = renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
			return new JLabel(new ImageIcon("./resources/icon/login.png"));
			//return c;
		}

	}
}