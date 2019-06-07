package testGestionDeDonnee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.junit.Test;
import org.xml.sax.SAXException;

import gestionDeDonnee.GestionDeDonnee;
import gestionDeDonnee.NiveauInvalide;
import gestionDeDonnee.NiveauNonTrouve;
import gestionDeDonnee.Score;

public class TestGestionDeDonnee {

	@Test
	public void testGetNiveau() throws SAXException, IOException, ParserConfigurationException, NiveauInvalide,
			NiveauNonTrouve, JDOMException {
		GestionDeDonnee g = new GestionDeDonnee();
		assertEquals("110112113000", g.getLevel(0, 0, 1));
	}

	@Test
	public void testSaveScore() throws SAXException, IOException, ParserConfigurationException, JDOMException {
		GestionDeDonnee g = new GestionDeDonnee();
		g.saveScore(0, 0, 0, 982, "adzgzegb");
		List<Score> sc = g.getScore(0, 0, 0);
		boolean ok = false;
		for (Score score : sc) {
			if (score.getName().equals("adzgzegb") && (score.getTime() == 982)) {
				ok = true;
				break;
			}
		}
		assertTrue(ok);
	}

}
