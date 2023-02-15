package work;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * Customer class definition
 * @author Mayowa Olufunke Oladimeji
 *
 */
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private int CustomerID=0;
	private String emailAdress;
	private String address;
	private String phoneNumber;
	private LocalDate dob;
	private static int count = 0;


	public Customer(String firstName, String lastName, String emailAddress,
			String address, String phoneNumber, LocalDate dob) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAdress = emailAddress;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.CustomerID=count++;
	}


	public Customer() {
		// TODO Auto-generated constructor stub
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public int getCustomerID() {
		return CustomerID;
	}


	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}


	public String getEmailAdress() {
		return emailAdress;
	}


	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
	}



	public static int getCount() {
		return count;
	}


	public static void setCount(int count) {
		Customer.count = count;
	}


	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", CustomerID=" + CustomerID
				+ ", emailAddress=" + emailAdress + ", address=" + address + ", phoneNumber=" + phoneNumber + ", dob="
				+ dob + "]";
	}










}
