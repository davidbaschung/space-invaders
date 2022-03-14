package projet.model;

import java.awt.*;

public interface MoveableItem {
    void move(long deltaTime);
    void setPosition(float x, float y);
    Rectangle getBoundingBox();
}
