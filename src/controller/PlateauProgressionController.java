package controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import gestionDeDonnee.GestionDeDonnee;
import gestionDeDonnee.NoProfileException;

public class PlateauProgressionController extends PlateauController{

	@Override
	public void testJeuFini() throws InterruptedException, SAXException, IOException, ParserConfigurationException,
			JDOMException, NoProfileException {
		boolean test = false;
		int k = 0;
		for (int i = 0; i < 3; i++) {
			if (estPlacer[i]) {
				k++;
			}
			if (k == 3) {
				test = true;
				 System.out.println("<#<|JEU FINI|>#>");
				 System.out.println("YOLO MODE PROGRESSION");
				canvas1.setVisible(false);
				imgBravo.setImage(bravoImg);
				imgBravo.setVisible(true);
				imgBravo.toFront();
				btnRetourMenuSelection.toFront();
				btnRenitialiser.setOpacity(0);
				btnSolution.setOpacity(0);
				
				btnRetourMenuSelection.setX(-400);
				btnRetourMenuSelection.setY(-100);
				
				if(!solutionDevoile) {
					GestionDeDonnee g = new GestionDeDonnee();
					g.setProgression(getProfilName(), modeDiurne, diff, niveau, true);
				}
				
			}
		}

	}
	
	public void setParam(int ctx,int diff,int nv ) {
		this.modeDiurne = ctx;
		this.niveau = nv;
		this.diff = diff;
		System.out.println("Pass par la ");
	}

}
