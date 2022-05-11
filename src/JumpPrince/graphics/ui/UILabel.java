package JumpPrince.graphics.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UILabel extends UIComponent{
	private String text;
	private int fontSize;
	private String font;
	
	public UILabel(String text, int x, int y, String font, int fontSize, int color) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.fontSize = fontSize;
		this.font = font;
		this.color = new Color(color);
	}
	
	public void setLabel(String text) {
		this.text = text;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g, int xOffset, int yOffset) {
		g.setFont(new Font(font, Font.PLAIN, fontSize));
		g.setColor(color);
		g.drawString(text, x + xOffset, y + yOffset + 7);
	}
}
