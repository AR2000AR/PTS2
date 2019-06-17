package gestionDeDonnee;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

public class GestionDeDonnee {
	// ------------------------------------------
	private static InputStream level_file = null;
	private static Document xmlNiveaux = null;
	// ------------------------------------------
	private static Document xmlScores;
	private static InputStream scores_file = null;
	private static List<Score> scores = null;
	// ------------------------------------------
	private static Document xmlProfiles;
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
			SAXBuilder levelBuilder = new SAXBuilder();
			xmlNiveaux = levelBuilder.build(level_file);
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
	 * Ajoute un score à la liste de score pour le niveau indiqué en paramètre. Ne
	 * garde que 20 scores
	 *
	 * @param context    - <b>false</b> diurne ou <b>true</b> nocturne. [0-1]
	 * @param difficulte - difficulte du niveau [0-3]
	 * @param niveau     - numéro du niveau. [0-5]
	 * @param score
	 */
	private void addScore(int context, int difficulte, int niveau, Score score) {
		List<Score> scores = getScore(context, difficulte, niveau);
		Element niveauElement = getNiveauElement(xmlScores.getRootElement().getChildren("niveau"), context, difficulte,
				niveau);
		List<Element> scoreElements = niveauElement.getChildren();
		scoreElements.clear();
		scores.add(score);
		Collections.sort(scores);
		while (scores.size() > 20) {
			scores.remove(scores.size() - 1);
		}
		for (Score scoreI : scores) {
			scoreElements.add(newScoreElement(scoreI));
		}
		// Element newScore = newScoreElement(score);
		// getNiveauElement(xmlScores.getRootElement().getChildren("niveau"), context,
		// difficulte, niveau).addContent(newScore);
	}

	/**
	 * @throws NoProfileException
	 * @throws IOException
	 *
	 */
	public void delProfil(String profilName) throws NoProfileException, IOException {
		Element profil = getProfil(profilName);
		List<Element> profilList = xmlProfiles.getRootElement().getChildren();
		profilList.remove(profil);
		saveXML(xmlProfiles, getFileWriterFromName("profil.xml"));
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
		List<Element> niveauxElements = xmlNiveaux.getRootElement().getChildren("niveau");
		for (Element niveauElement : niveauxElements) {
			if ((niveauElement.getAttribute("nbContext").getValue().equals(Integer.toString(context)))
					&& (niveauElement.getAttribute("nbDifficulte").getValue().equals(Integer.toString(difficulte)))
					&& (niveauElement.getAttribute("nbNiveau").getValue().equals(Integer.toString(niveau)))) {
				List<Element> niveauChildElement = niveauElement.getChildren("description");
				return niveauChildElement.get(0).getText().split("#");
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
			int nbNiveau = -1;
			int nbContext = -1;
			int nbDifficulte = -1;
			nbNiveau = Integer.parseInt(element.getAttributeValue("nbNiveau"));
			nbContext = Integer.parseInt(element.getAttributeValue("nbContext"));
			nbDifficulte = Integer.parseInt(element.getAttributeValue("nbDifficulte"));
			if ((nbNiveau == niveau) && (nbDifficulte == difficulte) && (nbContext == context))
				return element;
		}

		Element nouvNiveau = new Element("niveau");
		nouvNiveau.setAttribute("nbNiveau", Integer.toString(niveau));
		nouvNiveau.setAttribute("nbContext", Integer.toString(context));
		nouvNiveau.setAttribute("nbDifficulte", Integer.toString(difficulte));
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
		if (niveaux == null)
			return result;
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

	private Element newScoreElement(Score score) {
		Element scoreElement = new Element("score");
		Element nameElement = new Element("name");
		Element timeElement = new Element("time");
		nameElement.setText(score.getName());
		timeElement.setText(Integer.toString(score.getTime()));
		scoreElement.addContent(nameElement);
		scoreElement.addContent(timeElement);
		return scoreElement;
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
	 * Ajoute un score pour la difficulté indiqué en paramètre et sauvegarde dans le
	 * fichier.
	 *
	 * @param context    - <b>false</b> diurne ou <b>true</b> nocturne. [0-1]
	 * @param difficulte - difficulte du niveau [0-3]
	 * @param score      - score à ajouter
	 * @param pseudo     - Nom du profil
	 * @throws IOException
	 */
	public void saveScore(int context, int difficulte, int score, String pseudo) throws IOException {
		saveScore(context, difficulte, 6, score, pseudo);
	}

	/**
	 * Ajoute un score pour la difficulté induiqué en oaramètre et le sauvegarde
	 *
	 * @param context    - <b>false</b> diurne ou <b>true</b> nocturne. [0-1]
	 * @param difficulte - difficulte du niveau [0-3]
	 * @param score      - <b>Score</b> à ajouter
	 * @throws IOException
	 */
	public void saveScore(int context, int difficulte, Score score) throws IOException {
		saveScore(context, difficulte, 6, score);
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
		//out.output(xmlDocument, System.out);
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
			niveauElement.addContent(finiElement);
		}
		if (fini) {
			finiElement.setText("1");
		} else {
			finiElement.setText("0");
		}
		saveXML(xmlProfiles, getFileWriterFromName("profil.xml"));
	}
}
