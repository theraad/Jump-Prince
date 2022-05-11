package JumpPrince;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.util.EventListener;

import javax.swing.JFrame;

import JumpPrince.entity.mob.player.Player;
import JumpPrince.graphics.Screen;
import JumpPrince.graphics.ui.JPEvent;
import JumpPrince.graphics.ui.UIButton;
import JumpPrince.graphics.ui.UICallbackHelper;
import JumpPrince.graphics.ui.UIManager;
import JumpPrince.graphics.ui.UIPanel;
import JumpPrince.input.Keyboard;
import JumpPrince.input.Mouse;
import JumpPrince.level.Level;
import JumpPrince.sound.Sound;
import Jumprince.serialization.JPDatabase;
import Jumprince.serialization.JPField;
import Jumprince.serialization.JPObject;

public class Game extends Canvas implements Runnable, EventListener{
	private static final long serialVersionUID = 1L;
	private boolean running = false;
	private final double PLAYER_SPAWN_X = 1140.0;
	private final double PLAYER_SPAWN_Y = 21535.0;
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static int width;
	private static int height;
	private static final int SCALE = 1;
	private static final String TITLE = "Jump Prince";
	private static final int UPDATES_PER_SECOND_LIMIT = 60;
	private int fpsLimit = 50;
	
	private Thread thread;
	private JFrame frame;
	
	private Sound music;
	private Level level;
	private Player player;
	
	private Keyboard keyboard;
	public static Mouse mouse;
	
	private Screen screen;
	private BufferedImage image;
	private int[] pixels;
	
	private UIManager ui;
	
	public Game() {
		width = (int)screenSize.getWidth() / SCALE;
		height = (int)screenSize.getHeight() / SCALE;
		frame = new JFrame();
		createFrame();
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		screen = new Screen(width, height);
		keyboard = new Keyboard();
		mouse = new Mouse();
		addKeyListener(keyboard);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		level = Level.level;
		player = new Player(PLAYER_SPAWN_X, PLAYER_SPAWN_Y, keyboard);
		player.init(level);
		level.add(player);
		uiInit();
		if(new File("saves/JPSave.jpd").exists())
			loadGame();
		else
			newGame();
		music = new Sound("/music/background.wav", true);
	}
	
	public void uiInit() {
		ui = new UIManager();
		
		UICallbackHelper cbhNewGame = new UICallbackHelper();
		UICallbackHelper cbhContinueGame = new UICallbackHelper();
		UICallbackHelper cbhSaveGame = new UICallbackHelper();
		UICallbackHelper cbhMusic = new UICallbackHelper();
		UICallbackHelper cbhQuit = new UICallbackHelper();
		
		UIPanel menu = new UIPanel(0, 0, screen.width, screen.height, 0x0);
		UIButton menuNewGameButton = new UIButton(screen.width/2 - 150, 235, 300, 65, 0xd1bb6b, "           New Game", 0xffffff, new JPEvent() {
			
			public void onMouseClick() {
				newGame();
				menu.hide();
			}

			public void onMouseEnter() {
				cbhNewGame.getButton().setColor(0xa89f4a);
			}

			public void onMouseExit() {
				cbhNewGame.getButton().setColor(0xd1bb6b);
			}

			public void onMousePressed() {
				cbhNewGame.getButton().setColor(0x827b37);
			}

			public void onMouseReleased() {
				cbhNewGame.getButton().setColor(0xa89f4a);
			}
			
		});
		cbhNewGame.setButton(menuNewGameButton);
		
		UIButton menuContinueButton = new UIButton(screen.width/2 - 150, 315, 300, 65, 0xd1bb6b, "                 Load", 0xffffff, new JPEvent() {
			
			public void onMouseClick() {
				loadGame();
				menu.hide();
			}

			public void onMouseEnter() {
				cbhContinueGame.getButton().setColor(0xa89f4a);
			}

			public void onMouseExit() {
				cbhContinueGame.getButton().setColor(0xd1bb6b);
			}

			public void onMousePressed() {
				cbhContinueGame.getButton().setColor(0x827b37);
			}

			public void onMouseReleased() {
				cbhContinueGame.getButton().setColor(0xa89f4a);
			}
			
		});
		cbhContinueGame.setButton(menuContinueButton);
		
		UIButton menuSaveButton = new UIButton(screen.width/2 - 150, 395, 300, 65, 0xd1bb6b, "                 Save", 0xffffff, new JPEvent() {
			
			public void onMouseClick() {
				saveGame();
				menu.hide();
			}

			public void onMouseEnter() {
				cbhSaveGame.getButton().setColor(0xa89f4a);
			}

			public void onMouseExit() {
				cbhSaveGame.getButton().setColor(0xd1bb6b);
			}

			public void onMousePressed() {
				cbhSaveGame.getButton().setColor(0x827b37);
			}

			public void onMouseReleased() {
				cbhSaveGame.getButton().setColor(0xa89f4a);
			}
			
		});
		cbhSaveGame.setButton(menuSaveButton);
		
		UIButton menuMusicButton = new UIButton(screen.width/2 - 150, 475, 300, 65, 0xd1bb6b, "             Music On", 0xffffff, new JPEvent() {
			
			public void onMouseClick() {
				if(music.isPlaying()) {
					music.stop();
					cbhMusic.getButton().setLabel("             Music Off");
				}else {
					music.start();
					cbhMusic.getButton().setLabel("             Music On");
				}
			}

			public void onMouseEnter() {
				cbhMusic.getButton().setColor(0xa89f4a);
			}

			public void onMouseExit() {
				cbhMusic.getButton().setColor(0xd1bb6b);
			}

			public void onMousePressed() {
				cbhMusic.getButton().setColor(0x827b37);
			}

			public void onMouseReleased() {
				cbhMusic.getButton().setColor(0xa89f4a);
			}
			
		});
		cbhMusic.setButton(menuMusicButton);
		
		UIButton menuQuitButton = new UIButton(screen.width - 120, screen.height - 60, 90, 40, 0xd1bb6b, " Quit", 0xffffff, new JPEvent() {
			public void onMouseClick() {
				System.exit(0);
			}
			
			public void onMouseEnter() {
				cbhQuit.getButton().setColor(0xa89f4a);
			}

			public void onMouseExit() {
				cbhQuit.getButton().setColor(0xd1bb6b);
			}

			public void onMousePressed() {
				cbhQuit.getButton().setColor(0x827b37);
			}

			public void onMouseReleased() {
				cbhQuit.getButton().setColor(0xa89f4a);
			}
		});
		
		cbhQuit.setButton(menuQuitButton);
		
		menu.addComponent(menuNewGameButton);
		menu.addComponent(menuContinueButton);
		menu.addComponent(menuSaveButton);
		menu.addComponent(menuMusicButton);
		menu.addComponent(menuQuitButton);
		
		UIPanel menuButtonPanel = new UIPanel(screen.width - 80, 0, 40, 40);
		UICallbackHelper cbhMenuBtn = new UICallbackHelper();
		UIButton menuBtn = new UIButton(screen.width - 120, 20, 90, 40, 0xd1bb6b, "Menu", 0xffffff, new JPEvent() {
			public void onMouseClick() {
				if(menu.isActive()) {
					menu.hide();
				}
				else {
					menu.show();
				}
			}
			
			public void onMouseEnter() {
				cbhMenuBtn.getButton().setColor(0xa89f4a);
			}

			public void onMouseExit() {
				cbhMenuBtn.getButton().setColor(0xd1bb6b);
			}

			public void onMousePressed() {
				cbhMenuBtn.getButton().setColor(0x827b37);
			}

			public void onMouseReleased() {
				cbhMenuBtn.getButton().setColor(0xa89f4a);
			}
		});
		cbhMenuBtn.setButton(menuBtn);
		menuButtonPanel.addComponent(menuBtn);
		ui.addPanel(menu);
		ui.addPanel(menuButtonPanel);
		menuButtonPanel.show();
		
	}
	
