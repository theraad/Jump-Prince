package JumpPrince.entity.particle;

import java.util.Random;

import JumpPrince.entity.Entity;
import JumpPrince.graphics.Screen;
import JumpPrince.graphics.Sprite;
import JumpPrince.level.Level;

public class Particle extends Entity{
		
		private int life;
		private double xChange, yChange, yy;
		private static Random r = new Random();
		
		public Particle(Level level, int x, int y, int amount, Sprite sprite) {
			super(x, y-1, sprite);
			init(level);
			xChange = r.nextGaussian();
			yChange = yy = Math.abs(r.nextGaussian()) * -1;
			life = 16 + r.nextInt(35);
			yy = (yChange / life) * 2;
			
			for(int i = 0; i < amount - 1; i++) {
				level.add(new Particle(x, y-1, sprite, level));
			}
			
			level.add(this);
		}
		
		private Particle(int x, int y, Sprite sprite, Level level) {
			super(x, y, sprite);
			init(level);
			life = 16 + r.nextInt(25);
			xChange = r.nextGaussian();
			yChange = yy = Math.abs(r.nextGaussian()) * -1;
			yy = (yChange / life) * 2;
		}
		
		public void update() {
			life--;
			if(life == 0)
				remove();
			x += xChange;
			y += yChange;
			yChange -= yy;
			xChange /= 1.15;
		}
		
		public void render(Screen screen) {
			screen.renderSprite((int)x, (int)y, sprite);
		}
		
}
