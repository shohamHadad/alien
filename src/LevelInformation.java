import java.util.List;

/**
 * Created by 1 on 02/06/2017.
 */
public interface LevelInformation {
    /**
     * The function returns the number of balls.
     * @return - number of balls.
     */
    int numberOfBalls();
    /**
     * The initial velocity of each ball.
     * @return - velocity.
     */
    List<Velocity> initialBallVelocities();
    /**
     * The function returns the paddle speed.
     * @return - returns the paddle speed.
     */
    int paddleSpeed();
    /**
     * The function returns the paddle Width.
     * @return - returns the paddle Width.
     */
    int paddleWidth();
    /**
     * the level name will be displayed at the top of the screen.
     * @return -  the level name
     */
    String levelName();
    /**
     * Returns a sprite with the background of the level.
     * @return - Returns a sprite with the background of the level
     */
    Sprite getBackground();
    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return - block.
     */
    List<Block> blocks();
    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return - number Of Blocks To Remove
     */
    int numberOfBlocksToRemove();

}

