/**
 * Sample Skeleton for 'menu.fxml' Controller Class
 */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController extends Controller {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="comp"
	private Button comp; // Value injected by FXMLLoader

	@FXML // fx:id="scor"
	private Button scor; // Value injected by FXMLLoader

	@FXML // fx:id="entr"
	private Button entr; // Value injected by FXMLLoader

	@FXML // fx:id="prog"
	private Button prog; // Value injected by FXMLLoader

	@FXML
	void back() {
		getMainClass().showProfilSelection();
	}

	@FXML
	void comp() {

	}

	@FXML
	void entr() {
		getMainClass().choixNvEntrProg(false);
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert comp != null : "fx:id=\"comp\" was not injected: check your FXML file 'menu.fxml'.";
		assert scor != null : "fx:id=\"scor\" was not injected: check your FXML file 'menu.fxml'.";
		assert entr != null : "fx:id=\"entr\" was not injected: check your FXML file 'menu.fxml'.";
		assert prog != null : "fx:id=\"prog\" was not injected: check your FXML file 'menu.fxml'.";

	}

	@FXML
	void option() throws IOException {
		getMainClass().showOption();
	}

	@FXML
	void prog() {
		getMainClass().choixNvEntrProg(true);
	}

	@FXML
	void scores() throws IOException {
		getMainClass().showScores(0, 1);
	}
}
