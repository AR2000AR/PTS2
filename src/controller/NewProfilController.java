/**
 * Sample Skeleton for 'NewProfil.fxml' Controller Class
 */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import gestionDeDonnee.GestionDeDonnee;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class NewProfilController extends Controller {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="textField"
	private TextField textField; // Value injected by FXMLLoader

	@FXML
	private Node okButton; // Value injected by FXMLLoader

	@FXML
	void back(MouseEvent event) {
		getMainClass().showProfilSelection();
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert textField != null : "fx:id=\"textField\" was not injected: check your FXML file 'NewProfil.fxml'.";
		assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'inscription.fxml'.";
	}

	@FXML
	void newProfil() throws SAXException, IOException, ParserConfigurationException, JDOMException {
		System.out.println(textField.getText());
		GestionDeDonnee g = new GestionDeDonnee();
		g.newProfil(textField.getText());
		getMainClass().showProfilSelection();
	}

	@FXML
	void textChange() {
		Platform.runLater(() -> {
			if (textField.getText().length() > 2) {
				okButton.setDisable(false);
				okButton.setOpacity(1);
			} else {
				okButton.setDisable(true);
				okButton.setOpacity(0.5);
			}
		});
	}
}
