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
	// ------------------------------------------
	private static InputStream level_file = null;
	private static Document xmlNiveaux = null;
	// ------------------------------------------
	private static org.jdom2.Document xmlScores;
	private static InputStream scores_file = null;
	private static List<Score> scores = null;
	// ------------------------------------------
	private static org.jdom2.Document xmlProfiles;
	private static InputStream profils_file = null;

	// -------------------------------------------
	public GestionDeDonnee() throws SAXException, IOException, ParserConfigurationException, JDOMException {
		if (level_file == null) {
			level_file = getClass().getClassLoader().getResourceAsStream("niveau.xml");
		}
		if (scores_file == null) {
			scores_file = getClass().getClassLoader().getResourceAsStream("score.xml");
		}
		if (profils_file == null) {
			profils_file = getClass().getClassLoader().getResourceAsStream("profil.xml");
		}

		if (xmlScores == null) {
			SAXBuilder scoreBuilder = new SAXBuilder();
			xmlScores = scoreBuilder.build(scores_file);
		}
		if (xmlNiveaux == null) {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder levelBuilder = factory.newDocumentBuilder();
			xmlNiveaux = levelBuilder.parse(level_file);
		}
		if (scores == null) {
			scores = new ArrayList<Score>();
		}
		if (xmlProfiles == null) {
			SAXBuilder profilBuilder = new SAXBuilder();
			xmlProfiles = profilBuilder.build(profils_file);
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
		getNiveauElement(xmlScores.getRootElement().getChildren("niveau"), context, difficulte, niveau)
				.addContent(newScore);
	}

	private FileWriter getFileWriterFromName(String fileName) throws IOException {
		return new FileWriter(getClass().getClassLoader().getResource(fileName).getPath());
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

	private Element getNiveauElement(List<Element> listNiveaux, int context, int difficulte, int niveau) {
		for (Element element : listNiveaux) {
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
		listNiveaux.add(nouvNiveau);
		return nouvNiveau;
	}

	public Element getProfil(String profilName) throws NoProfileException {
		List<Element> profilList = xmlProfiles.getRootElement().getChildren();
		if (getProfileNameList().indexOf(profilName) == -1)
			throw new NoProfileException("Le profil " + profilName + " n'existe pas.");
		for (Element profil : profilList) {
			if (profil.getAttributeValue("nom").equals(profilName))
				return profil;
		}
		return null; // Ne devrais jamais être attein.
	}

	public List<String> getProfileNameList() {
		List<Element> profilElements = xmlProfiles.getRootElement().getChildren();
		List<String> result = new ArrayList<String>();
		for (Element profil : profilElements) {
			result.add(profil.getAttributeValue("nom"));
		}
		return result;
	}

	public Boolean[][][] getProgression(String profilName) throws NoProfileException {
		Element profil = getProfil(profilName);
		Boolean[][][] result = new Boolean[2][4][7];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				for (int k = 0; k < result[i][j].length; k++) {
					result[i][j][k] = false;
				}
			}
		}
		List<Element> niveaux = profil.getChildren("niveau");
		for (Element niveau : niveaux) {
			if (niveau.getChild("fini").getTextNormalize().equals("1")) {
				int nbContext = Integer.parseInt(niveau.getAttributeValue("nbContext"));
				int nbDifficulte = Integer.parseInt(niveau.getAttributeValue("nbDifficulte"));
				int nbNiveau = Integer.parseInt(niveau.getAttributeValue("nbNiveau"));
				result[nbContext][nbDifficulte][nbNiveau] = true;
			}
		}
		return result;
	}

	public int getProgressionValue(String profilName) throws NoProfileException {
		Boolean[][][] progTable = getProgression(profilName);
		int prog = 0;
		for (int i = 0; i < progTable.length; i++) {
			for (int j = 0; j < progTable[i].length; j++) {
				for (int k = 0; k < progTable[i][j].length; k++) {
					if (progTable[i][j][k] == true) {
						prog++;
					}
				}
			}
		}
		return prog;
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
		List<Element> scoreElementList = getNiveauElement(xmlScores.getRootElement().getChildren("niveau"), context,
				difficulte, niveau).getChildren("score");
		for (Element scoreElement : scoreElementList) {
			addScore(scoreElement);
		}
	}

	public boolean newProfil(String nom) throws IOException {
		List<String> nomsExistant = getProfileNameList();
		if (nomsExistant.indexOf(nom) == -1) {
			Element profilsElement = xmlProfiles.getRootElement();
			Element newProfil = new Element("profil");
			newProfil.setAttribute(new Attribute("nom", nom));
			profilsElement.addContent(newProfil);
			saveXML(xmlProfiles, getFileWriterFromName("profil.xml"));
		}
		return false;
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
		saveXML(xmlScores, getFileWriterFromName("score.xml"));
	}

	public void saveScore(int mode, int difficulte, int score, String pseudo) throws IOException {
		saveScore(mode, difficulte, 6, score, pseudo);
	}

	private void saveXML(org.jdom2.Document xmlDocument, FileWriter file) throws IOException {
		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		out.output(xmlDocument, file);
		// out.output(xmlDocument, System.out);
	}

	public void setProgression(String nomProfil, boolean[][][] fini)
			throws NoProfileException, IOException, TableauMalformeException {
		if ((fini.length == 2) && (fini[0].length == 4) && ((fini[0][0].length == 5) || (fini[0][0].length == 6))) {
			for (int i = 0; i < fini.length; i++) {
				for (int j = 0; j < fini[i].length; j++) {
					for (int k = 0; k < fini[i][j].length; k++) {
						setProgression(nomProfil, i, j, k, fini[i][j][k]);
					}
				}
			}
		} else
			throw new TableauMalformeException("le tableau à trois dimention n'a pas la bonne dimmention");
	}

	public void setProgression(String nomProfil, int context, int difficulte, int niveau, boolean fini)
			throws NoProfileException, IOException {
		Element profil = getProfil(nomProfil);
		Element niveauElement = getNiveauElement(profil.getChildren(), context, difficulte, niveau);
		Element finiElement = niveauElement.getChild("fini");
		if (finiElement == null) {
			finiElement = new Element("fini");
		}
		if (fini) {
			finiElement.setText("1");
		} else {
			finiElement.setText("0");
		}
		saveXML(xmlProfiles, getFileWriterFromName("profil.xml"));
	}
}
