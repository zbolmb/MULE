
/**
 * An abstract class regarding MapTiles
 * @author Karl Nicodemus
 *
 */

public abstract class MapTiles {

	private int food;
	private int energy;
	private int ore;
	private boolean crystalite;
	private boolean highlightable;

	public MapTiles() {
		setFood(0);
		setEnergy(0);
		setOre(0);
		setCrystalite(false);
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getOre() {
		return ore;
	}

	public void setOre(int ore) {
		this.ore = ore;
	}

	public boolean isCrystalite() {
		return crystalite;
	}

	public void setCrystalite(boolean crystalite) {
		this.crystalite = crystalite;
	}

	public boolean isHighlightable() {
		return highlightable;
	}

	public void setHighlightable(boolean highlightable) {
		this.highlightable = highlightable;
	}
}

