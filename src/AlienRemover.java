/**
 * Created by 1 on 06/07/2017.
 */
public class AlienRemover implements HitListener {
        private GameLevel game;
        private Counter removedAliens;

        /**
         * Removes blocks.
         * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
         * of the number of blocks that remain.
         * @param game - gets game.
         * @param removedAliens - gets removedAliens.
         */
        public AlienRemover(GameLevel game, Counter removedAliens) {
            this.game = game;
            this.removedAliens = removedAliens;
        }
        /**
         * Blocks that are hit and reach 0 hit-points should be removed
         * from the game. Remember to remove this listener from the block
         * that is being removed from the game.
         * @param beingHit - gets block beingHit.
         * @param hitter - gets Ball hitter.
         */
        public void hitEvent(Block beingHit, Ball hitter) {
            if (beingHit.getHitPoints() == 0) {
                beingHit.removeFromGame(this.game);
                beingHit.removeHitListener(this);
                hitter.removeFromGame(this.game);
                this.removedAliens.decrease(1);

            }
        }

}
