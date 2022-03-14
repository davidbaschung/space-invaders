package projet.model.spacecharacters.enemies;

import projet.model.spacecharacters.Enemy;

import static projet.Settings.CRAB_LIFE;
/**
 * @author David Baschung & Chaimaa Khald
 */
public class Crab extends Enemy {

    public Crab() {
        super(CRAB_LIFE);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    @Override
    public int hashCode() {
        return  "Crab".hashCode();
    }

    @Override
    public Crab clone() {
        return new Crab();
    }

}
