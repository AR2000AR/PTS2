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
	/**
	 * Crée un objet <b>GestionDeDonnee</b>. Si c'est le premier les attributs
	 * statics sont initialisés
	 *
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws JDOMException
	 */
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

	/**
	 * Lit un score depuis un <b>Element</b> et l'ajoute à la liste de scores
	 *
	 * @param scoreElement - Un <b>Element</b> qui contien les info sur le score
	 */
	private void addScore(Element scoreElement) {
		int time = Integer.parseInt(scoreElement.getChildTextTrim("time"));
		String name = scoreElement.getChildTextTrim("name");
		scores.add(new Score(name, time));
		Collections.sort(scores);
	}

	/**
	 * Ajoute un score à la liste de score pour le niveau indiqué en paramètre.
	 *
	 * @param context    - <b>false</b> diurne ou <b>true</b> nocturne. [0-1]
	 * @param difficulte - difficulte du niveau [0-3]
	 * @param niveau     - numéro du niveau. [0-5]
	 * @param score
	 */
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

	/**
	 * Revoie un <b>FileWriter</b> pour le fichier dont le nom est passé en
	 * paramètre
	 *
	 * @param fileName - Nom du ficher
	 * @return <b>FileWriter</b>
	 * @throws IOException
	 */
	private FileWriter getFileWriterFromName(String fileName) throws IOException {
		return new FileWriter(getClass().getClassLoader().getResource(fileName).getPath());
	}

	public String getLevel(int context, int difficulte, int niveau) throws NiveauInvalide, NiveauNonTrouve {
		return getLevelDescriptor(context, difficulte, niveau)[0];
	}

	/**
	 * Renvoie une chaine de caractère décrivant un niveau
	 *
	 * @author Audrézet Rémi
	 * @param context    - <b>false</b> diurne ou <b>true</b> nocturne. [0-1]
	 * @param difficulte - difficulte du niveau [0-3]
	 * @param niveau     - numéro du niveau. [0-5]
	 * @return - Chaine formaté
	 * @throws NiveauInvalide
	 * @throws NiveauNonTrouve
	 */
	private String[] getLevelDescriptor(int context, int difficulte, int niveau)
			throws NiveauInvalide, NiveauNonTrouve {
		if (((context != 0) && (context != 1)) || (difficulte < 0) || (difficulte > 3) || (niveau < 0) || (niveau > 5))
			throw new NiveauInvalide("Le niveau " + niveau + " n'est pas un niveau valide");
		NodeList domNiveaux = xmlNiveaux.getElementsByTagName("niveau");
		for (int i = 0; i < domNiveaux.getLength(); i++) {
			Node niveauI = domNiveaux.item(i);
			if (niveauI.getNodeType() == Node.ELEMENT_NODE) {
				NamedNodeMap attrs = niveauI.getAttributes();
				if ((attrs.getNamedItem("nbContext").getNodeValue().equals(Integer.toString(context)))
						&& (attrs.getNamedItem("nbDifficulte").getNodeValue().equals(Integer.toString(difficulte)))
						&& (attrs.getNamedItem("nbNiveau").getNodeValue().equals(Integer.toString(niveau)))) {
					NodeList niveauChildNode = niveauI.getChildNodes();
					for (int j = 0; j < niveauChildNode.getLength(); j++) {
						if (niveauChildNode.item(j).getNodeName().equals("description"))
							return niveauChildNode.item(j).getTextContent().split("#");
					}
				}
			}
		}
		throw new NiveauNonTrouve("Le niveau indiqué n'a pas été trouvé. Le fichier peut être endomagé");
	}

	public String getLevelSoluce(int context, int difficulte, int niveau) throws NiveauInvalide, NiveauNonTrouve {
		return getLevelDescriptor(context, difficulte, niveau)[1];
	}

	/**
	 * Trouve un <b>Element</b> pour le niveau demandé. Si il n'existe pas il est
	 * crée.
	 *
	 * @param listNiveaux - <b>List</b> d'<b>Element</b>
	 * @param context     - <b>false</b> diurne ou <b>true</b> nocturne. [0-1]
	 * @param difficulte  - difficulte du niveau [0-3]
	 * @param niveau      - numéro du niveau. [0-5]
	 * @return
	 */
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

	/**
	 * Renvoie l'<b>Element</b> pour le profil dont le nom est donnée en paramètre
	 *
	 * @param profilName - Nom du profil
	 * @return - L'<b>Element</b> pour le profil
	 * @throws NoProfileException Si le profil n'existe pas une erreur est soulevé
	 */
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

	/**
	 * Revoie la liste des noms de profils
	 *
	 * @return - <b>List</b> de <b>String</b>
	 */
	public List<String> getProfileNameList() {
		List<Element> profilElements = xmlProfiles.getRootElement().getChildren();
		List<String> result = new ArrayList<String>();
		for (Element profil : profilElements) {
			result.add(profil.getAttributeValue("nom"));
		}
		return result;
	}

	/**
	 * Revoie un tableau à trois dimentions contenant le détaille des niveaux fini
	 *
	 * @param profilName - Nom du profil où chercher la progression
	 * @return <b>Boolean</b>[2][4][6]
	 * @throws NoProfileException
	 */
	public Boolean[][][] getProgression(String profilName) throws NoProfileException {
		Element profil = getProfil(profilName);
		Boolean[][][] result = new Boolean[2][4][6];
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

	/**
	 * Revoie le nombre de niveaux finis
	 *
	 * @param profilName - Nom du profil où chercher la progression
	 * @return <b>int</b>
	 * @throws NoProfileException
	 */
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

	/**
	 * Revoi une <b>List</b> de <b>Score</b> pour la difficulté (niveau = 6)
	 *
	 * @param context    - <b>false</b> diurne ou <b>true</b> nocturne. [0-1]
	 * @param difficulte - difficulte du niveau [0-3]
	 * @return - <b>List</b> de <b>Score</b>
	 */
	public List<Score> getScore(int context, int difficulte) {
		return getScore(context, difficulte, 6);
	}

	/**
	 * Renvoi une <b>List</b> de <b>Score</b> pour le niveau indiqué en paramètre.
	 *
	 * @param context    - <b>false</b> diurne ou <b>true</b> nocturne. [0-1]
	 * @param difficulte - difficulte du niveau [0-3]
	 * @param niveau     - numéro du niveau. [0-5]
	 * @return - <b>List</b> de <b>Score</b>
	 */
	public List<Score> getScore(int context, int difficulte, int niveau) {
		loadScores(context, difficulte, niveau);
		return scores;
	}

	/**
	 * Charge la liste de <b>Score</b> pour le niveau passé en argument
	 *
	 * @param context    - <b>false</b> diurne ou <b>true</b> nocturne. [0-1]
	 * @param difficulte - difficulte du niveau [0-3]
	 * @param niveau     - numéro du niveau. [0-5]
	 */
	private void loadScores(int context, int difficulte, int niveau) {
		scores.clear();
		List<Element> scoreElementList = getNiveauElement(xmlScores.getRootElement().getChildren("niveau"), context,
				difficulte, niveau).getChildren("score");
		for (Element scoreElement : scoreElementList) {
			addScore(scoreElement);
		}
	}

	/**
	 * Crée un nouveau profil avec le nom fourni en paramètre
	 *
	 * @param nom - Nom du profil
	 * @return <b>False</b> si le profil n'a pas été crée sinon <b>True</b>
	 * @throws IOException
	 */
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
	 * @param context   - <b>false</b> diurne ou <b>true</b> nocturne. [0-1]
	 * @param difficlte - Difficulte du niveau [0-3]
	 * @param niveau    - Numéro du niveau. [0-5]
	 * @param score     - Le score à sauvegarder.
	 * @param pseudo    - Le pseudo du joueur.
	 * @throws IOException
	 */
	public void saveScore(int context, int difficulte, int niveau, int score, String pseudo) throws IOException {
		saveScore(context, difficulte, niveau, new Score(pseudo, score));
	}

	/**
	 * Ajoute un score et pour le niveau indiqué en paramètre et sauvegarde dans le
	 * fichier.
	 *
	 * @param context    - <b>false</b> diurne ou <b>true</b> nocturne. [0-1]
	 * @param difficulte - difficulte du niveau [0-3]
	 * @param niveau     - numéro du niveau. [0-5]
	 * @param score      - <b>Score</b> à ajouter
	 * @throws IOException
	 */
	public void saveScore(int context, int difficulte, int niveau, Score score) throws IOException {
		loadScores(context, difficulte, niveau);
		addScore(context, difficulte, niveau, score);
		saveXML(xmlScores, getFileWriterFromName("score.xml"));
	}

	/**
	 * Ajoute un score et pour la difficulté indiqué en paramètre et sauvegarde dans
	 * le fichier.
	 *
	 * @param context    - <b>false</b> diurne ou <b>true</b> nocturne. [0-1]
	 * @param difficulte - difficulte du niveau [0-3]
	 * @param score      - <b>Score</b> à ajouter
	 * @throws IOException
	 */
	public void saveScore(int context, int difficulte, int score, String pseudo) throws IOException {
		saveScore(context, difficulte, 6, score, pseudo);
	}

	/**
	 * Ecrie le <b>Document</b> dans le fichier forunit par le <b>FileWriter</b>
	 *
	 * @param xmlDocument - Document à sauvegarder
	 * @param file        - <b>FileWriter</b> à utiliser pour écrire dans le fichier
	 * @throws IOException
	 */
	private void saveXML(org.jdom2.Document xmlDocument, FileWriter file) throws IOException {
		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		out.output(xmlDocument, file);
		// out.output(xmlDocument, System.out);
	}

	/**
	 * Sauvegarde la progression pour tous les niveaux du tableau
	 *
	 * @param nomProfil - Nom du profil pour la sauvegarde
	 * @param fini      - Tableau de <b>boolean</b>[2][4][6]
	 * @throws NoProfileException       - Le profil indiqué n'existe pas
	 * @throws IOException
	 * @throws TableauMalformeException - Le tableau n'a pas les bonnes dimmentions
	 */
	public void setProgression(String nomProfil, boolean[][][] fini)
			throws NoProfileException, IOException, TableauMalformeException {
		if ((fini.length == 2) && (fini[0].length == 4) && ((fini[0][0].length == 4) || (fini[0][0].length == 6))) {
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

	/**
	 * Sauvegarde la progression pour le niveau passé en paramètre
	 *
	 * @param nomProfil  - Nom du profil pour la sauvegarde
	 * @param context    - <b>false</b> diurne ou <b>true</b> nocturne. [0-1]
	 * @param difficulte - difficulte du niveau [0-3]
	 * @param score      - <b>Score</b> à ajouter
	 * @param fini
	 * @throws NoProfileException - Le profil indiqué n'existe pas
	 * @throws IOException
	 */
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
