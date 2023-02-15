package work;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;
/**
 * Create inventory of properties and places of interests from CSV files and serialise their corresponding lists
 * @author Mayowa
 *
 */
public class PropertyInventory {
	private static final Properties config = new Properties();

	public static void initialise() throws FileNotFoundException, IOException, ClassNotFoundException {
		config.load(new FileInputStream("systems.properties"));
		DataHandle.readProperties(config);

	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		initialise();
//				PropertyReader propertyreader=new PropertyReader("files/placeofinterest.csv");
//				PlaceOfInterestList placeOfInterestList=new PlaceOfInterestList();
//				
//				while(propertyreader.readNextLine()) {
//		
//					placeOfInterestList.addPlacesOfInterest(propertyreader.createPOI());
//				}
//				
//				DataHandle.writeToFile(placeOfInterestList);
//				System.out.println(placeOfInterestList.getPlacesOfInterestlist().size());
//				propertyreader.close();
//
//				PropertyReader places=new PropertyReader("property.csv");
//				PropertyList prop=new PropertyList();
//				while(places.readNextLine()) {
//		
//					prop.addProperty(places.createProperty());
//				}
//				
//				DataHandle.writeToFile(prop);
//				System.out.println(prop.getProperties().size());
//				places.close();
				

		//
		//					 File file = new File("PropertyRentList.dat");
		//					 file.delete();
		//				          System.out.println( " has been deleted.");

	}

}
