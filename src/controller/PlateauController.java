package controller;

import java.awt.MouseInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import application.Case;
import application.EnumCase;
import gestionDeDonnee.GestionDeDonnee;
import gestionDeDonnee.NiveauInvalide;
import gestionDeDonnee.NiveauNonTrouve;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PlateauController {
	@FXML
	protected ImageView p1;
	int etatP1 = 1;

	@FXML
	protected ImageView p2;
	int etatP2 = 1;

	@FXML
	protected ImageView p3;
	int etatP3 = 1;

	@FXML
	protected Canvas canvas1;
	protected GraphicsContext gc1;

	@FXML
	protected ImageView imgBravo;

	Image imgPlateau = new Image("file:src/image/plateau.png");
	Image imgCochon = new Image("file:src/image/pig.png");
	Image imgLoup = new Image("file:src/image/wolf.png");

	Image imgp11 = new Image("file:src/image/p11.png");
	Image imgp12 = new Image("file:src/image/p12.png");
	Image imgp13 = new Image("file:src/image/p13.png");
	Image imgp14 = new Image("file:src/image/p14.png");

	Image imgp21 = new Image("file:src/image/p21.png");
	Image imgp22 = new Image("file:src/image/p22.png");

	Image imgp31 = new Image("file:src/image/p31.png");
	Image imgp32 = new Image("file:src/image/p32.png");
	Image imgp33 = new Image("file:src/image/p33.png");
	Image imgp34 = new Image("file:src/image/p34.png");

	Image bravoImg = new Image("file:src/image/Bravo.png");

	Image tabImageP1[] = { imgp11, imgp12, imgp13, imgp14 };
	Image tabImageP2[] = { imgp21, imgp22 };
	Image tabImageP3[] = { imgp31, imgp32, imgp33, imgp34 };

	ArrayList<ImageView> listeP = new ArrayList<>();
	double[][] coorOrig = new double[3][2];
	protected String s;

	int nCase = -1;

	double decalageXYP1[][] = { { 50, 150 }, { 50, 50 }, { 150, 50 }, { 150, 150 } };
	double decalageXYP2[][] = { { 150, 50 }, { 50, 150 } };
	double decalageXYP3[][] = { { 250, 150 }, { 50, 250 }, { 50, 50 }, { 150, 50 } };

	double decalageXY[][][] = { decalageXYP1, decalageXYP1, decalageXYP1 };
	int tabEtat[] = { etatP1, etatP2, etatP3 };

	double registreX[] = { 440, 540, 640, 740, 840 };
	double registreY[] = { 160, 260, 360, 460, 560 };

	double registreXtest[] = { 540, 640, 740, 840 };
	double registreYtest[] = { 260, 360, 460, 560 };

	double coordSurCochon[][] = { { 0, 0 }, { 0, 0 }, { 0, 0 } };
	boolean estSurCochon[] = { false, false, false };

	boolean niveauFini = false;
	int modeDiurne;

	// private int mode;
	private int diff;
	private int niveau;

	public void initialize() {
		gc1 = canvas1.getGraphicsContext2D();
		Platform.runLater(() -> {
			try {
				initialiserPlateau(gc1, 2);
			} catch (SAXException | IOException | ParserConfigurationException | JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			initPiece();
		});

	}

	public void initPiece() {
		p1.setImage(imgp11);
		p2.setImage(imgp21);
		p3.setImage(imgp31);
		listeP.add(p1);
		listeP.add(p2);
		listeP.add(p3);

		coorOrig[0][0] = 30;
		coorOrig[0][1] = 30;

		coorOrig[1][0] = 30;
		coorOrig[1][1] = 280;

		coorOrig[2][0] = 30;
		coorOrig[2][1] = 460;

	}

	public void retourOrigine() {
		listeP.get(temoin - 1).setX(0);
		listeP.get(temoin - 1).setY(0);

	}

	public void switchHeightWidth(ImageView img) {
		double w = img.getFitHeight();
		double h = img.getFitWidth();
		img.setFitWidth(w);
		img.setFitHeight(h);
	}

	@FXML
	void prendrePiece1(MouseEvent event) {
		temoin = 1;
		int y = coorPestPlacer[0][0];
		int x = coorPestPlacer[0][1];

		if (estPlacerSurPlateau()) {
			estPlacer[0] = false;
			// System.out.println("Entrer switch");
			switch (etatP1) {
			case 1:
				plateau[x][y] = new Case(EnumCase.LIBRE);
				plateau[x - 1][y] = new Case(EnumCase.LIBRE);
				plateau[x][y + 1] = new Case(EnumCase.LIBRE);
				affPlateau();

				break;
			case 2:
				plateau[x][y] = new Case(EnumCase.LIBRE);
				plateau[x + 1][y] = new Case(EnumCase.LIBRE);
				plateau[x][y + 1] = new Case(EnumCase.LIBRE);
				affPlateau();
				break;

			case 3:
				plateau[x][y] = new Case(EnumCase.LIBRE);
				plateau[x + 1][y] = new Case(EnumCase.LIBRE);
				plateau[x][y - 1] = new Case(EnumCase.LIBRE);
				affPlateau();
				break;
			case 4:
				plateau[x][y] = new Case(EnumCase.LIBRE);
				plateau[x][y - 1] = new Case(EnumCase.LIBRE);
				plateau[x - 1][y] = new Case(EnumCase.LIBRE);
				affPlateau();
				break;
			default:
				break;

			}
			remettreCochon(x, y);
		}

	}

	@FXML
	void prendrePiece2(MouseEvent event) {
		temoin = 2;

		int y = coorPestPlacer[1][0];
		int x = coorPestPlacer[1][1];
		if (estPlacerSurPlateau()) {
			estPlacer[1] = false;

			switch (etatP2) {
			case 1:
				plateau[x][y] = new Case(EnumCase.LIBRE);
				plateau[x][y + 1] = new Case(EnumCase.LIBRE);
				plateau[x][y - 1] = new Case(EnumCase.LIBRE);
				affPlateau();
				break;
			case 2:
				plateau[x][y] = new Case(EnumCase.LIBRE);
				plateau[x + 1][y] = new Case(EnumCase.LIBRE);
				plateau[x - 1][y] = new Case(EnumCase.LIBRE);
				affPlateau();
				break;
			default:
				break;
			}
			remettreCochon(x, y);
		}

	}

	@FXML
	void prendrePiece3(MouseEvent event) {
		temoin = 3;

		int y = coorPestPlacer[2][0];
		int x = coorPestPlacer[2][1];

		if (estPlacerSurPlateau()) {
			estPlacer[2] = false;

			switch (etatP3) {
			case 1:
				plateau[x][y] = new Case(EnumCase.LIBRE);
				plateau[x - 1][y] = new Case(EnumCase.LIBRE);
				plateau[x][y - 2] = new Case(EnumCase.LIBRE);
				plateau[x][y - 1] = new Case(EnumCase.LIBRE);
				affPlateau();
				break;
			case 2:
				plateau[x][y] = new Case(EnumCase.LIBRE);
				plateau[x][y + 1] = new Case(EnumCase.LIBRE);
				plateau[x - 1][y] = new Case(EnumCase.LIBRE);
				plateau[x - 2][y] = new Case(EnumCase.LIBRE);
				affPlateau();
				break;

			case 3:
				plateau[x][y] = new Case(EnumCase.LIBRE);
				plateau[x][y + 1] = new Case(EnumCase.LIBRE);
				plateau[x][y + 2] = new Case(EnumCase.LIBRE);
				plateau[x + 1][y] = new Case(EnumCase.LIBRE);
				affPlateau();
				break;
			case 4:
				plateau[x][y] = new Case(EnumCase.LIBRE);
				plateau[x][y - 1] = new Case(EnumCase.LIBRE);
				plateau[x + 1][y] = new Case(EnumCase.LIBRE);
				plateau[x + 2][y] = new Case(EnumCase.LIBRE);
				affPlateau();
				break;
			default:
				break;
			}
			remettreCochon(x, y);
		}

		
	}

	@FXML
	void tournerP(MouseEvent event) {

		if (event.getButton().toString() == ("SECONDARY")) {
			switch (temoin) {
			case 1:
				switchHeightWidth(p1);
				etatP1++;
				if (etatP1 == 5) {
					etatP1 = 1;
				}
				p1.setImage(tabImageP1[etatP1 - 1]);
				break;

			case 2:
				switchHeightWidth(p2);
				switch (etatP2) {
				case 1:
					etatP2++;
					p2.setImage(imgp22);
					break;
				case 2:
					etatP2 = 1;
					p2.setImage(imgp21);
					break;
				}
				break;

			case 3:

				switchHeightWidth(p3);
				etatP3++;
				if (etatP3 == 5) {
					etatP3 = 1;
				}
				p3.setImage(tabImageP3[etatP3 - 1]);
				break;

			}
			centrerSurSouris(event, listeP.get(temoin - 1));
		}

	}

	protected void centrerSurSouris(MouseEvent event, ImageView p) {
		p.setX(event.getX());
		p.setY(event.getY());
		double x;
		double y;

		switch (temoin) {

		case 1:
			x = (decalageXYP1[etatP1 - 1][0]);
			y = (decalageXYP1[etatP1 - 1][1]);

			p.setX(p.getX() - x);
			p.setY(p.getY() - y);

			break;

		case 2:
			x = (decalageXYP2[etatP2 - 1][0]);
			y = (decalageXYP2[etatP2 - 1][1]);

			p.setX(p.getX() - x);
			p.setY(p.getY() - y);
			break;
		case 3:
			x = (decalageXYP3[etatP3 - 1][0]);
			y = (decalageXYP3[etatP3 - 1][1]);

			p.setX(p.getX() - x);
			p.setY(p.getY() - y);
			break;

		}
	}

	protected void deplacerAvecSouris(MouseEvent event, ImageView p, int i) {
		p.setOpacity(0.75);
		temoin = i;
		coordSouris(event);
		listeP.get(temoin - 1).toFront();
		// System.out.println(temoin);
		centrerSurSouris(event, p);
	}

	@FXML
	void dragOnP1(MouseEvent event) {
		deplacerAvecSouris(event, p1, 1);
		temoin = 1;
	}

	@FXML
	void dragOnP2(MouseEvent event) {
		deplacerAvecSouris(event, p2, 2);
		temoin = 2;

	}

	@FXML
	void dragOnP3(MouseEvent event) {
		deplacerAvecSouris(event, p3, 3);
		temoin = 3;

	}

	public double[] coordSouris(MouseEvent event) {

		double x = event.getX() + coorOrig[temoin - 1][0];
		double y = event.getY() + coorOrig[temoin - 1][1];
		double valeurRetour[] = new double[2];
		valeurRetour[0] = x;
		valeurRetour[1] = y;
		return valeurRetour;
	}

	@FXML
	void relache(MouseEvent event) throws InterruptedException {
		if (event.getButton().toString() == ("PRIMARY")) {
			if (listeP.get(temoin - 1) != null) {
				listeP.get(temoin - 1).setOpacity(1);
				double empl[] = new double[2];
				empl = emplacementPlateau(event);
				testPoserPiece(empl);
				testJeuFini();
			}
			temoin = 0;
		}
	}

	public void testJeuFini() throws InterruptedException {
		boolean test = false;
		int k = 0;
		for (int i = 0; i < 3; i++) {
			if (estPlacer[i]) {
				k++;
			}
			if (k == 3) {
				test = true;
				// System.out.println("<#<|JEU FINI|>#>");
				canvas1.setVisible(false);
				imgBravo.setImage(bravoImg);
				imgBravo.setVisible(true);
				imgBravo.toFront();
			}
		}
	}

	protected void testPoserPiece(double[] empl) {
		/*
		 * test si une piece peut être placer ou non et si oui la place
		 * 
		 */
		if (empl[0] != -1 && empl[1] != -1 && empl[0] < 5 && empl[1] < 5) {
			// System.out.println(Arrays.toString(empl));
			if (testPlacer(empl)) {
				placerPiece(empl);
				coorPestPlacer[temoin - 1][0] = (int) empl[0];
				coorPestPlacer[temoin - 1][1] = (int) empl[1];
				estPlacer[temoin - 1] = true;

			} else {
				retourOrigine();
			}
		}

	}

	protected void placerPiece(double[] empl) {

		listeP.get(temoin - 1).setX(registreX[(int) empl[0]] - coorOrig[temoin - 1][0] - leDecalage()[0]);
		listeP.get(temoin - 1).setY(registreY[(int) empl[1]] - coorOrig[temoin - 1][1] - leDecalage()[1]);

	}

	protected double[] leDecalage() {
		double retour[] = new double[2];
		retour = decalageXY[temoin - 1][tabEtat[temoin - 1]];
		// System.out.println(Arrays.toString(retour));

		switch (temoin) {

		case 1:
			retour[0] = 0;
			retour[1] = 0;

			if (etatP1 == 1) {
				retour[1] = 100;
			}
			if (etatP1 == 3) {
				retour[0] = 100;

			}
			if (etatP1 == 4) {
				retour[0] = 100;
				retour[1] = 100;
			}
			break;
		case 2:

			retour[0] = 0;
			retour[1] = 0;

			if (etatP2 == 1) {
				retour[0] = 100;
			}
			if (etatP2 == 2) {
				retour[1] = 100;

			}

			break;
		case 3:
			retour[0] = 0;
			retour[1] = 0;

			if (etatP3 == 1) {
				retour[0] = 200;
				retour[1] = 100;
			}
			if (etatP3 == 2) {
				retour[1] = 200;
			}
			if (etatP3 == 4) {
				retour[0] = 100;
			}

			break;

		}

		return retour;

	}

	protected double[] emplacementPlateau(MouseEvent event) {
		double x = 0;
		double y = 0;

		double retour[] = { -1, -1 };

		x = coordSouris(event)[0];
		y = coordSouris(event)[1];

		boolean tx = false;
		boolean ty = false;

		if (x > 440 && x <= 840 && y > 160 && y <= 560) {
			for (int i = 0; i < registreX.length; i++) {
				if (!tx) {
					if (x < registreXtest[i]) {
						tx = true;
						retour[0] = i;
					}
				}
				if (!ty) {
					if (y < registreYtest[i]) {
						ty = true;
						retour[1] = i;
					}
				}

			}
		}
		return retour;
	}

	@FXML
	void sourisEntrer(MouseEvent event) {
		// System.out.println(event.getX() + " " + event.getY());
	}

	public void placeOrigine() {
		if (temoin != 9) {
			listeP.get(temoin).setX(coorOrig[temoin][0]);
			listeP.get(temoin).setY(coorOrig[temoin][1]);
		} else {
			// System.out.println("Pas de piece prise");
		}
	}

	public void initialiserPlateau(GraphicsContext gc, int i)
			throws SAXException, IOException, ParserConfigurationException, JDOMException {
		gc.drawImage(imgPlateau, 0, 0, 400, 400);
//		File donne = new File("src/application/data.txt");
//		Scanner sc = new Scanner(donne);
//		System.out.println();
//		String s = "error";
//		for (int j = 0; j < i; j++) {
//			s = sc.nextLine();
//		}
		GestionDeDonnee g = new GestionDeDonnee();
		s = "ERREUR CHARGEMENT COORDONNEE";
		try {

			s = g.getLevel(modeDiurne, diff, niveau);
		} catch (NiveauInvalide | NiveauNonTrouve e) {
			e.printStackTrace();
			Platform.exit();
		}

		// System.out.println(s);
		double x1, y1, x2, y2, x3, y3, x4, y4;

		x1 = 0;
		y1 = 0;
		x2 = 0;
		y2 = 0;
		x3 = 0;
		y3 = 0;
		x4 = 0;
		y4 = 0;

		y1 = 100 * (Integer.parseInt(String.valueOf(s.charAt(1))));
		x1 = 100 * (Integer.parseInt(String.valueOf(s.charAt(2))));
		y2 = 100 * (Integer.parseInt(String.valueOf(s.charAt(4))));
		x2 = 100 * (Integer.parseInt(String.valueOf(s.charAt(5))));
		y3 = 100 * (Integer.parseInt(String.valueOf(s.charAt(7))));
		x3 = 100 * (Integer.parseInt(String.valueOf(s.charAt(8))));
		y4 = 100 * (Integer.parseInt(String.valueOf(s.charAt(10))));
		x4 = 100 * (Integer.parseInt(String.valueOf(s.charAt(11))));

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

		for (int n = 0; n < 4; n++) {
			// System.out.println(Arrays.toString(plateau[n]));
		}

	}

	public void initierMatriceVirtuel(double x, double y, EnumCase e) {
		x /= 100;
		y /= 100;
		plateau[(int) y][(int) x] = new Case(e);

		for (int i = 0; i < 4; i++) {
			// System.out.println(Arrays.toString(plateau[i]));
		}

	}

	int temoin = 0;

	int autourP1[][] = { { 1, -1 }, { 1, 1 }, { -1, 1 }, { -1, 1 } };

	int autourP3[][] = { { 1, -1 }, { 1, 1 }, { -1, 1 }, { -1, 1 } };

	Case[][] plateau = new Case[4][4];

	boolean estPlacer[] = { false, false, false };
	int coorPestPlacer[][] = new int[3][2];

	public void affPlateau() {
		System.out.println("");
		for (int i = 0; i < 4; i++) {
			System.out.println(Arrays.toString(plateau[i]));
		}
		System.out.println("");
	}

	public boolean testPlacer(double[] t) {
		// System.out.println(Arrays.toString(t));
		int x = (int) t[1];
		int y = (int) t[0];

		boolean test = true;
		test = testDessusDessousPiece((int) t[0], (int) t[1]);
		if (test) {
			switch (temoin) {
			case 1:
				switch (etatP1) {
				case 1:
					plateau[x][y] = new Case(EnumCase.OCCUPER);
					plateau[x - 1][y] = new Case(EnumCase.OCCUPER);
					plateau[x][y + 1] = new Case(EnumCase.OCCUPER);
					surCochon(x, y);
					affPlateau();

					break;
				case 2:
					plateau[x][y] = new Case(EnumCase.OCCUPER);
					plateau[x + 1][y] = new Case(EnumCase.OCCUPER);
					plateau[x][y + 1] = new Case(EnumCase.OCCUPER);
					surCochon(x, y);
					affPlateau();
					break;

				case 3:
					plateau[x][y] = new Case(EnumCase.OCCUPER);
					plateau[x + 1][y] = new Case(EnumCase.OCCUPER);
					plateau[x][y - 1] = new Case(EnumCase.OCCUPER);
					surCochon(x, y);
					affPlateau();
					break;
				case 4:
					plateau[x][y] = new Case(EnumCase.OCCUPER);
					plateau[x][y - 1] = new Case(EnumCase.OCCUPER);
					plateau[x - 1][y] = new Case(EnumCase.OCCUPER);
					surCochon(x, y);
					affPlateau();
					break;
				default:
					break;

				}
				break;
			case 2:
				switch (etatP2) {
				case 1:
					plateau[x][y] = new Case(EnumCase.OCCUPER);
					plateau[x][y + 1] = new Case(EnumCase.OCCUPER);
					plateau[x][y - 1] = new Case(EnumCase.OCCUPER);
					surCochon(x, y);
					affPlateau();
					break;
				case 2:
					plateau[x][y] = new Case(EnumCase.OCCUPER);
					plateau[x + 1][y] = new Case(EnumCase.OCCUPER);
					plateau[x - 1][y] = new Case(EnumCase.OCCUPER);
					surCochon(x, y);
					affPlateau();
					break;
				default:
					break;
				}

				break;
			case 3:
				switch (etatP3) {
				case 1:
					plateau[x][y] = new Case(EnumCase.OCCUPER);
					plateau[x - 1][y] = new Case(EnumCase.OCCUPER);
					plateau[x][y - 2] = new Case(EnumCase.OCCUPER);
					plateau[x][y - 1] = new Case(EnumCase.OCCUPER);
					surCochon(x, y);
					affPlateau();
					break;
				case 2:
					plateau[x][y] = new Case(EnumCase.OCCUPER);
					plateau[x][y + 1] = new Case(EnumCase.OCCUPER);
					plateau[x - 1][y] = new Case(EnumCase.OCCUPER);
					plateau[x - 2][y] = new Case(EnumCase.OCCUPER);
					surCochon(x, y);
					affPlateau();
					break;

				case 3:
					plateau[x][y] = new Case(EnumCase.OCCUPER);
					plateau[x][y + 1] = new Case(EnumCase.OCCUPER);
					plateau[x][y + 2] = new Case(EnumCase.OCCUPER);
					plateau[x + 1][y] = new Case(EnumCase.OCCUPER);
					surCochon(x, y);
					affPlateau();
					break;
				case 4:
					plateau[x][y] = new Case(EnumCase.OCCUPER);
					plateau[x][y - 1] = new Case(EnumCase.OCCUPER);
					plateau[x + 1][y] = new Case(EnumCase.OCCUPER);
					plateau[x + 2][y] = new Case(EnumCase.OCCUPER);
					surCochon(x, y);
					affPlateau();
					break;
				default:
					break;
				}
				break;

			default:
				break;
			}
		}
		return test;
	}

	private boolean testDessusDessous(int x, int y, int modeDiurne2) {
		if (x >= 0 && x <= 3 && y >= 0 && y <= 3) {
			Case c = plateau[(int) y][(int) x];

			if (modeDiurne2 == 0) {
				if (c.etatCase == EnumCase.LIBRE) {
					return true;
				} else {
					return false;
				}
			} else {
				if (c.etatCase == EnumCase.COCHON || c.etatCase == EnumCase.LIBRE) {
					return true;
				} else {
					return false;
				}
			}

		} else {
			return false;
		}
	}

	public void surCochon(int x, int y) {
		if (modeDiurne == 1) {
			coordSurCochon[temoin - 1][0] = y;
			coordSurCochon[temoin - 1][1] = x;
			
			estSurCochon[temoin - 1] = true;
			System.out.println(Arrays.toString(estSurCochon));
			// System.out.println(Arrays.toString(coordSurCochon[temoin - 1]));
			// System.out.println("YOLO");
			// System.out.println(Arrays.toString(estSurCochon));

		}
	}

	public void remettreCochon(int x, int y) {
		// System.out.println("COCHON REMIS");
		if(estSurCochon[temoin -1]) {
		plateau[x][y] = new Case(EnumCase.COCHON);
		estSurCochon[temoin -1] = false;
		}
	}

	protected boolean testDessusDessousPiece(int x, int y) {
		boolean testPiecePoser = false;
		boolean t1 = false;
		boolean t2 = false;
		if (testDessusDessous(x, y, modeDiurne)) {

			switch (temoin) {
			case 1:
				if (etatP1 == 1) {
					t1 = testDessusDessous(x + 1, y) && testDessusDessous(x, y - 1);
				}
				if (etatP1 == 2) {
					t1 = testDessusDessous(x + 1, y) && testDessusDessous(x, y + 1);
				}
				if (etatP1 == 3) {
					t1 = testDessusDessous(x - 1, y) && testDessusDessous(x, y + 1);
				}
				if (etatP1 == 4) {
					t1 = testDessusDessous(x - 1, y) && testDessusDessous(x, y - 1);
				}
				t2 = t1;
				break;

			case 2:
				if (etatP2 == 1) {
					t1 = testDessusDessous(x + 1, y) && testDessusDessous(x - 1, y);
				} else {
					t1 = testDessusDessous(x, y + 1) && testDessusDessous(x, y - 1);
				}
				t2 = t1;
				break;

			case 3:
				switch (etatP3) {

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

	protected boolean testDessusDessous(double x, double y) {
		if (x >= 0 && x <= 3 && y >= 0 && y <= 3) {
			Case c = plateau[(int) y][(int) x];
			if (c.etatCase == EnumCase.LIBRE) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean estPlacerSurPlateau() {
		boolean test = false;
		test = estPlacer[temoin - 1];
		return test;
	}

	public int[] retourneCoord() {
		int result[] = new int[2];
		result = coorPestPlacer[temoin];
		return result;
	}

	public void setParam(int ctx, int df, int nv) {
		this.niveau = nv;
		this.diff = df;
		this.modeDiurne = ctx;
		// System.out.println(ctx + " " + df + " " + nv);
	}

}
