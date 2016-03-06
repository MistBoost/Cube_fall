package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

import menu.GameoverMenu;
import menu.Menu;
import menu.MenuSpawner;
import menu.PlayMenu;
import play.HUD;
import play.Handler;
import play.ID;
import play.Player;
import play.Spawner;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -1442798787354930462L;

	public static final int WIDTH = 300, HEIGHT = WIDTH * 2;
    public int frames;	
	
	private Handler handler;
	private HUD hud;
	private Spawner spawner;
	private Menu menu;
	private MenuSpawner menuSpawner;
	private GameoverMenu gameoverMenu;
	private PlayMenu playMenu;
	private Thread thread;
	private KeyManager keyManager;
	public Window window;
	private Mouse mouse;
	public static GameState gameState;
	
	private boolean running = false;
	public static boolean paused = false;
	public static boolean firstPlayMenuRun = false, firstMenuRun = true, firstPlayRun = true, firstGameoverMenuRun = true;
	
	public Game() {
		handler = new Handler();
		keyManager = new KeyManager();
		Game.gameState = GameState.Menu;
		window = new Window(WIDTH, HEIGHT, "Cube fall", this);
		mouse = new Mouse(window);
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		this.addKeyListener(keyManager);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		running = true;
		thread.start();
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;        
        frames = 0;
        long timer = System.currentTimeMillis();	
        while (running) {
        	long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1) {
            	tick();
                delta--;  
                if (running) render();
                frames++;
                if (System.currentTimeMillis() - timer > 1000) {
            		window.getFrame().setTitle("Cube fall | " + frames + "fps");
                    timer += 1000;
                    frames = 0;
                }
            }
        }
        stop();
	}

	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();		
		if (gameState == GameState.Menu) {
			if (firstMenuRun) {
				initMenu(g);
				firstMenuRun = false;
			}
			g.setColor(Color.black);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			handler.render(g);
			menu.render(g);
		} else if (gameState == GameState.Play) {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			handler.render(g);
			if (!firstPlayRun) hud.render(g);
		} else if (gameState == GameState.Gameover) {
			if (firstGameoverMenuRun) {
				initGameoverMenu(g);
				firstGameoverMenuRun = false;
			}
			g.setColor(Color.black);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			handler.render(g);
			gameoverMenu.render(g);
		} else if (gameState == GameState.PlayMenu) {
			if (firstPlayMenuRun) {
				initPlayMenu(g);
				firstPlayMenuRun = false;				
			}
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			handler.render(g);
			if (!firstPlayRun) hud.render(g);
			playMenu.render(g);
		}
		mouse.render(g);
		g.dispose();
		bs.show();
	}
	
	private void initMenu(Graphics g) {
		Handler.object.clear();
		menu = new Menu(g);
		menuSpawner = new MenuSpawner();
	}
	
	private void initGameoverMenu(Graphics g) {
		Handler.object.clear();
		gameoverMenu = new GameoverMenu(g);
		menuSpawner = new MenuSpawner();
	}
	
	private void initPlayMenu(Graphics g) {
		playMenu = new PlayMenu(g);
	}

	private void tick() {
		if (gameState == GameState.Menu) {
			if (!firstMenuRun) {
				menu.tick();
				menuSpawner.tick(handler);
				handler.tick();
			}
		} else if (gameState == GameState.Play) {
			if (firstPlayRun) {
				hud = new HUD();
				spawner = new Spawner();
				Handler.object.clear();
				handler.addObject(new Player(WIDTH / 2 - 16, HEIGHT - 103, ID.Player, handler, keyManager));
				for (int i = 0; i < Handler.object.size(); i++) {
					if (Handler.object.get(i).getID() == ID.Player) {
						this.addMouseListener((MouseListener) Handler.object.get(i));
						this.addMouseMotionListener((MouseMotionListener) Handler.object.get(i));
					}
				}
				firstPlayRun = false;
			}
			spawner.tick(handler);
			handler.tick();
			hud.tick();
			if (Player.getPHealth() <= 0) {
				Game.gameState = GameState.Gameover;
				firstGameoverMenuRun = true;
			}
		} else if (gameState == GameState.Gameover) {
			if (!firstGameoverMenuRun) {
				gameoverMenu.tick();
				menuSpawner.tick(handler);
				handler.tick();
			}
		} else if (gameState == GameState.PlayMenu) {
			if (!firstPlayMenuRun) {
				playMenu.tick();
			}
		}
	}

	public static void main(String[] args) {
		new Game();
	}

}
