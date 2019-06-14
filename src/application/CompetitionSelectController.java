package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CompetitionSelectController {

	
	@FXML
	private Label timer;
	
	@FXML
	private ImageView option;
	
    @FXML
    private Button nocturne;

    @FXML
    private Button diurne;

    @FXML
    private VBox diffDiurne;

    @FXML
    private VBox diffNocturne;
    
    @FXML
    private ToggleGroup toggleGroupDiurne;
    
    @FXML
    private ToggleGroup toggleGroupNocturne;
    
    @FXML
    private Button goCompet;

    
    private int contexte;
    private int diff;
    
    @FXML
    void clickOnGoCompet() {
    	try {
    		for(int i=0;i<4; i++)
    		{
    			System.out.println("salut");
    			if(diffDiurne.getChildren().get(i).isPressed()) System.out.println(i);
//    			RadioButton button = (RadioButton) diffDiurne.getChildren().get(i);
//    			if(
    		}
    		
    	Stage primaryStage = (Stage) goCompet.getScene().getWindow();
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("competition.fxml"));
		Scene scene = new Scene(root, 874, 678);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	} catch (IOException e) {

		e.printStackTrace();
	}
    }
    
    @FXML
    void clickOnDiurne() {
    	diffDiurne.setDisable(false);
    	if(diffNocturne.getOpacity()!=0.24) diffNocturne.setOpacity(0.24);
    	if(diffDiurne.getOpacity()!=1)diffDiurne.setOpacity(1);
    	diffNocturne.setDisable(true);
    	contexte=0;
    	
    }

    @FXML
    void clickOnNocturne() {
    	diffNocturne.setDisable(false);
    	if(diffDiurne.getOpacity()!=0.24)diffDiurne.setOpacity(0.24);
    	if(diffNocturne.getOpacity()!=1)diffNocturne.setOpacity(1);
    	diffDiurne.setDisable(true);
    	contexte=1;
   }
	
	@FXML
	void clickOnOption() {
		try {
			Stage primaryStage = (Stage) option.getScene().getWindow();
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("options.fxml"));
			Scene scene = new Scene(root, 874, 678);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	
	
}
