package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Random;

import main.Game;
import play.GameObject;
import play.ID;

public class MenuCube extends GameObject {
	
	private float angle;
	private int velAngle;
	private Color color;
	private float linePos, modifier;
	
	public MenuCube() {
		super(0, -20, ID.Enemy, 16, 16);
		Random r = new Random();
		x = r.nextInt(Game.WIDTH - 23 - 5) + 5;
		angle = (float) (r.nextFloat()*2*Math.PI);
		linePos = 0;
		velAngle = r.nextInt(91)+45;
		modifier = 0.4f;
		velY = 1;
		color = new Color(r.nextInt(2)*255, r.nextInt(2)*255, r.nextInt(2)*255);
		if (color == new Color(0,0,0)) color = Color.WHITE;
	}
	public void tick() {
		angle += Math.PI/velAngle;
		y += (int) velY;
		linePos += modifier;
		if ((linePos >= WIDTH) || (linePos <= 0)) {
			modifier = -modifier;
		}
	}
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
	    g2d.setColor(color);
	    Rectangle rect = new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.rotate(angle, (int)x + WIDTH / 2, (int)y + HEIGHT / 2);
	    g2d.draw(rect);
	    g2d.drawLine((int) (x + linePos), (int) (y + linePos), (int) (x +WIDTH - linePos), (int) (y + HEIGHT - linePos));
	    g2d.drawLine((int) (x + linePos), (int) (y + HEIGHT - linePos), (int) (x +WIDTH - linePos), (int) (y + linePos));
	    g2d.rotate(-angle);
	    g2d.dispose();
	}

}
