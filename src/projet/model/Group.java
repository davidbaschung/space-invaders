package projet.model;

import projet.model.spacecharacters.Enemy;
import projet.utils.FloatList;
import projet.utils.ItemList;
import projet.view.GraphicItem;
import projet.view.Sprite;

import java.awt.*;

import static projet.model.SpaceInvaders.enemies;

public class Group<T extends Enemy> implements MoveableItem, AttackerItem, HittableItem, GraphicItem {

	enum dir{STOP,LEFT,RIGHT,DOWNLEFT,DOWNRIGHT;}
	private static dir commonDirection= dir.LEFT;
	private static float speed = 100f/10;
	private static float downDistanceCounter =0;
	private static long lastShotTime;
	public Group() {
		super();
	}

	public static void shoot(Enemy[] enemies) {
		if (System.currentTimeMillis()-lastShotTime >= 500) {
			if (Math.round(Math.random())==1) {
				int size = 0;
				for (int i = 0; i < enemies.length; i++) {
					if (enemies[i] != null) {
						size++;
					} else break;
				}
				int position = (int) Math.round(Math.random()* (size-1));
				Enemy shooterEnemy = enemies[position];
				shooterEnemy.shoot(false);
				lastShotTime = System.currentTimeMillis();
			}
		}
	}
	@Override
	public void shoot(boolean up) { }

	@Override
	public boolean gotHit() {
		return false;
	}

	int getWidth(Enemy[] enemies) {
		return (int) getBoundingBox().getWidth();
		// note : enemies may have various sizes...
	}

	float getHeight(Enemy[] enemies) {
		return (int) getBoundingBox().getWidth();
	}

	void detectAndRemoveDeadEnemies(Enemy[] enemies) {

	}

	public static void move(Enemy[] enemies, long deltaTime) {


		long l = deltaTime-deltaTime+22; //TODO (fail avec seulement deltaTime)
		float deltaRatio = (float) l/1000;
		if (speed<100)
			speed += 0.1f;
		float distance = speed*deltaRatio;
//        System.out.println("distance 1 : "+distance+" deltaRatio : "+deltaRatio+" deltaTime:"+deltaTime);
		int size = ItemList.getSize(enemies);


		if (getMin(enemies)<=5 && commonDirection== dir.LEFT) {
			commonDirection = dir.DOWNRIGHT;
		} else if (getMax(enemies)>=95 && commonDirection== dir.RIGHT) {
			commonDirection = dir.DOWNLEFT;
			downDistanceCounter += distance;
		} else if (downDistanceCounter >=5f) {
			downDistanceCounter = 0;
			if (commonDirection== dir.DOWNRIGHT)
				commonDirection = dir.RIGHT;
			else if (commonDirection== dir.DOWNLEFT)
				commonDirection = dir.LEFT;
		}

		float dx=0, dy=0;
		int normSpeed = 5;
		if (commonDirection == dir.LEFT) {
			dx = -distance; dy = 0;
		} else if (commonDirection == dir.RIGHT) {
			dx = distance; dy = 0;
		} else if (commonDirection == dir.DOWNRIGHT) {
			dx = 0; dy = distance;
			downDistanceCounter += distance;
		} else if (commonDirection == dir.DOWNLEFT) {
			dx = 0; dy = distance;
			downDistanceCounter += distance;
		}
		for (int i=0; i<size; i++) {
			enemies[i].x += dx;
			enemies[i].y += dy;
		}

		for (int i=0; i<size; i++) {
			Enemy enemy = enemies[i];
			if (enemy!=null)
				enemy.updateSprite();
		}

	}

	@Override
	public void move(long deltaTime) { }
	@Override
	public void setPosition(float x, float y) {
		//TODO complete/unimplement
	}

	static float getMax(Enemy[] enemies) {
		int size = ItemList.getSize(enemies);
		float[] xPositions = new float[size];
		for (int i=0; i<size; i++) {
			xPositions[i] = enemies[i].x;
		}
		return FloatList.getMax(xPositions);
	}

	static float getMin(Enemy[] enemies) {
		int size = ItemList.getSize(enemies);
		float[] xPositions = new float[size];
		for (int i=0; i<size;  i++) {
			xPositions[i] = enemies[i].x;
		}
		return FloatList.getMin(xPositions);
	}

	@Override
	public Rectangle getBoundingBox() {
		return enemies[0].getBoundingBox();
	}

	@Override
	public void setSprite(Sprite sprite) {

	}

	@Override
	public void updateSprite() {

	}
}
