package play;

import java.util.Random;

import main.Timer;
import play.enemies.CubeLvl1;
import play.enemies.CubeLvl2;

public class Spawner {
	private long spawnrate;
	private boolean running;
	private Timer timer;
	private Random r;
	
	public Spawner() {
		r = new Random();
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
			if (r.nextInt(4) == 3) {
				handler.addObject(new CubeLvl2());
			} else {
				handler.addObject(new CubeLvl1());
			}
		}
	}
	
}
