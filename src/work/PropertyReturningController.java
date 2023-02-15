package work;

import java.io.IOException;
import java.time.LocalDate;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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

public class PropertyReturningController {

	@FXML
	private Button backBtn;

	@FXML
	private TextArea infoBoard;

	@FXML
	private TextField damagesTxt;

	@FXML
	private TableColumn<PropertyRent, String> pcode;
	@FXML
	private DatePicker newdate;
	@FXML
	private Button renew;

	@FXML
	private Button returnbtn;

	@FXML
	private TableView<PropertyRent> table;

	@FXML
	private TableColumn<PropertyRent, Integer> custID;
	@FXML
	private ComboBox<String> combo;

	@FXML
	private Button searchBtn;

	@FXML
	private TextField searchTxt;

	@FXML
	private TableColumn<PropertyRent, String> custName;

	private PropertyRentList l;
	private PropertyList p;
	private EmailSender endEmail;

	/**
	 * iterate property rent list and store in an observable list for table view
	 * @return ObservableList<PropertyRent>
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ObservableList<PropertyRent> getProps() throws ClassNotFoundException, IOException{

		ObservableList<PropertyRent> property=FXCollections.observableArrayList();
		for(PropertyRent i:l.getRented()) {
			property.add(i);
		}

		return property;


	}
	/**
	 * setup for controller class
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void initialize() throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		l=DataHandle.readPropertyRentList();
		p=DataHandle.readPropertyList();



		infoBoard.setEditable(false);
		if(l.getRented()!=null) {
			pcode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProperty().getPostcode()));
			System.out.println(10);
			custID.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCustomer().getCustomerID()));
			System.out.println(20);
			custName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomer().getFirstName().concat(" "+ cellData.getValue().getCustomer().getLastName())));

			table.setItems(getProps());
			System.out.println(0);
		}
		combo.getItems().addAll("Customer ID","PostCode","Customer Name");



	}



	@FXML
	public void returnListener(ActionEvent event) throws IOException, ClassNotFoundException {
		PropertyRent selectedProperty = table.getSelectionModel().getSelectedItem();
		if(selectedProperty!=null) {
			//validation for empty input
			if(damagesTxt.getText().isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Fill in the damages field");
				alert.showAndWait();
			}else {
				try {
					//validation for appropriate return date
					//if(LocalDate.now().equals(selectedProperty.getEndDate())||LocalDate.now().isAfter(selectedProperty.getEndDate())) {
						double damages=Double.parseDouble(damagesTxt.getText());

						selectedProperty.setDamage(damages);

						selectedProperty.setEndDate(LocalDate.now());
						PdfWrite.createPDF(selectedProperty.printEndInvoice());
						endEmail=new EmailSender();
						endEmail.sendMail(selectedProperty.getCustomer().getEmailAdress(), "Conclusion of Rental -Invoice Attached for "+selectedProperty.getProperty().getPostcode());

						infoBoard.clear();
						setFalse(selectedProperty.getProperty());
						infoBoard.setText(selectedProperty.printEndInvoice()+"\n\n\nEnd Invoice has been sent customers email address");
						l.removeRental(selectedProperty);
						damagesTxt.clear();


						DataHandle.writeToFile(l);
						table.refresh();
						table.setItems(getProps());
//					//}else {
//						Alert alert = new Alert(Alert.AlertType.ERROR);
//						alert.setContentText("Action can not be completed");
//						alert.showAndWait();
//					}
				}catch(Exception e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Fill the monetary value of damages");
					alert.showAndWait();
				}
			}
		}
		if(selectedProperty==null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Select a property");
			alert.showAndWait();
		}

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

	@SuppressWarnings("unused")
	@FXML
	public void renewListener(ActionEvent event) {
		PropertyRent selectedProperty = table.getSelectionModel().getSelectedItem();
		LocalDate oldDate=selectedProperty.getEndDate();
		LocalDate newReturnDate=newdate.getValue();
		if(selectedProperty!=null) {
			if(newReturnDate==null) {
				Alert alert = new Alert(Alert.AlertType.ERROR);

				alert.setContentText("Fill in new return date");
				alert.showAndWait();
			}else if(newReturnDate!=null) {
				//validation for appropriate return date 
				//if(LocalDate.now().isAfter(oldDate.plusMonths(6))&&(LocalDate.now().isAfter(oldDate)||LocalDate.now().equals(oldDate)) ) {
					try {
						selectedProperty.setEndDate(LocalDate.now());
						double damages=Double.parseDouble(damagesTxt.getText());
						selectedProperty.setDamage(damages);
						PdfWrite.createPDF(selectedProperty.printRenewalInvoice());
						endEmail=new EmailSender();
						endEmail.sendMail(selectedProperty.getCustomer().getEmailAdress(), "Commencement of Renewal - Previous Invoice Attached for "+selectedProperty.getProperty().getPostcode());
						infoBoard.clear();
						infoBoard.setText(selectedProperty.printRenewalInvoice()+"\n\n\nRenewal information and \nInvoice sent to customer's email adrress");
						selectedProperty.setEndDate(newReturnDate);
						selectedProperty.setStartDate(LocalDate.now());
						newdate.setValue(null);
						damagesTxt.clear();

					}catch(Exception e) {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("Fill the monetary value of damages");
						alert.showAndWait();
					}

//				}else if(!newReturnDate.isAfter(oldDate.plusMonths(6)) ){
//					Alert alert = new Alert(Alert.AlertType.ERROR);
//					alert.setTitle("Error");
//					alert.setContentText("Action cannot be completed\nProperty is either not due for return\nProperties must let for at least 6 months");
//					alert.showAndWait();
//
//				}
			}
		}
		else{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please select a property");
			alert.showAndWait();
		}
	}


	/**
	 * helper method to set property to false rent status and write to file, for a cleaner code
	 * @param m
	 * @throws IOException
	 */
	public void setFalse(Property m) throws IOException  {
		for(Property l:p.getProperties()) {
			l.setRentStatus(false);
			DataHandle.writeToFile(p);
		}
	}

	@FXML
	public void searchBtnListener(ActionEvent event) throws ClassNotFoundException, IOException {
		String selectedAttribute = combo.getValue();
		String searchText1 = searchTxt.getText();

		//setup for search via a filtered list
		FilteredList<PropertyRent> filteredData = new FilteredList<>(getProps(), p -> true);
		filteredData.setPredicate(data -> {
			if (searchTxt == null || searchText1.isEmpty()||selectedAttribute==null) {
				return true;
			}
			if (selectedAttribute.equals("Customer ID")) {
				return String.valueOf(data.getCustomer().getCustomerID()).contains( searchText1);
			} else if (selectedAttribute.equals("PostCode")) {
				return data.getProperty().getPostcode().toLowerCase().contains(searchText1.toLowerCase());
			}
			else if (selectedAttribute.equals("Customer Name")) {
				return data.getCustomer().getFirstName().toLowerCase().concat(data.getCustomer().getLastName().toLowerCase()).contains(searchText1.toLowerCase());
			}
			else {
				return false;
			}

		});
		table.setItems(filteredData);
		searchTxt.clear();



	}

}
