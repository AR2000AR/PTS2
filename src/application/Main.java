package application;

import org.omg.CORBA.portable.InputStream;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.image.cochon1;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		VBox root = new VBox();
		primaryStage.setTitle("3 Petit cochon");
		Scene scene = new Scene(root, 1080, 720);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

		Class<?> Cochon = this.getClass();

		java.io.InputStream input = Cochon.getResourceAsStream("/image/pig.png");
		Image image = new Image("/image/pig.png", 100, 100, false, false);

		// Image image = new Image(input);
		ImageView tabView = new ImageView(image);
		ImageView cochon1 = new ImageView(image);
		tabView.setRotate(90);

		VBox vbox = new VBox();
		root.getChildren().add(vbox);
		vbox.getChildren().addAll(cochon1, tabView);
		
		cochon1.setOnMousePressed(e->{
			System.out.println(" # "+e.getEventType());
			if(e.getClickCount() == 2){
                System.out.println("Double click");
                cochon1.setRotate(cochon1.getRotate()+90);
            }
		});
		
		
			
		
	}

}
