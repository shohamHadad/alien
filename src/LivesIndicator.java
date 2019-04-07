import biuoop.DrawSurface;

/**
 * Created by 1 on 29/05/2017.
 */
public class LivesIndicator implements Sprite {
    private Counter live;

    /**
     * The building of LivesIndicator.
     * @param live - gets number of live.
     */
    public LivesIndicator(Counter live) {
        this.live = live;
    }

    /**
     * draw the sprite to the screen.
     * @param d - drawsurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.black);
        d.drawText(200, 15, "Live: " + this.live.getValue(), 16);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt - gets double dt.
     */
    public void timePassed(double dt) {

    }

    /**
     * add the game.
     * @param g - gets game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
