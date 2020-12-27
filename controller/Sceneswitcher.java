package at.sipovsven.GetIt.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Sceneswitcher  {
	private Button cssButton;
    Boolean b;

//public void startNewScene() throws IOException{
//		    Stage stage = new Stage();
//		    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
//		    Parent root = loader.load();
//		    MainController controller = loader.getController();
//		    Scene scene = new Scene(root);
//		    stage.setScene(scene);
//		    stage.show();
//		    
//		    MainController mainController = loader.getController();
//		    mainController.transferMessage(b);
//		}
	public void openNewWindow(String fxmlFile, String title, Boolean b) throws Exception {
		// make Stage = new Stage for method to work
	    Stage stage1 = new Stage();
	    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/fxml/MainScene.fxml"));
	    Parent root1 = loader1.load();
	    Scene scene1 = new Scene(root1);
	    stage1.setScene(scene1);
	    
	
	    
	    MainController mainController = loader1.getController();
	    mainController.transferMessage(b);
	    System.out.println(mainController.transferMessage(b) + " transfermessage!");
		if (mainController.transferMessage(b) == true) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/" + fxmlFile + ".fxml"));
			Stage stage = new Stage();
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			stage.setTitle(title);
			stage.setScene(scene);
			stage.show();
		}
		
	else {
			FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/" + fxmlFile + ".fxml"));
		Stage stage = new Stage();
		Parent root = loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/darktheme.css").toExternalForm());
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	}
	
	
	
}
