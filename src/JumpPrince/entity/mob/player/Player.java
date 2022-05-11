package JumpPrince.entity.mob.player;

import JumpPrince.entity.mob.Mob;
import JumpPrince.entity.particle.Particle;
import JumpPrince.graphics.Screen;
import JumpPrince.graphics.Sprite;
import JumpPrince.input.Keyboard;

public class Player extends Mob{
	
	public Player(double x, double y, Keyboard input) {
		super(x, y, Sprite.player_static_right);
		this.input = input;
		direction = Direction.NONE;
	}
	
	protected void move(double xm, double ym) {	
		xm = Math.ceil(xm);
		ym = Math.ceil(ym);
		while(ym != 0) {
			if(!collision(0, val(ym)))
				y += val(ym);
			else if(val(ym) > 0){
				new Particle(level, (int)x, (int)y + 32, 50, Sprite.basic_particle);
				break;
			}
			ym += valInvert(ym); 
		}
		
		while(xm != 0) {
			if(!collision(val(xm) ,0))
				x += val(xm);
			else
				break;
			xm += valInvert(xm);
		}
	}
	
	private void selectSprite() {
		animSprite++;
		if(animSprite > 100000) animSprite = 0;
		if(direction == Direction.LEFT) {
			sprite = Sprite.player_static_left;
			if(walking) {
				if(animSprite % 42 <= 7)
					sprite = Sprite.player_run_left1;
				else if(animSprite % 42 <= 14)
					sprite = Sprite.player_run_left2;
				else if(animSprite % 42 <= 21)
					sprite = Sprite.player_run_left3;
				else if(animSprite % 42 <= 28)
					sprite = Sprite.player_run_left4;
				else if(animSprite % 42 <= 35)
					sprite = Sprite.player_run_left5;
				else if(animSprite % 42 <= 42)
					sprite = Sprite.player_run_left6;
			}
		}
		if(direction == Direction.RIGHT) {
			sprite = Sprite.player_static_right;
			if(walking) {
				if(animSprite % 42 <= 7)
					sprite = Sprite.player_run_right1;
				else if(animSprite % 42 <= 14)
					sprite = Sprite.player_run_right2;
				else if(animSprite % 42 <= 21)
					sprite = Sprite.player_run_right3;
				else if(animSprite % 42 <= 28)
					sprite = Sprite.player_run_right4;
				else if(animSprite % 42 <= 35)
					sprite = Sprite.player_run_right5;
				else if(animSprite % 42 <= 42)
					sprite = Sprite.player_run_right6;
			}
		}
		if(jumping && input.spacePressed && direction == Direction.RIGHT)
			sprite = Sprite.player_prejump_right;
		
		if(jumping && input.spacePressed && direction == Direction.LEFT)
			sprite = Sprite.player_prejump_left;
		
		if(jumping && !input.spacePressed && xm > 0) {
			if(ym >= 0)
				sprite = Sprite.player_jump_right_down;
			else
				sprite = Sprite.player_jump_right_up;
					
		}
		if(jumping && !input.spacePressed && xm < 0) {
			if(ym >= 0)
				sprite = Sprite.player_jump_left_down;
			else
				sprite = Sprite.player_jump_left_up;
		}
		if(jumping && !input.spacePressed && xm == 0 && ym > 0) {
			sprite = Sprite.player_fall_straight;
		}
		if(falling)
			sprite = Sprite.player_fall_straight;
	}
	
	public void update() {
		xm = 0.0; 
		ym = 0.0;
		walking = false;
		onGround = collision(0, 1);
		
		updateJumping();
		
		if(onGround) {
			yFallChange = 0;
			if(input.left) {
				xm = -walkSpeed;
				direction = Direction.LEFT;
			}
			if(input.right) {
				xm = walkSpeed;
				direction = Direction.RIGHT;
			}
		}else if(!onGround && !jumping && !falling){
			falling = true;
		}
		
		if(falling) {
			fall();
		}
		
		if(xm != 0 && !jumping) {
			walking = true;
		    move(xm, 0);
		}
	}
	
	public void render(Screen screen) {
		selectSprite();
		screen.renderPlayer((int)x - 32, (int)y - 32, sprite);
	}
	
}

