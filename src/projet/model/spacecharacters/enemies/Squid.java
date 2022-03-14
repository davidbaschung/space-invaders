package projet.model.spacecharacters.enemies;

import projet.model.spacecharacters.Enemy;

import static projet.Settings.SQUID_LIFE;
/**
 * @author David Baschung & Chaimaa Khald
 */
public class Squid extends Enemy {

    public Squid() {
        super(SQUID_LIFE);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    @Override
    public int hashCode() {
        return  "Squid".hashCode();
    }

    @Override
    public Squid clone() {
        return new Squid();
    }

}
