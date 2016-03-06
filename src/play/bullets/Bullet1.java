package play.bullets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import play.GameObject;
import play.Handler;
import play.ID;

public class Bullet1 extends GameObject {
	private Handler handler;

	public Bullet1(float x, float y, int mx, int my, ID id, Handler handler) {
		super(x, y, id, 0, 10);
		velX = 5;
		velY = 5;
		int vx = (int) (mx - this.x);
		int vy = (int) (my - this.y);
		float c = (float) Math.sqrt(vx*vx + vy*vy);
		angle = (float) Math.asin(vy/c);
		if (vx < 0) {
			angle = (float) -(angle - Math.PI);
		}
		this.handler = handler;
	}

	public void tick() {
		x += Math.cos(angle) * velX;
		y += Math.sin(angle) * velY;
		for (int i = 0; i < Handler.object.size(); i++) {
			if (Handler.object.get(i).getID() == ID.Enemy) {
				if ((x >= Handler.object.get(i).getX()) && x <= (Handler.object.get(i).getX() + Handler.object.get(i).getWidth())) {
					if ((y >= Handler.object.get(i).getY()) && y <= (Handler.object.get(i).getY() + Handler.object.get(i).getHeight())) {
						Handler.object.get(i).takeDamage(1);
						handler.removeObject(this);
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.ORANGE);
	    Rectangle rect = new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.rotate(angle + Math.PI/2, (int)x + WIDTH / 2, (int)y + HEIGHT / 2);
	    g2d.draw(rect);
	    g2d.fill(rect);
	    g2d.rotate(-angle);
	    g2d.dispose();
	}

}
