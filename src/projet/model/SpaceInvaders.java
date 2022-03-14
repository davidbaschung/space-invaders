package projet.model;

import oop.lib.Animation;
import projet.controller.Controller;
import projet.model.levels.Level1;
import projet.model.spacecharacters.Enemy;
import projet.model.spacecharacters.Player;
import projet.model.spacecharacters.enemies.Alien;
import projet.model.spacecharacters.enemies.Crab;
import projet.model.spacecharacters.enemies.Squid;
import projet.model.spacecharacters.enemies.UFO;
import projet.utils.ItemList;
import projet.view.WorldGraphics;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ConcurrentModificationException;

import static java.lang.Thread.sleep;
import static projet.Settings.MAX_FPS;
import static projet.Settings.MAX_SPRITES;

/**
 * @author David Baschung & Chaimaa Khald
 *
 * Class defining a world where Characters and items can be run. Item must be spawned in a new Level class.
 */


public class SpaceInvaders extends Animation{

	public Player player;
    public static Enemy[] enemies;
    private static Shot[] bullets;
    private long deltaTime;
    private float width;
    private float height;
    private Board board;
	private long lastFrameCounter;
	private boolean run=true;

	private SpaceInvaders() {}

    protected SpaceInvaders(int level){
		try {
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enemies = new Enemy[MAX_SPRITES];
        bullets = new Shot[MAX_SPRITES];

        String[] imageNames = {Player.class.getName(),Alien.class.getName(),Crab.class.getName(),Squid.class.getName(),UFO.class.getName(),Shot.class.getName()};
        for (String s : imageNames)
            WorldGraphics.declareImageName(s);

        try {
            WorldGraphics.loadGraphicItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
		JFrame j = new JFrame();
		j.setVisible(true);

        player = new Player("jack");
        width = WorldGraphics.getWorldViewport().getMinWorldWidth();
        height = WorldGraphics.getWorldViewport().getRealHeight();
		board = new Level1(player,enemies,bullets,width,height);
		WorldGraphics.launchGraphics();
		try {
			sleep(500); // TODO depending on invokelater
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WorldGraphics.getWorldGraphics().getWGFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		WorldGraphics.getWorldGraphics().getWGFrame().setVisible(true);
		new Controller(this);
		runWorld();
    }

    public static void createShot(boolean up, float x, float y) {
		Shot s = new Shot(up,x,y);
		for (int i=0; i<bullets.length; i++) {
			Shot b = bullets[i];
			if (b==null) {
				bullets[i] = s;
				break;
			}
		}
		WorldGraphics.getWorldGraphics().add(s.getSprite());
	}

    @Override
	protected void init() {
    	super.init();

	}

    private void runWorld() {
    	int fpsTimeCounter=0;
    	float minCount = 1000f/MAX_FPS;
    	long start=0;

    	while (run) {
			if (fpsTimeCounter>=minCount) {
				start = System.currentTimeMillis();
				update();
				fpsTimeCounter = 0;

			} else {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			long end = System.currentTimeMillis();
			deltaTime = end - start;
			fpsTimeCounter += deltaTime;
		}
    }

    private void update() {
        player.move(deltaTime);
        Group.move(enemies, deltaTime);
        Group.shoot(enemies);
        resolveShotCollisions();
    }

	private void resolveShotCollisions() {
		for (int i=0; i<bullets.length; i++) {
			Shot bullet = bullets[i];
			if (bullet != null) {
				float worldHeight = WorldGraphics.getWorldViewport().getMinWorldHeight();
				if (bullet.y< -worldHeight/10 || bullet.y> worldHeight+worldHeight/10) {
					try {
						WorldGraphics.getWorldGraphics().remove(bullet.getSprite());
						ItemList.remove(bullets, bullet);
					} catch (ItemList.EmptyArrayException e) {
						e.printStackTrace();
					}
				} else {
					bullet.move(deltaTime);
					if (bullet.up) {
						for (int j = 0; j < enemies.length; j++) {
							Enemy enemy = enemies[j];
							if (enemy != null) {
								if (enemy.checkCollision(bullet.getBoundingBox())) {
									System.out.println("an enemy was shot");
									try {
										WorldGraphics.getWorldGraphics().remove(bullet.getSprite());
										ItemList.remove(bullets, bullet);
										WorldGraphics.getWorldGraphics().remove(enemy.getSprite());
										ItemList.remove(enemies, enemy);
									} catch (ItemList.EmptyArrayException | ConcurrentModificationException e) {
										e.printStackTrace();
									}
									if (ItemList.isEmpty(enemies)) {
										run = false;
										System.out.println("YOU WON !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
									}
								}
							} else break;
						}
					} else {
						if (player.checkCollision(bullets[i].getBoundingBox())) {
							System.out.println("# # # YOU HAVE BEEN SHOT AND DIED # # #aint");
							System.exit(0);
						}
					}
				}
			} else break;
		}
	}

	private void endWorld() {

    }

}
