package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;

import main.Game;
import play.GameObject;
import play.ID;

public class MenuCircle extends GameObject {
	private Color color;
	private float parameter, modifier, sParameter, sModifier;
	
	public MenuCircle() {
		super(0, -20, ID.Enemy, 16, 16);
		Random r = new Random();
		velY = 1;
		modifier = 0.5f;
		sModifier = modifier;
		parameter = ((r.nextInt(5) + 1) * 4) + 8;
		sParameter = parameter;
		x = r.nextInt((int) (Game.WIDTH - 23 - sParameter)) + sParameter;
		color = new Color(r.nextInt(2)*255, r.nextInt(2)*255, r.nextInt(2)*255);
		if (color == new Color(0,0,0)) color = Color.WHITE;
	}
	public void tick() {
		y += velY;
		if (parameter >= 2*sParameter) {
			modifier = -sModifier;
		} else if (parameter <= sParameter / 2) {
			modifier = sModifier;
		}
		parameter += modifier;
		if (modifier > 0) modifier += 0.05; else modifier -= 0.3;
	}
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(color);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.rotate(angle, (int)x, (int)y);
	    
	    //RENDER SOMETHING
	    g2d.drawOval((int) (x-(parameter / 2)), (int)(y-(parameter / 2)), (int)parameter, (int)parameter);
	    g2d.drawOval((int) (x-(parameter / 4)), (int)(y-(parameter / 4)), (int)parameter/2, (int)parameter/2);
	    
	    g2d.rotate(-angle);
	    g2d.dispose();
	}

}
