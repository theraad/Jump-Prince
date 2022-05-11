package JumpPrince.entity.mob;

import JumpPrince.entity.Entity;
import JumpPrince.graphics.Screen;
import JumpPrince.graphics.Sprite;
import JumpPrince.input.Keyboard;
import JumpPrince.level.Level;
import JumpPrince.tiles.Tile;

public class Mob extends Entity{
	
	protected Keyboard input;
	protected Direction direction;
	protected double xm = 0, ym = 0;
	protected int animSprite = 0;
	protected boolean walking = false;
	protected boolean jumping = false;
	protected boolean falling = false;
	protected boolean onGround;
	protected double walkSpeed = 5.0;
	protected static final int MOB_WEIGHT = 80;
	public double t = 0.0;
	
	public double yFallChange = 0; 
	public double xVelocity = 0, yVelocity = 0, xVelAbsolute = 0.0, yVelAbsolute = 5.5; 
	
	protected Mob(double x, double y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	protected Mob(double x, double y) {
		super(x, y);
	}
	
	public static enum Direction{
		NONE ,UP, DOWN, LEFT, RIGHT;
	};
	
	public boolean isJumping() {
		return jumping;
	}
	
	public boolean isFalling() {
		return falling;
	}
	
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
	protected void fall() {
		if(yFallChange == 0) {
			yFallChange = 1;
			t = 1.0;
		}
		else
			yFallChange = level.gravity*t*t;
			
		t += 0.25;
		
		if(yFallChange > 40)
			yFallChange = 40;
		
		if(collision(0, yFallChange)) {
			falling = false;
		}
		
		move(0, yFallChange);
	}
	
	public boolean collision(double xa, double ya) {
		int tileSize = Tile.TILE_SIZE;
		
		for(int corner = 0; corner < 4; corner++) {
			double xt = ((x + xa) - corner % 2 * (tileSize - 7) + 28) / tileSize;
			double yt = ((y + ya) - corner / 2 * (tileSize - 6) + 31) / tileSize;
			
			if(level.getTile((int)xt, (int)yt).solid()) return true;
		}
		
		if(level.getTile((int)(x + xa + 16) / tileSize, (int)(y + ya) / tileSize).solid()) return true;
		return false;
	}
	
	protected void move(double xm, double ym) {	
		xm = Math.ceil(xm);
		ym = Math.ceil(ym);
		while(ym != 0) {
			if(!collision(0, val(ym)))
				y += val(ym);
			ym += valInvert(ym);
		}
		
		while(xm != 0) {
			if(!collision(val(xm), 0))
				x += val(xm);
			xm += valInvert(xm);
		}
	}
	
	protected void updateJumping() {
		if(input.spacePressed && onGround) {
			if(yVelAbsolute < 25.0)
				yVelAbsolute += 0.3;
			if((input.left || input.right) && xVelAbsolute < 11.0)
				xVelAbsolute += yVelAbsolute*0.02;
	
			if(direction == Direction.RIGHT)
				xVelocity = xVelAbsolute;
			else
				xVelocity = -xVelAbsolute;
			yVelocity = -yVelAbsolute;
			t = 1.0;
			jumping = true;
		}else if(jumping){
			t += 0.25;
			xm = xVelocity;
			ym = yVelocity + level.gravity*t*t;
			ym = Math.ceil(ym);
			xm = Math.ceil(xm);
			if(ym > 40)
				ym = 40;
			if(collision(0, ym)) {
				if(ym < 0) {
					yVelocity /= 2;
				}
				else {
					xVelAbsolute = 0;
					yVelAbsolute = 5.5;
					jumping = false;
				}
			}
			if(collision(xm, 0)) {
				xVelocity = (xVelocity * -1) / 2;
			}
		}
		move(xm, ym);
	}
	
	protected int val(double value) {
		if(value < 0) return -1;
		return 1;
	}
	
	protected int valInvert(double value) {
		if(value > 0) return -1;
		return 1;
	}
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		
	}
	
	
}
