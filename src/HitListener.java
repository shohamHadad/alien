/**
 * Created by 1 on 23/05/2017.
 */

public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - gets block.
     * @param hitter - gets ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}