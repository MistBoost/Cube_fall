package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.MemoryImageSource;

public class Mouse implements MouseListener, MouseMotionListener {
	
	public static int mx,my;
	public static boolean leftButton, rightButton;
	
	public Mouse(Window window) {
		leftButton = false;
		rightButton = false;
		int[] pixels = new int[16 * 16];
		Image image = Toolkit.getDefaultToolkit().createImage(
		        new MemoryImageSource(16, 16, pixels, 0, 16));
		Cursor transparentCursor =
		        Toolkit.getDefaultToolkit().createCustomCursor
		             (image, new Point(0, 0), "invisibleCursor");
		window.getFrame().setCursor(transparentCursor);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if (leftButton) g.setColor(Color.red); else {
			g.setColor(Color.green);
		}
		g.drawLine(mx - 7, my - 7, mx + 7, my + 7);
		g.drawLine(mx - 7, my + 7, mx + 7, my - 7);
	}

	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) {
			leftButton = true;
		} else if (e.getButton() == 3) {
			rightButton = true;
		}
	}
	public void mouseReleased(MouseEvent e) {
		leftButton = false;
		rightButton = false;
	}
	public void mouseDragged(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}
	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}
	
}
