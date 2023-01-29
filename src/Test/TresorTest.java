package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;


import Classes.Tresor;

public class TresorTest {

	Tresor t1;
	Tresor t2;
	Tresor t3;
	
	@BeforeEach
	public void preparerArme() {
		this.t1 = new Tresor("Test", 600);
		this.t2 = new Tresor("Test", 600);
		this.t3 = new Tresor("Ca ne devrait pas etre egal", 1000);
	}
	
	@RepeatedTest(100)
	public void hashCodeTest() {	
		assertEquals(t1.hashCode(), (new Tresor("Test", 600).hashCode()));
		assertTrue(t1.hashCode() == t2.hashCode());
		assertNotEquals(t1.hashCode(), t3.hashCode());
	}
	
	@RepeatedTest(100)
	public void equalsTest() {
		assertTrue(t1.equals(t2) == true);					// On test si t1 est egal a t2
		assertFalse(t1.equals(t3) == true);					// On test si t1 est pas egal a t3
	}
	
}
