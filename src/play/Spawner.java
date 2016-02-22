package play;

import main.Timer;
import play.enemies.CubeLvl1;

public class Spawner {
	private long spawnrate;
	private boolean running;
	private Timer timer;
	
	public Spawner() {
		spawnrate = 1000;
		timer = new Timer();
		start();
	}
	
	public void start() {
		running = true;
		timer.start(spawnrate);
	}
	
	public void stop() {
		running = false;
	}
	
	public void tick(Handler handler) {
		timer.tick();
		if (!timer.isActive() && running) {
			timer.start(spawnrate);
			handler.addObject(new CubeLvl1());
		}
	}
	
}
