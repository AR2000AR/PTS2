package controller;

import java.io.IOException;

/**
 * Sample Skeleton for 'Scores.fxml' Controller Class
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import com.sun.javafx.geom.transform.GeneralTransform3D;

import gestionDeDonnee.GestionDeDonnee;
import gestionDeDonnee.Score;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ScoresController extends Controller {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="scoresView"
	private ListView<?> scoresView; // Value injected by FXMLLoader

	@FXML // fx:id="contextImg"
	private ImageView contextImg; // Value injected by FXMLLoader

	@FXML // fx:id="diffImg"
	private ImageView diffImg; // Value injected by FXMLLoader

	private int context;
	private int difficulte;

	@FXML
	void back() throws IOException {
		getMainClass().choixComp(false);

	}

	@FXML
	void home() {
		getMainClass().showMenu();
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() throws SAXException, IOException, ParserConfigurationException, JDOMException {
		assert scoresView != null : "fx:id=\"scoresView\" was not injected: check your FXML file 'Scores.fxml'.";
		assert contextImg != null : "fx:id=\"contextImg\" was not injected: check your FXML file 'Scores.fxml'.";
		assert diffImg != null : "fx:id=\"diffImg\" was not injected: check your FXML file 'Scores.fxml'.";

		Platform.runLater(() -> {
			switch (context) {
			case 0:
				contextImg.setImage(
						new Image(getClass().getClassLoader().getResourceAsStream("image/imageBoutonDiurne.png")));
				break;
			case 1:
				contextImg.setImage(
						new Image(getClass().getClassLoader().getResourceAsStream("image/imageBoutonNocturne.png")));
				break;
			}

			switch (difficulte) {
			case 0:
				diffImg.setImage(
						new Image(getClass().getClassLoader().getResourceAsStream("image/imageBoutonStarter.png")));
				break;
			case 1:
				diffImg.setImage(
						new Image(getClass().getClassLoader().getResourceAsStream("image/imageBoutonJunior.png")));
				break;
			case 2:
				diffImg.setImage(
						new Image(getClass().getClassLoader().getResourceAsStream("image/imageBoutonExpert.png")));
				break;
			case 3:
				diffImg.setImage(
						new Image(getClass().getClassLoader().getResourceAsStream("image/imageBoutonMaster.png")));
				break;
			}

			GestionDeDonnee g;
			try {
				g = new GestionDeDonnee();
				List<Score> scores = g.getScore(context, difficulte);
				List<String> displayString = new ArrayList<String>();
				for (Score score : scores) {
					displayString.add(String.format("%-20s | %s", score.getName(), Integer.toString(score.getTime())));
				}
				ObservableList<String> list = (ObservableList<String>) scoresView.getItems();
				list.addAll(displayString);
			} catch (SAXException | IOException | ParserConfigurationException | JDOMException e) {
				e.printStackTrace();
				Platform.exit();
			}

		});

	}

	public void setParam(int context, int difficulte) {
		this.context = context;
		this.difficulte = difficulte;
	}
}
