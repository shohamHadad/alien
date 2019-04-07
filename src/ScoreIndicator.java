import biuoop.DrawSurface;

/**
 * Created by 1 on 29/05/2017.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     *  The building of ScoreIndicator.
     * @param score - gets score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    /**
     * draw the sprite to the screen.
     * @param d -drawsurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.black);
        d.drawText(400, 15, "score: " + this.score.getValue(), 16);
    }
    /**
     * notify the sprite that time has passed.
     * @param dt - gets double dt.
     */
    public void timePassed(double dt) {

    }

    /**
     * add the gameLevel.
     * @param g - gets gameLevel
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
