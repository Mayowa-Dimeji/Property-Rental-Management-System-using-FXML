package work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * property list class definition
 * @author Mayowa
 *
 */
public class PropertyList implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Property> propList;

	public PropertyList() {
		propList=new ArrayList<Property>();
	}

	public void addProperty(Property b) {
		propList.add(b);
	}

	public  void setPropertyList(List<Property> pList) {
		this.propList = pList;
	}
	public List<Property> getProperties() {
		return propList;
	}
	public void removeProperty(Property b) {
		propList.remove(b);
	}

	@Override
	public String toString() {
		return "PropertyList [propList=" + propList + "]";
	}

}
