package cst135n.milestone.contactbook;

import java.util.ArrayList;

// Child of BaseContact
public class BusinessContact extends BaseContact {

	private String hours;
	private String website;

	public BusinessContact(long number, String name, String phone, ArrayList<Photo> photo, Location location,
			String hours, String website) {
		super(number, name, phone, photo, location);
		this.hours = hours;
		this.website = website;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public String toString() {
		return "BusinessContact -  Name: " + getName() + "| Number: " + getNumber() + "| Phone : " + getPhone()
				+ "| Hours: " + hours + "| Website: " + website + "| Photo : " + getPhoto() + "| Location: "
				+ getLocation();
	}
	public String toFile() {
		return "BusinessContact|" + getName() + "|" + getNumber() + "|" + getPhone()
				+ "|" + hours + "|" + website + "|" + getPhoto() + "|"
				+ getLocation();
	}
}
