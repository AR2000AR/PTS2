package application;

import java.io.IOException;

import com.sun.javafx.geom.transform.GeneralTransform3D;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class tre2Controller extends Controller {

	@FXML
	private ImageView nv1img;

	@FXML
	private ImageView nv3img;

	@FXML
	private ImageView nv2img;

	@FXML
	private ImageView nv4img;

	@FXML
	private ImageView sectionDiurne;

	@FXML
	private ImageView sectionNocturne;

	@FXML
	private ImageView nv5img;

	@FXML
	private ImageView nv6img;

	@FXML
	private ImageView sectionJunior;

	@FXML
	private ImageView sectionMaster;

	@FXML
    private ImageView sectionExpert;

	@FXML
	private ImageView sectionStarter;

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

	static Image imBtnDiurne = new Image("file:src/image/imageBoutonDiurne.png");
	static Image imBtnNocturne = new Image("file:src/image/imageBoutonNocturne.png");
	
	static Image imBtnStarter = new Image("file:src/image/imageBoutonStarter.png");
	static Image imBtnJunior = new Image("file:src/image/imageBoutonJunior.png");
	static Image imBtnExpert = new Image("file:src/image/imageBoutonExpert.png");
	static Image imBtnMaster = new Image("file:src/image/imageBoutonMaster.png");
	
	

	private int contexteSelect = 0;

	public void initialize() {

		nv1img.setImage(im1ec);
		nv2img.setImage(im2ec);
		nv3img.setImage(im3ec);
		nv4img.setImage(im4ec);
		nv5img.setImage(im5ec);
		nv6img.setImage(im6ec);

		sectionDiurne.setImage(imBtnDiurne);
		sectionNocturne.setImage(imBtnNocturne);
		sectionNocturne.setOpacity(0.5);
		
		sectionStarter.setImage(imBtnStarter);
		sectionJunior.setImage(imBtnJunior);
		sectionExpert.setImage(imBtnExpert);
		sectionMaster.setImage(imBtnMaster);

		sectionStarter.setOpacity(1);
		sectionJunior.setOpacity(0.5);
		sectionExpert.setOpacity(0.5);
		sectionMaster.setOpacity(0.5);
		
	}

	@FXML
	void switchContexteBtn01() {
		this.contexteSelect = 0;
		switchContexte(this.contexteSelect);
	}

	@FXML
	void switchContexteBtn02() {
		System.out.println("COUCOU");
		this.contexteSelect = 1;
		switchContexte(this.contexteSelect);
	}

	void switchContexte(int i) {

		if (i == 0) {
			sectionDiurne.setOpacity(1);
			sectionNocturne.setOpacity(0.5);
		} else {
			sectionDiurne.setOpacity(0.5);
			sectionNocturne.setOpacity(1);
		}

	}

	@FXML
	void clic01() throws IOException {
		getMainClass().chargerPageEntrainement(0, 1, 1);
	}

	@FXML
	void clic02() throws IOException {
		getMainClass().chargerPageEntrainement(0, 1, 2);
	}

	@FXML
	void clic03() throws IOException {
		getMainClass().chargerPageEntrainement(0, 1, 3);
	}

}
