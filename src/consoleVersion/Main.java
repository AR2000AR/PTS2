package consoleVersion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		Object plateau[][] = new Object[4][4];
		initialisation(plateau);
		affPlateau(plateau);

	}

	public static void affPlateau(Object tab[][]) {
		for (int i = 0; i < tab.length; i++) {
			System.out.println(Arrays.toString(tab[i]));
		}
	}

	public static void initialisation(Object tab[][]) throws FileNotFoundException {

		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				tab[i][j] = 1;
			}
		}

		/*
		 * 
		 * 0 => zone innociupable 1 => zone occupable 2 => Cochon 3 => Loup
		 * 
		 */
		//////////// zone blocker
		tab[0][0] = 0;
		tab[0][3] = 0;
		tab[3][0] = 0;

		//////////// zone cochon
		
		Cochon c1 = new Cochon();
		Cochon c2 = new Cochon();
		Cochon c3 = new Cochon();
		
		
		tab[1][1] = c1;
		tab[2][3] = c2;
		
		System.out.println();

		
		
	}

}
