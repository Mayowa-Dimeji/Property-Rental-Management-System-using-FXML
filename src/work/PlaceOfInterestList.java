package work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * place of interest list definition
 * @author Mayowa
 *
 */
public class PlaceOfInterestList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<PlaceOfInterest> placesOfInterestlist;


	public PlaceOfInterestList() {
		placesOfInterestlist = new ArrayList<PlaceOfInterest>();
	}


	public List<PlaceOfInterest> getPlacesOfInterestlist() {
		return placesOfInterestlist;
	}


	public void addPlacesOfInterest(PlaceOfInterest p) {
		placesOfInterestlist.add(p);
	}
	public void removePlacesOfInterest(PlaceOfInterest p) {
		placesOfInterestlist.remove(p);
	}


	@Override
	public String toString() {
		return "PlaceOfInterestList [placesOfInterestlist=" + placesOfInterestlist + "]";
	}


}
