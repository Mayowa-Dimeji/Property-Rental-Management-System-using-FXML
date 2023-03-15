package work;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private Button login;

	@FXML
	private PasswordField pword;

	;

	@FXML
	private TextField uname;




	@FXML
	public void loginListener(ActionEvent event) throws IOException {

		//validation for empty fields

		if(uname.getText().isEmpty() || pword.getText().isEmpty() ) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Empty input");
			alert.setContentText("You are required to fill all  fields");
			alert.showAndWait();
		}
		// validation for only admin username and admin password into the software
		else if(uname.getText().equals("admin") && pword.getText().equals("admin") ){
			Parent parent = FXMLLoader.load(getClass().getResource("Dashboard.fxml")); 

			// Build the scene graph.
			Scene scene = new Scene(parent); 

			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			// Display our window, using the scene graph.
			stage.setTitle("Next Page"); 
			stage.setScene(scene);
			stage.show();   
		}
		//validation for incorrect details
		else{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Invalid input");
			alert.setContentText("Password and username do not match");
			alert.showAndWait();
		}

	}




}
