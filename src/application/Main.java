package application;

import java.util.Arrays;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	static int temoin = 0;

	static int autourP1[][] = { { 1, -1 }, { 1, 1 }, { -1, 1 }, { -1, 1 } };

	static int autourP3[][] = { { 1, -1 }, { 1, 1 }, { -1, 1 }, { -1, 1 } };

	public static Case[][] plateau = new Case[4][4];

	public void start(Stage primaryStage) {
		try {

			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Plateau.fxml"));
			Scene scene = new Scene(root, 1080, 720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean testPlacer(double[] t) {
		System.out.println(Arrays.toString(t));
		for (int i = 0; i < 4; i++) {
			System.out.println(Arrays.toString(plateau[i]));
		}
		boolean test = true;
		test = testDessusDessousPiece((int) t[0], (int) t[1]);
		
		return test;
	}

	private static boolean testDessusDessousPiece(int x, int y) {
		boolean testPiecePoser = false;
		boolean t1 = false;
		boolean t2 = false;
		if (testDessusDessous(x, y)) {

			switch (temoin) {
			case 1:
				System.out.println("YOLO");
				if (PlateauController.etatP1 == 1) {
					System.out.println("ICI");
					t1 = testDessusDessous(x + 1, y) && testDessusDessous(x, y - 1);
				}
				if (PlateauController.etatP1 == 2) {
					t1 = testDessusDessous(x + 1, y) && testDessusDessous(x, y + 1);
				}
				if (PlateauController.etatP1 == 3) {
					t1 = testDessusDessous(x - 1, y) && testDessusDessous(x, y + 1);
				}
				if (PlateauController.etatP1 == 4) {
					t1 = testDessusDessous(x - 1, y) && testDessusDessous(x, y - 1);
				}
				t2 = t1;
				break;

			case 2:
				if (PlateauController.etatP2 == 1) {
					t1 = testDessusDessous(x + 1, y) && testDessusDessous(x - 1, y);
				} else {
					t1 = testDessusDessous(x, y + 1) && testDessusDessous(x, y - 1);
				}
				t2 = t1;
				break;

			case 3:
				switch (PlateauController.etatP3) {

				case 1:
					t1 = testDessusDessous(x - 1, y) && testDessusDessous(x - 2, y);
					t2 = testDessusDessous(x, y - 1);
					break;
				case 2:
					t1 = testDessusDessous(x, y - 1) && testDessusDessous(x, y - 2);
					t2 = testDessusDessous(x + 1, y);
					break;
				case 3:
					t1 = testDessusDessous(x + 1, y) && testDessusDessous(x + 2, y);
					t2 = testDessusDessous(x, y + 1);
					break;
				case 4:
					t1 = testDessusDessous(x, y + 1) && testDessusDessous(x, y + 2);
					t2 = testDessusDessous(x - 1, y);
					break;

				}
				break;

			default:
				break;
			}

		}

		testPiecePoser = t1 && t2;
		return testPiecePoser;
	}

	private static boolean testDessusDessous(double x, double y) {
		if (x >= 0 && x <= 3 && y >= 0 && y <= 3) {
			Case c = plateau[(int) y][(int) x];
			if (c.etatCase == EnumCase.LIBRE) {
				return true;
			} else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
