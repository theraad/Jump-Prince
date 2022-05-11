package JumpPrince.graphics.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class UIPanel {

	private int x, y;
	private int width, height;
	private Color backgroundColor;	
	private boolean active = false;
	
	public UIPanel(int x, int y, int width, int height, int backgroundColor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.backgroundColor = new Color(backgroundColor);
	}
	
	public UIPanel(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		backgroundColor = new Color(-1);
	}
	
	private List<UIComponent> components = new ArrayList<UIComponent>();
	
	public void addComponent(UIComponent component) {
		components.add(component);
	}
	
	public void update() {
		for(UIComponent component : components) {
			component.update();
		}
	}
	
	public void render(Graphics g) {
		if(backgroundColor.getRGB() != -1) {
			g.setColor(backgroundColor);
			g.fillRect(x, y, width, height);
		}
		for(UIComponent component : components) {
			component.render(g);
		}
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void show() {
		active = true;
	}
	
	public void hide() {
		active = false;
	}
	
}
