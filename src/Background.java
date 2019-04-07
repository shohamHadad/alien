import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Created by 1 on 23/06/2017.
 */
public class Background implements Sprite {

    private Color color;
    private String levelName;

    /**
     * The building of the Background .
     * @param levelName - gets a level name.
     */
    public Background(String levelName) {
        this.levelName = levelName;
    }

    /**
     * draw the sprite to the screen.
     * @param d - gets a DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.fillRectangle(0, 20, 800, 580);
//        d.setColor(java.awt.Color.black);
//        d.drawText(600, 15, levelName, 15);
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt - gets double dt.
     */
    public void timePassed(double dt) {
    }
}
