package cst135n.milestone.contactbook;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.sql.*;

public class AddressBook {

	private Scanner sc = new Scanner(System.in);

	String pattern = "yyyy-MM-dd";
	SimpleDateFormat format = new SimpleDateFormat(pattern);

	public ArrayList<BaseContact> bc = new ArrayList<>();

	static final String DB_URL = "jdbc:mysql://127.0.0.1/greendragon";
	static final String USER = "DB_access";
	static final String PASS = "db_p@$$word";

	
	static Connection connection;

	AddressBook() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void displayUserMenu() {
		
		// ROB - Added the DO Loop
		
		menu: do {
			System.out.println("*******************");
			System.out.println("** CONTACT MENU ***");
			System.out.println("*******************");
			System.out.println("0] EXIT");
			System.out.println("1] ADD CONTACT (PERSONAL)");
			System.out.println("2] ADD CONTACT (BUSINESS)");
			System.out.println("3] VIEW ALL CONTACT");
			System.out.println("4] VIEW A CONTACT'S DETAILS");
			System.out.println("5] UPDATE A CONTACT");
			System.out.println("6] DELETE A CONTACT");

			System.out.println("Select your option...");
			switch (Integer.parseInt(sc.nextLine())) {
			case 1:
				addPersonContact();
				break;
			case 2:
				addBusinessContact();
				break;
			case 3:
				displayContact();
				break;
			case 4:
				searchContact();
				break;
			case 5:
				editContact();
				break;
			case 6:
				removeContact();
				break;
			default:
				System.out.println("EXITING MENU");
				break menu;

			}

		} while (true);

	}

