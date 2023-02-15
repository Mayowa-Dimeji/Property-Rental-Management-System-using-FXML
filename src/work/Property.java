package work;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * property class definition
 * @author Mayowa
 *
 */
public class Property implements Serializable{
	private static final long serialVersionUID = 1L;
	private LocalDate listDate;
	private int bedrooms;
	private int bathrooms;
	private double rent;
	private int size;
	private String postcode;
	private double latitude;
	private double longitude;
	private String type;
	private String garden;
	private boolean rentStatus;


	public Property(LocalDate listDate, int bedrooms, int bathrooms, double rent, int size, String postcode,
			double latitude, double longitude, String type, String garden, boolean rentStatus) {

		this.listDate = listDate;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.rent = rent;
		this.size = size;
		this.postcode = postcode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.type = type;
		this.garden = garden;
		this.rentStatus = rentStatus;
	}


	public LocalDate getListDate() {
		return listDate;
	}


	public void setListDate(LocalDate listDate) {
		this.listDate = listDate;
	}


	public int getBedrooms() {
		return bedrooms;
	}

	public double getDeposit() {
		double d=(6*rent)+(0.2*rent);

		return d;

	}




	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}


	public int getBathrooms() {
		return bathrooms;
	}


	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}


	public double getRent() {
		return rent;
	}


	public void setRent(double rent) {
		this.rent = rent;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public String getPostcode() {
		return postcode;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getGarden() {
		return garden;
	}


	public void setGarden(String garden) {
		this.garden = garden;
	}


	public boolean isRentStatus() {
		return rentStatus;
	}


	public void setRentStatus(boolean rentStatus) {
		this.rentStatus = rentStatus;
	}


	@Override
	public String toString() {
		return "Property [listDate=" + listDate + ", bedrooms=" + bedrooms + ", bathrooms=" + bathrooms + ", rent="
				+ rent + ", size=" + size + ", postcode=" + postcode + ", latitude=" + latitude + ", longitude="
				+ longitude + ", type=" + type + ", garden=" + garden + ", rentStatus=" + rentStatus + "]";
	}



}
