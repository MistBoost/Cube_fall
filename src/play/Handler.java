package play;

import java.awt.Graphics;
import java.util.LinkedList;

import main.Game;

public class Handler {

	public static LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			if ((object.get(i).y > Game.HEIGHT - 30) || (object.get(i).y < -30) || (object.get(i).x < 0) || (object.get(i).x > Game.WIDTH)) removeObject(object.get(i));
			object.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			object.get(i).render(g);
		}
	}
	
	public void addObject(GameObject object) {
		Handler.object.add(object);
	}
	public void removeObject(GameObject object) {
		Handler.object.remove(object);
	}
	
}
