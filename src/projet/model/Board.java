package projet.model;

import oop.lib.Display;
import projet.model.spacecharacters.Enemy;
import projet.model.spacecharacters.Player;

/**
 * @author David Baschung & Chaimaa Khald
 *
 * Model class for new Levels. Each instance must implement call callValidateLevel at the end of the constructor, with all items as parameters.
 */
public abstract class Board extends Display {
    private Player player;
    private Enemy[] enemies;
    private Shot[] shots; //TODO lists and player not useful...
    private float width;
    private float height;

    public Board(Player player, Enemy[] enemies, Shot[] shots, float width, float height) {
		super(0,0);
		this.player = player;
        this.enemies = enemies;
        this.shots = shots;
        this.width = width;
        this.height = height;
    }

    /**
     *  callValidateLevel must be implemented at the end of the constructor (--> abstract)
	 *  (only enemies passed currently)
      */
    protected abstract void callValidateLevel(Enemy... e);

    protected void validateLevel(Enemy... e) {
        // (only 1 player)
		int oCount = 0;
		for (int i=0; i<enemies.length && oCount<e.length; i++) {
			if (enemies[i]==null) {
				enemies[i] = e[oCount];
				oCount++;
			}
		}
    }

	@Override
	public int hashCode() {
		return "Board".hashCode();
	}

    @Override
    public void finalize() {
    	player = null;
    	enemies = null;
	}


	@Override
	public void paint(Display display) {
		int x = 1/0;
	} //TODO

////TODO
//	public void setRealDimensions(int realWidth, int realHeight) {
//		WorldGraphics.calculateRealHeight()
//	}
}
