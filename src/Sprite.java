import biuoop.DrawSurface;
/**
 *@author Shoham Hadad <shoham200296@gmail.com>
 * Id 207595620
 *@since 2017-05-08
 *class name- CollisionInfo.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d -drawsurface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     * @param dt - gets double dt.
     */
    void timePassed(double dt);
}