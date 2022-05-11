package JumpPrince.graphics;

public class Sprite {

	public static final int AIR_COLOR = 0x6b93cf;
	public final int WIDTH, HEIGHT;
	public int[] pixels;
	
	//world sprites
	public static Sprite stone_classic_1 = new Sprite(0, 0, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite stone_classic_2 = new Sprite(1, 0, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite stone_edge_left = new Sprite(2, 0, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite stone_edge_right = new Sprite(3, 0, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite stone_border = new Sprite(4, 0, 64, 64, SpriteSheet.tile_sheet);
	
	public static Sprite grass_classic_1 = new Sprite(0, 1, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite grass_classic_2 = new Sprite(1, 1, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite grass_edge_left = new Sprite(2, 1, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite grass_edge_right = new Sprite(3, 1, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite grass_border = new Sprite(4, 1, 64, 64, SpriteSheet.tile_sheet);
	
	public static Sprite scull_classic_1 = new Sprite(0, 2, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite scull_classic_2 = new Sprite(1, 2, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite scull_edge_left = new Sprite(2, 2, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite scull_edge_right = new Sprite(3, 2, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite scull_border = new Sprite(4, 2, 64, 64, SpriteSheet.tile_sheet);
	
	public static Sprite wood_classic_1 = new Sprite(0, 3, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite wood_classic_2 = new Sprite(1, 3, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite wood_edge_left = new Sprite(2, 3, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite wood_edge_right = new Sprite(3, 3, 64, 64, SpriteSheet.tile_sheet);
	public static Sprite wood_border = new Sprite(4, 3, 64, 64, SpriteSheet.tile_sheet);
	
	public static Sprite voidSprite = new Sprite(64, 64, 0x000000);
	public static Sprite backgroundAir = new Sprite(64, 64, AIR_COLOR);
	
	//Player sprites
	public static Sprite player_static_right = new Sprite(0, 0, 64, 64, SpriteSheet.player_sheet);
	public static Sprite player_static_left = new Sprite(1, 0, 64, 64, SpriteSheet.player_sheet);
	
	public static Sprite player_run_right1 = new Sprite(0, 1, 64, 64, SpriteSheet.player_sheet);
	public static Sprite player_run_right2 = new Sprite(1, 1, 64, 64, SpriteSheet.player_sheet);
	public static Sprite player_run_right3 = new Sprite(2, 1, 64, 64, SpriteSheet.player_sheet);
	public static Sprite player_run_right4 = new Sprite(3, 1, 64, 64, SpriteSheet.player_sheet);
	public static Sprite player_run_right5 = new Sprite(4, 1, 64, 64, SpriteSheet.player_sheet);
	public static Sprite player_run_right6 = new Sprite(5, 1, 64, 64, SpriteSheet.player_sheet);
	
	public static Sprite player_run_left1 = new Sprite(5, 2, 64, 64, SpriteSheet.player_sheet);
	public static Sprite player_run_left2 = new Sprite(4, 2, 64, 64, SpriteSheet.player_sheet);
	public static Sprite player_run_left3 = new Sprite(3, 2, 64, 64, SpriteSheet.player_sheet);
	public static Sprite player_run_left4 = new Sprite(2, 2, 64, 64, SpriteSheet.player_sheet);
	public static Sprite player_run_left5 = new Sprite(1, 2, 64, 64, SpriteSheet.player_sheet);
	public static Sprite player_run_left6 = new Sprite(0, 2, 64, 64, SpriteSheet.player_sheet);
	
	public static Sprite player_prejump_right = new Sprite(0, 3, 64, 64, SpriteSheet.player_sheet);
	public static Sprite player_prejump_left = new Sprite(1, 3, 64, 64, SpriteSheet.player_sheet);
	
	public static Sprite player_jump_right_up = new Sprite(0, 4, 64, 64, SpriteSheet.player_sheet);
	public static Sprite player_jump_right_down = new Sprite(2, 4, 64, 64, SpriteSheet.player_sheet);
	public static Sprite player_jump_left_up = new Sprite(1, 4, 64, 64, SpriteSheet.player_sheet);
	public static Sprite player_jump_left_down = new Sprite(3, 4, 64, 64, SpriteSheet.player_sheet);
	
	public static Sprite player_fall_straight = new Sprite(0, 5, 64, 64, SpriteSheet.player_sheet);
	
	//Particle sprites:
	public static Sprite basic_particle = new Sprite(3, 3, 0x808080);
	
	private Sprite(int xOffset, int yOffset, int width, int height, SpriteSheet sheet){
		this.WIDTH = width;
		this.HEIGHT = height;
		pixels = new int[width * height];
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				pixels[x + y * width] = sheet.pixels[(x + xOffset * width) + (y + yOffset * height) * sheet.width];
			}
		}
	}


	public Sprite(int width, int height, int color) {
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[width * height];
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}
	
}

