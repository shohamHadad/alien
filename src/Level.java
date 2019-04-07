import java.util.List;

/**
 * Created by 1 on 23/06/2017.
 */
public class Level implements LevelInformation {

    private int numberOfBalls;
    private List<Velocity> ballVelocities;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * The building of the Level.
     */
    public Level() {
    }

    /**
     * The function returns the number of balls.
     * @return - number of balls.
     */
    public int numberOfBalls() {
        return 1;
    }
    /**
     * The initial velocity of each ball.
     * @return - velocity.
     */
    public List<Velocity> initialBallVelocities() {
        Velocity v = new Velocity(4, -50);
        List<Velocity> list = new java.util.ArrayList<Velocity>(0);
        list.add(v);
        return list;
    }
    /**
     * The function returns the paddle speed.
     * @return - returns the paddle speed.
     */
    public int paddleSpeed() {
        return 600;
    }

    /**
     * The function returns the paddle Width.
     * @return - returns the paddle Width.
     */
    public int paddleWidth() {
        return 100;
    }

    /**
     * the level name will be displayed at the top of the screen.
     * @return -  the level name
     */
    public String levelName() {
        return "Alien";
    }

    /**
     * Returns a sprite with the background of the level.
     * @return - Returns a sprite with the background of the level
     */
    public Sprite getBackground() {
        return new Background(this.levelName());
    }
    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return - block.
     */
    public List<Block> blocks() {
//        List<Block> list = new java.util.ArrayList<Block>(0);
//        int counterHit = 0;
//        for (int i = 10; i < 780; i += 52) {
//            Rectangle rect = new Rectangle(new Point(i, 250), 52, 25);
//            //Each row of blocks in a different color
//            if (i == 114 || (i == 166)) {
//                counterHit = 1;
//                Block block = new Block(rect, java.awt.Color.yellow, counterHit);
//                list.add(block);
//            }
//            if (i == 218 || (i == 270)) {
//                counterHit = 1;
//                Block block = new Block(rect, java.awt.Color.yellow, counterHit);
//                list.add(block);
//            }
//            if ((i == 322) || (i == 374) || (i == 426)) {
//                counterHit = 1;
//                Block block = new Block(rect, java.awt.Color.green, counterHit);
//                list.add(block);
//            }
//            // Block block = new Block(new Rectangle(new Point(385, 135), 30, 30), java.awt.Color.red, 1);
//        }
//        return list;
        return null;
    }
    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return - number Of Blocks To Remove
     */
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
