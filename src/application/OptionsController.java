package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class OptionsController {

	@FXML
	private ImageView home;
	@FXML
	private Button dalto;

	@FXML
	private Button delete;

	@FXML
	void clickOnHome() {
		try {
			Stage primaryStage = (Stage) home.getScene().getWindow();
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("menu.fxml"));
			Scene scene = new Scene(root, 874, 678);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@FXML
	void clickOnDalto() {
		// activer mode daltonien ou désactiver
	}

	@FXML
	void clickOnDelete() {
		// supprimer les données liées au compte
	}
}
