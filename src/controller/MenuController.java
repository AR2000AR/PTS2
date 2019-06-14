package controller;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuController {

	@FXML
	private Button comp;

	@FXML
	private Button scor;

	@FXML
	private Button entr;

	@FXML
	private Button prog;

	@FXML
	private ImageView option;
	

	

	@FXML
	void clickOnScor() {
		try {
			Stage primaryStage = (Stage) scor.getScene().getWindow();
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("scores.fxml"));
			Scene scene = new Scene(root, 874, 678);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@FXML
	void clickOnComp() {
		try {
			Stage primaryStage = (Stage) comp.getScene().getWindow();
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("competitionSelect.fxml"));
			Scene scene = new Scene(root, 874, 678);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@FXML
	void clickOnProg() {
		try {
			Stage primaryStage = (Stage) prog.getScene().getWindow();
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("progressionSelect.fxml"));
			Scene scene = new Scene(root, 874, 678);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@FXML
	void clickOnEntr() {
		try {
			Stage primaryStage = (Stage) entr.getScene().getWindow();
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("entrainementSelect.fxml"));
			Scene scene = new Scene(root, 874, 678);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
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
