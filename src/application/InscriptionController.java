package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InscriptionController {

	
    @FXML
    private Button inscription2;
    




    @FXML
    void clickOnInscription2() {
    	try {
			Stage primaryStage= (Stage)inscription2.getScene().getWindow();
			Parent root= (Parent)FXMLLoader.load(getClass().getResource("menu.fxml"));
			Scene scene = new Scene(root,874,678);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }
}
