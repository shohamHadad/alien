import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import biuoop.KeyboardSensor;

/**
 * @author Shoham Hadad <shoham200296@gmail.com>
 *         Id 207595620
 * @since 2017-05-01
 * class name- CollisionInfo.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    // private GUI gui = new GUI("Game", 800, 600);
    private Counter removedBlocks;
    private Counter removedBall;
    private Counter currentScore;
    private Counter aliens1;
    private Counter live;
    // private AnimationRunner runner;
    private boolean running;
    private Paddle paddle;
    private LevelInformation level;
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private int blockToRemove;
    private GUI gui;
    private Aliens aliens;
    private java.util.List<Alien> list2;
    private int levelNum;
    private long time;

    /**
     * constructor - The game is built environment and sprites.
     * @param level        - gets level.
     * @param ar           gets AnimationRunner.
     * @param live         - gets live.
     * @param currentScore - get score.
     * @param gui          -  gets GUI.
     * @param aliens1 - counter of aliens.
     * @param levelNum  gets a number of level.
     */
    public GameLevel(LevelInformation level, AnimationRunner ar, Counter live, Counter currentScore,
                     GUI gui, Counter aliens1, int levelNum) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.removedBlocks = new Counter(0);
        this.removedBall = new Counter(0);
        this.aliens1 = aliens1;
        this.currentScore = currentScore;
        this.live = live;
        this.level = level;
        this.ar = ar;
        this.ks = gui.getKeyboardSensor();
        this.gui = gui;
        this.removedBlocks.increase(blockToRemove);
        this.levelNum = levelNum;
        this.time = System.currentTimeMillis();
    }

    /**
     * add the collidable to the environment.
     *
     * @param c - Kind of collidable.
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            this.environment.addCollidable(c);
        }
    }

    /**
     * The function removes an collidable from the list.
     *
     * @param c - kind of collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * add the sprite to the sprite.
     *
     * @param s - kind of sprite.
     */
    public void addSprite(Sprite s) {
        if (s != null) {
            this.sprites.addSprite(s);
        }
    }

    /**
     * The function removes an sprite from the list.
     *
     * @param s - kind of sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        this.addSprite(this.level.getBackground());
        BlockRemover blockRemover = new BlockRemover(this, this.removedBlocks);
        AlienRemover alienRemover = new AlienRemover(this, this.aliens1);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.currentScore);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.currentScore);
        LivesIndicator livesIndicator = new LivesIndicator(this.live);
        BallRemover ballRemover = new BallRemover(this, this.removedBall);
        scoreIndicator.addToGame(this);
        livesIndicator.addToGame(this);

//        java.util.List<Block> listOfBlock = this.level.blocks();
//        for (int i = 0; i < listOfBlock.size(); i++) {
//            listOfBlock.get(i).addToGame(this);
//            listOfBlock.get(i).addHitListener(blockRemover);
//            listOfBlock.get(i).addHitListener(scoreTrackingListener);
//            //removedBlocks.increase(1);
//        }

        this.aliens = new Aliens(1 * Math.pow(1.1, (this.levelNum - 1)));
        list2 = this.aliens.getListAlien();
        for (int i = 0; i < list2.size(); i++) {
            list2.get(i).addToGame(this);
            //block.addHitListener(new PrintingHitListener());
            list2.get(i).addHitListener(alienRemover);
            list2.get(i).addHitListener(scoreTrackingListener);
            scoreIndicator.addToGame(this);
            livesIndicator.addToGame(this);
            //aliens1.increase(1);
        }


        for (int i = 100; i < 250; i += 5) {
            for (int j = 480; j < 500; j += 5) {
                    Block block = new Block(new Rectangle(new Point(i, j), 5, 5), java.awt.Color.cyan);
                    block.addToGame(this);
                    block.addHitListener(blockRemover);
                    livesIndicator.addToGame(this);
                    removedBlocks.increase(1);
                }
            }
        for (int i = 325; i < 475; i += 5) {
            for (int j = 480; j < 500; j += 5) {
                    Block block = new Block(new Rectangle(new Point(i, j), 5, 5), java.awt.Color.cyan);
                    block.addToGame(this);
                    block.addHitListener(blockRemover);
                    livesIndicator.addToGame(this);
                    removedBlocks.increase(1);

            }
        }
        for (int i = 600; i < 750; i += 5) {
            for (int j = 480; j < 500; j += 5) {
                    Block block = new Block(new Rectangle(new Point(i, j), 5, 5), java.awt.Color.cyan);
                    block.addToGame(this);
                    block.addHitListener(blockRemover);
                    livesIndicator.addToGame(this);
                    removedBlocks.increase(1);
            }
        }
        //created the four blocks that are the limits of our game
        Block up = new Block(new Rectangle(new Point(0, 20), 800, 10), java.awt.Color.DARK_GRAY);
        up.addToGame(this);
        up.addHitListener(ballRemover);
        Block left = new Block(new Rectangle(new Point(0, 20), 10, 600), Color.gray);
        left.addToGame(this);
        Block right = new Block(new Rectangle(new Point(790, 20), 10, 600), Color.gray);
        right.addToGame(this);
        Block down = new Block(new Rectangle(new Point(0, 590), 800, 10), Color.DARK_GRAY);
        down.addToGame(this);
        down.addHitListener(ballRemover);
        java.util.List<Block> list = this.level.blocks();
        java.util.List<Block> list1 = new java.util.ArrayList<Block>();
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle(); // or a similar method
        this.ar.run(new CountdownAnimation(5, 3, this.sprites));
        this.running = true;
        this.ar.run(this);
        this.sprites.removeSprite(this.paddle);
        this.environment.removeCollidable(this.paddle);
    }

    /**
     * The function runs the game.
     */
    public void run() {
        while ((this.live.getValue() > 0) && (this.aliens1.getValue() > 0)) {
            this.playOneTurn();
        }
    }

    /**
     * The function returns a fact that animation must stop.
     * @return - The function returns a fact that animation must stop
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * The function draws the game.
     * @param d  - A screen to draw.
     * @param dt - gets double dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        java.util.Random rand = new java.util.Random();
        double shootingAlienX = -1;
        double shootingAlienY = -1;
        d.setColor(java.awt.Color.black);
        d.drawText(600, 15, "Level Number: " + this.levelNum, 15);
           int i = rand.nextInt(50);
            int shootingAlien = i;
        if (System.currentTimeMillis() - this.time >= 500) {
            if (this.list2.get(i).getHitPoints() != 0) {
                shootingAlienX = this.list2.get(i).getRectangle().getUpperLeft().getX();
                shootingAlienY = this.list2.get(i).getRectangle().getUpperLeft().getY();
                for (int j = 0; j < 50; j++) {
                    if (this.list2.get(j).getRectangle().getUpperLeft().getX() == shootingAlienX && this.list2.get(j)
                            .getRectangle().getUpperLeft().getY() > shootingAlienY) {
                        shootingAlien = j;
                    }
                }
            }
            list2.get(shootingAlien).shot(this.environment, this);
           this.time = System.currentTimeMillis();
        }
        // the logic from the previous playOneTurn method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        this.aliens.checkMove();
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if ((this.removedBlocks.getValue() <= 0)) {
            this.currentScore.increase(100);
            this.running = false;
        }
//        if (removedBall.getValue() == 0) {
//            this.live.decrease(1);
//            //this.playOneTurn();
//            this.running = false;
//        }
        if ((this.live.getValue() == 0) || this.aliens1.getValue() == 0) {
            this.running = false;
        }
        int[] arr = this.aliens.check();
        if (arr[2] == 0 && (list2.get(arr[1]).getRectangle().getUpperLeft().getY() + list2.get(arr[1]).getRectangle()
                .getHeight()) >= 500) {
            this.live.decrease(1);
            this.aliens.reset();
        }
        if (this.ks.isPressed("p")) {
            Animation pauseScreen = new PauseScreen(this.ks);
            Animation pauseScreenPressed = new KeyPressStoppableAnimation(this.ks, this.ks.SPACE_KEY, pauseScreen);
            this.ar.run(pauseScreenPressed);
        }
        if (this.paddle.getLive().getValue() == 0) {
            this.sprites.removeSprite(this.paddle);
            this.environment.removeCollidable(this.paddle);
            this.live.decrease(1);
            createBallsOnTopOfPaddle();
            this.aliens.reset();
            if (this.live.getValue() != 0) {
                this.ar.run(new CountdownAnimation(5, 3, this.sprites));;
            }
        }
    }
    /**
     * *The function creates a game.
     * @param args - Accepts array.
     */
