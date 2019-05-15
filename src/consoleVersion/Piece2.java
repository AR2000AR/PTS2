package consoleVersion;

public class Piece2 extends Piece {

	public Piece2() {
		
		
		
		for(int i =0; i<4; i++)
		{
			for(int j =0; j<4; j++)
			{
				//que 2 états nécessaires
				etat1[j][i]=0;
				etat2[j][i]=0;
				etat3[j][i]=0;
				etat4[j][i]=0;
				
			}
		
		}
		//état 1, vers le haut
		etat1[2][1]=1;
		etat1[2][2]=1;
		etat1[2][3]=1;
		
		etat3[2][1]=1;
		etat3[2][2]=1;
		etat3[2][3]=1;
		
		//état 2, vers la droite
		etat2[1][2]=1;
		etat2[2][2]=1;
		etat2[3][2]=1;
		
		etat4[1][2]=1;
		etat4[2][2]=1;
		etat4[3][2]=1;



		
		
		
		
	}
	
	
}
