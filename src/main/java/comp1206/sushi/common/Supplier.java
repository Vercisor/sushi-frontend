package comp1206.sushi.common;

import javafx.scene.control.CheckBox;

public class Supplier extends Model {

	private String name;
	private Postcode postcode;
	private Number distance;
	private CheckBox select;

	public Supplier(String name, Postcode postcode) {
		this.name = name;
		this.postcode = postcode;
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

	public Postcode getPostcode() {
		return this.postcode;
	}
	
	public void setPostcode(Postcode postcode) {
		this.postcode = postcode;
	}

	public Number getDistance() {
		return postcode.getDistance();
	}

}
