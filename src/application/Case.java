package application;

public class Case {

	public EnumCase etatCase = EnumCase.LIBRE;
	public String nom = "L";

	public Case() {
	}

	public Case(EnumCase e) {
		this.etatCase = e;
		switch (this.etatCase) {
		case LIBRE:
			this.nom = " ";
			break;

		case INEXISTANT: {
			this.nom = "X";
			break;

		}
		case OCCUPER: {
			this.nom = "#";
			break;
		}
		case COCHON: {
			this.nom = "C";
			break;
		}
		case LOUP: {
			this.nom = "L";
			break;
		}

		}
	}

	@Override
	public String toString() {
		return this.nom;
	}

}
