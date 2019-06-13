package application;

import java.io.IOException;
import java.util.Arrays;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {

			// AnchorPane root = (AnchorPane)
			// FXMLLoader.load(getClass().getResource("fxml/Plateau.fxml"));
			// AnchorPane root = (AnchorPane)
			// FXMLLoader.load(getClass().getResource("fxml/Competition.fxml"));
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("fxml/tre2.fxml"));

			Scene scene = new Scene(root, 1080, 720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void chargerPageEntrainement(int ctx, int nv, int diff) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		AnchorPane root = (AnchorPane) loader.load(getClass().getResource("fxml/Plateau.fxml"));
		PlateauController pc = loader.getController();
		pc.setParam(ctx,nv,diff);
	}

}
