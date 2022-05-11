package JumpPrince.graphics;

public class Screen {

	public int width, height;
	public int[] pixels;
	int xOffset, yOffset;
	public final int ALPHA_COLOR = 0xffff00ff;
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < sprite.HEIGHT; y++) {
			int ya = y + yp;
			if(ya >= height || ya < 0)
				continue;
			for(int x = 0; x < sprite.WIDTH; x++) {
				int xa = x + xp;
				if(xa >= width || xa < 0)
					continue;
				int color = sprite.pixels[x + y * sprite.WIDTH];
				if(color != ALPHA_COLOR)
					pixels[xa + ya * width] = color;
				else
					pixels[xa + ya * width] = Sprite.AIR_COLOR;
			}
		}
	}
	
	public void renderSpriteSolid(int xp, int yp, Sprite sprite) {
		for(int y = 0; y < sprite.HEIGHT; y++) {
			int ya = y + yp;
			if(ya >= height || ya < 0)
				continue;
			for(int x = 0; x < sprite.WIDTH; x++) {
				int xa = x + xp;
				if(xa >= width || xa < 0)
					continue;
				int color = sprite.pixels[x + y * sprite.WIDTH];
				if(color != ALPHA_COLOR)
					pixels[xa + ya * width] = color;
			}
		}
	}
	
	public void renderPlayer(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < sprite.HEIGHT; y++) {
			int ya = y + yp;
			if(ya >= height || ya < 0)
				continue;
			for(int x = 0; x < sprite.WIDTH; x++) {
				int xa = x + xp;
				if(xa >= width || xa < 0)
					continue;
				int color = sprite.pixels[x + y * sprite.WIDTH];
				if(color != ALPHA_COLOR)
					pixels[xa + ya * width] = color;
			}
		}
	}
	
}

