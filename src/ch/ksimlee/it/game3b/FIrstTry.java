package pack;
import java.util.Scanner;

import pack.Log.Level;
class Ratespielfunktion {
	public static void main(String args[]){
		Scanner UI = new Scanner(System.in);
		int x,z;
		z=42;
		System.out.println("Willkommen beim Ratespiel! Finde die gesuchte anze Zahl.");
		System.out.println("Gib deinen Lösungsvorschlag ein:");
		x = UI.nextInt();
		if(x==z){
			System.out.println("Glückwunsch! Du hast die Zahl gefunden.");
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
				System.out.println("Glückwunsch! Du hast die Zahl gefunden.");
				Log.log(Level.DEBUG, "Test Login");
		}	
		
	}

}
