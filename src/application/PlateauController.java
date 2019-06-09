package application;

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

import gestionDeDonnee.GestionDeDonnee;
import gestionDeDonnee.NiveauInvalide;
import gestionDeDonnee.NiveauNonTrouve;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PlateauController {
	@FXML
	private ImageView p1;
	static int etatP1 = 1;

	@FXML
	private ImageView p2;
	static int etatP2 = 1;

	@FXML
	private ImageView p3;
	static int etatP3 = 1;

	@FXML
	private Canvas canvas1;
	private GraphicsContext gc1;

	static Image imgPlateau = new Image("file:src/image/plateau.png");
	static Image imgCochon = new Image("file:src/image/pig.png");
	static Image imgLoup = new Image("file:src/image/wolf.png");

	static Image imgp11 = new Image("file:src/image/p11.png");
	static Image imgp12 = new Image("file:src/image/p12.png");
	static Image imgp13 = new Image("file:src/image/p13.png");
	static Image imgp14 = new Image("file:src/image/p14.png");

	static Image imgp21 = new Image("file:src/image/p21.png");
	static Image imgp22 = new Image("file:src/image/p22.png");

	static Image imgp31 = new Image("file:src/image/p31.png");
	static Image imgp32 = new Image("file:src/image/p32.png");
	static Image imgp33 = new Image("file:src/image/p33.png");
	static Image imgp34 = new Image("file:src/image/p34.png");

	static Image tabImageP1[] = { imgp11, imgp12, imgp13, imgp14 };
	static Image tabImageP2[] = { imgp21, imgp22 };
	static Image tabImageP3[] = { imgp31, imgp32, imgp33, imgp34 };

	static ArrayList<ImageView> listeP = new ArrayList<>();
	static double[][] coorOrig = new double[3][2];
	static private String s;

	static int nCase = -1;

	static double decalageXYP1[][] = { { 50, 150 }, { 50, 50 }, { 150, 50 }, { 150, 150 } };
	static double decalageXYP2[][] = { { 150, 50 }, { 50, 150 } };
	static double decalageXYP3[][] = { { 50, 150 }, { 50, 50 }, { 250, 50 }, { 150, 250 } };

	static double decalageXY[][][] = { decalageXYP1, decalageXYP1, decalageXYP1 };
	static int tabEtat[] = { etatP1, etatP2, etatP3 };

	static double registreX[] = { 440, 540, 640, 740, 840 };
	static double registreY[] = { 160, 260, 360, 460, 560 };

	static double registreXtest[] = { 540, 640, 740, 840 };
	static double registreYtest[] = { 260, 360, 460, 560 };

	public void initialize() {
		try {

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

		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public static void switchHeightWidth(ImageView img) {
		double w = img.getFitHeight();
		double h = img.getFitWidth();
		img.setFitWidth(w);
		img.setFitHeight(h);
	}

	@FXML
	void tournerP(MouseEvent event) {
		if (event.getButton().toString() == ("SECONDARY")) {
			switch (Main.temoin) {
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
			centrerSurSouris(event, listeP.get(Main.temoin - 1));
		}

	}

	private void centrerSurSouris(MouseEvent event, ImageView p) {
		p.setX(event.getX());
		p.setY(event.getY());
		double x;
		double y;

		switch (Main.temoin) {

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

	private void deplacerAvecSouris(MouseEvent event, ImageView p, int i) {
		p.setOpacity(0.75);
		Main.temoin = i;
		coordSouris(event);
		listeP.get(Main.temoin - 1).toFront();
		System.out.println(Main.temoin);
		centrerSurSouris(event, p);
	}

	@FXML

	void dragOnP1(MouseEvent event) {
		deplacerAvecSouris(event, p1, 1);
		Main.temoin = 1;

	}

	@FXML
	void dragOnP2(MouseEvent event) {
		deplacerAvecSouris(event, p2, 2);
	}

	@FXML
	void dragOnP3(MouseEvent event) {
		deplacerAvecSouris(event, p3, 3);
	}

	public static double[] coordSouris(MouseEvent event) {

		double x = event.getX() + coorOrig[Main.temoin - 1][0];
		double y = event.getY() + coorOrig[Main.temoin - 1][1];
		double valeurRetour[] = new double[2];
		valeurRetour[0] = x;
		valeurRetour[1] = y;
		System.out.println(Arrays.toString(valeurRetour));
		return valeurRetour;
	}

	@FXML
	void relache(MouseEvent event) {
		if (event.getButton().toString() == ("PRIMARY")) {
			if (listeP.get(Main.temoin - 1) != null) {
				listeP.get(Main.temoin - 1).setOpacity(1);
				double empl[] = new double[2];
				empl = emplacementPlateau(event);
				testPoserPiece(empl);
			}
			Main.temoin = 0;
		}
	}

	private void testPoserPiece(double[] empl) {
		/*
		 * test si une piece peut être placer ou non et si oui la place
		 * 
		 */
		if (empl[0] != -1 && empl[1] != -1) {
			System.out.println(Arrays.toString(empl));
			placerPiece(empl);
		}

	}

	private void placerPiece(double[] empl) {

		listeP.get(Main.temoin-1).setX(registreX[(int) empl[0]] - coorOrig[Main.temoin-1][0]);
		listeP.get(Main.temoin-1).setY(registreY[(int) empl[1]] - coorOrig[Main.temoin-1][1] );
		
	}
	
	private double[] leDecalage() {
		double retour[] = new double[2];
		retour = decalageXY[Main.temoin-1][tabEtat[Main.temoin-1]];
		System.out.println(Arrays.toString(retour));
		return retour;
		
	}
	
	private double[] emplacementPlateau(MouseEvent event) {
		double x = 0;
		double y = 0;

		double retour[] = { -1, -1 };

		x = coordSouris(event)[0];
		y = coordSouris(event)[1];

		boolean tx = false;
		boolean ty = false;

		if (x > 440 && y > 160) {
			for (int i = 0; i < registreX.length; i++) {
				if (!tx) {
					if (x <= registreXtest[i]) {
						tx = true;
						retour[0] = i;
					}
				}
				if (!ty) {
					if (y <= registreYtest[i]) {
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

	public static void placeOrigine() {
		if (Main.temoin != 9) {
			listeP.get(Main.temoin).setX(coorOrig[Main.temoin][0]);
			listeP.get(Main.temoin).setY(coorOrig[Main.temoin][1]);
		} else {
			System.out.println("Pas de piece prise");
		}
	}

	public static void initialiserPlateau(GraphicsContext gc, int i)
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
			s = g.getLevel(0, 2, 5);
		} catch (NiveauInvalide | NiveauNonTrouve e) {
			e.printStackTrace();
			Platform.exit();
		}

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

		System.out.println(x1);
		System.out.println(y1);
		System.out.println(x2);
		System.out.println(y2);
		System.out.println(x3);
		System.out.println(y3);
		System.out.println(x4);
		System.out.println(y4);

		for (int k = 0; k < 4; k++) {
			for (int j = 0; j < 4; j++) {
				Main.plateau[k][j] = new Case(EnumCase.LIBRE);
			}
			Main.plateau[0][0] = new Case(EnumCase.INEXISTANT);
			Main.plateau[3][0] = new Case(EnumCase.INEXISTANT);
			Main.plateau[0][3] = new Case(EnumCase.INEXISTANT);

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
			System.out.println(Arrays.toString(Main.plateau[n]));
		}

	}

	public static void initierMatriceVirtuel(double x, double y, EnumCase e) {
		x /= 100;
		y /= 100;
		Main.plateau[(int) y][(int) x] = new Case(e);
	}

}
