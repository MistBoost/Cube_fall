package play;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.Game;
import main.GameState;
import main.KeyManager;
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
	private KeyManager keyManager;
	private static boolean invincible;
	
	private enum FightingState {
		Melle{},
		Ranged{}
	}
	private FightingState fightingState;
	
	public Player(int x, int y, ID id, Handler handler, KeyManager keyManager) {
		super(x, y, id, 32, 32);
		velX = 0;
		velY = 0;
		this.keyManager = keyManager;
		weapon = new BasicWeapon1();
		this.handler = handler;
		maxHealth = 10;
		health = maxHealth;
		shooting = false;
		fightingState = FightingState.Ranged;
		invincible = true;
	}
	
	public static void setPHealth(int hp) {
		if (!invincible) {
			health = hp;
		}
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
					if (fightingState == FightingState.Ranged) {
						weapon.leftShoot(handler, (int)this.x, (int)this.y, mx, my, WIDTH);
					}
				} else if (mouseButton == 3) {
					
				}
				
			}
		}
		if (fightingState == FightingState.Melle) {
			if (keyManager.keys[KeyEvent.VK_W]) {
				velY = -15;
			}
			if (keyManager.keys[KeyEvent.VK_S]) {
				velY = 15;
			}
			if (keyManager.keys[KeyEvent.VK_A]) {
				velX = -5;
			}
			if (keyManager.keys[KeyEvent.VK_D]) {
				velX = 5;
			}
		}
		
		// Y movement
		
			
		
		// X movement
		if ((this.getX() + velX >= 0) && (this.getX() + velX <= Game.WIDTH - this.WIDTH - 7)) {
			this.setX(this.getX() + velX);
		} else if (this.getX() + velX < 0) {
			this.setX(0);
		} else if (this.getX() + velX > Game.WIDTH - this.WIDTH - 7) {
			this.setX(Game.WIDTH - this.WIDTH - 7);
		}
		if (velX < 0) velX = velX + 1; 
			else if (velX > 0) velX = velX - 1;
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.drawRect((int)x, (int)y, WIDTH, HEIGHT);
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 3) {
			if (fightingState == FightingState.Ranged) {
				fightingState = FightingState.Melle;
			} else {
				fightingState = FightingState.Ranged;
			}
		}
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
