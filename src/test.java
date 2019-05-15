
public class test {

	public static void main(String[] args) {
	
		
		int taillemax= 4;
		int plateau[][] = new int[taillemax][taillemax];
		
		for(int i =0; i<taillemax; i++)
		{
			for(int j =0; j<taillemax; j++)
			{
				plateau[i][j]=1; // emplacement dispos
			}
		}
		
		plateau[0][0]=0;
		plateau[0][3]=0; // malformations du plateau
		plateau[3][0]=0;
		
		//affichage
		for(int i =0; i<taillemax; i++)
		{
			for(int j =0; j<taillemax; j++)
			{
				System.out.print(plateau[j][i]); // emplacement dispos
			}
			System.out.println("");
		}
		
		plateau[1][1]=2;
		plateau[3][2]=2 ;  //cochons
		
		
		
		System.out.println();
		System.out.println("ESPACE");
		System.out.println();
		
		for(int i =0; i<taillemax; i++)
		{
			for(int j =0; j<taillemax; j++)
			{
				System.out.print(plateau[j][i]); // emplacement dispos
			}
			System.out.println("");
		}
		
		
		//déclaration pièces
		
		int piece1[][]= new int[3][3];
		int piece2[][]= new int[3][3];
		int piece3[][]= new int[3][3];
		
		for(int i =0; i<taillemax-1; i++)
		{
			for(int j =0; j<taillemax-1; j++)
			{
				piece1[i][j]=0; // emplacement dispos
				piece2[i][j]=0;
				piece3[i][j]=0;
			}
		}
		
		
		piece1[0][0]=1; //piece droite P2
		piece1[0][1]=1;
		piece1[0][2]=1;
		
		
		piece2[0][0]=1;
		piece2[0][1]=1;
		piece2[0][2]=1;	//piece coudée longue P3
		piece2[1][0]=1;
		
		
		piece3[0][0]=1;
		piece3[1][0]=1;	//piece coudée courte P1
		piece3[0][1]=1;
		
		System.out.println();
		System.out.println("ESPACE");
		System.out.println();
		
		for(int i =0; i<taillemax-1; i++)
		{
			for(int j =0; j<taillemax-1; j++)
			{
				System.out.print(piece1[j][i]); // emplacement dispos
			}
			System.out.println("");
		}
		
		System.out.println();
		System.out.println("ESPACE");
		System.out.println();
		
		for(int i =0; i<taillemax-1; i++)
		{
			for(int j =0; j<taillemax-1; j++)
			{
				System.out.print(piece2[j][i]); // emplacement dispos
			}
			System.out.println("");
		}
		
		System.out.println();
		System.out.println("ESPACE");
		System.out.println();
		
		for(int i =0; i<taillemax-1; i++)
		{
			for(int j =0; j<taillemax-1; j++)
			{
				System.out.print(piece3[j][i]); // emplacement dispos
			}
			System.out.println("");
		}
		
		System.out.println("voulez vous placez une piece sur le plateau ? sachant qu'il faut montrer ");
		
		for(int i =0; i<taillemax-1; i++)
		{
			for(int j =0; j<taillemax-1; j++)
			{
				System.out.print(piece2[i][j]); // emplacement dispos
			}
			System.out.println("");
		}
		
		
	}

}
