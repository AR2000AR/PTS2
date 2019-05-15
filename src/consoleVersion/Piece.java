package consoleVersion;

public class Piece {

	/*
	 * 1 11
	 * 
	 * 111
	 * 
	 * 111 1
	 */
	public int mat[][]= new Object[3][3];
	Object stade1[][] = new Object[3][3];
	Object stade2[][] = new Object[3][3];
	Object stade3[][] = new Object[3][3];
	Object stade4[][] = new Object[3][3];

	

	public Piece(int i) {

		switch (i) {
		case 1:

			this.stade1[0][0] = 1;
			this.stade1[0][1] = 1;
			this.stade1[0][2] = 0;

			this.stade1[1][0] = 1;
			this.stade1[1][1] = 0;
			this.stade1[1][2] = 0;

			this.stade1[2][0] = 0;
			this.stade1[2][1] = 0;
			this.stade1[2][2] = 0;

			this.stade2[0][0] = 1;
			this.stade2[0][1] = 0;
			this.stade2[0][2] = 0;

			this.stade2[1][0] = 1;
			this.stade2[1][1] = 1;
			this.stade2[1][2] = 0;

			this.stade2[2][0] = 0;
			this.stade2[2][1] = 0;
			this.stade2[2][2] = 0;

			this.stade3[0][0] = 0;
			this.stade3[0][1] = 1;
			this.stade3[0][2] = 0;

			this.stade3[1][0] = 1;
			this.stade3[1][1] = 1;
			this.stade3[1][2] = 0;

			this.stade3[2][0] = 0;
			this.stade3[2][1] = 0;
			this.stade3[2][2] = 0;

			this.stade4[0][0] = 1;
			this.stade4[0][1] = 0;
			this.stade4[0][2] = 0;

			this.stade4[1][0] = 1;
			this.stade4[1][1] = 1;
			this.stade4[1][2] = 0;

			this.stade4[2][0] = 0;
			this.stade4[2][1] = 0;
			this.stade4[2][2] = 0;

			this.mat = this.stade1;

			break;
		case 2:
			this.stade1[0][0] = 1;
			this.stade1[0][1] = 1;
			this.stade1[0][2] = 1;

			this.stade1[1][0] = 0;
			this.stade1[1][1] = 0;
			this.stade1[1][2] = 0;

			this.stade1[2][0] = 0;
			this.stade1[2][1] = 0;
			this.stade1[2][2] = 0;

			this.stade2[0][0] = 0;
			this.stade2[0][1] = 1;
			this.stade2[0][2] = 0;

			this.stade2[1][0] = 0;
			this.stade2[1][1] = 1;
			this.stade2[1][2] = 0;

			this.stade2[2][0] = 0;
			this.stade2[2][1] = 1;
			this.stade2[2][2] = 0;

			this.stade3[0][0] = 1;
			this.stade3[0][1] = 1;
			this.stade3[0][2] = 1;

			this.stade3[1][0] = 0;
			this.stade3[1][1] = 0;
			this.stade3[1][2] = 0;

			this.stade3[2][0] = 0;
			this.stade3[2][1] = 0;
			this.stade3[2][2] = 0;

			this.stade4[0][0] = 0;
			this.stade4[0][1] = 1;
			this.stade4[0][2] = 0;

			this.stade4[1][0] = 0;
			this.stade4[1][1] = 1;
			this.stade4[1][2] = 0;

			this.stade4[2][0] = 0;
			this.stade4[2][1] = 1;
			this.stade4[2][2] = 0;

			this.mat = this.stade1;

			break;
		case 3:
			this.stade1[0][0] = 1;
			this.stade1[0][1] = 1;
			this.stade1[0][2] = 1;

			this.stade1[1][0] = 1;
			this.stade1[1][1] = 0;
			this.stade1[1][2] = 0;

			this.stade1[2][0] = 0;
			this.stade1[2][1] = 0;
			this.stade1[2][2] = 0;

			this.stade2[0][0] = 1;
			this.stade2[0][1] = 1;
			this.stade2[0][2] = 0;

			this.stade2[1][0] = 0;
			this.stade2[1][1] = 1;
			this.stade2[1][2] = 0;

			this.stade2[2][0] = 0;
			this.stade2[2][1] = 1;
			this.stade2[2][2] = 0;

			this.stade3[0][0] = 0;
			this.stade3[0][1] = 0;
			this.stade3[0][2] = 1;

			this.stade3[1][0] = 1;
			this.stade3[1][1] = 1;
			this.stade3[1][2] = 1;

			this.stade3[2][0] = 0;
			this.stade3[2][1] = 0;
			this.stade3[2][2] = 0;

			this.stade4[0][0] = 1;
			this.stade4[0][1] = 0;
			this.stade4[0][2] = 0;

			this.stade4[1][0] = 1;
			this.stade4[1][1] = 0;
			this.stade4[1][2] = 0;

			this.stade4[2][0] = 1;
			this.stade4[2][1] = 1;
			this.stade4[2][2] = 0;

			this.mat = this.stade1;

			break;

		}

	}

	public static void affP() {
		System.out.println("####################");
		
		for(int i=0;i<3;i++) {
			
		}
		
		System.out.println("####################");

	}
	
	
}
