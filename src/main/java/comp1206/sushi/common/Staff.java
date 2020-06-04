package comp1206.sushi.common;


import javafx.scene.control.CheckBox;

public class Staff extends Model {

	private String name;
	private String status;
	private Number fatigue;
	private CheckBox select;
	
	public Staff(String name) {
		this.setName(name);
		this.setFatigue(0);
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

	public Number getFatigue() {
		return fatigue;
	}

	public void setFatigue(Number fatigue) {
		this.fatigue = fatigue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		notifyUpdate("status",this.status,status);
		this.status = status;
	}

}
