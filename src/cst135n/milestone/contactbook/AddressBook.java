package cst135n.milestone.contactbook;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import java.util.Scanner;

public class AddressBook {
	// Database Server and DB credentials
	static final String DB_URL = "jdbc:mysql://127.0.0.1/greendragon";
	static final String USER = "root";
	static final String PASS = "rootroot1!";

	static Connection connection;

	static final File file = new File("contact.txt");

	static AddressBook ab = new AddressBook();

	AddressBook() {
		try {
			// Register the JDBC Driver
			// The driver was added to the project in the pom.xml file
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Make the connection to the DB server and DB by passing in server credentials
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void login() {
		System.out.println("=================");
		System.out.println("      LOGIN      ");
		System.out.println("=================");

		System.out.println("ENTER USERNAME:");
		String username = sc.nextLine();
		System.out.println("ENTER PASSWORD:");
		String password = sc.nextLine();
		int user_id = -1;
		String business_role = null;

		try {
			// Write the SQL with ? as placeholders for user or code variables
			String sql = "SELECT user_id, user_name, password, business_role FROM user WHERE user_name = ? AND password = ?";

			// Create a PreparedStatement object so it look for the ? placeholders
			PreparedStatement stmt = connection.prepareStatement(sql);

			// Replace the ? with the correct number and type of variable (e.g. String)
			stmt.setString(1, username);
			stmt.setString(2, password);

			// Execute the query based on the PreparedStatement object, do not pass the sql
			// variable
			ResultSet results = stmt.executeQuery();

			// Loop through the results of the query
				while(results.next()) {
					System.out.println(user_id =  results.getInt("user_id"));
					System.out.println("USER = " + results.getString("user_name"));
					System.out.println("PASSWORD = " + results.getString("password"));
					System.out.println(business_role =  results.getString("business_role"));	
				}
			// Credentials are valid so return TRUE
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (user_id >= 0) {
			System.out.println("Congratulations you are logged in!");
			displayMenu();
		} else {
			System.out.println("Who are you? I don't recognize those credentials.");
		}
	}

	Scanner sc = new Scanner(System.in);

	public ArrayList<BaseContact> bc = new ArrayList<>();

	public void displayMenu() {
		menu: do {
			System.out.println("*******************");
			System.out.println("** CONTACT MENU ***");
			System.out.println("*******************");
			System.out.println("1] ADD PERSONAL CONTACT");
			System.out.println("2] ADD BUSINESS CONTACT");
			System.out.println("3] REMOVE CONTACT");
			System.out.println("4] SEARCH CONTACT");
			System.out.println("5] DISPLAY CONTACT");
			System.out.println("6] SORT CONTACT");
			System.out.println("7] EDIT CONTACT");
			System.out.println("0] EXIT");

			System.out.println("Select your option");
			switch (Integer.parseInt(sc.nextLine())) {
			case 1:
				addPersonContact();
				break;
			case 2:
				addBusinessContact();
				break;
			case 3:
				removeContact();
				break;
			case 4:
				searchContact();
				break;
			case 5:
				displayContact();
				break;
			case 6:
				sortContact();
				break;
			case 7:
				editContact();
				break;
			default:
				System.out.println("EXITING MENU");
				break menu;

			}

		} while (true);

	}

	private void addPersonContact() {
		ArrayList<Photo> photo = new ArrayList();

		// will count and numbe photos
		int countPhotoID = 0;

		// will allow user to add addtional photos
		boolean addAnother = true;

		System.out.println("****************************");
		System.out.println("*** ADD PERSONAL CONTACT ***");
		System.out.println("****************************");
		try {
			// System.out.println("Select: " + p + " Personal or " + b + " Business");
			// if (sc.nextLine().toUpperCase().equals(p)) {
			System.out.println("Contact Name : ");
			String name = sc.nextLine();
			System.out.println("Contact Number : ");
			long number = Long.parseLong(sc.nextLine());
			System.out.println("Contact Phone Type : ");
			String phone = sc.nextLine();
			System.out.println("Contact Birthday : ");
			System.out.println("Format : YYYY-MM-DD ");
			String date = sc.nextLine();
			LocalDate dob = LocalDate.parse(date);
			System.out.println("Contact Description : ");
			String description = sc.nextLine();
			System.out.println("Contact List : ");
			String list = sc.nextLine();
			System.out.println("Contact Relative : ");
			String relative = sc.nextLine();

			do {
				System.out.println("+++++++++++++++++++");
				System.out.println("+++Contact Photo+++");
				System.out.println("+++++++++++++++++++");
				System.out.println("Contact Photo ID : " + ++countPhotoID);
				System.out.println("Format : YYYY-MM-DD ");
				String date2 = sc.nextLine();
				LocalDate dop = LocalDate.parse(date2);
				System.out.println("Contact Photo Description : ");
				String notes = sc.nextLine();
				photo.add(new Photo(countPhotoID, dop, notes));
				System.out.println("Add another photo? (Y/N)");
				String ans = sc.nextLine().toUpperCase();
				if (ans.equals("N")) {
					addAnother = false;
				} else {
					addAnother = true;
				}

			} while (addAnother == true);

			System.out.println("++++++++++++++++++++++");
			System.out.println("+++Contact Location+++");
			System.out.println("++++++++++++++++++++++");

			System.out.println("Contact Street : ");
			String street = sc.nextLine();
			System.out.println("Contact City : ");
			String city = sc.nextLine();
			System.out.println("Contact State : ");
			String state = sc.nextLine();

			bc.add(new PersonContact(number, name, phone, photo, new Location(street, city, state), dob, description,
					list, relative));

		} catch (Exception e) {
			System.out.println("Invalid input. Try again.");
			addPersonContact();
		}

	}

	public void setBc(PersonContact p) {
		bc.add(p);

	}

	private void addBusinessContact() {
		ArrayList<Photo> photo = new ArrayList();
		// will count and numbe photos
		int countPhotoID = 0;

		// will allow user to add addtional photos
		boolean addAnother = true;
		try {

			// if (sc.nextLine().toUpperCase().equals(b)) {
			System.out.println("Contact Name : ");
			String name = sc.nextLine();
			System.out.println("Contact Number : ");
			int number = Integer.parseInt(sc.nextLine());
			System.out.println("Contact Phone Type : ");
			String phone = sc.nextLine();
			System.out.println("Contact Hours : ");
			String hours = sc.nextLine();
			System.out.println("Contact Website : ");
			String website = sc.nextLine();

			do {
				System.out.println("+++++++++++++++++++");
				System.out.println("+++Contact Photo+++");
				System.out.println("+++++++++++++++++++");
				System.out.println("Contact Photo ID : " + ++countPhotoID);
				System.out.println("Format : YYYY-MM-DD ");
				String date2 = sc.nextLine();
				LocalDate dop = LocalDate.parse(date2);
				System.out.println("Contact Photo Description : ");
				String notes = sc.nextLine();
				photo.add(new Photo(countPhotoID, dop, notes));
				System.out.println("Add another photo? (Y/N)");
				String ans = sc.nextLine().toUpperCase();
				if (ans.equals("N")) {
					addAnother = false;
				} else {
					addAnother = true;
				}

			} while (addAnother == true);

			System.out.println("++++++++++++++++++++++");
			System.out.println("+++Contact Location+++");
			System.out.println("++++++++++++++++++++++");

			System.out.println("Contact Street : ");
			String street = sc.nextLine();
			System.out.println("Contact City : ");
			String city = sc.nextLine();
			System.out.println("Contact State : ");
			String state = sc.nextLine();

			bc.add(new BusinessContact(number, name, phone, photo, new Location(street, city, state), hours, website));

		} catch (Exception e) {
			System.out.println("Invalid input. Try again.");
			addBusinessContact();
		}
	}

	public void removeContact() {
		boolean removeAnother = true;
		try {
			do {
				displayContact();
				System.out.println("**********************");
				System.out.println("*** REMOVE CONTACT ***");
				System.out.println("**********************");
				System.out.println("Which contact to remove : ");

				int contactid = Integer.parseInt(sc.nextLine()) - 1;
				System.out.println(bc.get(contactid) + "\n");
				bc.remove(contactid);

				System.out.println("Remove another contact? (Y/N)");
				String ans = sc.nextLine().toUpperCase();
				if (ans.equals("N")) {
					removeAnother = false;
				} else {
					removeAnother = true;
				}

			} while (removeAnother == true);
		} catch (Exception e) {
			System.out.println("Invalid input. Try again.");
			removeContact();
		}
	}

	public void searchContact() {
		System.out.println("**********************");
		System.out.println("*** SEARCH CONTACT ***");
		System.out.println("**********************");
		System.out.println("Which contact to search : ");

		String name = sc.nextLine();
		for (BaseContact c : bc) {
			if (c.getName().equals(name)) {
				System.out.println(c);
			}
		}
	}

	public void displayContact() {
		int counter = 1;
		System.out.println("***********************");
		System.out.println("*** DISPLAY CONTACT ***");
		System.out.println("***********************");

		for (BaseContact contact : bc) {
			System.out.println(counter++ + "|" + contact);
		}

	}

	public void sortContact() {
		sort: do {
			System.out.println("****************");
			System.out.println("** SORT MENU ***");
			System.out.println("****************");
			System.out.println("1] NAME");
			System.out.println("2] RELATIVE");
			System.out.println("3] LOCATION");
			System.out.println("4] ?????");
			System.out.println("0] EXIT");

			System.out.println("Select your option");
			switch (Integer.parseInt(sc.nextLine())) {
			case 1:

				Collections.sort(bc, (b1, b2) -> b1.getName().compareTo(b2.getName()));
				for (BaseContact contact : bc) {
					System.out.println(contact);
				}
				break;
			case 2:
				System.out.println("Sort by Relative group");
				System.out.println(bc);

				break;
			case 3:
				System.out.println("Sort by Location group");
				System.out.println(bc);
				// Collections.sort(bc, (b1, b2) ->
				// b1.getLocation().compareTo(b2.getLocation()));
				break;
			case 4:
				System.out.println("Sort by ????? group");
				break;
			default:
				System.out.println("EXITING MENU");
				break sort;
			}
		} while (true);

	}

	public void editContact() {
		displayContact();
		System.out.println("********************");
		System.out.println("*** EDIT CONTACT ***");
		System.out.println("********************");
		System.out.println("Which contact to edit : ");

		int contactid = Integer.parseInt(sc.nextLine()) - 1;
		System.out.println("Editting contact now: ");
		System.out.println(bc.get(contactid));
		if (bc.get(contactid).getClass().getSimpleName().equals("PersonContact"))
			editPersonContact((PersonContact) bc.get(contactid));

		else
			editBusinessContact((BusinessContact) bc.get(contactid));
	}

	private void editPersonContact(PersonContact bc) {
		System.out.println("Editting Personal Contact: ");

		try {

			System.out.println("Contact Name : ");
			bc.setName(sc.nextLine());
			System.out.println("Contact Number : ");
			bc.setNumber(Integer.parseInt(sc.nextLine()));
			System.out.println("Contact Phone Type : ");
			bc.setPhone(sc.nextLine());
			System.out.println("Contact Birthday : ");
			System.out.println("Format : YYYY-MM-DD ");
			String date = sc.nextLine();
			bc.setDob(LocalDate.parse(date));
			System.out.println("Contact List : ");
			bc.setList(sc.nextLine());
			System.out.println("Contact Relative : ");
			bc.setRelative(sc.nextLine());

			System.out.println("++++++++++++++++++++++");
			System.out.println("+++Contact Location+++");
			System.out.println("++++++++++++++++++++++");

			System.out.println("Contact Street : ");
			String street = sc.nextLine();
			System.out.println("Contact City : ");
			String city = sc.nextLine();
			System.out.println("Contact State : ");
			String state = sc.nextLine();

			bc.setLocation(new Location(street, city, state));
		} catch (Exception e) {
			System.out.println("Invalid input. Try again.");
			addPersonContact();
		}
	}

	private void editBusinessContact(BusinessContact bc) {
		System.out.println("Editting Business Contact: ");

		try {

			System.out.println("Contact Name : ");
			bc.setName(sc.nextLine());
			System.out.println("Contact Number : ");
			bc.setNumber(Integer.parseInt(sc.nextLine()));
			System.out.println("Contact Phone Type : ");
			bc.setPhone(sc.nextLine());
			System.out.println("Contact Hours : ");
			bc.setHours(sc.nextLine());
			System.out.println("Contact Website : ");
			bc.setWebsite(sc.nextLine());

			System.out.println("++++++++++++++++++++++");
			System.out.println("+++Contact Location+++");
			System.out.println("++++++++++++++++++++++");

			System.out.println("Contact Street : ");
			String street = sc.nextLine();
			System.out.println("Contact City : ");
			String city = sc.nextLine();
			System.out.println("Contact State : ");
			String state = sc.nextLine();

			bc.setLocation(new Location(street, city, state));
		} catch (Exception e) {
			System.out.println("Invalid input. Try again.");
			addPersonContact();
		}

	}

}
