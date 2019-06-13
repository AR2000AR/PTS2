package application;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import gestionDeDonnee.NoProfileException;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ProfilPane extends AnchorPane {

	private String name = "";
	private int prog = 0;
	private VBox infoBox;
	private Label nameLabel;
	private Label progLabel;
	private Image img = null;

	private ImageView bk = null;

	public ProfilPane(String name, int prog)
			throws SAXException, IOException, ParserConfigurationException, JDOMException, NoProfileException {
		super();
		if (img == null) {
			img = new Image(getClass().getClassLoader().getResourceAsStream("image/profil_bk.png"));
		}
		super.setPadding(new Insets(2.0, 0.0, 2.0, 0.0));
		this.name = name;
		this.prog = prog;
		infoBox = new VBox();
		nameLabel = new Label(this.name);
		progLabel = new Label(Integer.toString(this.prog));
		infoBox.getChildren().addAll(nameLabel, progLabel);
		bk = new ImageView(img);
		super.getChildren().add(bk);
		super.getChildren().add(infoBox);
		super.setMaxWidth(img.getWidth());
		// super.setStyle("-fx-border-color: black");
		// super.setStyle("-fx-border-width:5");
	}

}
