package testGestionDeDonnee;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import gestionDeDonnee.GestionDeDonnee;
import gestionDeDonnee.NiveauInvalide;
import gestionDeDonnee.NiveauNonTrouve;

public class TestGestionDeDonnee {

	@Test
	public void test() throws SAXException, IOException, ParserConfigurationException, NiveauInvalide, NiveauNonTrouve {
		GestionDeDonnee g = new GestionDeDonnee();
		assertEquals("110112113000", g.getLevel(0, 0, 1));
	}

}
