package cst135n.milestone.contactbook;

import java.util.Comparator;

class Sort implements Comparator <BaseContact> {


	public int compare(BaseContact b1, BaseContact b2) {
		return b1.getName().compareTo(b2.getName());
	}

	public String toString(){
		  return "Sort";
		 }
}
