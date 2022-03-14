package projet;
/**
 * @author David Baschung & Chaimaa Khald
 *
 * All main Settings are listed here.
 */
public class Settings {

    public static int MAX_IMAGES = 30;
    public static int MAX_SPRITES = 100;
    public static int IMAGES_RESOLUTION = 1080;
    public static String IMAGES_PATH = "src\\images\\";
    public static String IMAGES_FORMAT = ".png";

    public static int MAX_FPS = 60;
    public static long FRAMES_RATE;

	@Override
    public String toString() {
        return "Settings{}";
    }

    public static int  PLAYER_LIFE = 100;
    public static int  ALIEN_LIFE = 101;
    public static int  CRAB_LIFE = 102;
    public static int  SQUID_LIFE = 103;
    public static int  UFO_LIFE = 104;

}
