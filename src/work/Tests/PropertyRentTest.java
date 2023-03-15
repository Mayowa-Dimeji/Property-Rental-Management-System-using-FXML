package work.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import work.Customer;
import work.Property;
import work.PropertyRent;

class PropertyRentTest {
	private Property p;
	private Customer c;
	private PropertyRent rent;



	@BeforeEach
	void setUp() throws Exception {
		p=new Property(null, 0, 0, 1000, 0, null, 0, 0, null, null, false);
		c=new  Customer ();
		rent= new PropertyRent(p, c, null);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		this.p=null;
		this.c=null;
		this.rent=null;
	}

	@Test
	void testGetDeposit() {
		System.out.println(p.getDeposit());
		assertEquals(6200, p.getDeposit(), 0.1);
	}

	@Test
	void testGetTotal() {
		rent.setStartDate(LocalDate.of(2020,01,01));
		rent.setEndDate(LocalDate.of(2021,01,01));
		System.out.println(rent.getTotal());
		assertEquals(12000, rent.getTotal(), 0.1);
	}

}
