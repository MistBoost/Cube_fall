package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import main.Game;
import main.GameState;
import play.HUD;

public class GameoverMenu {
	
	public LinkedList<MenuButton> menuButtons;
	private Font font;
	private int rC,gC,bC, bDiff;

	public GameoverMenu(Graphics g) {
		//color
		rC = 0; gC = 255; bC = 0;
		bDiff = 2;
		font = new Font("Arial", Font.PLAIN, 40);
		menuButtons = new LinkedList<MenuButton>();
		menuButtons.add(new MenuButton("Again", Color.green, g));
		menuButtons.add(new MenuButton("Return", Color.cyan, g));
		for (int i = 1; i <= menuButtons.size(); i++) {
			menuButtons.get(i - 1).setY((Game.HEIGHT - 500) / (menuButtons.size() + 1) * i);
		}
	}
	
	public void tick() {
		
		for (int i = 1; i <= menuButtons.size(); i++) {
			menuButtons.get(i - 1).setY(120 + (Game.HEIGHT - 200) / (menuButtons.size() + 1) * i);
		}
		
		changeColors();		
		
		font = new Font("Arial", Font.PLAIN, 40);
		for (int i = 0; i < menuButtons.size(); i++) {
			menuButtons.get(i).tick();
		}
		if (menuButtons.get(0).getClicked()) {
			Game.gameState = GameState.Play;
			Game.firstPlayRun = true;
		}
		if (menuButtons.get(1).getClicked()) {
			Game.gameState = GameState.Menu;
			Game.firstMenuRun = true;
		}
	}
	
	public void render(Graphics g) {	
		
		//game over text
		g.setColor(new Color(rC,gC,bC));
		g.setFont(font);
		g.drawString("Game over!", 45, 70);
		
		//score text
		g.setColor(new Color(rC,gC,255 - bC));
		g.setFont(new Font("Arial", Font.PLAIN, 25));
		g.drawString("Score: " + HUD.score, 78, 167);
		
		//buttons
		for (int i = 0; i < menuButtons.size(); i++) {
			menuButtons.get(i).render(g);
		}
	}
	
	private void changeColors() {
		if (bC + bDiff > 255) {
			bC = 255;
		} else if (bC + bDiff < 0) {
			bC = 0;
		} else {
			bC += bDiff;
		}
		if ((bC <= 0) || (bC >= 255)) {
			bDiff = -bDiff;
		}
	}
}
