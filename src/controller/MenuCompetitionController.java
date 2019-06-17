package controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MenuCompetitionController extends Controller {

	private static boolean imported = false;

	static Image im1ec;

	static Image im2ec;

	static Image im3ec;

	static Image im4ec;

	static Image im5ec;

	static Image im6ec;

	static Image im1f;

	static Image im2f;

	static Image im3f;
	static Image im4f;
	static Image im5f;
	static Image im6f;
	static Image imBtnDiurne;
	static Image imBtnNocturne;

	static Image imBtnStarter;
	static Image imBtnJunior;
	static Image imBtnExpert;
	static Image imBtnMaster;
	static Image imJouer;
	static Image imVoirScore;

	static Image imRetour;
	@FXML
	private ImageView btnValider;

	@FXML
	private ImageView btnRetour;
	@FXML
	private ImageView sectionDiurne;
	@FXML
	private ImageView sectionNocturne;
	@FXML
	private ImageView sectionJunior;

	@FXML
	private ImageView sectionMaster;
	@FXML
	private ImageView sectionExpert;
	@FXML
	private ImageView sectionStarter;

	private int contexteSelect = -1; // 0->1
	private int nv = -1; // 0 -> 5
	private int diff = -1; // 0 ->3

	private boolean selectDiff[] = new boolean[4];
	private ImageView tabObj[] = new ImageView[4];
	private boolean tabNvim[] = new boolean[6];
	private ImageView tabObj2[] = new ImageView[6];

	protected boolean valeurTemoin; // 0 => lance competition , 1 => lance score

	@FXML
	void clicSurRetour() {
		getMainClass().showMenu();
	}

	@FXML
	void clicValider() throws IOException {

		if ((contexteSelect != -1) && (diff != -1)) {

			if (valeurTemoin) {
				System.out.println("Mode Diurne : " + contexteSelect + "\n Difficulte = " + diff);
				getMainClass().chargerComp(contexteSelect, diff);
			} else {
				getMainClass().showScores(contexteSelect, diff);
			}
		}

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

	public void initialize() {

		if (!imported) {

			im1ec = new Image(getClass().getResourceAsStream("/image/1enCours.png"));
			im2ec = new Image(getClass().getResourceAsStream("image/2enCours.png"));
			im3ec = new Image(getClass().getResourceAsStream("image/3enCours.png"));
			im4ec = new Image(getClass().getResourceAsStream("image/4enCours.png"));
			im5ec = new Image(getClass().getResourceAsStream("image/5enCours.png"));
			im6ec = new Image(getClass().getResourceAsStream("image/6enCours.png"));

			im1f = new Image(getClass().getResourceAsStream("image/1enFait.png"));
			im2f = new Image(getClass().getResourceAsStream("image/2enFait.png"));
			im3f = new Image(getClass().getResourceAsStream("image/3enFait.png"));
			im4f = new Image(getClass().getResourceAsStream("image/4enFait.png"));
			im5f = new Image(getClass().getResourceAsStream("image/5enFait.png"));
			im6f = new Image(getClass().getResourceAsStream("image/6enFait.png"));

			imBtnDiurne = new Image(getClass().getResourceAsStream("image/imageBoutonDiurne.png"));
			imBtnNocturne = new Image(getClass().getResourceAsStream("image/imageBoutonNocturne.png"));

			imBtnStarter = new Image(getClass().getResourceAsStream("image/imageBoutonStarter.png"));
			imBtnJunior = new Image(getClass().getResourceAsStream("image/imageBoutonJunior.png"));
			imBtnExpert = new Image(getClass().getResourceAsStream("image/imageBoutonExpert.png"));
			imBtnMaster = new Image(getClass().getResourceAsStream("image/imageBoutonMaster.png"));

			imJouer = new Image(getClass().getResourceAsStream("image/imageBoutonJouer.png"));
			imVoirScore = new Image(getClass().getResourceAsStream("image/imageBoutonVoirScore.png"));
			imRetour = new Image(getClass().getResourceAsStream("image/imageBoutonRetour.png"));
			imported = true;
		}

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

		Platform.runLater(() -> {
			if (valeurTemoin) {
				btnValider.setImage(imJouer);
			} else {
				btnValider.setImage(imVoirScore);
			}
		});

		btnRetour.setImage(imRetour);

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
	void masterClic(MouseEvent event) {
		selectDiff[0] = false;
		selectDiff[1] = false;
		selectDiff[2] = false;
		selectDiff[3] = true;
		switchDiff();
		diff = 3;
	}

	public void setParam(boolean b) {
		this.valeurTemoin = b;
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

			}
		}
		tabObj[r].setEffect(bloom);

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

			}
		}
		tabObj2[r].setEffect(bloom);

	}
}
