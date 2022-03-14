package projet.model.spacecharacters.enemies;

import projet.model.spacecharacters.Enemy;

import static projet.Settings.UFO_LIFE;
/**
 * @author David Baschung & Chaimaa Khald
 */
public class UFO extends Enemy {

    public UFO() {  super(UFO_LIFE);    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    @Override
    public int hashCode() {
        return  "UFO".hashCode();
    }

    @Override
    public UFO clone() {
        return new UFO();
    }

}
