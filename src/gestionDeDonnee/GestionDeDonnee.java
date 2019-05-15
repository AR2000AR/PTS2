package gestionDeDonnee;

import java.util.List;

public class GestionDeDonnee {
	 @SuppressWarnings("unused")
	private String filePath;
	 
	 public GestionDeDonnee(String filePath) {
		 super();
		 this.filePath=filePath;
	 }
	 /**
	  * Renvoie une <b>List</b> contenent des tableau de <b>int</b> contenant les coordonées des pions.<br></br>
	  * La première coordonée sera toujours le loup en nocturne.
	  * @author Audrézet Rémi
	  * @param mode - <b>false</b> diurne ou <b>true</b> nocturne.
	  * @param level - numéro du niveau.
	  * @see List
	  */
	 public List<int[]> getLevel(boolean mode,int level) {
		//A implementer
		return null;
	 }
	 
	 /**
	  * Sauvegarde un score pour un niveau
	  * @author Audrézet Rémi
	  * @param mode - <b>false</b> diurne ou <b>true</b> nocturne.
	  * @param level - Numéro du niveau.
	  * @param score - Le score à sauvegarder.
	  * @param pseudo - Le pseudo du joueur.
	  */
	 public void saveScore(boolean mode,int level,int score,String pseudo) {
		 //A implémenter
	 }
	 
	 /**
	  * Revoie une <b>List</b> contenant un tableau d'<b>Object</b>.
	  * <ul>
	  * <li>Object[0] -> score</li>
	  * <li>Object[1] -> pseudo</li>
	  * </ul>
	  * 
	  * @param mode - <b>false</b> diurne ou <b>true</b> nocturne.
	  * @param level
	  * @see List
	  * @see Object
	  * @return <b>List</b> conentent un tableau d'<b>Object</b>
	  */
	 public List<Object[]> getScore(boolean mode,int level) {
		 //A implémenter
		 return null;
		 
	 }
}
