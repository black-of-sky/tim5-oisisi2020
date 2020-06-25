package team5.model;

public class Medicine {
	private String id, title, producer;
	private boolean recipe;
	private float price;
	private boolean deleted = false;

	public Medicine(String id, String title, String producer, boolean recipe, float price) {
		this.id = id;
		this.title = title;
		this.producer = producer;
		this.recipe = recipe;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public boolean isRecipe() {
		return recipe;
	}

	public void setRecipe(boolean recipe) {
		this.recipe = recipe;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
