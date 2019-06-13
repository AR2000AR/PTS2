package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class tre2Controller {


    @FXML
    private ImageView nv1img;

    @FXML
    private ImageView nv3img;

    @FXML
    private ImageView nv2img;

    @FXML
    private ImageView nv4img;

    @FXML
    private Label lblSection;

    @FXML
    private ImageView nv5img;

    @FXML
    private ImageView nv6img;
    
    static Image im1ec = new Image("file:src/image/1enCours.png");
    static Image im2ec = new Image("file:src/image/2enCours.png");
    static Image im3ec = new Image("file:src/image/3enCours.png");
    static Image im4ec = new Image("file:src/image/4enCours.png");
    static Image im5ec = new Image("file:src/image/5enCours.png");
    static Image im6ec = new Image("file:src/image/6enCours.png");
    
    static Image im1f = new Image("file:src/image/1enFait.png");
    static Image im2f = new Image("file:src/image/2enFait.png");
    static Image im3f = new Image("file:src/image/3enFait.png");
    static Image im4f = new Image("file:src/image/4enFait.png");
    static Image im5f = new Image("file:src/image/5enFait.png");
    static Image im6f = new Image("file:src/image/6enFait.png");
    
    static Image imCadena = new Image("file:src/image/cadena.png");
    
    
    public void initialize() {
    	
    	nv1img.setImage(im1f);
    	nv2img.setImage(imCadena);
    	nv3img.setImage(imCadena);
    	nv4img.setImage(imCadena);
    	nv5img.setImage(imCadena);
    	nv6img.setImage(imCadena);
    	
    	
    	
    	
    }
	
}
