package comp1206.sushi.common;

import javafx.scene.control.CheckBox;

public class Ingredient extends Model {

	private String name;
	private String unit;
	private Supplier supplier;
	private Number restockThreshold;
	private Number restockAmount;
	private CheckBox select;

	public Ingredient(String name, String unit, Supplier supplier, Number restockThreshold,
			Number restockAmount) {
		this.setName(name);
		this.setUnit(unit);
		this.setSupplier(supplier);
		this.setRestockThreshold(restockThreshold);
		this.setRestockAmount(restockAmount);
		this.select = new CheckBox();
	}

	public CheckBox getSelect() {
		return select;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Number getRestockThreshold() {
		return restockThreshold;
	}

	public void setRestockThreshold(Number restockThreshold) {
		this.restockThreshold = restockThreshold;
	}

	public Number getRestockAmount() {
		return restockAmount;
	}

	public void setRestockAmount(Number restockAmount) {
		this.restockAmount = restockAmount;
	}

}
