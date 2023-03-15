
package work;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 * 
 * @author Mayowa
 *
 */
public class PropertyRentingController {

	@FXML
	private Button back;

	@FXML
	private Button addP;

	@FXML
	private ComboBox<String> combo;

	@FXML
	private Button searchBtn;

	@FXML
	private TextField searchText;

	@FXML
	private TextField bathsss;

	@FXML
	private TextField bedsss;

	@FXML
	private TextField gardennn;

	@FXML
	private TextField lattt;

	@FXML
	private TextField longgg;

	@FXML
	private TextField pcodee;

	@FXML
	private TextField rentttt;

	@FXML
	private TextField sizeee;

	@FXML
	private TextField typeee;

	@FXML
	private TableColumn<Property, Integer> baths;

	@FXML
	private TableColumn<Property, Integer> beds;

	@FXML
	private TextField custID;

	@FXML
	private DatePicker expectedReturn;

	@FXML
	private TextArea infoBoard;

	@FXML
	private TableColumn<Property, LocalDate> ldate;

	@FXML
	private TableColumn<Property, String> pcode;

	@FXML
	private TableColumn<Property, Double> rent;

	@FXML
	private Button renter;

	@FXML
	private Button delete;

	@FXML
	private TableColumn<Property, Integer> size;

	@FXML
	private TableView<Property> table;

	private PropertyRent po;
	private PropertyRentList pp;
	private PropertyList p;
	private CustomerList cl;
	private PlaceOfInterestList pt;
	private Property selectedProperty;
	private FilteredList<Property> filteredData;
	private EmailSender beginInvoicemail;


	/**
	 * setup for property class, 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void initialize() throws ClassNotFoundException, IOException {

		p=DataHandle.readPropertyList();
		cl=DataHandle.readCustomerList();
		pp=DataHandle.readPropertyRentList();
		pt = DataHandle.readPlacesOfInterestList();

		if(p.getProperties()!=null) {
			//setup table columns
			ldate.setCellValueFactory(new PropertyValueFactory<>("listDate"));

			pcode.setCellValueFactory(new PropertyValueFactory<>("postcode"));


			beds.setCellValueFactory(new PropertyValueFactory<>("bedrooms"));

			baths.setCellValueFactory(new PropertyValueFactory<>("bathrooms"));

			size.setCellValueFactory(new PropertyValueFactory<>("size"));


			rent.setCellValueFactory(new PropertyValueFactory<>("rent"));


			table.setItems(getProps());



			table.setOnMouseClicked(event -> {
				infoBoard.clear();
				String s="The Distance from this property to:\n";

				//Iterate through place of interest list and display a properties proximity in Km to all places of interest
				for (int i=0;i < pt.getPlacesOfInterestlist().size();i++) {


					PlaceOfInterest pl=pt.getPlacesOfInterestlist().get(i);
					Property selected = table.getSelectionModel().getSelectedItem();


					if (selected != null) {


						DecimalFormat df = new DecimalFormat("#,##0.00");
						double km=getDistance(pl.getLatitude(),pl.getLongitude(),selected.getLatitude(),selected.getLongitude());
						s +="\n"+pl.getName()+" is "+df.format(km)+"Km, "; 

					}  

				}
				infoBoard.setText(s);




			});
		}
		//set up combo box for search
		combo.getItems().addAll("Price","Listing Date","Number of Bedrooms","Postcode");
	}

	/**
	 * iterates property list and stores list for table view
	 * @return ObservableList<Property>
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ObservableList<Property> getProps() throws ClassNotFoundException, IOException{

		ObservableList<Property> properties=FXCollections.observableArrayList();
		for(Property i:p.getProperties()) {

			if(!i.isRentStatus()) {

				properties.add(i);
			}else {System.out.println("when true");}
		}

		return properties;

	}



	@FXML
	public void addPListener(ActionEvent event)  {
		//validate for empty fields
		if( bathsss.getText().isEmpty()||bedsss.getText().isEmpty()||gardennn.getText().isEmpty()|| lattt.getText().isEmpty()|| longgg.getText().isEmpty()||pcodee.getText().isEmpty()||rentttt.getText().isEmpty()||sizeee.getText().isEmpty()||typeee.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Empty input");
			alert.setContentText("You are required to fill all  fields");
			alert.showAndWait();
		}else {

			try {
				//validation for parsing inputs
				System.out.println(0);
				Property newproperty=new Property(LocalDate.now(), Integer.parseInt(bedsss.getText()),Integer.parseInt(bathsss.getText()), 
						Double.parseDouble(rentttt.getText()), Integer.parseInt(sizeee.getText()), pcodee.getText(), Double.parseDouble(lattt.getText()), Double.parseDouble(longgg.getText()), typeee.getText(), gardennn.getText(), false);
				System.out.println("property parsed");
				p.addProperty(newproperty);
				table.getItems().add(newproperty);
				infoBoard.clear();
				infoBoard.setText("Property Added");
				System.out.println(1);


				try {
					DataHandle.writeToFile(p);
					System.out.println("l");
				} catch (IOException e) {
					// TODO handle IOexception
					e.printStackTrace();
				}

			} catch (Exception e) {
				// TODO: handle exception
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Invalid Input" );
				alert.showAndWait();
			}
			//clear fields after adding 
			bathsss.clear();
			bedsss.clear();
			gardennn.clear();
			longgg.clear();
			lattt.clear();
			pcodee.clear();
			rentttt.clear();
			sizeee.clear();
			typeee.clear();
		}

	}





	/**
	 * helper method to calculate distance of two places given their longitude and latitudes
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */

