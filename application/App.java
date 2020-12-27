package at.sipovsven.GetIt.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {		
	
	
	@Override
	public void start(Stage primaryStage) {

		
		try {
			// object-tree

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/MainScene.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setTitle("Get-it");
			primaryStage.setScene(scene);
			primaryStage.show();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
