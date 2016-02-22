package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import main.Game;
import main.GameState;

public class PlayMenu {
	
	public boolean addedListeners, canRemoveListeners;
	public LinkedList<MenuButton> menuButtons;

	public PlayMenu(Graphics g) {
		addedListeners = false;
		canRemoveListeners = false;
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
			canRemoveListeners = true;
		}
	}
	
	public void render(Graphics g) {		
		for (int i = 0; i < menuButtons.size(); i++) {
			menuButtons.get(i).render(g);
		}
	}
}
