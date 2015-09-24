import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


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
	private String owner;
	private String name;

	//----------Should replace this with an image of tile--------------
	private Rectangle mapTileGui;
	private static int w = 100;
	private static int h = 100;
	private Color mapType;
	//-----------------------------------------------------------------

	/**
	 * 
	 * @param food
	 * @param energy
	 * @param ore
	 * @param crystalite
	 * @param highlightable
	 * @param mapType ** This is temporary. Should be an image **
	 */
	public MapTiles(int food, int energy, int ore, boolean crystalite, boolean highlightable, String name, Color mapType) {
		this.food = food;
		this.ore = ore;
		this.crystalite = crystalite;
		this.highlightable = highlightable;
		this.mapTileGui = new Rectangle(w, h, mapType);
		this.mapType = mapType;
		this.owner = "None";
		this.name = name;
	}

	/**
	 * Getters and Setters
	 * @return various
	 */

	public int getFood() { return food; }
	public void setFood(int food) { this.food = food; }
	public int getEnergy() { return energy; }
	public void setEnergy(int energy) { this.energy = energy; }
	public int getOre() { return ore; }
	public void setOre(int ore) { this.ore = ore; }
	public boolean isCrystalite() { return crystalite; }
	public void setCrystalite(boolean crystalite) { this.crystalite = crystalite; }
	public boolean isHighlightable() { return highlightable; }
	public void setHighlightable(boolean highlightable) { this.highlightable = highlightable; }
	public Color getMapType() { return mapType; }
	public void getMapType(Color mapType) { this.mapType = mapType; }
	public String getName() { return name; }
	public static int getW() { return w; };
	public static int getH() { return h; };
	//-------------------------------------------------------------------
	public Rectangle getMapTileGui() { return mapTileGui;}
	public void setMapTileGui(Rectangle mapTileGui) { this.mapTileGui = mapTileGui; };
	//-------------------------------------------------------------------
	
	public String getOwner() { return owner; }
	public void setOwner(String owner) { this.owner = owner; }
	
}

