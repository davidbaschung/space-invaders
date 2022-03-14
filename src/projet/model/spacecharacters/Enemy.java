package projet.model.spacecharacters;

import projet.utils.FloatList;

import java.awt.*;

/**
 * @author David Baschung & Chaimaa Khald
 */
public abstract class Enemy extends SpaceCharacter {


    static long lastmove=0;
    static long timeSpan=1000;
    private static int nextDirection=00;
    private static boolean skip;
    static boolean flag = false;


    protected Enemy(int life) {
        super(life);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    @Override
    public int hashCode() {
        return "enemy".hashCode();
    }
}
