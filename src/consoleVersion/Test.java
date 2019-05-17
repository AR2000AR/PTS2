package consoleVersion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		Piece p1 = new Piece();
		Plateau pl = new Plateau();

		p1.affPiece();
		p1.faireTourner();
		p1.affPiece();

		System.out.println("Etat initiale :");
		pl.afficherPlateau();

		Cochon c1 = new Cochon();
		Cochon c2 = new Cochon();
		Cochon c3 = new Cochon();
		Loup l = new Loup();

		System.out.println("");

		File donne = new File("src/consoleVersion/data.txt");
		Scanner sc = new Scanner(donne);

		System.out.println();
		String s = sc.nextLine();

		System.out.println("Niveau 1");
		System.out.println("Code de génération : " + s);
		genererNiveauTXT(pl, s, c1, c2, c3, l);

		System.out.println("Niveau 2");
		System.out.println("Code de génération : " + s);
		s="";
		s = sc.nextLine();
		genererNiveauTXT(pl, s, c1, c2, c3, l);

		System.out.println();
		
		
	}

	public static void genererNiveauTXT(Plateau p, String s, Cochon c1, Cochon c2, Cochon c3, Loup l) {
	p.reInitialisation();
		if (s.charAt(0) == '1') {
			p.ajouterAnimal(Integer.parseInt(String.valueOf(s.charAt(2))),
					Integer.parseInt(String.valueOf(s.charAt(1))), c1);
		}
		if (s.charAt(3) == '1') {
			p.ajouterAnimal(Integer.parseInt(String.valueOf(s.charAt(5))),
					Integer.parseInt(String.valueOf(s.charAt(4))), c2);
		}
		if (s.charAt(6) == '1') {
			p.ajouterAnimal(Integer.parseInt(String.valueOf(s.charAt(8))),
					Integer.parseInt(String.valueOf(s.charAt(7))), c3);
		}
		if (s.charAt(9) == '1') {
			p.ajouterAnimal(Integer.parseInt(String.valueOf(s.charAt(11))),
					Integer.parseInt(String.valueOf(s.charAt(10))), l);
		}
		p.afficherPlateau();
	}

}
