package JumpPrince.entity;

import JumpPrince.graphics.Screen;
import JumpPrince.graphics.Sprite;
import JumpPrince.level.Level;

public abstract class Entity {
	
	public double x, y;
	protected Sprite sprite;
	protected Level level;
	protected boolean isRemoved = false;
	
	protected Entity(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	protected Entity(double x, double y, Sprite sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void remove() {
		isRemoved = true;
	}
	
	public boolean isRemoved() {
		return isRemoved;
	}
	
	protected void update() {
		
	}
	
	protected void render(Screen screen) {
		
	}
	
	protected void init(Level level) {
		this.level = level;
	}
}

