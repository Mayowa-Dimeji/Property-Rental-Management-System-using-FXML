package work;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PropertyReader {

	private Scanner inputFile;
	private String line;



	/**
	      The constructor opens a file to read
	      the grades from.
	      @param filename The file to open.
	 * @throws ClassNotFoundException 
	 */

	public PropertyReader(String filename)
			throws IOException, ClassNotFoundException
	{
		File file = new File(filename);
		inputFile = new Scanner(file);

	}


	/**
	      The readNextLine method reads the next line
	      from the file.
	      @return true if the line was read, false
	      otherwise.
	 */

	public boolean readNextLine() throws IOException
	{
		boolean lineRead; // Flag variable

		// Determine whether there is more to read.
		lineRead = inputFile.hasNext();

		// If so, read the next line. 
		if (lineRead)
			line = inputFile.nextLine();

		return lineRead;
	}

	/**
	 * creates place of interest objects from file read
	 * @return
	 * @throws NumberFormatException
	 */
	public PlaceOfInterest createPOI()throws NumberFormatException {
		// Tokenize the last line read from the file.
		String[] tokens = line.split(",");
		for (String str : tokens) {
			PlaceOfInterest place= new PlaceOfInterest(tokens[0], tokens[1], Double.parseDouble(tokens[2]),  Double.parseDouble(tokens[3]));  
			return place;
		}
		return null;

	}
	/**
	 * creates property object from file read
	 * @return
	 * @throws NumberFormatException
	 */
	public Property createProperty()throws NumberFormatException {
		// Tokenize the last line read from the file.
		String[] tokens = line.split(",");
		for (String str : tokens) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			Property place= new Property(LocalDate.parse((tokens[0]),formatter), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]),  Double.parseDouble(tokens[3]),Integer.parseInt(tokens[4]),tokens[5],Double.parseDouble(tokens[6]),Double.parseDouble(tokens[7]),tokens[8],tokens[9],false);  

			return place;
		}

		return null;
	}


	/**
	      The close method closes the file.
	 */

	public void close() throws IOException
	{
		inputFile.close();
	}

}
