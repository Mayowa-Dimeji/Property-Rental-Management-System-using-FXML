package work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PropertyRentList implements Serializable {
	/**property Rent class definition
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PropertyRent> pList;

	public PropertyRentList() {
		pList = new ArrayList<PropertyRent>();
	}

	public List<PropertyRent> getRented() {
		return pList;
	}

	public void addRental(PropertyRent h) {
		pList.add(h);
	}
	public void removeRental(PropertyRent h) {
		pList.remove(h);
	}

	@Override
	public String toString() {
		return "PropertyRentList [pList=" + pList + "]";
	}


}
