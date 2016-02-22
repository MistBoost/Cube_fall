package play;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.Game;
import main.GameState;
import play.weapons.BasicWeapon1;
import play.weapons.Weapon;

public class Player extends GameObject implements MouseListener, MouseMotionListener {
	private static int health;
	public static int maxHealth;
	private int mx,my;
	private Handler handler;
	private Color color;
	private boolean shooting;
	private int mouseButton;
	private Weapon weapon;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id, 32, 32);
		weapon = new BasicWeapon1();
		this.handler = handler;
		maxHealth = 10;
		health = maxHealth;
		shooting = false;
	}
	
	public static void setPHealth(int hp) {
		health = hp;
	}
	public static int getPHealth() {
		return health;
	}

	public void tick() {
		weapon.tick();
		int diff = 255 / Player.maxHealth * Player.getPHealth();
		if (diff > 255) diff = 255;
		if (diff < 40) diff = 0;
		color = new Color(255 - diff, 0 + diff, 0);
		if (shooting) {
			if (Game.gameState == GameState.Play) {
				if (mouseButton == 1) {
					weapon.leftShoot(handler, (int)this.x, (int)this.y, mx, my, WIDTH);
				} else if (mouseButton == 3) {
				}
				
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.drawRect((int)x, (int)y, WIDTH, HEIGHT);
		if (shooting) g.setColor(Color.red); else {
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
		if ((e.getX() >= 95 && e.getX() <= 190 && e.getY() >= 1 && e.getY() <= 41) && Game.gameState == GameState.Play) {
			if (Game.gameState != GameState.PlayMenu) {
				Game.firstPlayMenuRun = true;
				Game.gameState = GameState.PlayMenu;
			}
		} else {
			if (e.getButton() == 1) {
				mx = e.getX();
				my = e.getY();
				shooting = true;
				mouseButton = 1;
			} else if (e.getButton() == 3) {
				mx = e.getX();
				my = e.getY();
				shooting = true;
				mouseButton = 3;
			}
		}
	}
	public void mouseReleased(MouseEvent e) {
		shooting = false;
	}
	public void mouseDragged(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		if (e.getX() >= 95 && e.getX() <= 190 && e.getY() >= 1 && e.getY() <= 41) {
			HUD.menuHovered = true;
		} else HUD.menuHovered = false;
	}
	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		if (e.getX() >= 95 && e.getX() <= 190 && e.getY() >= 1 && e.getY() <= 41) {
			HUD.menuHovered = true;
		} else HUD.menuHovered = false;
	}

}
