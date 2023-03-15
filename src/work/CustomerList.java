package work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * CustomerList class definition
 * @author Mayowa
 *
 */
public final class CustomerList implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Customer> customerList;

	public CustomerList() {
		customerList = new ArrayList<Customer>();
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void addCustomer(Customer c) {
		customerList.add(c);
	}

	public void setCustomerList(Customer customer) {
		customerList.add(customer);
	}
	public void removeCustomer(Customer c) {
		customerList.remove(c);
	}

	@Override
	public String toString() {
		return "CustomerList [customerList=" + customerList + "]";
	}



}
