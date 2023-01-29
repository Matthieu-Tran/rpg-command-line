package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import Classes.Personnage;
import Classes.Personnage.Role;
import Classes.Tresor;

public class PersonnageTest {
	Personnage p1;
	Personnage p2;
	Personnage p3;	
	
	@BeforeEach
	public void preparerArme() {
		this.p1 = new Personnage("unClerc", Role.GUERRIER);					// on cree un personnage avec des PV initiaux aleatoires
		this.p2 = new Personnage("unGuerrier", Role.CLERC);
		this.p3 = p1;
		p1.prendreTresor(1);												// 1 = new Tresor("Petite bourse", 10));
		p2.prendreTresor(2);												// 2 = new Tresor("Reserve du roi", 5000)); 
	}
	
	@RepeatedTest(100)
	public void testEtatPersonnage() {
		assertTrue(true==p1.getEtat());										// On test si le personnage est bien en vie
		assertTrue(false==p1.getFuit());									// On test si le personnage n'a pas encore fuit
		p1.fuir();															// Le personnage fuit
		assertFalse(false==p1.getFuit());
	}
	
	@RepeatedTest(100)
	public void testTresor() {
		p2.setPvActuel(1);												
		p1.attaquerPersonnage(p2);
		assertTrue(false==p2.getEtat());									// On regarde si il est bien mort
		HashSet<Tresor>tresor = new HashSet<Tresor>();
		tresor.add(new Tresor("Reserve du roi", 5000));
		tresor.add(new Tresor("Petite bourse", 10));
		assertTrue(p1.getTresor().containsAll(tresor));						// On regarde si le vainqueur a bien pris les tresor
		
	}
	
	@RepeatedTest(100)
	public void degatTestSansArmes() {
		p1.attaquerPersonnage(p2);
		assertEquals(p2.getPvActuel(), p2.getPvMax()-2);					// On test si le guerrier fait bien 2 de degats sans arme
		p2.attaquerPersonnage(p1);
		assertEquals(p1.getPvActuel(), p1.getPvMax()-1);					// On test si le clerc fait bien 1 de degats sans arme, ca test aussi le fait que le clerc ne peut pas avoir des degats nul ou negatif
	}
	
	@RepeatedTest(100)
	public void degatTestArme() {
		p1.equiperArme(999); 												// on equipe une arme de test qui va faire 8 de degats minimum et 8 de degats maximum
		p1.attaquerPersonnage(p2);
		assertEquals(p2.getPvActuel(), p2.getPvMax()-9);					// 8+1 car le personnage est un guerrier
		p2.equiperArme(999);
		p2.attaquerPersonnage(p1);
		assertEquals(p1.getPvActuel(), p1.getPvMax()-7);					// 8-1 car le personnage est un clerc
	}
	
	@RepeatedTest(100)
	public void soinPersonnage(){
		p2.equiperArme(999);
		p2.soignerPersonnage(p1);
		assertEquals(p2.getPvActuel(), p2.getPvMax());						// On test le fait qu'on ne peut pas soigner au delas des PV max du joueur
		p2.attaquerPersonnage(p1);											// On attaque le joueur pour le soigner par la suite
		p2.soignerPersonnage(p1);
		assertEquals(p1.getPvActuel(), p1.getPvMax()-3);					// On a fait 8-1 degats, on le soigne de 4, (-7+4)= -3 donc il a maintenant ses PVMax - 3
		
		p1.equiperArme(999);
		p1.attaquerPersonnage(p2);											// On inflige des degats au deuxieme personnage pour essaye de le soigner par la suite
		int pvJoueurAvantSoinGuerrier = p2.getPvActuel();					// On recupere les PV du joueur 2 avant de tester si le guerrier fait du soin ou non.
		p1.soignerPersonnage(p2);											// On test si le fait de soigner avec un Guerrier fait quelque chose 
		assertEquals(pvJoueurAvantSoinGuerrier, p2.getPvActuel());			// On regarde que le guerrier n'a pas soigne, on compare avec les PV avant le soin et les PV actuels
	}
	
	@RepeatedTest(100)
	public void hashCodeTest() {	
		assertEquals(p1.hashCode(), p3.hashCode());
		assertTrue(p1.hashCode() == p1.hashCode());
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}
	
	@RepeatedTest(100)
	public void equalsTest() {
		assertTrue(p1.equals(p3) == true);					// On test si t1 est egal a t2
		assertFalse(p1.equals(p2) == true);					// On test si t1 est pas egal a t3
	}


}
