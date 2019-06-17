package controller;

import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import com.sun.javafx.geom.transform.GeneralTransform3D;

import gestionDeDonnee.GestionDeDonnee;
import gestionDeDonnee.NoProfileException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ChoixNiveauEntrainementProgressionController extends Controller {

	@FXML
	protected ImageView btnJouer;

	@FXML
	protected ImageView btnRetour;

	@FXML
	private ImageView nv1img;

	@FXML
	private ImageView nv3img;

	@FXML
	private ImageView nv2img;

	@FXML
	private ImageView nv4img;

	@FXML
	private ImageView nv5img;

	@FXML
	private ImageView nv6img;
	
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

	
	static Image tabImec[] = {im1ec,im2ec,im3ec,im4ec,im5ec,im6ec};
	static Image tabIf[] = {im1f,im2f,im3f,im4f,im5f,im6f};
	
	protected ImageView tabImV[] = {nv1img,nv2img,nv3img,nv4img,nv5img,nv6img};
	
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

	public void initialize() throws SAXException, IOException, ParserConfigurationException, JDOMException, NoProfileException {

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

	
	public void testRealiser() throws SAXException, IOException, ParserConfigurationException, JDOMException, NoProfileException {
	/*	if(contexteSelect != -1 && diff != -1 && enProgression) {
		GestionDeDonnee g = new GestionDeDonnee();
		Boolean[][][] t = g.getProgression(getProfilName());
		
		System.out.println(Arrays.toString(t[contexteSelect][diff]));
		
		for(int i=0;i<6;i++) {
			if(t[contexteSelect][diff][i]) {
				System.out.println(t[contexteSelect][diff][i]);
				tabImV[i].setImage(tabIf[i]);
			}
		}
		}*/
	}
	
	@FXML
	void switchContexteBtn01() throws SAXException, IOException, ParserConfigurationException, JDOMException, NoProfileException {
		this.contexteSelect = 0;
		switchContexte(this.contexteSelect);
		testRealiser();
		
	}

	@FXML
	void switchContexteBtn02() throws SAXException, IOException, ParserConfigurationException, JDOMException, NoProfileException {
		this.contexteSelect = 1;
		switchContexte(this.contexteSelect);
		testRealiser();
	}

	void switchContexte(int i) throws SAXException, IOException, ParserConfigurationException, JDOMException, NoProfileException {

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
	
			testRealiser();
			
		
	}

	void switchDiff() throws SAXException, IOException, ParserConfigurationException, JDOMException, NoProfileException {

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
			testRealiser();


	}

	void switchNv() throws SAXException, IOException, ParserConfigurationException, JDOMException, NoProfileException {

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
		testRealiser();
	}

	@FXML
	void starterClic(MouseEvent event) throws SAXException, IOException, ParserConfigurationException, JDOMException, NoProfileException {
		selectDiff[0] = true;
		selectDiff[1] = false;
		selectDiff[2] = false;
		selectDiff[3] = false;
		switchDiff();
		diff = 0;
		testRealiser();
	}

	@FXML
	void juniorClic(MouseEvent event) throws SAXException, IOException, ParserConfigurationException, JDOMException, NoProfileException {
		selectDiff[0] = false;
		selectDiff[1] = true;
		selectDiff[2] = false;
		selectDiff[3] = false;
		switchDiff();
		diff = 1;
		testRealiser();
	}

	@FXML
	void expertClic(MouseEvent event) throws SAXException, IOException, ParserConfigurationException, JDOMException, NoProfileException {
		selectDiff[0] = false;
		selectDiff[1] = false;
		selectDiff[2] = true;
		selectDiff[3] = false;
		switchDiff();
		diff = 2;
		testRealiser();
	}

	@FXML
	void masterClic(MouseEvent event) throws SAXException, IOException, ParserConfigurationException, JDOMException, NoProfileException {
		selectDiff[0] = false;
		selectDiff[1] = false;
		selectDiff[2] = false;
		selectDiff[3] = true;
		switchDiff();
		diff = 3;
		testRealiser();
	}

	@FXML
	void clic01() throws IOException, SAXException, ParserConfigurationException, JDOMException, NoProfileException {
		tabNvim[0] = true;
		tabNvim[1] = false;
		tabNvim[2] = false;
		tabNvim[3] = false;
		tabNvim[4] = false;
		tabNvim[5] = false;
		nv = 0;
		switchNv();
		testRealiser();
	}

	@FXML
	void clic02() throws IOException, SAXException, ParserConfigurationException, JDOMException, NoProfileException {
		tabNvim[0] = false;
		tabNvim[1] = true;
		tabNvim[2] = false;
		tabNvim[3] = false;
		tabNvim[4] = false;
		tabNvim[5] = false;
		nv = 1;
		switchNv();
		testRealiser();
	}

	@FXML
	void clic03() throws IOException, SAXException, ParserConfigurationException, JDOMException, NoProfileException {
		tabNvim[0] = false;
		tabNvim[1] = false;
		tabNvim[2] = true;
		tabNvim[3] = false;
		tabNvim[4] = false;
		tabNvim[5] = false;
		nv = 2;
		switchNv();
		testRealiser();
	}

	@FXML
	void clic04() throws IOException, SAXException, ParserConfigurationException, JDOMException, NoProfileException {
		tabNvim[0] = false;
		tabNvim[1] = false;
		tabNvim[2] = false;
		tabNvim[3] = true;
		tabNvim[4] = false;
		tabNvim[5] = false;
		nv = 3;
		switchNv();
		testRealiser();
	}

	@FXML
	void clic05() throws IOException, SAXException, ParserConfigurationException, JDOMException, NoProfileException {
		tabNvim[0] = false;
		tabNvim[1] = false;
		tabNvim[2] = false;
		tabNvim[3] = false;
		tabNvim[4] = true;
		tabNvim[5] = false;
		nv = 4;
		switchNv();
		testRealiser();
	}

	@FXML
	void clic06() throws IOException, SAXException, ParserConfigurationException, JDOMException, NoProfileException {
		tabNvim[0] = false;
		tabNvim[1] = false;
		tabNvim[2] = false;
		tabNvim[3] = false;
		tabNvim[4] = false;
		tabNvim[5] = true;
		nv = 5;
		switchNv();
		testRealiser();
	}

	@FXML
	void clicSurJouer() throws IOException {
		
		if(contexteSelect != -1 && nv != -1 && diff != -1) {
			System.out.println(contexteSelect + "  "+ "  "+diff+"  "+nv);
			System.out.println(enProgression);
			if(enProgression) {
				getMainClass().chargerProgression(contexteSelect, diff, nv);
			}else {
				getMainClass().chargerEntrainement(contexteSelect, diff, nv);	
			}
			
		}
		
	}

	@FXML
	void clicSurRetour() {
		getMainClass().showMenu();
	}
	
	
	public void setParam(boolean mode) {
		this.enProgression = mode;
	}

}
