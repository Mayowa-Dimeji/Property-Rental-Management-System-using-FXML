package work;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Homepage extends Application{
	private static final Properties config = new Properties();

	public static void initialise() throws FileNotFoundException, IOException {
		config.load(new FileInputStream("systems.properties"));
		DataHandle.readProperties(config);
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		initialise();
		launch(args);


	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent parent = FXMLLoader.load(
				getClass().getResource("Login.fxml")); 

		// Build the scene graph.
		Scene scene = new Scene(parent); 

		// Display our window, using the scene graph.
		stage.setTitle("Home Page"); 
		stage.setScene(scene);
		stage.show();	
	}

}
