package play.weapons;

import java.awt.Graphics;

import play.Handler;
import play.ID;
import play.bullets.Bullet1;

public class BasicWeapon1 extends Weapon {
	
	public BasicWeapon1() {
		super(1, "Machine Gun", 175, -1, true);
		
	}
	
	public void leftShoot(Handler handler,int sx, int sy, int mx, int my, int WIDTH) {
		if (timer.isActive()) {
			
		} else {
			readyToShoot = true;
		}			
		if (readyToShoot) {
			readyToShoot = false;
			timer.start(fireDelay);
			handler.addObject(new Bullet1(sx, sy, mx, my, ID.Bullet1, handler));
			handler.addObject(new Bullet1(sx + WIDTH, sy, mx, my, ID.Bullet1, handler));
		}
	}

	public void render(Graphics g) {
		
	}

	public void tick() {
		timer.tick();
	}
	
}
