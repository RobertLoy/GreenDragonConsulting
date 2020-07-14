package cst135n.milestone.contactbook;

import java.time.LocalDate;

public class Photo {

	private int id;
	private LocalDate dop;
	private String notes;
	public Photo(int id, LocalDate dop, String notes) {
		super();
		this.id = id;
		this.dop = dop;
		this.notes = notes;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDop() {
		return dop;
	}
	public void setDop(LocalDate dop) {
		this.dop = dop;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Override
	public String toString() {
		return "Photo : " + "| Date of Photo: " + dop + "| Notes: " + notes;
	}
	
	public String toFile() {
		return "Photo|"  + dop + "|" + notes;
	}
}
