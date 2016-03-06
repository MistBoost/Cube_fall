package menu;

import java.util.Random;

import play.Handler;

public class MenuSpawner {
	
	private long start, now, spawnrate, temp;
	private boolean running;
	private Random r;
	private int ranInt;
	
	public MenuSpawner() {
		r = new Random();
		start();
	}
	
	public void start() {
		start = System.currentTimeMillis();
		temp = 0;
		running = true;
	}
	
	public void stop() {
		running = false;
	}
	
	public void tick(Handler handler) {
		if (running) {
			now = System.currentTimeMillis();
			spawnrate = (now - start) / 100;
			if (spawnrate != temp) {
				ranInt = r.nextInt(2);
				if (ranInt > 0) {
					handler.addObject(new MenuCube());
				} else {
					handler.addObject(new MenuCircle());
				}
				
			}
			temp = spawnrate;
		}
	}
	
}
