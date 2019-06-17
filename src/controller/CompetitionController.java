package controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import application.Case;
import application.EnumCase;
import application.Timer;
import gestionDeDonnee.GestionDeDonnee;
import gestionDeDonnee.NiveauInvalide;
import gestionDeDonnee.NiveauNonTrouve;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CompetitionController extends PlateauController {

	@FXML
	private ImageView btnPasseNv;

	@FXML
	private Label lblTime;
	static Timer monTimer;
	Image imBtnPass = new Image("file:src/image/imageBoutonPasserNiveau.png");
	protected String[] codeGeneration = new String[6];
	protected String[] codeSolution = new String[6];

	protected boolean[] nvFait = { false, false, false, false, false, false };
	private int n = 0;

	@Override
	public void initialize() {
		try {
			Platform.runLater(() -> {
				gc1 = canvas1.getGraphicsContext2D();

				GestionDeDonnee g;
				try {
					g = new GestionDeDonnee();
					for (int i = 0; i < 6; i++) {
						codeGeneration[i] = g.getLevel(modeDiurne, diff, i);
						codeSolution[i] = g.getLevelSoluce(modeDiurne, diff, i);
					}
				} catch (SAXException | IOException | ParserConfigurationException | JDOMException | NiveauInvalide | NiveauNonTrouve e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					Platform.exit();
				}

				System.out.println("Mode Diurne : " + modeDiurne + "\n Difficulte = " + diff);

				

				try {
					initialiserPlateau(gc1, n);
				} catch (SAXException | IOException | ParserConfigurationException | JDOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				initPiece();
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		monTimer = new Timer(this);
		Thread t = new Thread(monTimer);
		t.start();

		btnPasseNv.setImage(imBtnPass);
		btnSolution.setImage(imBtnSolution);
		btnRetourMenuSelection.setImage(imBtnRetour);
		btnRenitialiser.setImage(ReniImg);

	}

	public void ecrireTemps(double d) {
		Platform.runLater(() -> {
			lblTime.setText(Double.toString(d));
		});
	}

	@Override
	public void testJeuFini() throws JDOMException, SAXException, ParserConfigurationException, IOException {
		int k = 0;
		for (int i = 0; i < 3; i++) {
			if (estPlacer[i]) {
				k++;
			}
			}
			if (k == 3) {
				estPlacer[0] = false;
				estPlacer[1] = false;
				estPlacer[2] = false;

				nvFait[n] = true;
				passerNiveau();

			}
			if (toutLesNvFait() && !solutionDevoile) {
				try {
					GestionDeDonnee g = new GestionDeDonnee();
				} catch (SAXException | IOException | ParserConfigurationException | JDOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getMainClass().choixComp();
				System.out.println("#JEU FINI#");
				monTimer.stop();
			}
		
	}

	private boolean toutLesNvFait() {
		boolean test = true;
		int i = 0;
		while (i < 6 && test) {
			test = nvFait[i];
			i++;
		}
		return test;
	}

	protected int temoinTour = 0;
	protected int temoinTour2 = 0;

	@FXML
	void passerNiveau() {
		
		try {
			
			
			if (!toutLesNvFait()) {
				n++;
				System.out.println("n = " + n);
				if (n < 6) {
					majPlateau();
					renitialiserP();
				} else {
					n = 0;
					majPlateau();
					renitialiserP();
				}
			}
			
			
			
		} catch (SAXException | IOException | ParserConfigurationException | JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void majPlateau() {
		gc1.clearRect(0, 0, 400, 400);
		System.out.println("MAJ PLATEAU ICI NV METHODE");

		gc1.drawImage(imgPlateau, 0, 0, 400, 400);

		s = "ERREUR CHARGEMENT COORDONNEE";

		s = codeGeneration[n];

		System.out.println(s);

		double x1, y1, x2, y2, x3, y3, x4, y4;

		x1 = 0;
		y1 = 0;
		x2 = 0;
		y2 = 0;
		x3 = 0;
		y3 = 0;
		x4 = 0;
		y4 = 0;

		x1 = 100 * (Integer.parseInt(String.valueOf(s.charAt(1))));
		y1 = 100 * (Integer.parseInt(String.valueOf(s.charAt(2))));
		x2 = 100 * (Integer.parseInt(String.valueOf(s.charAt(4))));
		y2 = 100 * (Integer.parseInt(String.valueOf(s.charAt(5))));
		x3 = 100 * (Integer.parseInt(String.valueOf(s.charAt(7))));
		y3 = 100 * (Integer.parseInt(String.valueOf(s.charAt(8))));
		x4 = 100 * (Integer.parseInt(String.valueOf(s.charAt(10))));
		y4 = 100 * (Integer.parseInt(String.valueOf(s.charAt(11))));

		/*
		 * System.out.println(x1); System.out.println(y1); System.out.println(x2);
		 * System.out.println(y2); System.out.println(x3); System.out.println(y3);
		 * System.out.println(x4); System.out.println(y4);
		 */

		for (int k = 0; k < 4; k++) {
			for (int j = 0; j < 4; j++) {
				plateau[k][j] = new Case(EnumCase.LIBRE);
			}
			plateau[0][0] = new Case(EnumCase.INEXISTANT);
			plateau[3][0] = new Case(EnumCase.INEXISTANT);
			plateau[0][3] = new Case(EnumCase.INEXISTANT);

		}

		if (Integer.parseInt(String.valueOf(s.charAt(0))) == 1) {
			gc1.drawImage(imgCochon, x1 + 10, y1 + 10, 80, 80);
			initierMatriceVirtuel(x1, y1, EnumCase.COCHON);
		}
		if (Integer.parseInt(String.valueOf(s.charAt(3))) == 1) {
			gc1.drawImage(imgCochon, x2 + 10, y2 + 10, 80, 80);
			initierMatriceVirtuel(x2, y2, EnumCase.COCHON);
		}
		if (Integer.parseInt(String.valueOf(s.charAt(6))) == 1) {
			gc1.drawImage(imgCochon, x3 + 10, y3 + 10, 80, 80);
			initierMatriceVirtuel(x3, y3, EnumCase.COCHON);
		}
		if (Integer.parseInt(String.valueOf(s.charAt(9))) == 1) {
			gc1.drawImage(imgLoup, x4 + 10, y4 + 10, 80, 80);
			initierMatriceVirtuel(x4, y4, EnumCase.LOUP);
		}

	}

	@Override
	void clicRetourMenu() throws IOException {
		getMainClass().choixComp();
		monTimer.stop();
	}

	@Override
	public void initialiserPlateau(GraphicsContext gc, int n)
			throws SAXException, IOException, ParserConfigurationException, JDOMException {
		gc.clearRect(0, 0, 400, 400);
		System.out.println("MAJ PLATEAU");
		gc.drawImage(imgPlateau, 0, 0, 400, 400);

		s = "ERREUR CHARGEMENT COORDONNEE";

		s = codeGeneration[n];

		System.out.println(s);

		double x1, y1, x2, y2, x3, y3, x4, y4;

		x1 = 0;
		y1 = 0;
		x2 = 0;
		y2 = 0;
		x3 = 0;
		y3 = 0;
		x4 = 0;
		y4 = 0;

		x1 = 100 * (Integer.parseInt(String.valueOf(s.charAt(1))));
		y1 = 100 * (Integer.parseInt(String.valueOf(s.charAt(2))));
		x2 = 100 * (Integer.parseInt(String.valueOf(s.charAt(4))));
		y2 = 100 * (Integer.parseInt(String.valueOf(s.charAt(5))));
		x3 = 100 * (Integer.parseInt(String.valueOf(s.charAt(7))));
		y3 = 100 * (Integer.parseInt(String.valueOf(s.charAt(8))));
		x4 = 100 * (Integer.parseInt(String.valueOf(s.charAt(10))));
		y4 = 100 * (Integer.parseInt(String.valueOf(s.charAt(11))));

		/*
		 * System.out.println(x1); System.out.println(y1); System.out.println(x2);
		 * System.out.println(y2); System.out.println(x3); System.out.println(y3);
		 * System.out.println(x4); System.out.println(y4);
		 */

		for (int k = 0; k < 4; k++) {
			for (int j = 0; j < 4; j++) {
				plateau[k][j] = new Case(EnumCase.LIBRE);
			}
			plateau[0][0] = new Case(EnumCase.INEXISTANT);
			plateau[3][0] = new Case(EnumCase.INEXISTANT);
			plateau[0][3] = new Case(EnumCase.INEXISTANT);

		}

		if (Integer.parseInt(String.valueOf(s.charAt(0))) == 1) {
			gc.drawImage(imgCochon, x1 + 10, y1 + 10, 80, 80);
			initierMatriceVirtuel(x1, y1, EnumCase.COCHON);
		}
		if (Integer.parseInt(String.valueOf(s.charAt(3))) == 1) {
			gc.drawImage(imgCochon, x2 + 10, y2 + 10, 80, 80);
			initierMatriceVirtuel(x2, y2, EnumCase.COCHON);
		}
		if (Integer.parseInt(String.valueOf(s.charAt(6))) == 1) {
			gc.drawImage(imgCochon, x3 + 10, y3 + 10, 80, 80);
			initierMatriceVirtuel(x3, y3, EnumCase.COCHON);
		}
		if (Integer.parseInt(String.valueOf(s.charAt(9))) == 1) {
			gc.drawImage(imgLoup, x4 + 10, y4 + 10, 80, 80);
			initierMatriceVirtuel(x4, y4, EnumCase.LOUP);
		}

	}

	public void setParam(int contexteSelect, int diff) {
		this.modeDiurne = contexteSelect;
		this.diff = diff;

		System.out.println("DEPUIS SET PARAM    \n Mode Diurne : " + contexteSelect + "\n Difficulte = " + diff);
	}

}