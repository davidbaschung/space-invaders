package projet.model.spacecharacters;

import projet.model.Shot;
import projet.model.SpaceInvaders;
import projet.model.spacecharacters.enemies.Squid;

import java.awt.*;

import static projet.Settings.PLAYER_LIFE;
import static projet.model.SpaceInvaders.enemies;

/**
 * @author David Baschung & Chaimaa Khald
 */
public class Player extends SpaceCharacter {
    private String NAME;
    private long lastShotTime = 0;
    private enum dir{STOP,LEFT,RIGHT}
    private dir direction;

    public Player(String name) {
        super(PLAYER_LIFE);
        this.NAME = name;
    }

	public void shoot() {
		 if (System.currentTimeMillis()- lastShotTime >= 500) {
		 	super.shoot(true);
			 lastShotTime = System.currentTimeMillis();
		 }
	}

    public void setName(String s) { this.NAME=s; } //TODO

    public String getName() { return NAME; }

    @Override
	public void move(long deltaTime) {
    	float deltaRatio = (float)deltaTime/1000;

    	float distance = 100/3*deltaRatio; // note : 3 seconds to travel panel width
		if (direction== dir.LEFT) {
			if (x>5)
				x -= distance;
		} else if (direction== dir.RIGHT) {
			if (x<100-5)
				x += distance;
		}
		super.move(deltaTime);
	}

	public void stop() {
    	direction = dir.STOP;
    }
	public void moveLeft() {
		direction = dir.LEFT;
    }
	public void moveRight() {
    	direction = dir.RIGHT;
    }


    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode()) + "[name:" + NAME + "]";
    }

    @Override
	public int hashCode() {
    	return "Player".hashCode() ^ NAME.hashCode();
	}

	public boolean equals(Player p) {
    	if (this.NAME.equals(p.NAME)) return true;
    	return false;
	}

	@Override
	public void finalize() {
    	NAME = null;
	}
}
