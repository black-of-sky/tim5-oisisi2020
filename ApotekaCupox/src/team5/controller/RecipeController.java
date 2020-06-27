package team5.controller;

import team5.model.Context;
import team5.model.Medicine;
import team5.model.Recipe;
import team5.model.User;
import team5.view.MainFrame;
import team5.view.tables.models.MedicineAbstractTableModel;
import team5.view.tables.models.RecipeAbstractTableModel;
import team5.view.tables.models.UserAbstractTableModel;

public class RecipeController {
	private static RecipeController instance;

	private RecipeController() {

	}
	public static RecipeController getInstance() {
		if (instance == null)
			instance = new RecipeController();
		return instance;
	}


	

	public void insert(Recipe rec) {
		rec.setId(Context.getInstance().getRecipes().size());
		Context.getInstance().getRecipes().add(rec);
		int row = Context.getInstance().getMedicine().size() - 1;
		RecipeAbstractTableModel.getInstance().fireTableRowsInserted(row, row);
	}

}
