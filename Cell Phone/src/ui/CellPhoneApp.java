package ui;
import java.util.ArrayList;

import business.Contact;
import business.ContactDB;
import util.Console;
import util.Utilities;

public class CellPhoneApp {
	
	public static void main(String[] args) {
		
		boolean quit = false;
		startPhone();
		Utilities.displayMenu();
		while(!quit) {
			
			int action = Console.getInt("Enter menu choice:", 0, 6);
			
			switch (action) {
			case 0:System.out.println("Shutting down");
			quit = true;
			break;
			
			case 1: displayContacts();
			break;
			
			case 2: addContact();
			break;
			
			case 3: updateContact();
			break;
			
			case 4: deleteContact();
			break;
			
			case 5: queryContact();
			break;
			
			case 6: Utilities.displayMenu();
			break;
			}
			
//			if (action == 0) {
//				System.out.println("Shutting down"); 
//				quit = true;	
//			}else if(action ==1) {
//				displayContacts();	
//			}else if (action ==2) {
//				addContact();
//			}else if (action ==3) {
//				updateContact();
//			}else if (action ==4) {
//				deleteContact();
//			}else if (action ==5) {
//				queryContact();
//			}else {
//				printMenu();
//			}
		}
	}
	
	private static void addContact() {
		String name = Console.getString("Enter contact's name: ");
		long number = Console.getLong("Enter phone number: ", 2000000000, 9999999999L);
		String numberStr = Long.toString(number);
		String part1 = numberStr.substring(0, 3);
		String part2 = numberStr.substring(3, 6);
		String part3 = numberStr.substring(6, 10);
		numberStr = "("+part1 + ") " + part2 + "-"+part3; 
		//Contact newContact = Contact.createContact(name, numberStr);
		Contact c = new Contact (name, numberStr);
		if (ContactDB.addNewContact(c)) {
			System.out.println("Contact added: "+ name +" "+ numberStr );
			System.out.println();
		}else {
			//System.out.println(name + " already on file.");
			System.out.println();
			Utilities.displayMenu();
		}
	}
	
	private static void displayContacts() {
		ArrayList <Contact> contacts =  ContactDB.displayContacts();
		//line below skips entry in row one.
		for (Contact c:contacts.subList(1, contacts.size())) {
			System.out.println(c);
			}	
		System.out.println();
		Utilities.displayMenu();
	}
	
	private static void updateContact() {
		ArrayList <Contact> contacts =  ContactDB.displayContacts();
		//line below skips entry in row one.
		for (Contact c:contacts.subList(1, contacts.size())) {
			System.out.println(c);
		}
		int id = Console.getInt("Enter existing contact's ID: ");
		Contact c=ContactDB.queryContact(id);
		if (c == null) {
			System.out.println("Contact not found.");
			System.out.println();
			Utilities.displayMenu();
			return;
		}
		String newName = Console.getString("Enter new contact name: ");
		long newNumber = Console.getLong("Enter new number: ", 1000000000, 9999999999L);
		String numberStr = Long.toString(newNumber);
		String part1 = numberStr.substring(0, 3);
		String part2 = numberStr.substring(3, 6);
		String part3 = numberStr.substring(6, 10);
		numberStr = "("+part1 + ") " + part2 + "-"+part3; 
		//Contact newContact = Contact.createContact(newName, numberStr);
		//Contact c = new Contact (newName, numberStr);
		c.setName(newName);
		c.setPhonenumber(numberStr);
		if (ContactDB.updateContact(c)) {
			System.out.println("Record updated");
			System.out.println();
		}else {
			//System.out.println("Error updating contact.");
			System.out.println();
		}
		Utilities.displayMenu();
	}
	
	private static void deleteContact() {
		ArrayList <Contact> contacts =  ContactDB.displayContacts();
		//line below skips entry in row one.
		for (Contact c:contacts.subList(1, contacts.size())) {
			System.out.println(c);
		}
		int id = Console.getInt("Enter existing contact's ID: ");
		Contact c=ContactDB.queryContact(id);
		if (c == null) {
			System.out.println("Contact not found.");
			System.out.println();
			Utilities.displayMenu();
			return;
		}
		if (ContactDB.removeContact(c)) {
			System.out.println("Contact deleted.");
			System.out.println();
		}else {
			System.out.println("Error deleting contact");
			System.out.println();
		}
		Utilities.displayMenu();
	}
	
	private static void queryContact() {
		int id = Console.getInt("Enter existing contact's id: ");
		Contact existingContact=ContactDB.queryContact(id);
		if (existingContact == null) {
			System.out.println("Contact not found.");
			System.out.println();
			Utilities.displayMenu();
			return;
		}
		System.out.println("Name: "+existingContact.getName()+"'s number is "+existingContact.getPhonenumber());
		System.out.println();
		Utilities.displayMenu();
	}
	
	
	private static void startPhone() {
		System.out.println("Starting phone...");
		System.out.println();
	}
	
//	private static void printMenu() {
//		System.out.println("####Menu Options####");
//		System.out.println("Press:\n"+
//							"0 to shut down\n"+
//							"1 to display contacts\n"+
//							"2 to add a new contact\n"+
//							"3 to update a contact\n"+
//							"4 to delete a contact\n"+
//							"5 to see if a contact exists\n"+
//							"6 to print a list available actions");
//		System.out.println();
//	}

}
