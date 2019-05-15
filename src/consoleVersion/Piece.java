package consoleVersion;

public class Piece {

	private int etatRotation=1;
	public int[][] etat1 = new int[4][4];
	public int[][] etat2 = new int[4][4];
	public int[][] etat3 = new int[4][4];
	public int[][] etat4 = new int[4][4];
	
	public Piece() {
		etatActuel=etat1;
	}
	
	
	public int[][] etatActuel = new int[4][4];
	
	
	
	
	
	
	
	public void faireTourner() // se déclenchera avec un double clic et utilisera les rotations préfaites en les appliquant à l'état actuel
	{
		
		switch(etatRotation)
		{
		
		case 1 :
			this.etatActuel=etat2;
			etatRotation++;
			break;
		
		case 2 :
		this.etatActuel=etat3;
		etatRotation++;
		break;
		
		case 3 :
		this.etatActuel=etat4;
		etatRotation++;
		break;
		
		case 4 :
			this.etatActuel=etat1;
			etatRotation=1;
			break;
		default: System.out.println("la rotation a échouée");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * 1 11
	 * 
	 * 111
	 * 
	 * 111 1
	 */
//	public int mat[][]= new int[3][3];
//	int stade1[][] = new int[3][3];
//	int stade2[][] = new int[3][3];
//	int stade3[][] = new int[3][3];
//	int stade4[][] = new int[3][3];
//
//	
//
//	public Piece(int i) {
//
//		switch (i) {
//		case 1:
//
//			this.stade1[0][0] = 1;
//			this.stade1[0][1] = 1;
//			this.stade1[0][2] = 0;
//
//			this.stade1[1][0] = 1;
//			this.stade1[1][1] = 0;
//			this.stade1[1][2] = 0;
//
//			this.stade1[2][0] = 0;
//			this.stade1[2][1] = 0;
//			this.stade1[2][2] = 0;
//
//			this.stade2[0][0] = 1;
//			this.stade2[0][1] = 0;
//			this.stade2[0][2] = 0;
//
//			this.stade2[1][0] = 1;
//			this.stade2[1][1] = 1;
//			this.stade2[1][2] = 0;
//
//			this.stade2[2][0] = 0;
//			this.stade2[2][1] = 0;
//			this.stade2[2][2] = 0;
//
//			this.stade3[0][0] = 0;
//			this.stade3[0][1] = 1;
//			this.stade3[0][2] = 0;
//
//			this.stade3[1][0] = 1;
//			this.stade3[1][1] = 1;
//			this.stade3[1][2] = 0;
//
//			this.stade3[2][0] = 0;
//			this.stade3[2][1] = 0;
//			this.stade3[2][2] = 0;
//
//			this.stade4[0][0] = 1;
//			this.stade4[0][1] = 0;
//			this.stade4[0][2] = 0;
//
//			this.stade4[1][0] = 1;
//			this.stade4[1][1] = 1;
//			this.stade4[1][2] = 0;
//
//			this.stade4[2][0] = 0;
//			this.stade4[2][1] = 0;
//			this.stade4[2][2] = 0;
//
//			this.mat = this.stade1;
//
//			break;
//		case 2:
//			this.stade1[0][0] = 1;
//			this.stade1[0][1] = 1;
//			this.stade1[0][2] = 1;
//
//			this.stade1[1][0] = 0;
//			this.stade1[1][1] = 0;
//			this.stade1[1][2] = 0;
//
//			this.stade1[2][0] = 0;
//			this.stade1[2][1] = 0;
//			this.stade1[2][2] = 0;
//
//			this.stade2[0][0] = 0;
//			this.stade2[0][1] = 1;
//			this.stade2[0][2] = 0;
//
//			this.stade2[1][0] = 0;
//			this.stade2[1][1] = 1;
//			this.stade2[1][2] = 0;
//
//			this.stade2[2][0] = 0;
//			this.stade2[2][1] = 1;
//			this.stade2[2][2] = 0;
//
//			this.stade3[0][0] = 1;
//			this.stade3[0][1] = 1;
//			this.stade3[0][2] = 1;
//
//			this.stade3[1][0] = 0;
//			this.stade3[1][1] = 0;
//			this.stade3[1][2] = 0;
//
//			this.stade3[2][0] = 0;
//			this.stade3[2][1] = 0;
//			this.stade3[2][2] = 0;
//
//			this.stade4[0][0] = 0;
//			this.stade4[0][1] = 1;
//			this.stade4[0][2] = 0;
//
//			this.stade4[1][0] = 0;
//			this.stade4[1][1] = 1;
//			this.stade4[1][2] = 0;
//
//			this.stade4[2][0] = 0;
//			this.stade4[2][1] = 1;
//			this.stade4[2][2] = 0;
//
//			this.mat = this.stade1;
//
//			break;
//		case 3:
//			this.stade1[0][0] = 1;
//			this.stade1[0][1] = 1;
//			this.stade1[0][2] = 1;
//
//			this.stade1[1][0] = 1;
//			this.stade1[1][1] = 0;
//			this.stade1[1][2] = 0;
//
//			this.stade1[2][0] = 0;
//			this.stade1[2][1] = 0;
//			this.stade1[2][2] = 0;
//
//			this.stade2[0][0] = 1;
//			this.stade2[0][1] = 1;
//			this.stade2[0][2] = 0;
//
//			this.stade2[1][0] = 0;
//			this.stade2[1][1] = 1;
//			this.stade2[1][2] = 0;
//
//			this.stade2[2][0] = 0;
//			this.stade2[2][1] = 1;
//			this.stade2[2][2] = 0;
//
//			this.stade3[0][0] = 0;
//			this.stade3[0][1] = 0;
//			this.stade3[0][2] = 1;
//
//			this.stade3[1][0] = 1;
//			this.stade3[1][1] = 1;
//			this.stade3[1][2] = 1;
//
//			this.stade3[2][0] = 0;
//			this.stade3[2][1] = 0;
//			this.stade3[2][2] = 0;
//
//			this.stade4[0][0] = 1;
//			this.stade4[0][1] = 0;
//			this.stade4[0][2] = 0;
//
//			this.stade4[1][0] = 1;
//			this.stade4[1][1] = 0;
//			this.stade4[1][2] = 0;
//
//			this.stade4[2][0] = 1;
//			this.stade4[2][1] = 1;
//			this.stade4[2][2] = 0;
//
//			this.mat = this.stade1;
//
//			break;
//
//		}
//
//	}
//
//	public static void affP() {
//		System.out.println("####################");
//		
//		for(int i=0;i<3;i++) {
//			
//		}
//		
//		System.out.println("####################");
//
//	}
	
	
}
