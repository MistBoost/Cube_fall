package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import main.Game;
import main.Mouse;

public class MenuButton {

	private int x,y,width,height;
	private String text;
	private Color color;
	private Font font;
	private FontMetrics metrics;
	private boolean clicked, hovered;
	
	public MenuButton(String text, Color color, Graphics g) {
		font = new Font("Arial", Font.PLAIN, 20);
		this.text = text;
		this.color = color;
		metrics = g.getFontMetrics(font);
		clicked = false;
		width = 135;
		height = metrics.getHeight();
		x = (Game.WIDTH / 2 - 5) - width / 2;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return this.y;
	}
	public boolean getClicked() {
		return clicked;
	}
	
	public void tick() {
		if ((Mouse.mx >= x) && (Mouse.mx <= x + width)) {
			if ((Mouse.my >= y) && (Mouse.my <= y + height)) {
				hovered = true;
			} else {
				hovered = false;
			}
		} else {
			hovered = false;
		}
		
		if (Mouse.leftButton) {
			if ((Mouse.mx >= x) && (Mouse.mx <= x + width)) {
				if ((Mouse.my >= y) && (Mouse.my <= y + height)) {
					clicked = true;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x + 1, y + 1, width - 1, height - 1);
		if (hovered) {
			g.setColor(Color.yellow);
		} else {
			g.setColor(color);
		}
		g.drawRect(x, y, width, height);
		g.setFont(font);
		g.drawString(text, x + ((width - metrics.stringWidth(text)) / 2), y + font.getSize());
	}
}