	public void saveGame() {
		JPDatabase save = new JPDatabase("JumpPrinceSave");
		JPObject playerData = new JPObject("playerData");
		playerData.add(JPField.Double("x", player.x));
		playerData.add(JPField.Double("y", player.y));
		playerData.add(JPField.Boolean("jumping", player.isJumping()));
		playerData.add(JPField.Boolean("falling", player.isFalling()));
		playerData.add(JPField.Double("t", player.t));
		playerData.add(JPField.Double("yFallChange", player.yFallChange));
		playerData.add(JPField.Double("xVelocity", player.xVelocity));
		playerData.add(JPField.Double("yVelocity", player.yVelocity));
		playerData.add(JPField.Double("xVelAboslute", player.xVelAbsolute));
		playerData.add(JPField.Double("yVelAbsolute", player.yVelAbsolute));
		save.add(playerData);
		save.serializeToFile("saves/JPSave.jpd");
	}
	
	public void newGame() {
		level.reset();
		player = new Player(PLAYER_SPAWN_X, PLAYER_SPAWN_Y, keyboard);
		player.init(level);
		level.add(player);
		saveGame();
	}
	
	public void loadGame() {
		JPDatabase load = JPDatabase.Deserialize("saves/JPSave.jpd");
		JPObject playerData = load.findObject("playerData");
		player.x = playerData.findField("x").getDouble();
		player.y = playerData.findField("y").getDouble();
		player.setJumping(playerData.findField("jumping").getBoolean());
		player.setFalling(playerData.findField("falling").getBoolean());
		player.t = playerData.findField("t").getDouble();
		player.yFallChange = playerData.findField("yFallChange").getDouble();
		player.xVelocity = playerData.findField("xVelocity").getDouble();
		player.yVelocity = playerData.findField("yVelocity").getDouble();
		player.xVelAbsolute = playerData.findField("xVelAboslute").getDouble();
		player.yVelAbsolute = playerData.findField("yVelAbsolute").getDouble();
	}
	
	public void createFrame() {
		Dimension size = new Dimension(width * SCALE, height * SCALE);
		setPreferredSize(size);	
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.add(this);
		frame.setTitle(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this, "Game");
		running = true;
		thread.start();
		music.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		requestFocus();
		long oldTimeUpdates = System.nanoTime(), currentTimeUpdates;
		long oldTimeFPS = System.nanoTime(), currentTimeFPS;
		double deltaUpdates = 0.0, deltaFPS = 0.0;
		while(running) {
			currentTimeUpdates = currentTimeFPS = System.nanoTime();
			deltaUpdates += (double)(currentTimeUpdates - oldTimeUpdates) / (1000000000 / UPDATES_PER_SECOND_LIMIT);
			deltaFPS += (double)(currentTimeFPS - oldTimeFPS) / (1000000000 / fpsLimit);
			oldTimeFPS = oldTimeUpdates = currentTimeUpdates;
			
			if(deltaUpdates >= 1.0) {
				update();
				deltaUpdates--;
			}	
			if(deltaFPS >= 1.0) {
				render();
				deltaFPS--;
			}
		}
		
		stop();
	}
	
	public void update() {
		keyboard.update();
		ui.update();
		level.update();
		mouse.resetState();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		screen.clear();
		level.render((int)player.x, (int)player.y, screen);
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		g.drawImage(image, 0, 0, width*SCALE, height*SCALE, null);
		ui.render(g); 
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();		
		game.start();
	}
}