package ch.ksimlee.it.game3b;
import java.util.Scanner;

import ch.ksimlee.it.game3b.Log.Level;
class FirstTry {
	public static void main(String args[]){
		Scanner UI = new Scanner(System.in);
		int x,z;
		z=42;
		System.out.println("Willkommen beim Ratespiel! Finde die gesuchte anze Zahl.");
		System.out.println("Gib deinen L�sungsvorschlag ein:");
		x = UI.nextInt();
		if(x==z){
			System.out.println("Gl�ckwunsch! Du hast die Zahl gefunden.");
		}else{
			while (x!=z){
			if(x < z){
			System.out.println("Die Zahl ist zu Klein!");
			x = UI.nextInt();
			}else{
			System.out.println("Die Zahl ist zu Gross!");
			x = UI.nextInt();
			}
		}
				System.out.println("Gl�ckwunsch! Du hast die Zahl gefunden.");
				Log.log(Level.DEBUG, "Test Login");
		}	
		
	}

}
