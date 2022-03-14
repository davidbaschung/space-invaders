package projet.model.levels;

import oop.lib.Display;
import projet.model.Board;
import projet.model.Shot;
import projet.model.spacecharacters.Enemy;
import projet.model.spacecharacters.Player;
import projet.model.spacecharacters.enemies.Alien;
import projet.model.spacecharacters.enemies.Crab;
import projet.model.spacecharacters.enemies.Squid;
import projet.model.spacecharacters.enemies.UFO;

/**
 * @author David Baschung & Chaimaa Khald
 *
 * test Board
 */
public class Level1 extends Board {

    public Level1(Player player, Enemy[] enemies, Shot[] shots, float width, float height) {
        super(player,enemies,shots,width,height);
        player.setPosition(50,50);
        Enemy enemy1 = new Alien();
        enemy1.setPosition(20,15);
        Enemy enemy2 = new Crab();
        enemy2.setPosition(70,35);
        Enemy enemy3 = new Squid();
        enemy3.setPosition(80,25);
        Enemy enemy4 = new UFO();
        enemy4.setPosition(20,5);
        Enemy enemy5 = new UFO();
        enemy5.setPosition(40,5);
        Enemy enemy6 = new UFO();
        enemy6.setPosition(85,5);


        callValidateLevel(enemy1,enemy2,enemy3,enemy4,enemy5,enemy6);
    }

    protected void callValidateLevel(Enemy... e) {
        validateLevel(e);
    }

}
