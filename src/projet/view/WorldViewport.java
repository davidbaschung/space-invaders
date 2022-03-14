package projet.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author David Baschung & Chaimaa Khald
 *
 * class for JFrame and view management. Defines the dimensions and the relationship between the world and the frame.
 */
public class WorldViewport {

	private int realWidth, realHeight;
	private float minWorldWidth, minWorldHeight;
	private Dimension screen;

	float worldToFrameRatio;

	public WorldViewport(float minWorldWidth, float minWorldHeight) {
		this.minWorldWidth = minWorldWidth;
		this.minWorldHeight = minWorldHeight;

//		Dimension frame = worldGraphics.getWGFrame().getSize();

	}

	public float getMinWorldWidth() {
		return minWorldWidth;
	}

	public float getMinWorldHeight() {
		return minWorldHeight;
	}

	public float getWorldToFrameRatio() {
		return worldToFrameRatio;
	}

	public int getRealWidth() {  return realWidth;	}

	public int getRealHeight() {  return realHeight;  }
}
