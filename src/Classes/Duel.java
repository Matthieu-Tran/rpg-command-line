package Classes;
import java.util.Scanner;

import Classes.Personnage.Role;

public class Duel 
{
	static int nbBot=0;
	public static int choixPersonnage(Scanner scan) {
		int player;
		System.out.println("Voulez vous creer un bot ou bien voulez vous vous meme creer un personnage? | 0 pour un bot 1 pour humain");
		// On regarde si l'utilisateur a bien mis des entiers, sinon on lui redemande un entier
		while(!scan.hasNextInt()) {
		    System.out.println("Veuillez ne mettre que des int!"); 
		    scan.next(); // discard 
		} 
		player = scan.nextInt();
		while(player<0 || player >1) 
		{
			System.out.println("Veuillez ne pas vous tromper lors de ce choix... Choisissez un nombre entre 0 et 1");
			while(!scan.hasNextInt()) {
			    System.out.println("Veuillez ne mettre que des entier!"); 
			    scan.next(); // discard 
			} 
			player = scan.nextInt();	
		}
		return player;
	}
	
	public static Personnage creationPersonnage(int choix, Scanner scan) {
		Personnage personnage = null;
		if(choix==1) 
		{
			System.out.println("Quel est le nom de votre personnage ?");
			String name = scan.next();
			
			System.out.println("Quelle classe voulez vous prendre? | 1 Pour Clerc | 2 Pour Guerrier ?");
			while(!scan.hasNextInt()) {
			    System.out.println("Veuillez ne mettre que des entier!"); 
			    scan.next(); // discard 
			} 
			int choixClasse = scan.nextInt();
			while(choixClasse<1 || choixClasse >2) 
			{
					System.out.println("Veuillez choisir un bon numero une bonne classe");
					while(!scan.hasNextInt()) {
					    System.out.println("Veuillez ne mettre que des entier!"); 
					    scan.next(); // discard 
					} 
					choixClasse = scan.nextInt();
					
			}
			if(choixClasse==1) 
			{
				personnage = new Personnage(name, Role.CLERC);				// Attribue le role de CLERC et le nom choisi par l'utilisateur dans la variable p1
			}
			else if (choixClasse==2) 
			{
				personnage = new Personnage(name, Role.GUERRIER);			// Attribue le role de GUERRIER et le nom choisi par l'utilisateur dans la variable p1
			}
			
			System.out.println("Quelle arme voulez vous prendre?");
			System.out.println("(1) Epee, 15 Degats min, 20 Degats max | (2) Lance 5 Degats min, 40 Degats max | (3) Dague, 10 Degats min, 25 Degats max | (4) Main nue, 1 de degats");
			while(!scan.hasNextInt()) {
			    System.out.println("Veuillez ne mettre que des entier!"); 
			    scan.next(); // discard 
			} 
			int choixArme = scan.nextInt();
			while(choixArme<1 || choixArme >4) 
			{
					System.out.println("Veuillez choisir un bon numero pour une arme");
					while(!scan.hasNextInt()) {
					    System.out.println("Veuillez ne mettre que des entier!"); 
					    scan.next(); // discard 
					} 
					choixArme = scan.nextInt();
					
			}
			personnage.equiperArme(choixArme);				// Equipe l'arme choisi par l'utisateur
			
			System.out.println("Quelle tresor voulez vous prendre?");
			System.out.println("Veuillez choisir un nombre en 1 et 3 et vous vous retrouverez avec un tresor, 4 pour ne pas avoir de tresor");
			while(!scan.hasNextInt()) {
			    System.out.println("Veuillez ne mettre que des entier!"); 
			    scan.next(); // discard 
			} 
			int choixTresor= scan.nextInt();
			while(choixTresor<1 || choixTresor >4) 
			{
					System.out.println("Veuillez choisir un bon numero pour un tresor");
					while(!scan.hasNextInt()) {
					    System.out.println("Veuillez ne mettre que des entier!"); 
					    scan.next(); // discard 
					} 
					choixTresor = scan.nextInt();
					
			}
			personnage.prendreTresor(choixTresor);				// L'utisateur choisi au hasard le tresor qu'il va recuperer
			if(choixTresor<4) {
				System.out.println("Vous avez trouvez:");
				personnage.getLesTresor();
			}
		}
		// On cree un bot si l'utilisateur a choisi de creer un bot 
		else {
			int tmp = (int)(Math.random()*2)+1; 			// Genere un nombre en 1 et 2 et attribue aleatoirement le role du bot
			if (tmp == 1) {
				if(nbBot==0) {
					personnage = new Personnage("Atlas", Role.CLERC);
					System.out.println(personnage.getNom() +" est un CLERC");
				}
				else{
					personnage = new Personnage("SpotMini", Role.CLERC);
					System.out.println(personnage.getNom()+"  est un CLERC");
				}
			}
			else {
				if(nbBot==0) {
					personnage = new Personnage("Atlas", Role.GUERRIER);
					System.out.println(personnage.getNom()+" est un GUERRIER");
				}
				else {
					personnage = new Personnage("SpotMini", Role.GUERRIER);
					System.out.println(personnage.getNom()+" bot est un GUERRIER");
				}
				
			}
			int tmp2 = (int)(Math.random()*4)+1;			// On genere un chiffre entre 1 et 4 et on attribue aleatoirement une arme au BOT
			personnage.equiperArme(tmp2);
			switch(tmp2) {
			case 1:
				System.out.println(personnage.getNom()+" est equipe d'une epee");
				break;
			case 2:
				System.out.println(personnage.getNom()+" est equipe d'une lance");
				break;
			case 3:
				System.out.println(personnage.getNom()+" est equipe d'une dague");
				break;
			default:
				System.out.println("");
				break;
			}
			int tmp3 = (int)(Math.random()*3)+1;			// On genere un chiffre entre 1 et 3 et on attribue aleatoirement un tresor au BOT, on force le BOT a avoir un tresor
			personnage.prendreTresor(tmp3);
			System.out.println(personnage.getNom()+" a trouve: ");
			personnage.getLesTresor();
			nbBot++;
		}
		System.out.println();
		return personnage;
		
	}
	
