import java.util.ArrayList;
import biuoop.DrawSurface;
/**
 *@author Shoham Hadad <shoham200296@gmail.com>
 * Id 207595620
 *@since 2017-05-01
 *class name- CollisionInfo.
 */
public class SpriteCollection {
    private java.util.ArrayList<Sprite> spriteList;
    /**
     *get sprite collection.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }
    /**
     * add a new sprite to the list.
     * @param s - Sprite
     */
    public void addSprite(Sprite s) {
        if (s != null) {
            this.spriteList.add(s);
        }
    }
    /**
     * The function removes an sprite from the list.
     * @param s - kind of sprite.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     * @param dt - gets double dt.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).timePassed(dt);
        }
    }
    /**
     * call drawOn(d) on all sprites.
     * @param d - hhh
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).drawOn(d);
        }
    }
}