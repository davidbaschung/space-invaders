package projet.model.spacecharacters;

import projet.model.AttackerItem;
import projet.model.HittableItem;
import projet.model.MoveableItem;
import projet.model.SpaceInvaders;
import projet.view.GraphicItem;
import projet.view.Sprite;
import projet.view.WorldGraphics;

import java.awt.*;

/**
 * @author David Baschung & Chaimaa Khald
 *
 * Model class for Player and Enemies
 */

public abstract class SpaceCharacter implements GraphicItem, MoveableItem, HittableItem, AttackerItem {
    private Sprite sprite;
    int LIFE;
    public float x;
	public float y;


    protected SpaceCharacter(int life) {
        this(life,50,25);
    }
    protected SpaceCharacter(int life, int x, int y) {
    	this.LIFE = life;
    	this.x = x; this.y = y;
        sprite = WorldGraphics.createSprite(getClass().getName(),x,y);
    }

    public void move(long deltaTime) {
        updateSprite(); //TODO called by WG
    }

    public void shoot(boolean up) {
		SpaceInvaders.createShot(up, this.getX(), this.getY()-5); //TODO : pool unused bullets?
    }

	@Override
	public boolean gotHit() {
		System.out.println("HIT");
    	LIFE -= 20; //TODO just delete SpaceCharacter.
		return true;
	}

	@Override
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Sprite getSprite(){  return sprite;	}

	@Override
	public void updateSprite() { sprite.setCoordinates(x,y);}

    protected void decreaseLife(int delta) {
        LIFE -= delta;
    }

    protected void setLife(int x) { this.LIFE = x; }

	protected int getLife() {
		return LIFE;
	}

    public void setPosition(float x, float y) {
        this.x=x; this.y=y;
    }

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(sprite.getX(),sprite.getY(), sprite.getWidth(), sprite.getHeight());
	}

	public boolean checkCollision(Rectangle rectangle) {
		if (getBoundingBox().intersects(rectangle))
			return true;
		return false;
	}

	public float getX() {  return x;  }

    public float getY() {  return y;  }


	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + "[sprite:" + sprite + "," + x + "," + y + "]";
	}

	@Override
	public int hashCode() {
		return "SpaceCharacter".hashCode() ^ sprite.hashCode();
	}

	public boolean equals(SpaceCharacter s) {
    	if (sprite.equals(s) && s.LIFE==this.LIFE) return true;
    	return false;
	}

	@Override
	public void finalize() {
    	sprite = null;
	}

}
