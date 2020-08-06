package cst135n.milestone.contactbook;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;
import java.util.Scanner;


public class ConsoleApp  {

	static final File file = new File("contact.txt");

	static AddressBook ab = new AddressBook();

	public static void login() {
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
			while (results.next()) {
				System.out.println(user_id = results.getInt("user_id"));
				System.out.println("USER = " + results.getString("user_name"));
				System.out.println("PASSWORD = " + results.getString("password"));
				System.out.println(business_role = results.getString("business_role"));
			}
			// Credentials are valid so return TRUE
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (user_id >= 0 && business_role.contentEquals("admin")) {
			System.out.println("Congratulations you are logged in!");
			ab.displayAdminMenu();
		} 
		if (user_id >= 0 && business_role.contentEquals("user")) {
			System.out.println("Congratulations you are logged in!");
			ab.displayUserMenu();
		}else {
			System.out.println("Who are you? I don't recognize those credentials.");
		}
	}

	public static void main(String[] args) {
		login();
		
		ConsoleApp c = new ConsoleApp();
		c.read();
		c.write();
		try {
			
			storePersonContact();
			storeBusinessContact();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

		ConsoleApp c = new ConsoleApp();
		ab.displayMenu();


	}

}
