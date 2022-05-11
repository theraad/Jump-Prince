package JumpPrince.tiles;

import JumpPrince.graphics.Screen;
import JumpPrince.graphics.Sprite;

public abstract class Tile {

	public Sprite sprite;
	public static final int TILE_SIZE = 64;
	
	public static final int GRASS1_COL = 0xff005E00;
	public static final int GRASS2_COL = 0xff009000;
	public static final int GRASS_RIGHTEDGE_COL = 0xff00704B;
	public static final int GRASS_LEFTEDGE_COL = 0xff005B4B;
	public static final int GRASS_BORDER_COL = 0xff00ff00;
	
	public static final int STONE1_COL = 0xff607080;
	public static final int STONE2_COL = 0xff808080;
	public static final int STONE_RIGHTEDGE_COL = 0xff707070;
	public static final int STONE_LEFTEDGE_COL = 0xff606060;
	public static final int STONE_BORDER_COL = 0xffaaaaaa;
	
	public static final int SKULL1_COL = 0xffA58600;
	public static final int SKULL2_COL = 0xffFFB800;
	public static final int SKULL_RIGHTEDGE_COL = 0xffFF5E00;
	public static final int SKULL_LEFTEDGE_COL = 0xffFF9600;
	public static final int SKULL_BORDER_COL = 0xffff0000;
	
	public static final int WOOD1_COL = 0xff675E00;
	public static final int WOOD2_COL = 0xff677404;
	public static final int WOOD_RIGHTEDGE_COL = 0xff674500;
	public static final int WOOD_LEFTEDGE_COL = 0xff904500;
	public static final int WOOD_BORDER_COL = 0xff4B4E04;
	
	public static Tile grass_classic_1 = new GrassTile(Sprite.grass_classic_1);
	public static Tile grass_classic_2 = new GrassTile(Sprite.grass_classic_2);
	public static Tile grass_edge_left = new GrassTile(Sprite.grass_edge_left);
	public static Tile grass_edge_right = new GrassTile(Sprite.grass_edge_right);
	public static Tile grass_border = new GrassTile(Sprite.grass_border);
	
	public static Tile stone_classic_1 = new StoneTile(Sprite.stone_classic_1);
	public static Tile stone_classic_2 = new StoneTile(Sprite.stone_classic_2);
	public static Tile stone_edge_left = new StoneTile(Sprite.stone_edge_left);
	public static Tile stone_edge_right = new StoneTile(Sprite.stone_edge_right);
	public static Tile stone_border = new StoneTile(Sprite.stone_border);
	
	public static Tile skull_classic_1 = new ScullTile(Sprite.scull_classic_1);
	public static Tile skull_classic_2 = new ScullTile(Sprite.scull_classic_2);
	public static Tile skull_edge_left = new ScullTile(Sprite.scull_edge_left);
	public static Tile skull_edge_right = new ScullTile(Sprite.scull_edge_right);
	public static Tile skull_border = new ScullTile(Sprite.scull_border);
	
	public static Tile wood_classic_1 = new WoodTile(Sprite.wood_classic_1);
	public static Tile wood_classic_2 = new WoodTile(Sprite.wood_classic_2);
	public static Tile wood_edge_left = new WoodTile(Sprite.wood_edge_left);
	public static Tile wood_edge_right = new WoodTile(Sprite.wood_edge_right);
	public static Tile wood_border = new WoodTile(Sprite.wood_border);
	
	public static Tile voidTile = new voidTile(Sprite.voidSprite);
	public static Tile air = new voidTile(Sprite.backgroundAir);
	
	protected Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderSprite(x, y, sprite);
	}
	
	public boolean solid() {
		return true;
	}
	
}
