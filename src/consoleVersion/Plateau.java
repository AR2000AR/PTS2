package consoleVersion;

public class Plateau {
	
	private static final int INFINI = Integer.MAX_VALUE;
	int [][] plateau = new int[4][4];
	
	public Plateau() {
		for(int i =0; i<4; i++)
		{
			for(int j =0; j<4; j++)
			{
				plateau[i][j]=0;				
			}
		
		}
		
		plateau[0][0]=INFINI;
		plateau[0][3]=INFINI;
		plateau[3][0]=INFINI;
	}
	
	
	public void afficherPlateau() {
		for(int i =0; i<4; i++)
		{
			for(int j =0; j<4; j++)
			{
				System.out.println(plateau[i][j]=0);				
			}
		
		}
		
		
	}
	public void deplacerPiece(Piece unePiece)
	{
		
		
		
		
	}
	
}
