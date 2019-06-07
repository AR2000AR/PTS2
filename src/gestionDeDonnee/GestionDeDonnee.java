package gestionDeDonnee;

import java.io.FileWriter;
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
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
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
	private static Document xmlNiveaux = null;
	// ------------------------------------------
	private static org.jdom2.Document xmlScores;
	private static InputStream scores_file = null;
	private static List<Score> scores = null;

	// -------------------------------------------
	public GestionDeDonnee() throws SAXException, IOException, ParserConfigurationException, JDOMException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder levelBuilder = factory.newDocumentBuilder();
		if (level_file == null) {
			level_file = getClass().getClassLoader().getResourceAsStream("niveau.xml");
		}
		if (scores_file == null) {
			scores_file = getClass().getClassLoader().getResourceAsStream("score.xml");
		}
		SAXBuilder scoreBuilder = new SAXBuilder();
		if (xmlScores == null) {
			xmlScores = scoreBuilder.build(scores_file);
		}
		if (xmlNiveaux == null) {
			xmlNiveaux = levelBuilder.parse(level_file);
		}
		if (scores == null) {
			scores = new ArrayList<Score>();
		}
	}

	private void addScore(Element scoreElement) {
		int time = Integer.parseInt(scoreElement.getChildTextTrim("time"));
		String name = scoreElement.getChildTextTrim("name");
		scores.add(new Score(name, time));
		Collections.sort(scores);
	}

	private void addScore(int context, int difficulte, int niveau, Score score) {
		Element newScore = new Element("score");
		Element name = new Element("name");
		Element time = new Element("time");
		time.setText(Integer.toString(score.getTime()));
		name.setText(score.getName());
		newScore.addContent(time);
		newScore.addContent(name);
		trouveNiveauElement(context, difficulte, niveau).addContent(newScore);
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

	public List<Score> getScore(int context, int difficulte) {
		return getScore(context, difficulte, 6);
	}

	public List<Score> getScore(int context, int difficulte, int niveau) {
		loadScores(context, difficulte, niveau);
		return scores;
	}

	private void loadScores(int context, int difficulte, int niveau) {
		scores.clear();
		List<Element> scoreElementList = trouveNiveauElement(context, difficulte, niveau).getChildren("score");
		for (Element scoreElement : scoreElementList) {
			addScore(scoreElement);
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
	 * @throws IOException
	 */
	public void saveScore(int mode, int difficulte, int niveau, int score, String pseudo) throws IOException {
		saveScore(mode, difficulte, niveau, new Score(pseudo, score));
	}

	public void saveScore(int mode, int difficulte, int niveau, Score score) throws IOException {
		loadScores(mode, difficulte, niveau);
		addScore(mode, difficulte, niveau, score);
		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		out.output(xmlScores, new FileWriter(getClass().getClassLoader().getResource("score.xml").getPath()));
		// out.output(xmlScores, System.out);
	}

	public void saveScore(int mode, int difficulte, int score, String pseudo) throws IOException {
		saveScore(mode, difficulte, 6, score, pseudo);
	}

	private Element trouveNiveauElement(int context, int difficulte, int niveau) {
		List<Element> niveaux = xmlScores.getRootElement().getChildren("niveau");
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
			if ((nbNiveau == niveau) && (nbDifficulte == difficulte) && (nbContext == context))
				return element;
		}
		Element nouvNiveau = new Element("niveau");
		List<Attribute> nouvNiveauAttrs = new ArrayList<Attribute>();
		nouvNiveauAttrs.add(new Attribute("nbNiveau", Integer.toString(niveau)));
		nouvNiveauAttrs.add(new Attribute("nbContext", Integer.toString(context)));
		nouvNiveauAttrs.add(new Attribute("nbDifficulte", Integer.toString(difficulte)));
		nouvNiveau.setAttributes(nouvNiveauAttrs);
		niveaux.add(nouvNiveau);
		return nouvNiveau;
	}

}
