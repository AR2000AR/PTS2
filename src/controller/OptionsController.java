package controller;

import java.io.IOException;

/**
 * Sample Skeleton for 'Options.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import gestionDeDonnee.GestionDeDonnee;
import gestionDeDonnee.NoProfileException;
import javafx.fxml.FXML;

public class OptionsController extends Controller {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML
	void delProfil() throws NoProfileException, IOException, SAXException, ParserConfigurationException, JDOMException {
		GestionDeDonnee g = new GestionDeDonnee();
		g.delProfil(getProfilName());
		getMainClass().showProfilSelection();
	}

	@FXML
	void home() {
		getMainClass().showMenu();
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {

	}
}
