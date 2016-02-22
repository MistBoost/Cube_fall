package play.weapons;

import java.awt.Graphics;

import main.Timer;
import play.Handler;

public abstract class Weapon {
	protected float damage, fireDelay;
	protected String name;
	protected int ammo;
	protected boolean readyToShoot;
	protected Timer timer;
	
	public Weapon(float damage, String name, float fireDelay, int ammo, boolean readyToShoot) {
		this.damage = damage;
		this.name = name;
		this.fireDelay = fireDelay;
		this.ammo = ammo;
		this.readyToShoot = readyToShoot;
		timer = new Timer();
	}
	
	public abstract void tick();
	public abstract void leftShoot(Handler handler,int sx, int sy, int mx, int my, int WIDTH);
	public abstract void render(Graphics g);
}
