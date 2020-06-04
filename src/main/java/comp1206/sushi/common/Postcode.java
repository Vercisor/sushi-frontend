package comp1206.sushi.common;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.util.HashMap;
import java.util.Map;

public class Postcode extends Model {

	private String name;
	private Map<String,Double> latLong;
	private Number distance;

	private CheckBox select;

	public Postcode(String code) {
		this.name = code;
		calculateLatLong();
		this.distance = Integer.valueOf(0);
		this.select = new CheckBox();
	}

	public CheckBox getSelect() {
		return select;
	}

	public Postcode(String code, Restaurant restaurant) {
		this.name = code;
		calculateLatLong();
		calculateDistance(restaurant);
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Number getDistance() {
		return this.distance;
	}

	public Map<String,Double> getLatLong() {
		return this.latLong;
	}
	
	protected void calculateDistance(Restaurant restaurant) {
		//This function needs implementing
		Postcode destination = restaurant.getLocation();
		this.distance = Integer.valueOf(0);
	}
	
	protected void calculateLatLong() {
		//This function needs implementing
		this.latLong = new HashMap<String,Double>();
		latLong.put("lat", 0d);
		latLong.put("lon", 0d);
		this.distance = new Integer(0);
	}
	
}
