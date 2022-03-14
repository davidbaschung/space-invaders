package projet.view;

import oop.lib.Animation;
import oop.lib.Paintable;
import projet.utils.KeyValue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static projet.Settings.*;
/**
 * @author David Baschung & Chaimaa Khald
 *
 * class with static goal, accessible from anywhere. It manages view ,and lists all the Sprites to display.
 */
    public final class WorldGraphics extends Animation{
        private static String[] filenamesToLoad = new String[MAX_IMAGES];
        private static KeyValue<String,Image> images = new KeyValue(MAX_IMAGES);
        private static Sprite[] spriteList = new Sprite[MAX_SPRITES];
        private static WorldViewport worldViewport;
        private static float imageResToFrameRatio=Toolkit.getDefaultToolkit().getScreenSize().height/IMAGES_RESOLUTION;;
        private static WorldGraphics worldGraphics;
        private static int realWidth;
        private static int realHeight;
        private static Dimension screen;
        private static float worldToFrameRatio;

        private WorldGraphics() {}

        public static float getWorldToFrameRatio() {
                return worldToFrameRatio;
            }

//    public boolean add(Paintable p) {
//        super.add(p);
//        return false;
//    }

    @Override
            protected void init() {
                super.init();

                int shortLength=0;
                for (int i=0; i<spriteList.length; i++) {
                    if (spriteList[i]!=null)
                        shortLength++;
                    else break;
                }
                for (int i=0; i<shortLength; i++) {
                    add(spriteList[i]);

                }

                realWidth = calculateRealWidth();
                realHeight = calculateRealHeight();
                JFrame frame = worldGraphics.getWGFrame();
        screen = new Dimension(realWidth,realHeight);
        frame.setPreferredSize(screen);
        frame.setSize(screen);
        frame.setResizable(false);
        float widthRatio = realWidth / 100;
        float heightRatio = realHeight / 56.25f;
        worldToFrameRatio = widthRatio < heightRatio ? widthRatio : heightRatio;

	}

    public static void loadGraphicItems() throws IOException{
        try {
            int shortLength = 0;
            for (int i=0; i<filenamesToLoad.length; i++) {
                if (filenamesToLoad[i]!=null)
                    shortLength++;
                else break;
            }
            for (int i=0; i<shortLength; i++) {
                String name = filenamesToLoad[i];
                String path = IMAGES_PATH + Integer.toString(IMAGES_RESOLUTION) + File.separator + name  + IMAGES_FORMAT;
                Image img = ImageIO.read(new File(path));
//                Image img = new ImageIcon(WorldGraphics.class.getResource(path)).getImage();    // note : feedback S7. Doesn't work for me.
                img = img.getScaledInstance( (int) imageResToFrameRatio*img.getWidth(null), (int) imageResToFrameRatio*img.getHeight(null),0);
                images.put(name,img);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }


        worldGraphics = new WorldGraphics();
        worldViewport = new WorldViewport(100f, 56.25f);
    }

    public static void launchGraphics() {
        worldGraphics.launch(true);
    }


    public static void declareImageName(String name) {
        name = name.substring(name.lastIndexOf('.', name.length() - IMAGES_FORMAT.length()) + 1, name.length());
        for (int i=0; i<filenamesToLoad.length; i++) {
            if (filenamesToLoad[i]==null ) {
                filenamesToLoad[i] = name;
                break;
            }
        }

    }

    public static Sprite createSprite(String name, float x, float y) {
        name = name.substring(name.lastIndexOf('.', name.length() - IMAGES_FORMAT.length()) + 1, name.length());
        Image img = images.get(name);
        if (img == null)  System.out.println( "IMAGE HAS NOT BEEN DECLARED/LOADED FOR : "+name);
        float ratio = WorldGraphics.getWorldToFrameRatio();
        int xAdapted =  (int) (x*ratio-img.getWidth(null)/2);
        int yAdapted = (int) (y*ratio-img.getHeight(null)/2);
        Sprite spr = Sprite.createSprite(img,xAdapted,yAdapted);
        for (int i=0; i<spriteList.length; i++) {
            if (spriteList[i]==null ) {
                spriteList[i] = spr;
                break;
            }
        }

        return spr;
    }

    public static int calculateRealWidth() {
        JFrame frame = worldGraphics.getWGFrame();
        Dimension screenRealSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());
        int raisedTaskBarSize = scnMax.left + scnMax.right;
        int realWidth = (int) (screenRealSize.getWidth()) - raisedTaskBarSize;
        return realWidth;
    }

    public static int calculateRealHeight() {
        JFrame frame = worldGraphics.getWGFrame();
        Dimension screenRealSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());
        int wideTaskBarSize = scnMax.bottom + scnMax.top;
        int realHeight = (int) (screenRealSize.getHeight()) - wideTaskBarSize;
        return realHeight;
    }

    public Sprite[] getSpriteList() {  return spriteList;  }

    public static WorldGraphics getWorldGraphics() {
        return worldGraphics;
    }

    public static WorldViewport getWorldViewport() {
        return worldViewport;
    }

    public JFrame getWGFrame() {
        return getFrame();
    }

    public static int getRealWidth() {
        return realWidth;
    }

    public static int getRealHeight() {
        return realHeight;
    }

    public void finalize() { filenamesToLoad = null; images = null; spriteList = null;}
}
