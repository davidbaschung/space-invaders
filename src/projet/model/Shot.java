package projet.model;

import projet.view.GraphicItem;
import projet.view.Sprite;
import projet.view.WorldGraphics;

import java.awt.*;

/**
 * @author David Baschung & Chaimaa Khald
 *
 * Shots against an Enemy or a Player
 */
public class Shot implements GraphicItem, MoveableItem {
	private Sprite sprite;
	float x, y;
	boolean up;

    public Shot(boolean up, float x, float y) {
        this.up = up;
	    this.x = x; this.y = y;
        sprite = WorldGraphics.createSprite(getClass().getName(),x,y);
    }

    @Override
    public void move(long deltaTime) { //TODO pass float directly
        float deltaRatio = (float) 20f/1000;
        if (up) y -= deltaRatio*deltaTime;
        else  y += deltaRatio*deltaTime;
        updateSprite();
    }

    @Override
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

    @Override
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    protected Sprite getSprite() { return sprite; }

    @Override
    public void updateSprite() {
        sprite.setCoordinates(x,y);
    }


    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode()) + "[x:" + x + ",y:" + y + "]";
    }

    @Override
    public int hashCode() {
        return "Shot".hashCode();
    }

    @Override
    public void finalize() {
    	sprite = null;
	}
}
