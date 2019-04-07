/**
 * Created by 1 on 28/05/2017.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     *  The building of ScoreTrackingListener.
     * @param scoreCounter - gets scoreCounter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - gets block.
     * @param hitter - gets ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(100);
        }
    }
}
