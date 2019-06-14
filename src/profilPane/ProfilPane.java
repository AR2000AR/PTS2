package profilPane;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ProfilPane extends AnchorPane {

	private String name = "";
	private int prog = 0;
	private VBox infoBox;
	private Label nameLabel;
	private Label progLabel;
	private Image img = null;
	private AnchorPane spacer = null;
	private HBox rootPane = null;

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
		rootPane = new HBox();

		nameLabel = new Label(this.name);
		progLabel = new Label(Integer.toString((this.prog * 100) / 48) + "%");
		nameLabel.setFont(new Font("Segoe Script", 26));
		progLabel.setFont(nameLabel.getFont());
		nameLabel.setTextFill(Color.gray(0.9));
		progLabel.setTextFill(nameLabel.getTextFill());
		nameLabel.setPadding(new Insets(0, 0, 0, 10));
		progLabel.setPadding(nameLabel.getInsets());

		spacer = new AnchorPane();
		spacer.setPrefSize(110, 150);
		spacer.setMinSize(spacer.getPrefWidth(), spacer.getPrefHeight());

		infoBox.getChildren().addAll(nameLabel, progLabel);
		rootPane.getChildren().addAll(spacer, infoBox);
		bk = new ImageView(img);
		super.getChildren().add(bk);
		super.getChildren().add(rootPane);
		super.setMaxWidth(img.getWidth());
	}

	public String getName() {
		return name;
	}

}
