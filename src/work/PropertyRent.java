package work;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PropertyRent implements Serializable{


	private static final long serialVersionUID = 1L;
	private Property property;
	private Customer customer;
	private LocalDate startDate;
	private LocalDate endDate;
	private double damage;

	/**
	 * Constructor for class
	 * @param p
	 * @param c
	 * @param sdate
	 */
	public PropertyRent(Property p,Customer c,LocalDate sdate) {
		this.property=p;
		this.customer=c;
		this.startDate=sdate;
		this.endDate=null;
		this.damage=0;
	}


	public Property getProperty() {
		return property;
	}


	public void setProperty(Property property) {
		this.property = property;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public double getDamage() {
		return damage;
	}


	public void setDamage(double damage) {
		this.damage = damage;
	}

	/**
	 * this method provides the begin invoice layout
	 * @return String of begin invoice
	 */
	public String printBeginInvoice() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

		String s="Rent Invoice\nProperty to Rent:"+ property.getPostcode()+"\nRent: £"+property.getRent()+"\nDeposit includes 6 months Rent and Agency fee"+"\nDeposit: £"+property.getDeposit()+"\nDate: "+formatter.format(startDate);	 


		return s;	
	}
	/**
	 * this method provides end invoice layout 
	 * @return String of invoice
	 */
	public String printEndInvoice() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");


		String s="Final Invoice\nProperty Rented:"+ property.getPostcode()+"\nRent(PCM): £"+property.getRent()+calcTotal()+"\nFinal Date: "+formatter.format(endDate);	 


		return s;	
	}
	/**
	 * this method provides renewal invoice layout 
	 * @return String of invoice
	 */
	public String printRenewalInvoice() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");


		String s="Previous Rental Invoice\nProperty Rented:"+ property.getPostcode()+"\nRent(PCM): £"+property.getRent()+calcTotal()+"\nEnd Date: "+formatter.format(endDate)+"\n\nCustomers must pay any outstanding balances\nSimilarly, Any balance owed will be forwarded towards new rental contract";	 


		return s;	
	}

	/**
	 * helper method to calculate total rent
	 * @return
	 */
	public double getTotal() {

		long diffMonths =ChronoUnit.MONTHS.between(startDate, endDate);
		double total=property.getRent()*diffMonths;
		return total;

	}
	/**
	 * helper method for calculating outstanding balance and choosing appropriate layout
	 * @return String of information
	 */
	public String calcTotal() {

		double outstanding=(getTotal()-property.getDeposit())+damage;
		String s="\nThe final amount for the rental stay is : £"+getTotal()+"\nThe damages during the rental stay : £"+damage;
		if(outstanding<0) {
			s +="\nYour are to collect: £"+Math.abs(outstanding);
		}
		else if(outstanding>0) {
			s +="\nThe amount owed is : £"+outstanding;
		}
		else {
			s +="\nThere are no outstanding balances";
		}
		return s;
	}

	@Override
	public String toString() {
		return "PropertyRent [property=" + property + ", customer=" + customer + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", damage=" + damage + "]";
	}



}
