/**
 * Sample Skeleton for 'inscription.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import gestionDeDonnee.GestionDeDonnee;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ProfilSelectionController extends Controller {

	private static GestionDeDonnee g = null;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="vBox"
	private VBox vBox; // Value injected by FXMLLoader

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() throws SAXException, IOException, ParserConfigurationException, JDOMException {
		assert vBox != null : "fx:id=\"vBox\" was not injected: check your FXML file 'inscription.fxml'.";
		if (g == null) {
			g = new GestionDeDonnee();
		}

	}
}
