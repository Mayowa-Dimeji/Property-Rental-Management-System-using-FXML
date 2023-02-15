package work;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

public class DataHandle {
	private static String customerListFileName;
	private static String propertyListFileName;
	private static String propertyRentListFileName;
	private static String placeOfInterestListFileName;


	public static void readProperties(Properties config) {
		customerListFileName = config.getProperty("customerlist.file");
		propertyListFileName = config.getProperty("propertylist.file");
		propertyRentListFileName = config.getProperty("propertyrentlist.file");
		placeOfInterestListFileName = config.getProperty("placeofinterestlist.file");
	}


	// Serialize the object to a file
	public static void doSerialize(Object obj, String outputFile) throws IOException {
		FileOutputStream fileTowrite = new FileOutputStream(outputFile);
		ObjectOutputStream objTowrite = new ObjectOutputStream(fileTowrite);
		objTowrite.writeObject(obj);
		fileTowrite.close();
	}

	// Deserialize the Java object from a given file
	public static Object doDeserialize(String inputFile) throws IOException, ClassNotFoundException {
		Object obj = new Object();
		File f = new File (inputFile);
		if (f.exists()) {
			FileInputStream fileToread = new FileInputStream(inputFile);
			ObjectInputStream objToread = new ObjectInputStream(fileToread);
			if (f.length() > 0) {
				obj = objToread.readObject();
				objToread.close();
			} else {
				System.out.println("File " + inputFile + " is empty");
			}
		}
		else {
			System.out.println("File " + inputFile + " does not exist");
		}
		return obj;
	}

	public static void writeToFile(CustomerList list) throws IOException {
		doSerialize(list, customerListFileName);
		System.out.println("The Customer list has been written to "+ customerListFileName);	
	}

	public static CustomerList readCustomerList() throws IOException, ClassNotFoundException {
		CustomerList list = new CustomerList();
		Object obj;
		obj = doDeserialize(customerListFileName);
		if (obj instanceof CustomerList)
			list = (CustomerList) obj;
		System.out.println("Customer list size: "+list.getCustomerList().size());


		return list;
	}


	public static void writeToFile(PropertyList list) throws IOException {
		doSerialize(list, propertyListFileName);
		System.out.println("The Property list has been written to "+ propertyListFileName);	
	}

	public static PropertyList readPropertyList() throws IOException, ClassNotFoundException {
		PropertyList list = new PropertyList();
		Object obj = doDeserialize(propertyListFileName);
		if (obj instanceof PropertyList) {
			list = (PropertyList) obj;}
		System.out.println("Property list size: "+list.getProperties().size());

		return list;
	}

	public static void writeToFile(PropertyRentList list) throws IOException {
		doSerialize(list, propertyRentListFileName);
		System.out.println("The Properties rented were written to "+ propertyRentListFileName);	
	}

	public static PropertyRentList readPropertyRentList() throws IOException, ClassNotFoundException {
		PropertyRentList list = new PropertyRentList();
		Object obj;
		obj =  doDeserialize(propertyRentListFileName);
		if (obj instanceof PropertyRentList)
			list = (PropertyRentList) obj;
		System.out.println("Properties rented list size: "+list.getRented().size());



		return list;
	}

	public static PlaceOfInterestList readPlacesOfInterestList() throws IOException, ClassNotFoundException {
		PlaceOfInterestList list = new PlaceOfInterestList();
		Object obj = doDeserialize(placeOfInterestListFileName);
		if (obj instanceof PlaceOfInterestList)
			list = (PlaceOfInterestList) obj;
		System.out.println("Places of interests list size: "+list.getPlacesOfInterestlist().size());


		return list;
	}


	public static void writeToFile(PlaceOfInterestList list) throws IOException {
		// TODO Auto-generated method stub
		doSerialize(list, placeOfInterestListFileName);
		System.out.println("The serialized objects " +"were written to "+ placeOfInterestListFileName);	
	}



}
