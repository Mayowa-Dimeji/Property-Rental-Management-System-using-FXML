package work;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardController {

	@FXML
	private Button calendar;

	@FXML
	private Button potBtn;

	@FXML
	private Label Letted;

	@FXML
	private Label TotalNoOfprops;

	@FXML
	private Label noOfCuustomers;


	@FXML
	private Button customers;

	@FXML
	private Button renting;

	@FXML
	private Button returning;

	private CustomerList cl;
	private PropertyList p;
	private PropertyRentList l;

	/**
	 * setup for controller	error validation for empty inputs 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void initialize() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		cl=DataHandle.readCustomerList();
		p=DataHandle.readPropertyList();
		l=DataHandle.readPropertyRentList();

		String noOfProps=String.valueOf(p.getProperties().size());
		String noOfCustomers=String.valueOf(cl.getCustomerList().size());
		String noOfLetted=String.valueOf(l.getRented().size());

		TotalNoOfprops.setText(noOfProps);
		noOfCuustomers.setText(noOfCustomers);
		Letted.setText(noOfLetted);

	}


	@FXML
	public void CustomerListener(ActionEvent event) throws IOException {
		
//		to change scene to new scene
		//		Parent parent = FXMLLoader.load(
		//				getClass().getResource("CustomerList.fxml")); 
		//
		//		// Build the scene graph.
		//		Scene scene = new Scene(parent); 
		//
		//		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		//		// Display our window, using the scene graph.
		//		stage.setTitle("Next Page"); 
		//		stage.setScene(scene);
		//		stage.show(); 
		
//		to pop a new window 

		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerList.fxml"));
		Parent secondRoot = loader.load();
		Scene secondScene = new Scene(secondRoot);

		// Create a new stage for the second scene
		Stage secondStage = new Stage();
		secondStage.setScene(secondScene);
		secondStage.show();


	}

	@FXML
	public void RentListener(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("PropertyRenting.fxml")); 

		// Build the scene graph.
		Scene scene = new Scene(parent); 

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		// Display our window, using the scene graph.
		stage.setTitle("Next Page"); 
		stage.setScene(scene);
		stage.show(); 


	}



	@FXML
	public void returnListener(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(
				getClass().getResource("PropertyReturning.fxml")); 

		// Build the scene graph.
		Scene scene = new Scene(parent); 

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		// Display our window, using the scene graph.
		stage.setTitle("Next Page"); 
		stage.setScene(scene);
		stage.show(); 


	}
	@FXML
	public void potBtnListener(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(
				getClass().getResource("PlaceOfinterestview.fxml")); 

		// Build the scene graph.
		Scene scene = new Scene(parent); 

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		// Display our window, using the scene graph.
		stage.setTitle("Next Page"); 
		stage.setScene(scene);
		stage.show(); 


	}

}
