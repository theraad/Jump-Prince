package JumpPrince.level;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import JumpPrince.entity.Entity;
import JumpPrince.entity.mob.player.Player;
import JumpPrince.entity.particle.Particle;
import JumpPrince.graphics.Screen;
import JumpPrince.tiles.Tile;


public class Level {
	
	private int[] levelPixels;
	public int width, height;
	public static Level level = new Level("/levels/level/level.png");
	public double gravity = 0.25;
	
	private List<Player> players = new ArrayList<Player>();
	private List<Particle> particles = new ArrayList<Particle>();
	
	public Player getClientPlayer() {
		return players.get(0);
	}
	
	public void add(Entity e) {
		if(e instanceof Player)
			players.add((Player)e);
		if(e instanceof Particle)
			particles.add((Particle)e);
	}
	
	public Level(String path) {
		try {
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			levelPixels = new int[width * height];
			image.getRGB(0, 0, width, height, levelPixels, 0, width);
		} catch(Exception e) {
			System.out.println("Failed to load level!");
			e.printStackTrace();
		}
	}
	
	public void updateEntities() {
		for(int i = 0; i < players.size(); i++)
			players.get(i).update();
		for(int i = 0; i < particles.size(); i++)
			particles.get(i).update();
	}
	
	public void removeEntities() {
		for(int i = 0; i < particles.size(); i++)
			if(particles.get(i).isRemoved())
				particles.remove(i);
	}
	
	public void update() {
		removeEntities();
		updateEntities();
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(-((screen.width - level.width * Tile.TILE_SIZE) / 2), ((yScroll / screen.height) * screen.height));
		int x0 = ((width- screen.width) / 2) / Tile.TILE_SIZE;
		int x1 = (screen.width / Tile.TILE_SIZE);
		int y0 = ((yScroll / screen.height) * screen.height) / Tile.TILE_SIZE;
		int y1 = y0 + (screen.height / Tile.TILE_SIZE);
		
		for(int y = y0; y <= y1; y++) {
			for(int x = x0; x <= x1; x++) {
				Tile t = getTile(x, y);
				t.render(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, screen);
			}
		}
		for(int i = 0; i < players.size(); i++)
			players.get(i).render(screen);
		for(int i = 0; i < particles.size(); i++)
			particles.get(i).render(screen);
	}
	
	public Tile getTile(int x, int y) {
		if(x >= width || y >= height || x < 0 || y < 0) return Tile.voidTile;
		if(levelPixels[x + y * width] == Tile.STONE_BORDER_COL) return Tile.stone_border;
		if(levelPixels[x + y * width] == Tile.STONE_LEFTEDGE_COL) return Tile.stone_edge_left;
		if(levelPixels[x + y * width] == Tile.STONE_RIGHTEDGE_COL) return Tile.stone_edge_right;
		if(levelPixels[x + y * width] == Tile.STONE1_COL) return Tile.stone_classic_1;
		if(levelPixels[x + y * width] == Tile.STONE2_COL) return Tile.stone_classic_2;
		if(levelPixels[x + y * width] == Tile.GRASS_BORDER_COL) return Tile.grass_border; 
		if(levelPixels[x + y * width] == Tile.GRASS2_COL) return Tile.grass_classic_2;
		if(levelPixels[x + y * width] == Tile.GRASS1_COL) return Tile.grass_classic_1;
		if(levelPixels[x + y * width] == Tile.GRASS_LEFTEDGE_COL) return Tile.grass_edge_left;
		if(levelPixels[x + y * width] == Tile.GRASS_RIGHTEDGE_COL) return Tile.grass_edge_right;
		if(levelPixels[x + y * width] == Tile.SKULL_BORDER_COL) return Tile.skull_border;
		if(levelPixels[x + y * width] == Tile.SKULL2_COL) return Tile.skull_classic_2;
		if(levelPixels[x + y * width] == Tile.SKULL1_COL) return Tile.skull_classic_1;
		if(levelPixels[x + y * width] == Tile.SKULL_LEFTEDGE_COL) return Tile.skull_edge_left;
		if(levelPixels[x + y * width] == Tile.SKULL_RIGHTEDGE_COL) return Tile.skull_edge_right;
		if(levelPixels[x + y * width] == Tile.WOOD_BORDER_COL) return Tile.wood_border;
		if(levelPixels[x + y * width] == Tile.WOOD2_COL) return Tile.wood_classic_2;
		if(levelPixels[x + y * width] == Tile.WOOD1_COL) return Tile.wood_classic_1;
		if(levelPixels[x + y * width] == Tile.WOOD_LEFTEDGE_COL) return Tile.wood_edge_left;
		if(levelPixels[x + y * width] == Tile.WOOD_RIGHTEDGE_COL) return Tile.wood_edge_right;
		if(levelPixels[x + y * width] == 0xff2A3E7F) return Tile.air;
		return Tile.voidTile;
	}

	public void reset() {
		players.clear();
		particles.clear();
	}
	
}
