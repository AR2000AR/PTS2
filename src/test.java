
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class test extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello");
        VBox root = new VBox() {{
            getChildren().add(createList());
        }};
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    private Node createList() {
        return new ListView<String>() {{
            setItems(FXCollections.observableArrayList("Apple", "Orange", "Pear", "Banana"));
            setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    String selected = this.getSelectionModel().getSelectedItems().get(0);
                    new Alert(Alert.AlertType.INFORMATION) {{
                        setTitle("You selected:");
                        setHeaderText(selected);
                    }}.showAndWait();
                }
            });
        }};
    }
}