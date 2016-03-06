package play.enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Game;
import play.GameObject;
import play.HUD;
import play.Handler;
import play.ID;
import play.Player;

public class CubeLvl3 extends GameObject {
	private static BufferedImage image;
	private int damage;
	
	public CubeLvl3() {
		super(0, 25, ID.Enemy, 16, 16);
		health = 5;
		damage = 3;
		Random r = new Random();
		x = r.nextInt(Game.WIDTH - 23);
		velY = 0.5f;
		if (CubeLvl3.image == null) {
			File imageFile = new File("res/enemies.jpg");
	        try {
				CubeLvl3.image = ImageIO.read(imageFile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	public void tick() {
		y += velY;
		if (y + HEIGHT > Game.HEIGHT - 71) {
			Player.setPHealth(Player.getPHealth() - damage); //damage
			Handler.object.remove(this);
		}
		if (health == 0) {
			Handler.object.remove(this);
			HUD.score += 2;
		}
	}
	public void render(Graphics g) {
		g.drawImage(CubeLvl3.image, (int)x, (int) y, (int)x + WIDTH, (int)y + HEIGHT, 32, 0, 48, HEIGHT, null);
	}

}
