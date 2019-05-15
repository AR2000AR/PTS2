package consoleVersion;

public class Piece1 extends Piece {

	
	public void Piece1() {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.stade1[i][j] = 0;
				this.stade2[i][j] = 0;
				this.stade3[i][j] = 0;
				this.stade4[i][j] = 0;
			}
		}

		////////////////////// Stade1

		stade1[1][1] = 1;
		stade1[1][2] = 1;
		stade1[2][1] = 1;

		////////////////////////////

		//////////////////////// Stade 2

		stade2[1][1] = 1;
		stade2[1][2] = 1;
		stade2[2][2] = 1;

		///////////////////////// Stade3

		stade3[1][2] = 1;
		stade3[2][1] = 1;
		stade3[2][2] = 1;

		/////////////////////////

		stade3[1][1] = 1;
		stade3[2][1] = 1;
		stade3[2][2] = 1;

		////////////////////////

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				actuel[i][j] = stade1[i][j];
			}

		}

	}

}
