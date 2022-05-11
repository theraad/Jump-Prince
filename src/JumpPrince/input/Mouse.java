package JumpPrince.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener{

	public int x, y;
	public boolean clicked = false;
	public boolean pressed = false;
	public boolean released = false;
	
	public void resetState() {
		clicked = false;
		pressed = false;
		released = false;
	}
	
	public Mouse() {
		
	}
	
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		clicked = true;
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		pressed = true;
	}

	public void mouseReleased(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		released = true;
	}

	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

}