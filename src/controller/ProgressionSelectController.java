package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ProgressionSelectController {

	
	
	@FXML
	private ImageView option;
	@FXML
	
	void clickOnOption() {
		try {
			Stage primaryStage = (Stage) option.getScene().getWindow();
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("options.fxml"));
			Scene scene = new Scene(root, 874, 678);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
