package Classes;

import java.util.Objects;
import java.util.Random;

public class Arme {
	final private String nomArme;
	final private int nbDegatsMax;
	final private int nbDegatsMin;
	//private HashSet <Arme> lesArmes = new HashSet<Arme>();
	
	public Arme(String nomArme, int nbDegatsMin, int nbDegatsMax) {
		this.nomArme = nomArme;
		if(nbDegatsMax<nbDegatsMin) {
			int temp = nbDegatsMin;
			nbDegatsMin = nbDegatsMax;
			nbDegatsMax = temp;
			
		}
		this.nbDegatsMin = nbDegatsMin;
		this.nbDegatsMax = nbDegatsMax;
	//lesArmes.add(this);
	}

	public String getNomArme() {
		return nomArme;
	}

	public int getNbDegatsMax() {
		return nbDegatsMax;
	}

	public int getNbDegatsMin() {
		return nbDegatsMin;
	}
	
	public Arme setNomArme(String nomArme) {
		return new Arme(nomArme, this.nbDegatsMin, this.nbDegatsMax);
	}
	public Arme setnbDegatsMax(int nbDegatsMax) {
		return new Arme(this.nomArme, this.nbDegatsMin, nbDegatsMax);
	}
	public Arme setnbDegatsMin(int nbDegatsMin) {
		return new Arme(this.nomArme, nbDegatsMin, this.nbDegatsMax);
	}

	public int degat(){
		int max = this.getNbDegatsMax();
		int min = this.getNbDegatsMin();
		Random random = new Random();
		if(max-min == 0) {
			return min;
		}
		return random.nextInt(max-min) +min;

	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nomArme, nbDegatsMin, nbDegatsMax);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Arme))
			return false;
		Arme that = (Arme) obj;
		return Objects.equals(this.nomArme, that.nomArme) && this.nbDegatsMin == that.nbDegatsMin && this.nbDegatsMax == that.nbDegatsMax;
	}
	
	
	@Override
	public String toString() {
		return "Nom de l'arme: " + nomArme + ", nbDegatsMax=" + nbDegatsMax + ", nbDegatsMin=" + nbDegatsMin;
	}
	
	
	
}
