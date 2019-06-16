package application;

import java.io.IOException;

import controller.ChoixNiveauEntrainementProgressionController;
import controller.Controller;
import controller.PlateauController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage primaryStage;

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

	public void choixNvEntrProg(boolean mode) {

		try {

			/*
			 * A NE PAS EFFACER LES COMMENTAIRES
			 */

			// AnchorPane root = (AnchorPane)
			// FXMLLoader.load(getClass().getResource("fxml/Plateau.fxml"));
			// AnchorPane root = (AnchorPane)
			// FXMLLoader.load(getClass().getResource("fxml/Competition.fxml"));

			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ChoixNiveauEntrainementProgression.fxml"));
			Pane root = (Pane) loader.load();
			ChoixNiveauEntrainementProgressionController cnep = loader.getController();
			// cnep.setParam(?);
			Scene scene = new Scene(root, 1080, 720);
			scene.getStylesheets().add(getClass().getResource("fxml/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void profileSelected(String name) {
		Controller.setProfilName(name);
		showMenu();
	}

	public void showMenu() {
		try {
			Pane root = (Pane) FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"));
			Scene scene = new Scene(root, 1080, 720);
			scene.getStylesheets().add(getClass().getResource("fxml/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
			Platform.exit();
		}
	}

	public void showNewProfil() {
		try {
			Pane root = (Pane) FXMLLoader.load(getClass().getResource("fxml/profil/NewProfil.fxml"));
			Scene scene = new Scene(root, 1080, 720);
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
			Scene scene = new Scene(root, 1080, 720);
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

	public void chargerNivEntrProg(int ctx, int diff, int nv) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Plateau.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
        PlateauController pc = loader.getController();
        Scene scene = new Scene(root, 1080, 720);
        scene.getStylesheets().add(getClass().getResource("fxml/application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        pc.setParam(ctx, diff, nv);
    }

	public void chargerMenuSelectionEntrProg() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ChoixNiveauEntrainementProgression.fxml"));
		Pane root = (Pane) loader.load();
		ChoixNiveauEntrainementProgressionController cnep = loader.getController();
		// cnep.setParam(?);
		Scene scene = new Scene(root, 1080, 720);
		scene.getStylesheets().add(getClass().getResource("fxml/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
