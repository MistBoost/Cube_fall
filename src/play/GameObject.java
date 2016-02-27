package play;

import java.awt.Graphics;

public abstract class GameObject {

	protected int health, WIDTH, HEIGHT;
	protected float x, y;
	protected float angle;
	protected ID id;
	protected float velX, velY;
	
	public GameObject(float x2, float y2, ID id, int WIDTH, int HEIGHT) {
		this.x = x2;
		this.y = y2;
		this.id = id;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.health = 0;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public ID getID() {
		return id;
	}
	public int getWidth() {
		return WIDTH;
	}
	public int getHeight() {
		return HEIGHT;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public void takeDamage(int damage) {
		this.health -= damage;
	}
}
