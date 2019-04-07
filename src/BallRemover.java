/**
 * Created by 1 on 28/05/2017.
 */
public class BallRemover implements HitListener {

        private GameLevel game;
        private Counter remainingBall;

    /**
     * The building responsible for removing a ball from the game.
     * @param game - gets game.
     * @param removedBall - Getting the number of hits whit the ball.
     */
        public BallRemover(GameLevel game, Counter removedBall) {
            this.game = game;
            this.remainingBall = removedBall;
        }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * @param beingHit - Getting the hit block
     * @param hitter - Getting the ball hit
     */
        public void hitEvent(Block beingHit, Ball hitter) {
            hitter.removeHitListener(this);
            this.game.removeSprite(hitter);
            this.remainingBall.decrease(1);
        }

}
