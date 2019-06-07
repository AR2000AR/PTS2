package gestionDeDonnee;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GestionDeDonnee {
	private static final boolean VERBOSE = true;
	private static final String[] typeName = { "none", "Element", "Attr", "Text", "CDATA", "EntityRef", "Entity",
			"ProcInstr", "Comment", "Document", "DocType", "DocFragment", "Notation", };
	// -------------------------------------------
	private static InputStream level_file = null;
	private static Document xmlNiveaux;
	// ------------------------------------------
	private static org.jdom2.Document xmlScores;
	private static InputStream scores_file = null;
	private List<Score> scores;

	// -------------------------------------------
	public GestionDeDonnee() throws SAXException, IOException, ParserConfigurationException, JDOMException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder levelBuilder = factory.newDocumentBuilder();
		if (level_file == null) {
			level_file = getClass().getResourceAsStream("niveau.xml");
		}
		if (scores_file == null) {
			scores_file = getClass().getResourceAsStream("score.xml");
		}
		SAXBuilder scoreBuilder = new SAXBuilder();
		xmlScores = scoreBuilder.build(scores_file);
		xmlNiveaux = levelBuilder.parse(level_file);
		this.scores = new ArrayList<Score>();
	}

	private void addScore(Element scoreElement) {
		int time = Integer.parseInt(scoreElement.getChildTextTrim("time"));
		String name = scoreElement.getChildTextTrim("name");
		addScore(new Score(name, time));
	}

	private void addScore(Score score) {
		scores.add(score);
		Collections.sort(scores);
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
	 * @param context - <b>0</b> diurne ou <b>1</b> nocturne.
	 * @param level
	 * @see List
	 * @see Object
	 * @return
	 */
	public List<Object[]> getScore(int mode, int level) {
		// A implémenter
		return null;

	}

	private void loadScores(int context, int difficulte, int niveau) {
		List<Element> niveaux = xmlScores.getRootElement().getChildren();
		for (Element element : niveaux) {
			List<Attribute> attributes = element.getAttributes();
			int nbNiveau = -1;
			int nbContext = -1;
			int nbDifficulte = -1;
			for (Attribute attribute : attributes) {
				switch (attribute.getName()) {
				case "nbNiveau":
					nbNiveau = Integer.parseInt(attribute.getValue());
					break;
				case "nbContext":
					nbContext = Integer.parseInt(attribute.getValue());
					break;
				case "nbDifficulte":
					nbDifficulte = Integer.parseInt(attribute.getValue());
					break;
				default:
					break;
				}
			}
			if ((nbNiveau == niveau) && (nbDifficulte == difficulte) && (nbContext == context)) {
				List<Element> scoreElements = new ArrayList<Element>();
				for (Element scoreElement : scoreElements) {
					addScore(scoreElement);
				}
			}
		}
	}

	/**
	 * Sauvegarde un score pour un niveau
	 *
	 * @author Audrézet Rémi
	 * @param mode      - <b>false</b> diurne ou <b>true</b> nocturne. [0-1]
	 * @param difficlte - Difficulte du niveau [0-3]
	 * @param niveau    - Numéro du niveau. [0-5]
	 * @param score     - Le score à sauvegarder.
	 * @param pseudo    - Le pseudo du joueur.
	 */
	public void saveScore(int mode, int difficulte, int niveau, int score, String pseudo) {
		loadScores(mode, difficulte, niveau);
		addScore(new Score(pseudo, score));
	}

}
