package Classes;

import java.util.Objects;

public class Tresor {
	final private String description;
	final private double PO;
	public Tresor(String description, double PO) {
		this.description = description;
		this.PO = PO;
	}
	
	public String getDescription() {
		return description;
	}

	public double getPO() {
		return PO;
	}
	
	public Tresor setDescription(String description) {
		return new Tresor(description, this.PO);
	}

	public Tresor setPO(double pO) {
		return new Tresor(this.description, pO);
	}

	@Override
	public int hashCode() {
		return Objects.hash(PO, description);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tresor other = (Tresor) obj;
		return Double.doubleToLongBits(PO) == Double.doubleToLongBits(other.PO)
				&& Objects.equals(description, other.description);
	}

	@Override
	public String toString() {
		return "Nom du tresor: " + description + ", nombre de pieces d'or: " + PO ;
	}
}
