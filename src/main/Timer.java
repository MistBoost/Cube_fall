package main;

public class Timer {
	
	private float timeAtStart;
	private float timeToCount;
	private float timeNow;
	private boolean counting;
	
	public Timer() {
		counting = false;
	}
	
	public void start(float timeToCount) {
		timeAtStart = System.nanoTime() / 1000000;
		this.timeToCount = timeToCount;
		counting = true;
	}
	
	public void tick() {
		if (counting) {
			timeNow = System.nanoTime() / 1000000;
			if (timeNow - timeAtStart >= timeToCount) {
				counting = false;
			}		
		}
	}
	
	public boolean isActive() {
		return counting;
	}
	
}