	public void displayAdminMenu() {

		menu: do {
			System.out.println("*******************");
			System.out.println("** CONTACT MENU ***");
			System.out.println("*******************");
			System.out.println("0] EXIT");
			System.out.println("1] ADD A USER");
			System.out.println("2] VIEW ALL USERS");
			System.out.println("3] VIEW USER'S DETAILS");
			System.out.println("4] VIEW BUSINESS CONTACTS FOR ALL USERS");
			System.out.println("5] UPDATE USER'S PASSWORD");
			System.out.println("6] DELETE A SHOW");
			System.out.println("7] EXPORT USER EMAILS TO TEXT FILE");
			System.out.println("8] EXPORT USER WEBSITES TO TEXT FILE");

			System.out.println("Select your option...");
			switch (Integer.parseInt(sc.nextLine())) {
			case 1:

				break;
			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

				break;
			case 6:
				break;
			case 7:
				writeEmail();
				break;
			case 8:
				writeBusUrl();
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

		int userId = 4;

		// will allow user to add addtional photos
		boolean addAnother = true;

		System.out.println("****************************");
		System.out.println("*** ADD PERSONAL CONTACT ***");
		System.out.println("****************************");
		try {

			String sql = "INSERT INTO contact (type, name, last_name, phone_type, phone_num, email, dob, description, photo_id, user_id) VALUES (?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement stmt = connection.prepareStatement(sql);

			String type = "p";
			System.out.println("Contact First Name : ");
			String name = sc.nextLine();
			System.out.println("Contact Last Name : ");
			String last_name = sc.nextLine();
			System.out.println("Contact Number : ");
			long phone_num = Long.parseLong(sc.nextLine());
			System.out.println("Contact Phone Type : ");
			String phone_type = sc.nextLine();
			System.out.println("Contact Email : ");
			String email = sc.nextLine();
			System.out.println("Contact Birthday : ");
			System.out.println("Format : YYYY-MM-DD ");
			String date = sc.nextLine();
			Date dob = new Date(format.parse(date).getTime());
			System.out.println("Contact Description : ");
			String description = sc.nextLine();

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

			bc.add(new PersonContact(phone_num, name, phone_type, photo, new Location(street, city, state), dob,
					description));
      
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, type);
			stmt.setString(2, name);
			stmt.setString(3, last_name);
			stmt.setString(4, phone_type);
			stmt.setLong(5, phone_num);
			stmt.setString(6, email);
			stmt.setDate(7, dob);
			stmt.setString(8, description);
			stmt.setInt(9, countPhotoID);
			stmt.setInt(10, userId);
			stmt.execute();
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
		// will count and number photos
		int countPhotoID = 0;

		int userId = 4;
		// will allow user to add addtional photos
		boolean addAnother = true;

		try {

			String sql = "INSERT INTO contact (type, name, phone_type, phone_num, email, hours, website, description, photo_id, user_id) VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			// if (sc.nextLine().toUpperCase().equals(b)) {
			System.out.println("Contact Type : ");
			String type = "B";
			System.out.println("Contact Name : ");
			String name = sc.nextLine();
			System.out.println("Contact Phone Type : ");
			String phone_type = sc.nextLine();
			System.out.println("Contact Phone Number : ");
			long phone_num = Long.parseLong(sc.nextLine());
			System.out.println("Contact Email : ");
			String email = sc.nextLine();
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

			// ROB - Updated names to match inputs
			bc.add(new BusinessContact(phone_num, name, phone_type, photo, new Location(street, city, state), hours, website));

		} catch (SQLException e) {
			System.out.println("Invalid input. Try again.");

		}
	}

	public void removeContact() {
		boolean removeAnother = true;
		try {
			String sql = "SELECT contact_id, type, name, last_name, phone_type, phone_num FROM contact";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				System.out.print(results.getInt("contact_id") + "] ");
				System.out.print("\t" + results.getString("type") + "] ");
				System.out.print("\t" + results.getString("name") + "] ");
				System.out.print("\t" + results.getString("last_name") + "] ");
				System.out.print("\t" + results.getString("phone_type") + "] ");
				System.out.print("\t" + results.getLong("phone_num") + "\n");
			}
			System.out.println("Which CONTACT item to delete [THERE IS NO UNDO]?");
			int id = sc.nextInt();
			// DELETE a record from the table
			sql = "DELETE FROM CONTACT WHERE contact_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Oops, try again!");
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

		try {

			String sql = "SELECT last_name, name FROM contact ORDER BY last_name DESC";
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(sql);
			System.out.println("Contact");
			System.out.println("==========");
			while (results.next()) {
				String last = results.getString("last_name");
				String first = results.getString("name");
				System.out.println(last + ", " + first);
			}

		} catch (SQLException e) {

			e.printStackTrace();
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

			bc.setDob(new Date(format.parse(date).getTime()));

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

	public void writeEmail() {
		String path = "contact.txt";

		try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
			String sql = "SELECT name, last_name, email FROM contact";

			Statement statement = connection.createStatement();

			ResultSet result = statement.executeQuery(sql);

			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(path));

			// write header line containing column names
			fileWriter.write("NAME, LAST NAME, EMAIL:");

			while (result.next()) {
				String name = result.getString("name");
				String last_name = result.getString("last_name");
				String email = result.getString("email");

				String line = String.format("\"%s\",%s,%s", name, last_name, email);

				fileWriter.newLine();
				fileWriter.write(line);
			}

			statement.close();
			fileWriter.close();

		} catch (SQLException e) {
			System.out.println("Datababse error:");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("File IO error:");
			e.printStackTrace();
		}

	}

	public void writeBusUrl() {
		String path = "contact.txt";

		try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
			String sql = "SELECT name, last_name, website FROM contact";
//			String sql = "SELECT website FROM contact";
			Statement statement = connection.createStatement();

			ResultSet result = statement.executeQuery(sql);

			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(path));

			// write header line containing column names
			fileWriter.write("NAME, LAST NAME, WEBSITE:");

			while (result.next()) {
				String name = result.getString("name");
				String last_name = result.getString("last_name");
				String website = result.getString("website");

				String line = String.format("\"%s\",%s,%s", name, last_name, website);
//				String line = String.format( website);
				fileWriter.newLine();
				fileWriter.write(line);
			}

			statement.close();
			fileWriter.close();

		} catch (SQLException e) {
			System.out.println("Datababse error:");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("File IO error:");
			e.printStackTrace();
		}

	}
}
