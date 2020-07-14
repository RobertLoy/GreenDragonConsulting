package cst135n.milestone.contactbook;

import java.util.ArrayList;
// parent class of PersonContact and BusinessContact
public class BaseContact {
	
	private long number;
	private String name;
	private String phone;
	// Has-A relationship
	private ArrayList<Photo> photo = new ArrayList<>();
	private Location location = new Location(name, phone, name);
	
	
	public BaseContact(long number, String name, String phone, ArrayList<Photo> photo, Location location) {
		super();
		this.number = number;
		this.name = name;
		this.phone = phone;
		this.photo = photo;
		this.location = location;
	}


	public long getNumber() {
		return number;
	}


	public void setNumber(long number) {
		this.number = number;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public ArrayList<Photo> getPhoto() {
		return photo;
	}


	public void setPhoto(ArrayList<Photo> photo) {
		this.photo = photo;
	}


	public Location getLocation() {
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
	}

	

	@Override
	public String toString() {
		return "BaseContact - "  + "Name" + name  + " | Number: " + number + "| Phone" + phone + "| Photo:" + photo
				+ "| Location:" + location;
	}
	
	public String toFile() {
		return "BaseContact|"+ name  + "|" + number + "|" + phone + "|" + photo
				+ "|" + location;
	}

}
