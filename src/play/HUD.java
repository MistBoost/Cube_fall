package play;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;

public class HUD {
	
	private float hpBarWidth;
	private Color hpBarColor;
	public static int score;
	private Font font;
	public static boolean menuHovered;
	
	public HUD() {
		score = 0;
		font = new Font("Arial", Font.PLAIN, 14);
		menuHovered = false;
	}
	
	public void tick() {
		hpBarWidth = (120 * Player.getPHealth()) / Player.maxHealth;
		int diff = (210 * Player.getPHealth()) / Player.maxHealth;
		if (diff > 255) diff = 255;
		if (diff < 40) diff = 0;
		hpBarColor = new Color(255 - diff, 0 + diff, 0);
	}
	
	public void render(Graphics g) {
		
		//bottom container
		g.setColor(Color.blue);
		g.drawRect(0, 530, Game.WIDTH - 8, 40);
		g.setColor(Color.BLACK);
		g.fillRect(1, 531, Game.WIDTH - 9, 39);
		
		//top container
		g.setColor(Color.blue);
		g.drawRect(0, 1, Game.WIDTH - 8, 40);
		g.setColor(Color.BLACK);
		g.fillRect(1, 2, Game.WIDTH - 9, 39);
		g.setColor(Color.blue);
		g.drawRect(0, 1, 95, 40);
		
		//health bar
		if ((hpBarColor == null || hpBarWidth == 0) && !Game.firstPlayRun) {
			tick();
		}
		g.setColor(Color.BLUE);
		g.drawRect(6, 543, 121, 13);
		g.setColor(hpBarColor);
		g.fillRect(7, 544, (int)hpBarWidth, 12);
		

		g.setColor(Color.blue);
		//weapon bar
		g.drawRect(173, 536, 114, 28);
		//text score
		g.setFont(font);
		g.drawString("Score: " + score, 20, 25);
		
		//menu
		g.drawRect(95, 1, 95, 40);
		if (menuHovered) {
			g.setColor(Color.darkGray); 
			//g.drawRect(96, 2, 94, 38);
		} else {
			g.setColor(Color.blue);			
		}
		g.drawString("Menu", 124, 25);
	}
}
