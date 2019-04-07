/**
 * Created by 1 on 28/05/2017.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter removedBlocks;

    /**
     * Removes blocks.
     * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
     * of the number of blocks that remain.
     * @param game - gets game.
     * @param removedBlocks - gets removedBlocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.removedBlocks = removedBlocks;
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
            this.removedBlocks.decrease(1);
            hitter.removeFromGame(this.game);
        }
    }
}