package JumpPrince.graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	public int width, height;
	public int[] pixels;
	
	public static SpriteSheet tile_sheet = new SpriteSheet("/textures/spritesheets/tilesheet.png");
	public static SpriteSheet player_sheet = new SpriteSheet("/textures/spritesheets/playersheet.png");
	
	private SpriteSheet(String path) {
		try {
			System.out.print("loading " + path + " ... ");
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
			System.out.println("succeeded!");
		} catch (Exception e) {
			System.out.println("failed!");
			e.printStackTrace();
		}
	}
}

