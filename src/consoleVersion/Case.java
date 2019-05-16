package consoleVersion;

public class Case {

	public EtatCase ec = EtatCase.VIDE;
	public Object occupant = null;

	public Case() {

	}

	public Case(EtatCase etat) {
		this.ec = etat;
	}


	@Override
	public String toString() {
		if (this.ec == EtatCase.INEXISTANT) {
			return "   ";
		} else {
			if (this.ec == EtatCase.VIDE) {
				return "| |";
			} else {
				return ("|"+this.occupant.toString()+"|");
			}
		}
	}

	public EtatCase getEc() {
		return ec;
	}

	public void setEc(EtatCase ec) {
		this.ec = ec;
	}

	public Object getOccupant() {
		return occupant;
	}

	public void setOccupant(Object o) {
		this.occupant = o;
		if(o != null) {
			this.ec = EtatCase.OCCUPER;
		}else {
			this.ec = EtatCase.VIDE;
		}
	}

}
