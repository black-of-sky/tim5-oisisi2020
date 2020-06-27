package team5.view.tables;

import javax.swing.JTable;

import team5.model.Medicine;
import team5.model.Recipe;
import team5.model.User;
import team5.view.tables.models.MedicineAbstractTableModel;
import team5.view.tables.models.MedicineInRecipe;
import team5.view.tables.models.RecipeAbstractTableModel;
import team5.view.tables.models.UserAbstractTableModel;

public class TableFactory {
	private TableFactory() {
	}

	public static JTable getTable(Class<?> c) {
		JTable ret = new GenericTable();
		if (c == User.class) {
			ret.setModel(UserAbstractTableModel.getInstance());
		} else if (c == Medicine.class) {
			ret.setModel(MedicineAbstractTableModel.getInstance());
		} else if (c == Recipe.class) {
			ret.setModel(RecipeAbstractTableModel.getInstance());
		}
		return ret;
	}

	public static JTable getTable(Class<?> c, int index) {
		JTable ret = new GenericTable();

		if (c == Recipe.class) {
			ret.setModel(new MedicineInRecipe(index));

		}
		return ret;
	}

}
