package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import Classes.Arme;

public class ArmeTest {
	

	Arme arme;
	Arme arme2;
	Arme arme3;
	
	@BeforeEach
	public void preparerArme() {
		int min = 10;
		int min2 = 20;
		int minimum = (int)(Math.random()*(min2-min+1)+min);	// On genere un nombre entre 10 et 20, ca sera le minimum de degat de l'arme
		int max = 50;
		int max2 = 100;
		int maximum = (int)(Math.random()*(max2-max+1)+max);	// De meme pour le maximum
		this.arme = new Arme("Epee", minimum , maximum);
		this.arme2 = new Arme("Epee", minimum, maximum);
		this.arme3 = new Arme("PasUneEpee", 10, 100);
	}
	
	@RepeatedTest(100)
	public void degatTest() {
		assertTrue(arme.degat()<=arme.getNbDegatsMax());
		assertTrue(arme.degat()>=arme.getNbDegatsMin());
	}
	
	@RepeatedTest(100)
	public void EqualsTest(){
		assertTrue(arme.equals(arme2) == true);
		assertFalse(arme.equals(arme3) == true);
	}
	
	@Test
	public void testProbabilite() {
		int minDegat=10;
		int maxDegat=100;
		int nbTests =100;
		Arme arme1 = new Arme("Arme", minDegat, maxDegat);
		int result=0;
		for(int i=0;i<nbTests;i++) {
			result += arme1.degat();
		}
		assertTrue(minDegat*nbTests< result && maxDegat*nbTests> result);
		double ratio = (result)/(maxDegat*nbTests);							// On regarde si le ratio result<maxDegat*nbTests est bon
		assertTrue(ratio<=1);
	}
	
	@Test
	public void testArme() {
		Arme arme4 = new Arme("DegatsBizarre", 50, 10);			// On test ici qu'on switch bien les degats min et degats max si max<min
		assertEquals(arme4.getNbDegatsMax(), 50);
		assertEquals(arme4.getNbDegatsMin(), 10);
	}

	@Test
	public void hashCodeTest() {
		Arme Lance = new Arme("Lance", 30 , 3000);
		assertEquals(Lance.hashCode(), (new Arme("Lance", 30 , 3000).hashCode()));
		assertEquals(Lance.hashCode(), Lance.hashCode());
		assertNotEquals(Lance.hashCode(), (new Arme("Hache", 30 , 90).hashCode()));
	}

	

	
	
}
