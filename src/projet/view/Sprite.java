package projet.view;

import oop.lib.Paintable;
import oop.lib.Painting;

import java.awt.*;
/**
 * @author David Baschung & Chaimaa Khald
 *
 * a Sprite is a displayable Entity representing an image at a given position.
 */
public class Sprite implements Paintable {

    /**x and y-coordinate of the whole sprite (not texture region)*/
    private int x,y;
    private int X_MIN, X_MAX; //TODO max limits
    private Image image;

    private Sprite(Image image, int x, int y) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    @Override
    public void paint(Painting painting){
    	painting.drawImage(image,x,y);
    }



    protected static Sprite createSprite(Image image, int x, int y) {
        return new Sprite(image,x,y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth(){
        return image.getWidth(null);
    }

    public int getHeight(){
        return image.getHeight(null);
    }

    public void setCoordinates(float x, float y) {
		float ratio = WorldGraphics.getWorldToFrameRatio();
        this.x =  (int) (x*ratio-getWidth()/2);
        this.y = (int) (y*ratio-getHeight()/2);
    }

    public void setxRange(int xRange) {
		X_MIN = getWidth()/2;
		X_MAX = xRange-getWidth()/2;
	}

    public Image getImage() { return image; }


    @Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + "[image:" + image + ",x:" + x + ",y:" + y + "]";
	}
	@Override
	public int hashCode() {
		return super.hashCode() ^ x ^ y;
	}

	public boolean equals(Sprite s) {
    	if (s.x == this.x && s.y == this.y && s.image==this.image) return true;
    	return false;
	}
}
