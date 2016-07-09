package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import main.Game;
import main.GameState;
import main.Mouse;

public class PlayMenu {
	
	public LinkedList<MenuButton> menuButtons;

	public PlayMenu(Graphics g) {
		menuButtons = new LinkedList<MenuButton>();
		menuButtons.add(new MenuButton("Back", Color.green, g));
		menuButtons.add(new MenuButton("Exit", Color.red, g));
		for (int i = 1; i <= menuButtons.size(); i++) {
			menuButtons.get(i - 1).setY((Game.HEIGHT - 83) / (menuButtons.size() + 1) * i);
		}
	}
	
	public void tick() {
		for (int i = 0; i < menuButtons.size(); i++) {
			menuButtons.get(i).tick();
		}
		if (menuButtons.get(0).getClicked()) {
			Game.gameState = GameState.Play;
		}
		if (menuButtons.get(1).getClicked()) {
			Game.gameState = GameState.Menu;
			Game.firstMenuRun = true;
			Mouse.leftButton = false;
		}
	}
	
	public void render(Graphics g) {		
		for (int i = 0; i < menuButtons.size(); i++) {
			menuButtons.get(i).render(g);
		}
	}
}
