package cst135n.milestone.contactbook;

public class Location {
	
	
	private String street;
	private String city;
	private String state;
	
	
	public Location(String street, String city, String state) {
		super();
		
		this.street = street;
		this.city = city;
		this.state = state;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	@Override
	public String toString() {
		return "Location | Street: " + street + "| City: " + city + "| State: " + state;
	}
	
	public String toFile() {
		return "Location|" + street + "|" + city + "|" + state;
	}
	
	
	

}
