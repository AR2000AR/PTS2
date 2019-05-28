package gestionDeDonnee;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GestionDeDonnee {
	private static final String LEVEL_FILE = "./data/niveau.xml";
	private static Document xmlNiveaux;
	private static final boolean VERBOSE = true;
	static final String[] typeName = { "none", "Element", "Attr", "Text", "CDATA", "EntityRef", "Entity", "ProcInstr",
			"Comment", "Document", "DocType", "DocFragment", "Notation", };

	public GestionDeDonnee() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		xmlNiveaux = builder.parse(LEVEL_FILE);
	}

	/**
	 * Renvoie une chaine de caractère décrivant un niveau
	 *
	 * @author Audrézet Rémi
	 * @param mode       - <b>false</b> diurne ou <b>true</b> nocturne. [0-1]
	 * @param difficulte - difficulte du niveau [0-3]
	 * @param niveau     - numéro du niveau. [0-5]
	 * @return
	 * @throws NiveauInvalide
	 * @throws NiveauNonTrouve
	 */
	public String getLevel(int mode, int difficulte, int niveau) throws NiveauInvalide, NiveauNonTrouve {
		if ((difficulte < 0) || (difficulte > 3) || (niveau < 0) || (niveau > 5))
			throw new NiveauInvalide("Le niveau " + niveau + " n'est pas un niveau valide");
		NodeList domNiveaux = xmlNiveaux.getElementsByTagName("niveau");
		if (VERBOSE == true) {
			System.out.println(domNiveaux.getLength());
		}
		for (int i = 0; i < domNiveaux.getLength(); i++) {
			Node niveauI = domNiveaux.item(i);
			if (niveauI.getNodeType() == Node.ELEMENT_NODE) {
				NamedNodeMap attrs = niveauI.getAttributes();
				if ((attrs.getNamedItem("nbContext").getNodeValue().equals(Integer.toString(mode)))
						&& (attrs.getNamedItem("nbDifficulte").getNodeValue().equals(Integer.toString(difficulte)))
						&& (attrs.getNamedItem("nbNiveau").getNodeValue().equals(Integer.toString(niveau)))) {
					NodeList niveauChildNode = niveauI.getChildNodes();
					for (int j = 0; j < niveauChildNode.getLength(); j++) {
						if (niveauChildNode.item(j).getNodeName().equals("description"))
							return niveauChildNode.item(j).getTextContent();
					}
				}
			}
		}
		throw new NiveauNonTrouve("Le niveau indiqué n'a pas été trouvé. Le fichier peut être endomagé");
	}

	/**
	 * Revoie une <b>List</b> contenant un tableau d'<b>Object</b>.
	 * <ul>
	 * <li>Object[0] -> score</li>
	 * <li>Object[1] -> pseudo</li>
	 * </ul>
	 *
	 * @param mode  - <b>false</b> diurne ou <b>true</b> nocturne.
	 * @param level
	 * @see List
	 * @see Object
	 * @return <b>List</b> conentent un tableau d'<b>Object</b>
	 */
	public List<Object[]> getScore(boolean mode, int level) {
		// A implémenter
		return null;

	}

	/**
	 * Sauvegarde un score pour un niveau
	 *
	 * @author Audrézet Rémi
	 * @param mode   - <b>false</b> diurne ou <b>true</b> nocturne.
	 * @param level  - Numéro du niveau.
	 * @param score  - Le score à sauvegarder.
	 * @param pseudo - Le pseudo du joueur.
	 */
	public void saveScore(boolean mode, int level, int score, String pseudo) {
		// A implémenter
	}
}
