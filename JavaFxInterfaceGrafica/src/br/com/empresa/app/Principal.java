package br.com.empresa.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {
	public static Scene SCENE;
	

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/br/com/empresa/view/principal.fxml"));
		SCENE = new Scene(root);
		stage.setScene(SCENE);
		stage.setTitle("Exemplo CRUD");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
