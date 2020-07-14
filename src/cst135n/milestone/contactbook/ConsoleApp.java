package cst135n.milestone.contactbook;

/* Loom Link:
 * https://www.loom.com/share/7fa22a600b1c4236b5943a636519a236
 * 
 * Final Milestone - Philip
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;


public class ConsoleApp implements DataService {


	static final File file = new File("contact.txt");

	static AddressBook ab = new AddressBook();

	public static void main(String[] args) {

		ConsoleApp c = new ConsoleApp();
		c.read();
		c.write();

		ab.displayMenu();

		try {
			
			storePersonContact();
			storeBusinessContact();
		} catch (IOException e) {
			
			e.printStackTrace();
		}




	}

	@Override
	public void read() {
		System.out.println("Loading previous data...");
		
		try { 
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) !=null) {
				String [] part = line.split("\\|");
				switch (part[0]) {
				case "PersonContact":
					System.out.println("Creating Personal Contact");
					ab.bc.add(new PersonContact(Long.parseLong(part[2]), part[1], part[3], null, null, LocalDate.parse(part[4]), part[5], part[6], part[7]));
					break;
				case "BusinessContact":
					System.out.println("Creating Business Contact");
					ab.bc.add(new BusinessContact(Long.parseLong(part[2]), part[1], part[3], null, null, part[5], part[6]));
					break;
					default:
						System.out.println("Class not found");
				}
			}
			reader.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		


	}

	@Override
	public void write() {
		try {
			
			storePersonContact();
			storeBusinessContact();
			
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void storePersonContact() throws IOException {
		FileWriter wr = new FileWriter(file, false);
		System.out.println("Writing our Personal Contact to File");
		for (BaseContact base: ab.bc) {
			if(base.getClass().getSimpleName().equals("PersonContact")) { 
				System.out.println(((PersonContact)base).toFile());
				wr.write(((PersonContact)base).toFile() + System.lineSeparator());
			}
		}
		wr.close();
	}
	
	private static void storeBusinessContact() throws IOException {
		FileWriter wr = new FileWriter(file, false);
		System.out.println("Writing our Business Contact to File");
		for (BaseContact base: ab.bc) {
			if(base.getClass().getSimpleName().equals("BusinessContact")) { 
				System.out.println(((BusinessContact)base).toFile());
				wr.write(((BusinessContact)base).toFile() + System.lineSeparator());
			}
		}
		wr.close();
	}
}
