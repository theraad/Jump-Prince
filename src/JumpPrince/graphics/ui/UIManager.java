package JumpPrince.graphics.ui;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class UIManager {

	private List<UIPanel> panels = new ArrayList<UIPanel>();
	
	public void addPanel(UIPanel panel) {
		panels.add(panel);
	}
	
	public void update() {
		for(UIPanel panel : panels) {
			if(panel.isActive())
				panel.update();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < panels.size(); i++) {
			if(panels.get(i).isActive())
				panels.get(i).render(g);
		}
	}
	
	public UIManager() {
		
	}
	
}
