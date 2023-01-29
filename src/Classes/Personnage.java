package Classes;

import java.util.HashSet;
import java.util.Objects;

//Regarder ENUM

public class Personnage {
	public enum Role{CLERC, GUERRIER}
	
	private Arme arme = null;
	private HashSet <Tresor> lesTresor = new HashSet<Tresor>();
	
	private String nom;
	private int pvMax;
	private int pvActuel;
	private Role role;
	private Boolean etat, fuir;
	public Personnage(String nom, Role role) {
		int min =90;
		int max =100;
		int rand = (int)Math.floor(Math.random()*(max-min+1)+min);	  		// On genere aleatoirement les PV max du joueur entre 90 et 100 PV		
		this.nom = nom;
		this.pvMax = rand;
		this.pvActuel = rand;
		this.role = role;
		this.etat=true;
		this.fuir=false;
	}
	
	public Boolean getEtat() {
		return etat;
	}

	public String getNom() {
		return nom;
	}
	
	public void setPvActuel(int pvActuel) {
		this.pvActuel = pvActuel;
	}

	public int getPvMax() {
		return pvMax;
	}

	public int getPvActuel() {
		return pvActuel;
	}

	public String getArme() {
		if (arme==null) {
			return "Vous n'avez pas d'armes";
		}
		return arme.getNomArme();
	}

	public void getLesTresor() {
		for (Tresor temp: lesTresor) {
			System.out.println(temp);
		}
	}
	
	public HashSet<Tresor> getTresor() {
		HashSet <Tresor> Tresor = lesTresor; 
		return Tresor;
	}
	public void fuir() {
		this.fuir=true;
	}
	public Boolean getFuit() {
		return fuir;
	}
	public void equiperArme(int choix) { //On determine l'arme par le choix de l'utilisateur l'arme choisie;
		switch(choix) {
			case 1:
				arme = new Arme("Epee", 15 , 20);
				break;
			case 2:
				arme = new Arme("Lance", 5 , 40); 
				break;
			case 3:
				arme = new Arme("Dague", 10 , 25);
				break;
			case 999:
				arme = new Arme("Test", 8, 8);				//Cette arme va etre utiliser pour tester notre classe personnageTest
				break;
			default:
				System.out.println("Vous vous battez a main nue");
				break;				
		}
		
	}
	
	public void prendreTresor(int choix) {
		switch(choix) {
		case 1:
			lesTresor.add(new Tresor("Petite bourse", 10));
			break;
		case 2:
			lesTresor.add(new Tresor("Reserve du roi", 5000)); 
			break;
		case 3:
			lesTresor.add(new Tresor("Barrique de sucre", 500));
			break;
		default:
			System.out.println("Vous ne voulez pas trouver de tresor dommage..");
			break;				
	}
	}
	
	public void attaquerPersonnage(Personnage unPersonnage) {
		int PD;
		if(this.arme != null) {
			PD = this.arme.degat();		
		}
		else {
			PD = 1;
		}
		if(this.role == Role.GUERRIER) {
			PD++;
		}
		else if(this.role == Role.CLERC) {
			PD--;
			if(PD<1)
				PD=1;
		}
		if(unPersonnage.pvActuel-PD <=0) {
			unPersonnage.etat=false;			//L'ennemi est tue
			this.lesTresor.addAll(unPersonnage.lesTresor);	//On prends tous les tresors
			System.out.println("Bravo vous avez tue votre adversaire, vous lui avez inflige: "+ PD+" degats.");
		}
		else {
			unPersonnage.pvActuel-= PD;				
			System.out.println("Vous avez inflige:" + PD + " degats, votre adversaire lui reste: " + unPersonnage.pvActuel + " HP" );
		}
	}
	
	public void soignerPersonnage(Personnage unPersonnage) {
		if(this.role== Role.CLERC) {
			if(unPersonnage.pvActuel+4>unPersonnage.pvMax) {	// Si l'utilisateur veut se soigner plus que ses PV Max on lui affecte juste ses PV Max a ses PV Actuel
				unPersonnage.pvActuel = unPersonnage.pvMax;
				System.out.println("Vous vous etes soignes vos PV sont maintenants de:" + unPersonnage.pvActuel );
			}
			else {
				unPersonnage.pvActuel+=4;
				System.out.println("Vous vous etes soignes vos PV sont maintenants de:" + unPersonnage.pvActuel );
			}
		}
		else
		{
			System.out.println("Vous ne pouvez pas soigner car vous etes un guerrier");
		}
	}

	@Override
	public String toString() {
		return "Personnage [arme=" + arme + ", lesTresor=" + lesTresor + ", nom=" + nom + ", pvMax=" + pvMax
				+ ", pvActuel=" + pvActuel + ", role=" + role + ", etat=" + etat + ", fuir=" + fuir + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(arme, etat, fuir, lesTresor, nom, pvActuel, pvMax, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personnage other = (Personnage) obj;
		return Objects.equals(arme, other.arme) && Objects.equals(etat, other.etat) && Objects.equals(fuir, other.fuir)
				&& Objects.equals(lesTresor, other.lesTresor) && Objects.equals(nom, other.nom)
				&& pvActuel == other.pvActuel && pvMax == other.pvMax && role == other.role;
	}		
	
	
	
}

