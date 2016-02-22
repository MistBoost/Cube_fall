package menu;

import play.Handler;

public class MenuSpawner {
	
	private long start, now, spawnrate, temp;
	private boolean running = false;
	
	public MenuSpawner() {
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
				handler.addObject(new MenuCube());
			}
			temp = spawnrate;
		}
	}
	
}