	public static void combat(Personnage p1, Personnage p2, int player1, int player2, Scanner scan) {
		while ((p1.getEtat() && p2.getEtat()) && (!(p1.getFuit()) && !(p2.getFuit()))) {
			if(player1 ==0 && player2 ==0){
		        combatBOT(p1,p2);
		        if(p1.getFuit() || (!p2.getEtat()))	// Si l'adversaire est mort ou a fuit, on arrete d'etre dans la boucle
					return;
		        combatBOT(p2,p1);
			}
			else if(player1 ==1 && player2 ==0){
		        combatHumain(p1, p2, scan);
		        if(p1.getFuit() || (!p2.getEtat()))
					return;
		        combatBOT(p2, p1);
			}
			else if (player1 == 0 && player2 == 1){
				combatBOT(p1, p2);
		        if(p1.getFuit() || (!p2.getEtat()))
					return;
		        combatHumain(p2, p1, scan);
			}
			else{
		        combatHumain(p1, p2, scan);
				if(p1.getFuit() || (!p2.getEtat()))
					return;
		        combatHumain(p2, p1, scan);
			}
			System.out.println();
		}
	
	}
	public static void combatBOT(Personnage p1, Personnage p2) {
		int choix = (int)(Math.random()*2)+1;
		System.out.println("C'est au tour du " + p1.getNom() + ", " + p1.getPvActuel() + "HP");
		// Si le bot a des PV inferrieur a 10 le BOT fuit
		if (p1.getPvActuel()<10) {
			System.out.println(p1.getNom()+" a fuit!");
			p1.fuir();
		}
		else {
			switch(choix) {
			case 1:
				p1.attaquerPersonnage(p2);
				break;
			case 2:
				p1.soignerPersonnage(p1);
				break;
			}
		}
	}

  public static void combatHumain(Personnage p1, Personnage p2, Scanner scan){
    				System.out.println(p1.getNom() + ", il vous reste: " + p1.getPvActuel() + "HP. Quelle action voulez vous faire? (1) Pour attaquer (2) Pour vous soigner (3) Pour fuir");
				while(!scan.hasNextInt()) {
				    System.out.println("Veuillez ne mettre que des entier!"); 
				    scan.next(); // discard 
				} 
				int choixAction = scan.nextInt();
					switch(choixAction) {
					case 1:
						p1.attaquerPersonnage(p2);
						if(!p2.getEtat())				
							return;
						break;
					case 2:
						p1.soignerPersonnage(p1);
						break;
					case 3:
						p1.fuir();
						System.out.println(p1.getNom()+ " a fuit");
						break;
					default:
						System.out.println("Vous vous etes trompez de touche... c'est a l'adversaire maintenant");
						break;		
					}
					System.out.println();
  }
	
	public static void main(String[] args) 
	{	
		Scanner scan = new Scanner(System.in);
		System.out.println("(P1)");
		int player1 = choixPersonnage(scan);
		Personnage p1 = creationPersonnage(player1, scan);
		System.out.println("(P2)");
		int player2 = choixPersonnage(scan);
		Personnage p2 = creationPersonnage(player2, scan);
		/****************
		On commence le Duel
		 ****************/
		System.out.println();
		System.out.println("QUE LE COMBAT COMMENCE!!!!");
		System.out.println();
		//On commence le combat
		combat(p1, p2, player1, player2, scan);		
		System.out.println();
		if(p1.getEtat()==false) {
			System.out.println("Bravo, " + p2.getNom() + " a gagne!");
			System.out.println("Voici votre butin final:");
			p2.getLesTresor();
		}
		else if(p2.getEtat() == false){
			System.out.println("Bravo, " + p1.getNom() + " a gagne!");
			System.out.println("Voici votre butin final:");
			p1.getLesTresor();
		}
		else {
			System.out.println("Les joueurs ont fui la bataille...");
		}
		scan.close();
	}
}

