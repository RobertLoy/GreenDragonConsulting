package cst135n.milestone.contactbook;

import java.sql.Date;
import java.util.ArrayList;

//Child of BaseContact
public class PersonContact extends BaseContact {
	// TEST
	private Date dob;
	private String description;
	private String list;
	private String relative;

	public PersonContact(long number, String name, String phone, ArrayList<Photo> photo, Location location,
			Date dob, String description) {
		super(number, name, phone, photo, location);
		this.dob = dob;
		this.description = description;
		
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String notes) {
		this.description = notes;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getRelative() {
		return relative;
	}

	public void setRelative(String relative) {
		this.relative = relative;
	}

	@Override
	public String toString() {
		return "PersonContact -  Name: " + getName() + "| Number: " + getNumber() + "| Phone: " + getPhone() + "| Birthday: " + dob + "| Notes: " + description + "| List: " + list + "| Relative: " + relative
				+  "| Photo: "
				+ getPhoto() + "| Location: " + getLocation();
	}
	public String toFile() {
		return "PersonContact|" + getName() + "|" + getNumber() + "|" + getPhone() + "|" + dob + "|" + description + "|" + list + "|" + relative
				+  "|"
				+ getPhoto() + "|" + getLocation();
	}
}
