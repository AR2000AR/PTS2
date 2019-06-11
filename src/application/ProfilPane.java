package application;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import gestionDeDonnee.NoProfileException;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProfilPane extends HBox {

	private String name = "";
	private int prog = 0;
	private VBox infoBox;
	private Label nameLabel;
	private Label progLabel;

	public ProfilPane(String name, int prog)
			throws SAXException, IOException, ParserConfigurationException, JDOMException, NoProfileException {
		super();
		this.name = name;
		this.prog = prog;
		infoBox = new VBox();
		nameLabel = new Label(this.name);
		progLabel = new Label(Integer.toString(this.prog));
		infoBox.getChildren().addAll(nameLabel, progLabel);
		super.getChildren().add(infoBox);
		super.setStyle("-fx-border-color: black");
	}

}
