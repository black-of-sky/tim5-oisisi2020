package team5.view.tables;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import team5.view.tables.models.MedicineAbstractTableModel;

public class GenericTable extends JTable {

	private static final long serialVersionUID = 8900651367165240112L;

	public GenericTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(false);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setRowHeight(40);
		setShowGrid(false);
		setIntercellSpacing(new Dimension(0, 0));
		getTableHeader().setBackground(new Color(182, 64, 14));
		getTableHeader().setForeground(new Color(255, 255, 255));
		getTableHeader().setDefaultRenderer(new MyCoolTableHeaderRenderer(this));
		setFillsViewportHeight(true);
		getTableHeader().setReorderingAllowed(false);
		setAutoCreateRowSorter(true);
		
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {	
		JComponent c = (JComponent) super.prepareRenderer(renderer, row, column);
		MatteBorder border = new MatteBorder(0, 0, 10, 0, new Color(182, 64, 14));

		c.setBorder(border);
		if (isRowSelected(row)) {
			c.setBackground(new Color(255, 255, 204));
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
			// Component c = renderer.getTableCellRendererComponent(table, value,
			// isSelected, hasFocus, row, col);
			String v = (String) value;

			Image im = null;
			try {
				switch (v) {
				case "username":
					im = ImageIO.read(new File("./resources/icon/korisnik.png"));
					break;
				case "name":
					im = ImageIO.read(new File("./resources/icon/IME.png"));
					break;
				case "lastname":
					im = ImageIO.read(new File("./resources/icon/PREZIME.png"));
					break;
				case "type":
					im = ImageIO.read(new File("./resources/icon/TIP.png"));
					break;
				case "med_id":
					im = ImageIO.read(new File("./resources/icon/SIFRA.png"));
					break;
				case "med_title":
					im = ImageIO.read(new File("./resources/icon/IME.png"));
					break;
				case "med_producer":
					im = ImageIO.read(new File("./resources/icon/PROIZVODJAC.png"));
					break;
				case "med_price":
					im = ImageIO.read(new File("./resources/icon/CENA.png"));
					break;
				case "med_recipe":
					im = ImageIO.read(new File("./resources/icon/recept2.png"));
					break;
				case "rec_price":
					im = ImageIO.read(new File("./resources/icon/CENA.png"));
					break;
				case "rec_date":
					im = ImageIO.read(new File("./resources/icon/DATUM.png"));
					break;
				case "rec_jmbg":
					im = ImageIO.read(new File("./resources/icon/JMBG.png"));
					break;
				case "rec_doc":
					im = ImageIO.read(new File("./resources/icon/LEKAR.png"));
					break;
				case "rec_id":
					im = ImageIO.read(new File("./resources/icon/SIFRA.png"));
					break;
				
				}

		
		
				//kolone.add("removed");	
			
				// im = ImageIO.read(new File("./resources/icon/korisnici.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (im != null) {
				JLabel lab = new JLabel(new ImageIcon(im.getScaledInstance(144 - 28, 42 - 8, Image.SCALE_SMOOTH)));
				JPanel pan = new JPanel();
				pan.setBackground(new Color(182, 64, 14));
				pan.setBorder(new EmptyBorder(4, 4, 4, 4));
				pan.add(lab);
				return pan;
			}

			return new JLabel((v));// ./resources/icon/login.png
			// return c;
		}

	}
}