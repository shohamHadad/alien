import biuoop.DrawSurface;
/**
 *@author Shoham Hadad <shoham200296@gmail.com>
 * Id 207595620
 *@since 2017-05-30
 *class name- Animation .
 */
public interface Animation {
    /**
 * The function draws the screen.
 * @param d - A screen to draw.
 * @param dt - gets double dt.
 */
void doOneFrame(DrawSurface d, double dt);

    /**
     * A function that returns a boolean variable of true or false.
     * @return -  returns a boolean variable of true or false.
     */
    boolean shouldStop();
}