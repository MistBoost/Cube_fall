package play;

import java.awt.Graphics;
import java.util.LinkedList;

import main.Game;

public class Handler {

	public static LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject temp = object.get(i);
			if ((temp.y > Game.HEIGHT - 30) || (temp.y < -30) || (temp.x < 0) || (temp.x > Game.WIDTH)) removeObject(temp);
			temp.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject temp = object.get(i);
			temp.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		Handler.object.add(object);
	}
	public void removeObject(GameObject object) {
		Handler.object.remove(object);
	}
	
}
