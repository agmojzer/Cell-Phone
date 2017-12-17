package business;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import util.DBUtil;

public class ContactDB {
	
	private String myNumber;
	private ArrayList<Contact> myContacts;
	
	public ContactDB (String myNumber) {
		this.myNumber = myNumber;
		this.myContacts = new ArrayList<Contact>();
	}
	
	public static Contact getContactByID(int contactId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Contact contact = em.find(Contact.class, contactId);
			return contact;
		}
		finally {
			em.close();
		}
	}
	
//	public boolean addNewContact(Contact contact) {
//		if(findContact(contact.getName()) >=0) {
//			System.out.println("Contact is already on file");
//			return false;
//		}else {
//			myContacts.add(contact);
//		}return true;
//	}
	
	public static boolean addNewContact(Contact c){
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.persist(c);
			et.commit();
			success = true;
			
		}
		catch (Exception e) {
			//e.printStackTrace();
			//et.rollback();
		    System.err.println("Error: Contact name or phone number already exists.");

		}
		finally {
			em.close();
		}
		return success;
	}
	
	public boolean updateContact(Contact oldContact, Contact newContact) {
		int foundPosition = findContact(oldContact);
		if (foundPosition <0) {
			System.out.println(oldContact.getName() +", was not found.");
			return false;
		}else {		
		this.myContacts.set(foundPosition, newContact);
		System.out.println(oldContact.getName()+ " was replaced with "+newContact.getName()+".");
		return true;
		}
	}
	
	//
	//Find a way to combine these//
	//
	
	public static boolean updateContact(Contact c) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(c);
			et.commit();
			success = true;
		}
		catch (Exception e) {
			//e.printStackTrace();
			//et.rollback();
			System.err.println("Error: Contact name or phone number already exists.");
		}
		finally {
			em.close();
		}
		return success;
	}
	
	public static boolean removeContact(Contact c) 
	{
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.remove(em.merge(c));
			et.commit();
			success = true;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		}
		finally {
			em.close();
		}
		return success;
	}
	
	private int findContact(Contact contact) {
		return this.myContacts.indexOf(contact);
	}
	
	private int findContact(String contactName) {
		for (int i=0; i<this.myContacts.size(); i++) {
			Contact contact = this.myContacts.get(i);
			if(contact.getName().equals(contactName)) {
				return i;
			}
		}
		return -1;
	}
	
	public String queryContact(Contact contact) {
		if (findContact(contact) >=0) {
			return contact.getName();
		}
		return null;
	}

	
	public static Contact queryContact(int id) {
		//int position = findContact(name);
	//	if (position >=0) {
		//	return this.myContacts.get(position);
		//	}return null;	
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Contact c = em.find(Contact.class, id);
			return c;			
		}
		finally {
			em.close();
		}
	}
	
	public static ArrayList<Contact> displayContacts() {
		//System.out.println("Contact List");
		//for(int i=0; i<myContacts.size(); i++) {
			//System.out.println((i+1)+"."+this.myContacts.get(i).getName()+":"+this.myContacts.get(i).getPhonenumber());
		//}
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			TypedQuery <Contact>query = em.createQuery("SELECT c FROM Contact c", Contact.class);
			ArrayList<Contact> allContacts = new ArrayList<Contact>(query.getResultList());
			return allContacts;
		}finally {
			em.close();
		}
	}
	
	
}


