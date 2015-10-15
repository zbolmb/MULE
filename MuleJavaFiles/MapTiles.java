import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * An abstract class regarding MapTiles
 * @author Karl Nicodemus
 *
 */

public abstract class MapTiles {

	String owner;

	private int food;
	private int energy;
	private int ore;
	private boolean crystite;
	private boolean highlightable;
	private boolean mule1;
	private boolean mule2;
	private boolean mule3;
	private String name;
	private Image img;


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
	 * @param crystite
	 * @param highlightable
	 * @param mapType ** This is temporary. Should be an image **
	 */
	public MapTiles(int food, int energy, int ore, boolean crystite, boolean highlightable, String name, Color mapType, Image img) {
		this.food = food;
		this.ore = ore;
		this.crystite = crystite;
		this.highlightable = highlightable;
		this.mapTileGui = new Rectangle(w, h, mapType);
		this.mapType = mapType;
		this.owner = "None";
		this.name = name;
		this.img = img;
		this.mule1 = false;
		this.mule2 = false;
		this.mule3 = false;
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
	public boolean isCrystalite() { return crystite; }
	public void setCrystalite(boolean crystalite) { this.crystite = crystite; }
	public boolean isHighlightable() { return highlightable; }
	public boolean isMule1() { return this.mule1;}
	public boolean isMule2() { return this.mule2;}
	public boolean isMule3() { return this.mule3;}

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
	public Image getImg() { return img; }
	public void setImg(Image img) { this.img = img; }
	public ImageView getImgView() { return new ImageView(img); }

}
