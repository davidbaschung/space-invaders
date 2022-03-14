package projet.model.spacecharacters.enemies;

import projet.model.spacecharacters.Enemy;

import static projet.Settings.ALIEN_LIFE;
/**
 * @author David Baschung & Chaimaa Khald
 */
public class Alien extends Enemy {

    public Alien() {
        super(ALIEN_LIFE);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    @Override
    public int hashCode() {
        return  "Alien".hashCode();
    }

	@Override
	public Alien clone() {
    	return new Alien();
	}

}
