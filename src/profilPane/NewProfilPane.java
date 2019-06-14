package profilPane;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import gestionDeDonnee.NoProfileException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class NewProfilPane extends AnchorPane {
	private VBox infoBox;
	private Label nameLabel;
	private Image img = null;
	private AnchorPane spacer = null;
	private HBox rootPane = null;

	private ImageView bk = null;

	public NewProfilPane()
			throws SAXException, IOException, ParserConfigurationException, JDOMException, NoProfileException {
		super();
		if (img == null) {
			img = new Image(getClass().getClassLoader().getResourceAsStream("image/profil_bk.png"));
		}

		super.setPadding(new Insets(2.0, 0.0, 2.0, 0.0));

		infoBox = new VBox();

		rootPane = new HBox();

		nameLabel = new Label("Nouveau");
		nameLabel.setFont(new Font("Segoe Script", 30));
		nameLabel.setTextFill(Color.gray(0.9));

		spacer = new AnchorPane();
		spacer.setPrefSize(110, 150);
		spacer.setMinSize(spacer.getPrefWidth(), spacer.getPrefHeight());

		infoBox.getChildren().add(nameLabel);
		infoBox.setAlignment(Pos.CENTER);
		infoBox.setPrefSize(img.getWidth() - spacer.getPrefWidth(), img.getHeight() - spacer.getPrefHeight());

		rootPane.getChildren().addAll(spacer, infoBox);

		bk = new ImageView(img);

		super.getChildren().add(bk);
		super.getChildren().add(rootPane);
		super.setMaxWidth(img.getWidth());
	}

}
