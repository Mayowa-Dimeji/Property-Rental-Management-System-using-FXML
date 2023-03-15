package work;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CustomerListController {

	@FXML
	private TableColumn<Customer, String> ID;

	@FXML
	private TextField addytxt;

	@FXML
	private ComboBox<String> combo;

	@FXML
	private Button delete;

	@FXML
	private Button searchBtn;

	@FXML
	private TextField searchTxt;

	@FXML
	private Button back;

	@FXML
	private TableColumn<Customer, LocalDate> dob;

	@FXML
	private TableColumn<Customer, String> email;

	@FXML
	private TextField emailtxt;

	@FXML
	private TableColumn<Customer, String> fname;

	@FXML
	private TextField fnametxt;


	@FXML
	private DatePicker inputdob;

	@FXML
	private TableColumn<Customer, String> lname;

	@FXML
	private TextField lnametxt;

	@FXML
	private TextField phonetxt;

	@FXML
	private TableColumn<Customer, Integer> pno;

	@FXML
	private Button refresh;

	@FXML
	private TableView<Customer> table;

	@FXML
	private Button register;

	private CustomerList cl;
	private Customer selectedCustomer;
	private PropertyRentList list;


	/**
	 * iterates customer list and stores it for table view 
	 * @return ObservableList<Customer> 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ObservableList<Customer> getCuss() throws ClassNotFoundException, IOException{




		ObservableList<Customer> customers=FXCollections.observableArrayList();
		if (cl !=null) {
			for(int i=0;i<cl.getCustomerList().size();i++) {

				customers.add(cl.getCustomerList().get(i));

			}
		}
		return customers;

	}

	/**
	 * setup for controller class
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	public void initialize() throws ClassNotFoundException ,IOException {
		cl=DataHandle.readCustomerList();
		list=DataHandle.readPropertyRentList();

		selectedCustomer.setCount(cl.getCustomerList().size());
		if(cl.getCustomerList()!=null) {

			ID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
			dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
			email.setCellValueFactory(new PropertyValueFactory<>("emailAdress"));
			fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
			lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
			pno.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

			table.setItems(getCuss());
		}
		combo.getItems().addAll("ID","First Name","Last Name","Email");


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
	/**
	 * helper method for registration to pattern match email format
	 * @param email
	 * @return true if it matches and false if it does not 
	 */
	public static boolean isValidEmail(String email) {
		// Regular expression for validating email addresses
		String emailChecker = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		Pattern pattern = Pattern.compile(emailChecker, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}



	@FXML
	public void registerListener(ActionEvent event) throws IOException, ClassNotFoundException {

		//	error validation for empty inputs 
		if(fnametxt.getText().isEmpty() || lnametxt.getText().isEmpty() || emailtxt.getText().isEmpty() || addytxt.getText().isEmpty()||phonetxt.getText().isEmpty() || inputdob.getValue()==null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Empty input");
			alert.setContentText("fill all fields");
			alert.showAndWait();
		}else {
			try {
				//validation for appropriate parsing of inputs
				String fname=fnametxt.getText();
				String lname=lnametxt.getText();
				String email=emailtxt.getText();
				String addy=addytxt.getText();
				LocalDate date=inputdob.getValue();
				String phone=phonetxt.getText();

				if (isValidEmail(email)) {
					Customer c= new Customer(fname, lname, email, addy, phone, date);
					table.getItems().add(c);

					cl.addCustomer(c);
					DataHandle.writeToFile(cl);
					fnametxt.clear();
					lnametxt.clear();
					emailtxt.clear();
					addytxt.clear();
					inputdob.setValue(null);
					phonetxt.clear();
				}else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText("Please enter a valid email");
					alert.showAndWait();	
				}
			}catch(Exception e ) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Please verify input details");
				alert.showAndWait();
			}
		}


	}

	@FXML
	public void deleteListener(ActionEvent event) throws IOException, ClassNotFoundException {
		selectedCustomer=table.getSelectionModel().getSelectedItem();
		if (selectedCustomer!=null) {
			if(!safeToDelete(selectedCustomer)) {
				cl.removeCustomer(selectedCustomer);
				DataHandle.writeToFile(cl);
				table.refresh();
				table.setItems(getCuss());
			}
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Customer is currently renting a Property\nAction cannot be completed");
				alert.showAndWait();
			}
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please select a customer to delete");
			alert.showAndWait();
		}
	}

	/**
	 * helper method for delete listener 
	 * @param c
	 * @return true if customer is letting false if not 
	 */
	public boolean safeToDelete(Customer c) {
		for (PropertyRent i : list.getRented()) {
			if (i.getCustomer().getCustomerID()==(c.getCustomerID())) {
				return true;
			}
		}
		return  false;
	}


	@FXML
	public void searchBtnListener(ActionEvent event) throws ClassNotFoundException, IOException {
		String selectedAttribute = combo.getValue();
		String searchText1 = searchTxt.getText();
		FilteredList<Customer> filteredData = new FilteredList<>(getCuss(), p -> true);
		filteredData.setPredicate(data -> {
			if (searchTxt == null || searchText1.isEmpty()||selectedAttribute==null) {
				return true;
			}
			if (selectedAttribute.equals("ID")) {
				return String.valueOf(data.getCustomerID()).contains( searchText1);
			} else if (selectedAttribute.equals("First Name")) {
				return data.getFirstName().toLowerCase().contains(searchText1.toLowerCase());
			} else if (selectedAttribute.equals("Last Name")) {
				return data.getLastName().toLowerCase().contains( searchText1.toLowerCase());
			} else if (selectedAttribute.equals("Email")) {
				return data.getEmailAdress().toLowerCase().contains( searchText1.toLowerCase());
			} 
			else {
				return false;
			}
		});
		table.setItems(filteredData);
		searchTxt.clear();


	}

}
