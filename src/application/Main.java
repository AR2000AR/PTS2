package application;

import java.io.IOException;

import controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage primaryStage;
	private static String profilName;

	public static void main(String[] args) {
		launch(args);
	}

	public void chargerPageEntrainement(int ctx, int diff, int nv) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Plateau.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		PlateauController pc = loader.getController();
		Scene scene = new Scene(root, 1080, 720);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		pc.setParam(ctx, diff, nv);
	}

	public void profileSelected(String name) {
		Main.profilName = name;
	}

	public void showNewProfil() {
		try {
			Pane root = (Pane) FXMLLoader.load(getClass().getResource("fxml/profil/NewProfil.fxml"));
			Scene scene = new Scene(root, 899, 698);
			scene.getStylesheets().add(getClass().getResource("fxml/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
			Platform.exit();
		}
	}

	public void showProfilSelection() {
		try {
			Pane root = (Pane) FXMLLoader.load(getClass().getResource("fxml/profil/ProfilSelection.fxml"));
			Scene scene = new Scene(root, 899, 698);
			scene.getStylesheets().add(getClass().getResource("fxml/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
			Platform.exit();
		}
	}

	@Override
	public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
		Controller.setMainClass(this);
		showProfilSelection();
	}

}
