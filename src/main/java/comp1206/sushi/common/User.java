package comp1206.sushi.common;

import javafx.scene.control.CheckBox;

public class User extends Model {
	
	private String name;
	private String password;
	private String address;
	private Postcode postcode;
	private CheckBox select;

	public User(String username, String password, String address, Postcode postcode) {
		this.name = username;
		this.password = password;
		this.address = address;
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

	public Number getDistance() {
		return postcode.getDistance();
	}

	public Postcode getPostcode() {
		return this.postcode;
	}
	
	public void setPostcode(Postcode postcode) {
		this.postcode = postcode;
	}

}