//    public static void main(String[] args) {
//        //Level2 l2 = new Level2();
//        GUI gui = new biuoop.GUI("game", 800, 600);
//        KeyboardSensor keyboard = gui.getKeyboardSensor();
//        AnimationRunner runner = new AnimationRunner(gui);
//        GameFlow gameFlow = new GameFlow(runner, keyboard, gui, );
//        java.util.List<LevelInformation> levels = new java.util.ArrayList<>(0);
//        levels.add(new Level1());
//        levels.add(new Level2());
//        levels.add(new Level3());
//        levels.add(new Level4());
//        gameFlow.runLevels(levels);
//    }

    /**
     * The function create balls on top of paddle.
     */
    public void createBallsOnTopOfPaddle() {
//        //Creates ball
//        for (int i = 0; i < this.level.numberOfBalls(); i++) {
//            Ball ball = new Ball(400, 530, 5, new java.awt.Color(255, 159, 95));
//            ball.addToGame(this);
//            ball.setGameEnvironment(this.environment);
//            ball.setVelocity(this.level.initialBallVelocities().get(i));
//            removedBall.increase(1);
//        }
        //Create a paddle in the game
        Rectangle rectangle = new Rectangle(
                new Point(this.gui.getDrawSurface().getWidth() / 2 - this.level.paddleWidth() / 2, 540),
                this.level.paddleWidth(), 15);
        this.paddle = new Paddle(rectangle, Color.orange, this.ks, this.level.paddleSpeed(), this, this.environment);
        this.paddle.addToGame(this);
        //this.paddle.initialBallVelocities();
    }

    /**
     * The function return number of remove block.
     *
     * @return - The function return number of remove block.
     */
    public int numberOfBlocks() {
        return this.removedBlocks.getValue();
    }

    /**
     * The function return Sprites.
     *
     * @return - The function return Sprites.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * The function return Environment.
     *
     * @return - The function return Environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * The function return Paddle.
     *
     * @return - The function return Paddle.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }
}
//        //Creates two balls
//        Ball ball1 = new Ball(400, 530, 5, java.awt.Color.MAGENTA);
//        Ball ball2 = new Ball(400, 530, 5, java.awt.Color.MAGENTA);
//        Ball ball3 = new Ball(400, 530, 5, java.awt.Color.MAGENTA);
//        ball1.addToGame(this);
//        ball2.addToGame(this);
//        ball3.addToGame(this);
//        //Create a ball in the game.
//        ball1.setGameEnvironment(this.environment);
//        ball2.setGameEnvironment(this.environment);
//        ball3.setGameEnvironment(this.environment);
//        ball1.setVelocity(3, -3);
//        ball2.setVelocity(-3, -3);
//        ball3.setVelocity(2, -3);
//        removedBall.increase(3);