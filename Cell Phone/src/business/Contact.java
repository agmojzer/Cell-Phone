package business;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String phonenumber;
	
	public Contact(String name, String phoneNumber) {
		super();
		this.name = name;
		this.phonenumber = phoneNumber;	
	}
	
	public Contact() {
		name = "";
		phonenumber ="";
	}



	public String getName() {
		return name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

//	public static Contact createContact(String name, String phoneNumber) {
//		return new Contact(name, phoneNumber);
//	}

	@Override
	public String toString() {
		return "Contact: id="+id+ ", name=" + name + ", phonenumber=" + phonenumber;
	}
	
	
}
