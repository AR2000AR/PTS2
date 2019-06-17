package testGestionDeDonnee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import gestionDeDonnee.GestionDeDonnee;
import gestionDeDonnee.NiveauInvalide;
import gestionDeDonnee.NiveauNonTrouve;
import gestionDeDonnee.Score;

public class TestGestionDeDonnee {

	private static GestionDeDonnee g;

	@BeforeClass
	public static void before() throws SAXException, IOException, ParserConfigurationException, JDOMException {
		g = new GestionDeDonnee();
	}

	@Test
	public void testGetNiveau() throws SAXException, IOException, ParserConfigurationException, NiveauInvalide,
			NiveauNonTrouve, JDOMException {
		assertEquals("110112113000", g.getLevel(0, 0, 1));
		assertEquals("101000000121", g.getLevel(1, 0, 0));
	}

	@Test
	public void testSaveScore() throws SAXException, IOException, ParserConfigurationException, JDOMException {
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

	@Test
	public void testSaveScoreMultiple() throws IOException {
		Random r = new Random();
		for (int i = 0; i < 30; i++) {
			g.saveScore(0, 0, r.nextInt(60), "bob");
		}
		List<Score> scores = g.getScore(0, 0);
		assertEquals(20, scores.size());
	}

}
