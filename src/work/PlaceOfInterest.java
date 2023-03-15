package work;

import java.io.Serializable;
/**
 * Place of interest definition
 * @author Mayowa
 *
 */
public class PlaceOfInterest implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String postcode;
	private double latitude;
	private double longitude;




	public PlaceOfInterest(String name, String postcode, double latitude, double longitude) {

		this.name = name;
		this.postcode = postcode;
		this.latitude = latitude;
		this.longitude = longitude;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "PlaceOfInterest [name=" + name + ", postcode=" + postcode + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}




}
