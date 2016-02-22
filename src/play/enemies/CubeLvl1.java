package play.enemies;

import java.awt.Color;
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

public class CubeLvl1 extends GameObject {
	private static BufferedImage image;
	
	public CubeLvl1() {
		super(0, 25, ID.Enemy, 16, 16);
		health = 1;
		Random r = new Random();
		x = r.nextInt(Game.WIDTH - 23);
		velY = 1;
		if (CubeLvl1.image == null) {
			File imageFile = new File("res/enemies.jpg");
	        try {
				CubeLvl1.image = ImageIO.read(imageFile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	public void tick() {
		y += (int) velY;
		if (y + HEIGHT > Game.HEIGHT - 71) {
			Player.setPHealth(Player.getPHealth() - 1); //damage
			Handler.object.remove(this);
		}
		if (health == 0) {
			Handler.object.remove(this);
			HUD.score += 1;
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.red);
		//g.drawRect((int)x, (int)y, WIDTH, HEIGHT);
		g.drawImage(CubeLvl1.image, (int)x, (int)y, (int)x + WIDTH, (int)y + HEIGHT, 0, 0, WIDTH, HEIGHT, null);
	}

}
