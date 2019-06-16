package controller;

import java.io.IOException;

import com.sun.javafx.geom.transform.GeneralTransform3D;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ChoixNiveauEntrainementProgressionController extends Controller {

	@FXML
	private ImageView btnJouer;

	@FXML
	private ImageView btnRetour;

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

	static Image imJouer = new Image("file:src/image/imageBoutonJouer.png");
	static Image imRetour = new Image("file:src/image/imageBoutonRetour.png");

	private int contexteSelect = -1; // 0->1
	private int nv = -1; // 0 -> 5
	private int diff = -1; // 0 ->3

	protected boolean enProgression = false;
	
	private boolean selectDiff[] = new boolean[4];
	private ImageView tabObj[] = new ImageView[4];
	private boolean tabNvim[] = new boolean[6];
	private ImageView tabObj2[] = new ImageView[6];

	public void initialize() {

		nv1img.setImage(im1ec);
		nv2img.setImage(im2ec);
		nv3img.setImage(im3ec);
		nv4img.setImage(im4ec);
		nv5img.setImage(im5ec);
		nv6img.setImage(im6ec);

		sectionDiurne.setImage(imBtnDiurne);
		sectionNocturne.setImage(imBtnNocturne);

		sectionStarter.setImage(imBtnStarter);
		sectionJunior.setImage(imBtnJunior);
		sectionExpert.setImage(imBtnExpert);
		sectionMaster.setImage(imBtnMaster);

		selectDiff[0] = true;
		selectDiff[1] = false;
		selectDiff[2] = false;
		selectDiff[3] = false;

		tabObj[0] = sectionStarter;
		tabObj[1] = sectionJunior;
		tabObj[2] = sectionExpert;
		tabObj[3] = sectionMaster;

		tabObj2[0] = nv1img;
		tabObj2[1] = nv2img;
		tabObj2[2] = nv3img;
		tabObj2[3] = nv4img;
		tabObj2[4] = nv5img;
		tabObj2[5] = nv6img;

		btnJouer.setImage(imJouer);
		btnRetour.setImage(imRetour);

	}

	@FXML
	void switchContexteBtn01() {
		this.contexteSelect = 0;
		switchContexte(this.contexteSelect);
	}

	@FXML
	void switchContexteBtn02() {
		this.contexteSelect = 1;
		switchContexte(this.contexteSelect);
	}

	void switchContexte(int i) {

		Bloom bloom = new Bloom();
		Bloom debloom = new Bloom();

		bloom.setThreshold(0.2);
		debloom.setThreshold(1);

		if (i == 0) {
			sectionDiurne.setEffect(bloom);
			sectionNocturne.setEffect(debloom);
		} else {
			sectionDiurne.setEffect(debloom);
			sectionNocturne.setEffect(bloom);
		}
	}

	void switchDiff() {

		Bloom bloom = new Bloom();
		Bloom debloom = new Bloom();

		bloom.setThreshold(0.1);
		debloom.setThreshold(1);

		int r = 0;
		for (int i = 0; i < 4; i++) {
			if (selectDiff[i]) {
				r = i;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (i != r) {
				tabObj[i].setEffect(debloom);
				;
			}
		}
		tabObj[r].setEffect(bloom);
		;

	}

	void switchNv() {

		Bloom bloom = new Bloom();
		Bloom debloom = new Bloom();

		bloom.setThreshold(0.2);
		debloom.setThreshold(1);

		int r = 0;
		for (int i = 0; i < 6; i++) {
			if (tabNvim[i]) {
				r = i;
			}
		}
		for (int i = 0; i < 6; i++) {
			if (i != r) {
				tabObj2[i].setEffect(debloom);
				;
			}
		}
		tabObj2[r].setEffect(bloom);

	}

	@FXML
	void starterClic(MouseEvent event) {
		selectDiff[0] = true;
		selectDiff[1] = false;
		selectDiff[2] = false;
		selectDiff[3] = false;
		switchDiff();
		diff = 0;
	}

	@FXML
	void juniorClic(MouseEvent event) {
		selectDiff[0] = false;
		selectDiff[1] = true;
		selectDiff[2] = false;
		selectDiff[3] = false;
		switchDiff();
		diff = 1;
	}

	@FXML
	void expertClic(MouseEvent event) {
		selectDiff[0] = false;
		selectDiff[1] = false;
		selectDiff[2] = true;
		selectDiff[3] = false;
		switchDiff();
		diff = 2;
	}

	@FXML
	void masterClic(MouseEvent event) {
		selectDiff[0] = false;
		selectDiff[1] = false;
		selectDiff[2] = false;
		selectDiff[3] = true;
		switchDiff();
		diff = 3;
	}

	@FXML
	void clic01() throws IOException {
		tabNvim[0] = true;
		tabNvim[1] = false;
		tabNvim[2] = false;
		tabNvim[3] = false;
		tabNvim[4] = false;
		tabNvim[5] = false;
		nv = 0;
		switchNv();
	}

	@FXML
	void clic02() throws IOException {
		tabNvim[0] = false;
		tabNvim[1] = true;
		tabNvim[2] = false;
		tabNvim[3] = false;
		tabNvim[4] = false;
		tabNvim[5] = false;
		nv = 1;
		switchNv();
	}

	@FXML
	void clic03() throws IOException {
		tabNvim[0] = false;
		tabNvim[1] = false;
		tabNvim[2] = true;
		tabNvim[3] = false;
		tabNvim[4] = false;
		tabNvim[5] = false;
		nv = 2;
		switchNv();
	}

	@FXML
	void clic04() throws IOException {
		tabNvim[0] = false;
		tabNvim[1] = false;
		tabNvim[2] = false;
		tabNvim[3] = true;
		tabNvim[4] = false;
		tabNvim[5] = false;
		nv = 3;
		switchNv();
	}

	@FXML
	void clic05() throws IOException {
		tabNvim[0] = false;
		tabNvim[1] = false;
		tabNvim[2] = false;
		tabNvim[3] = false;
		tabNvim[4] = true;
		tabNvim[5] = false;
		nv = 4;
		switchNv();
	}

	@FXML
	void clic06() throws IOException {
		tabNvim[0] = false;
		tabNvim[1] = false;
		tabNvim[2] = false;
		tabNvim[3] = false;
		tabNvim[4] = false;
		tabNvim[5] = true;
		nv = 5;
		switchNv();
	}

	@FXML
	void clicSurJouer() throws IOException {
		
		if(contexteSelect != -1 && nv != -1 && diff != -1) {
			System.out.println(contexteSelect + "  "+ "  "+diff+"  "+nv);
			getMainClass().chargerNivEntrProg(contexteSelect, diff, nv);
		}
		
	}

	@FXML
	void clicSurRetour() {
		getMainClass().showMenu();
	}
}
