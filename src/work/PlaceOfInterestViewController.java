package work;

import java.io.IOException;
import java.time.LocalDate;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PlaceOfInterestViewController {

	@FXML
	private Button addPlace;

	@FXML
	private Button backBtn;

	@FXML
	private ComboBox<String> combo;

	@FXML
	private Button delete;

	@FXML
	private Label addedAlert;

	@FXML
	private TextField latTxt;

	@FXML
	private TableColumn<PlaceOfInterest, Double> latitude;

	@FXML
	private TextField longTxt;

	@FXML
	private TableColumn<PlaceOfInterest, Double> longitude;

	@FXML
	private TableColumn<PlaceOfInterest, Double> name;

	@FXML
	private TextField nametxt;

	@FXML
	private TableColumn<PlaceOfInterest, Double> pcode;

	@FXML
	private TextField pcodetxt;

	@FXML
	private Button searchBtn;

	@FXML
	private TextField searchTxt;

	@FXML
	private TableView<PlaceOfInterest> table;
	private PlaceOfInterestList l;
	private PlaceOfInterest selectedPlace;



	/**
	 * setup for controller class
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void initialize() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		l=DataHandle.readPlacesOfInterestList();
		addedAlert.setText(null);
		table.refresh();

		if(l.getPlacesOfInterestlist()!=null) {
			//set up columns 
			latitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
			longitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
			name.setCellValueFactory(new PropertyValueFactory<>("name"));
			pcode.setCellValueFactory(new PropertyValueFactory<>("postcode"));
			table.setItems(getPlace());
		}
		//set up combo box
		combo.getItems().addAll("Name","PostCode");

	}

	/**
	 * iterates places of interest list and stores it, for table view 
	 * @return ObservableList<PlaceOfInterest> 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ObservableList<PlaceOfInterest> getPlace() throws ClassNotFoundException, IOException{

		ObservableList<PlaceOfInterest> place=FXCollections.observableArrayList();
		if (l !=null) {
			for(PlaceOfInterest i:l.getPlacesOfInterestlist()) {

				place.add(i);

			}
		}
		return place;

	}


	@FXML
	public void addPlaceListener(ActionEvent event) throws IOException {
		//validation for empty fields
		if(longitude.getText().isEmpty() || pcode.getText().isEmpty() || name.getText().isEmpty() || latitude.getText().isEmpty() ) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Empty input");
			alert.setContentText("fill all fields");
			alert.showAndWait();
		}else {
			//validation for parsing inputs
			try {
				String longi=longTxt.getText();
				String pcodee=pcodetxt.getText();
				String namee=nametxt.getText();
				String lattt=latTxt.getText();



				PlaceOfInterest c= new PlaceOfInterest(namee, pcodee, Double.parseDouble(lattt), Double.parseDouble(longi));
				table.getItems().add(c);
				addedAlert.setText("Property Added");

				l.addPlacesOfInterest(c);;
				DataHandle.writeToFile(l);
				longTxt.clear();
				pcodetxt.clear();
				nametxt.clear();
				latTxt.clear();
			}catch(Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Correctly fill all fields");
				alert.showAndWait();
			}

		}

	}

	@FXML
	public void backBtnListener(ActionEvent event) throws IOException {
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
	public void deleteListener(ActionEvent event) throws IOException, ClassNotFoundException {
		table.refresh();
		selectedPlace=table.getSelectionModel().getSelectedItem();
		if (selectedPlace!=null) {
			l.removePlacesOfInterest(selectedPlace);
			DataHandle.writeToFile(l);
			table.refresh();
			table.setItems(getPlace());
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please select a place to delete");
			alert.showAndWait();
		}
	}


	@FXML
	public void searchBtnListener(ActionEvent event) throws ClassNotFoundException, IOException {
		table.refresh();
		String selectedAttribute = combo.getValue();
		String searchText1 = searchTxt.getText();
		//feeds observable list into filtered for search
		FilteredList<PlaceOfInterest> filteredData = new FilteredList<>(getPlace(), p -> true);
		filteredData.setPredicate(data -> {
			if (searchTxt == null || searchText1.isEmpty()||selectedAttribute==null) {
				return true;
			}
			if (selectedAttribute.equals("Name")) {
				return data.getName().toLowerCase().contains( searchText1.toLowerCase());
			} else if (selectedAttribute.equals("PostCode")) {
				return data.getPostcode().toLowerCase().toString().contains(searchText1.toLowerCase());
			}
			else {
				return false;
			}
		});
		table.setItems(filteredData);
		searchTxt.clear();



	}

}