	private static double getDistance(double lat1, double lon1, double lat2, double lon2) {
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return 6371 * c;
	}



	@FXML
	public void backListener(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("Dashboard.fxml")); 

		// Build the scene graph.
		Scene scene = new Scene(parent); 

		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		// Display our window, using the scene graph.
		stage.setTitle("Next Page"); 
		stage.setScene(scene);
		stage.show(); 


	}


	@FXML
	public void renterListener(ActionEvent event) throws ClassNotFoundException, IOException {

		selectedProperty = table.getSelectionModel().getSelectedItem();
		//validation for empty fields
		if(custID.getText().isEmpty()||expectedReturn.getValue()==null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Fill in all fields");
			alert.showAndWait(); 
		}

		if (selectedProperty == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Select A Property");
			alert.showAndWait();
		}else {
			try {
				//validation for parsing inputs
				LocalDate selected=LocalDate.now();
				Customer cus=getCus(Integer.parseInt(custID.getText()));
				LocalDate date=expectedReturn.getValue();
				if(cus==null) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Invalid customer ID .");
					alert.showAndWait(); 
				}
				else {
					//validation for at least 6 month period of rent
					if(date.isAfter(selected.plusMonths(6))) {
						po=new PropertyRent(selectedProperty,cus,selected);
						po.setEndDate(date);
						pp.addRental(po);

						DataHandle.writeToFile(pp);
						infoBoard.clear();
						//create PDF and send email to customer
						PdfWrite.createPDF(po.printBeginInvoice());
						beginInvoicemail=new EmailSender();
						beginInvoicemail.sendMail(cus.getEmailAdress(), "Welcome to your rental of "+selectedProperty.getPostcode()+" - invoice and payment information");
						infoBoard.setText(po.printBeginInvoice()+"\n\n\nA copy of this invoice has been sent to Customer's email");
						custID.clear();
						expectedReturn.setValue(null);

						selectedProperty.setRentStatus(true); 
						DataHandle.writeToFile(p);
						System.out.println(1);


						table.refresh();
						table.setItems(getProps());

					}else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("Properties must be letted for at least a 6 month period");
						alert.showAndWait();
					}

				}
			} catch(Exception e){
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Action can not be completed");
				alert.showAndWait();
			}
		}

	}

	/**
	 * Helper method to find customer object with ID
	 * @param Id
	 * @return Customer object
	 */
	public Customer getCus(int Id) {
		for (Customer user : cl.getCustomerList()) {
			if(user.getCustomerID()==Id) {
				return user;
			}

		}
		return null;
	}


	@FXML
	public void deleteListener(ActionEvent event) throws IOException, ClassNotFoundException {
		selectedProperty=table.getSelectionModel().getSelectedItem();
		if (selectedProperty!=null) {
			//validation for empty selection
			p.removeProperty(selectedProperty);
			DataHandle.writeToFile(p);
			infoBoard.clear();
			infoBoard.setText("Property Deleted");
			table.refresh();
			table.setItems(getProps());
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please select a Property to delete");
			alert.showAndWait();
		}
	}




	@FXML
	public void searchBtnListener(ActionEvent event) throws ClassNotFoundException, IOException {

		String selectedAttribute = combo.getValue();
		String searchText1 = searchText.getText();
		//search setup
		filteredData = new FilteredList<>(getProps(), p -> true);
		filteredData.setPredicate(data -> {
			if (searchText == null || searchText1.isEmpty()||selectedAttribute==null) {
				return true;
			}
			if (selectedAttribute.equals("Price")) {
				return String.valueOf(data.getRent()).contains( searchText1);
			} else if (selectedAttribute.equals("Listing Date")) {
				return data.getListDate().toString().contains(searchText1);
			} else if (selectedAttribute.equals("Number of Bedrooms")) {
				return String.valueOf(data.getBedrooms()).contains( searchText1);
			} else if (selectedAttribute.equals("Postcode")) {
				return data.getPostcode().toLowerCase().contains( searchText1.toLowerCase());
			} 
			else {
				return false;
			}
		});
		table.setItems(filteredData);
		searchText.clear();


	}


}
