package consoleVersion;

public class Test {

	public static void main(String[] args) {
		Piece p1 = new Piece();
		Plateau pl = new Plateau();
		
		p1.affPiece();
		p1.faireTourner();
		p1.affPiece();
		
		System.out.println("");
		pl.afficherPlateau();
		
		Cochon c1 = new Cochon();

		System.out.println("");
		pl.ajouterAnimal(1, 1, c1);
		pl.afficherPlateau();
	}

}
