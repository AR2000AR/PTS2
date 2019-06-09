package application;

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

	static ArrayList<ImageView> listeP = new ArrayList<>();
	static double[][] coorOrig = new double[3][2];
	static private String s;

	static int nCase = -1;

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
			System.out.println(event.getButton().toString());

			switch (Main.temoin) {

			case 1:
				switchHeightWidth(p1);
				switch (etatP1) {
				case 1:
					etatP1++;
					p1.setImage(imgp12);
					break;
				case 2:
					etatP1++;
					p1.setImage(imgp13);
					break;
				case 3:
					etatP1++;
					p1.setImage(imgp14);
					break;
				case 4:
					etatP1 = 1;
					p1.setImage(imgp11);
					break;
				}

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
				switch (etatP3) {
				case 1:
					etatP3++;
					p3.setImage(imgp32);
					break;
				case 2:
					etatP3++;
					p3.setImage(imgp33);
					break;
				case 3:
					etatP3++;
					p3.setImage(imgp34);
					break;
				case 4:
					etatP3 = 1;
					p3.setImage(imgp31);
					break;

				}

				break;

			}
			centrerSurSouris(event, listeP.get(Main.temoin - 1));
		}

	}

	private void centrerSurSouris(MouseEvent event, ImageView p) {
		p.setX(event.getX());
		p.setY(event.getY());

		switch (Main.temoin) {

		case 1:
			switch (etatP1) {
			case 1:
				p.setX(p.getX() - 50);
				p.setY(p.getY() - 150);
				break;

			case 2:
				p.setX(p.getX() - 50);
				p.setY(p.getY() - 50);
				break;
			case 3:
				p.setX(p.getX() - 150);
				p.setY(p.getY() - 50);
				break;

			case 4:
				p.setX(p.getX() - 150);
				p.setY(p.getY() - 150);
				break;
			}
			break;

		case 2:
			switch (etatP2) {
			case 1:
				p.setX(p.getX() - 150);
				p.setY(p.getY() - 50);
				break;

			case 2:
				p.setX(p.getX() - 50);
				p.setY(p.getY() - 150);
				break;
			}
			break;

		case 3:
			switch (etatP3) {
			case 1:
				p.setX(p.getX()-50);
				p.setY(p.getY()-150);
				break;

			case 2:
				System.out.println("COUCOU");
				p.setX(p.getX()-50);
				p.setY(p.getY()-50);
				break;
			case 3:
				p.setX(p.getX()-250);
				p.setY(p.getY()-50);
				break;

			case 4:
				p.setX(p.getX()-150);
				p.setY(p.getY()-250);
				break;
			}
			break;

		}

	}

	private void deplacerAvecSouris(MouseEvent event, ImageView p, int i) {
		p.setOpacity(0.75);

		System.out.println(event.getX() + "  " + event.getY());

		Main.temoin = i;
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

	@FXML
	void relache(MouseEvent event) {
		if (event.getButton().toString() == ("PRIMARY")) {
			if (listeP.get(Main.temoin - 1) != null) {
				listeP.get(Main.temoin - 1).setOpacity(1);
				map(event.getX(), event.getY());
				System.out.println(Main.temoin);
			} else {
				placeOrigine();
				System.out.println(Main.temoin);
			}

			Main.temoin = 0;
		}
	}

	@FXML
	void sourisEntrer(MouseEvent event) {
		System.out.println(event.getX() + "  " + event.getY());
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

	private int map(double xSouris, double ySouris) {

		int xtab;
		int ytab;
		/// fzire le delta des coordonnees

		switch (Main.temoin) {
		case 1:
			System.out.println("yolo");
			xSouris -= coorOrig[0][0];
			ySouris -= coorOrig[0][1];

			System.out.println(xSouris + "  " + ySouris);
			break;

		case 2:
			xSouris -= coorOrig[1][0];
			ySouris -= coorOrig[1][1];
			break;

		case 3:
			xSouris -= coorOrig[2][0];
			ySouris -= coorOrig[2][1];
			break;
		}

		// if ((xSouris >= 440 && xSouris <= 840) && (ySouris >= 160 && ySouris <= 560))
		// {
		// traitement des cases inexistante
		/*
		 * if ((xSouris <= 540 && ySouris < 260) || (xSouris >= 740 && ySouris < 260) ||
		 * (xSouris <= 540 && ySouris >= 460)) { return -1; }
		 */
		xtab = (int) Math.round(0.01 * xSouris - 4.4);
		ytab = (int) Math.round(0.01 * xSouris - 1.6);
		System.out.println("Avant switch");

		System.out.println(nCase + "  " + xtab + " " + ytab);
		return nCase;
	}

}
