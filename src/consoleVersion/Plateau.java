package consoleVersion;

public class Plateau {

	private static final int INFINI = Integer.MAX_VALUE;
	public Case[][] plateau = new Case[4][4];

	public Plateau() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				plateau[i][j] = new Case();
			}

		}

		plateau[0][0].setEc(EtatCase.INEXISTANT);
		plateau[0][3].setEc(EtatCase.INEXISTANT);
		plateau[3][0].setEc(EtatCase.INEXISTANT);
	}

	public void afficherPlateau() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(plateau[i][j].toString());
			}
			System.out.println("");
		}

	}

	public void ajouterAnimal(int i, int j, Object o) {
		plateau[i][j].setOccupant(o);
	}

	public boolean empLibrePourPiece(Piece p) {
		boolean test = true;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {

				if (p.etatActuel[i][j] != 0) {
					if (plateau[i][j].ec == EtatCase.OCCUPER || plateau[i][j].ec == EtatCase.INEXISTANT) {
						test = false;
					}
				}

			}
		}
		return test;
	}

//	public void deplacerPiece(Piece unePiece) {
	// }
}