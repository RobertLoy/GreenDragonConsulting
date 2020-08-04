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

	public static void main(String[] args) {

		
		ConsoleApp c = new ConsoleApp();

		ab.displayMenu();

	}

}
