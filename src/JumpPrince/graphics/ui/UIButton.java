package JumpPrince.graphics.ui;

import java.awt.Color;
import java.awt.Graphics;

import JumpPrince.Game;

public class UIButton extends UIComponent{

	private JPEvent evt;
	private UILabel label;
	private boolean entered = false;
	private boolean onButton = false;
	private boolean pressed = false;
	
	public UIButton(int x, int y, int width, int height, int buttonColor, String labelText, int textColor, JPEvent evt) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = new Color(buttonColor);
		this.evt = evt;
		label = new UILabel(labelText, x, y, "SansSerif", 20, textColor);
	}
	
	public boolean mouseInButton() {
		return (Game.mouse.x >= x && Game.mouse.x <= x + width &&  Game.mouse.y > y && Game.mouse.y < y + height);
	}
	
	public void update() {
		onButton = false;
		if(mouseInButton()) {
			onButton = true;
			if(!entered) {
				evt.onMouseEnter();
				entered = true;
			}
			if(Game.mouse.clicked){
				evt.onMouseClick();
			}
			if(Game.mouse.pressed && !pressed){
				evt.onMousePressed();
				pressed = true;
			}
			if(Game.mouse.released) {
				evt.onMouseReleased();
				pressed = false;
			}
		}else if(!mouseInButton() && entered) {
			entered = false;
			evt.onMouseExit();
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		label.render(g, height / 2, height / 2);
	}
	
	public void setColor(int color) {
		this.color = new Color(color);
	}
	
	public void setLabel(String text) {
		label.setLabel(text);
	}
	
	
}
