package team5.controller;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import team5.model.Context;
import team5.model.Medicine;
import team5.model.Recipe;
import team5.model.User;
import team5.view.MainFrame;
import team5.view.tables.models.MedicineAbstractTableModel;
import team5.view.tables.models.MedicineInRecipe;
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

	public float getTotalPrice(int index) {
		float total = 0;
		Set<Entry<String, Integer>> set;
		if (index != -1)
			set = Context.getInstance().getRecipes().get(index).getQuantity().entrySet();
		else
			set = Context.getInstance().getRecipeBeingCreated().getQuantity().entrySet();

		for (Entry<String, Integer> entry : set) {
			total += entry.getValue() * MedicineController.getInstance().getById(entry.getKey()).getPrice();
		}
		return total;
	}

	public void addMedicine(String string, int i) {
		LinkedHashMap<String, Integer> map = Context.getInstance().getRecipeBeingCreated().getQuantity();
		if (map.containsKey(string))
			map.put(string, map.get(string) + i);
		else
			map.put(string, i);
		MedicineInRecipe.getActiveInstance().fireTableDataChanged();

	}

}
