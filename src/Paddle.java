import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Shoham Hadad <shoham200296@gmail.com>
 * Id 207595620
 * @since 2017-05-08
 * class name- CollisionInfo.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private java.awt.Color color;
    private int paddleSpeed;
    private boolean stop = false;
    private GameLevel game;
    private long time;
    private GameEnvironment environment;
    private Counter live;

    /**
     * constructor - Builds the paddle by defining the rectangle of the paddle and by the color of the paddle.
     * @param rectangle -The shape of the paddle.
     * @param color - the color of the paddle
     * @param keyboard - kind of keyboard;
     * @param paddleSpeed - number of paddle speed.
     * @param game - gets a game.
     * @param environment - gets a environment.
     */
    public Paddle(Rectangle rectangle, java.awt.Color color, biuoop.KeyboardSensor keyboard, int paddleSpeed,
                  GameLevel game, GameEnvironment environment) {
        this.rectangle = rectangle;
        this.color = color;
        this.keyboard = keyboard;
        this.paddleSpeed = paddleSpeed;
        this.game = game;
        this.time = System.currentTimeMillis();
        this.environment = environment;
        this.live = new Counter(1);
    }

    /**
     * The paddle will move to the left.
     * @param dt - gets double dt.
     */
    public void moveLeft(double dt) {
        if (this.keyboard.isPressed(biuoop.KeyboardSensor.LEFT_KEY)) {
            Point centerNew = new Point(this.rectangle.getUpperLeft().getX() - (this.paddleSpeed * dt),
                    this.rectangle.getUpperLeft().getY());
            //Checks that the paddle does not come out of the screen
            if (this.rectangle.getUpperLeft().getX() <= 10) {
                centerNew = new Point(this.rectangle.getUpperLeft().getX(), this.rectangle.getUpperLeft().getY());
            }
            this.rectangle.setUpperLeft(centerNew);
        }
    }

    /**
     * The paddle will move to the right.
     * @param dt - gets double dt.
     */
    public void moveRight(double dt) {
        Point centerNew;
        if (this.keyboard.isPressed(biuoop.KeyboardSensor.RIGHT_KEY)) {
            centerNew = new Point(this.rectangle.getUpperLeft().getX() + (this.paddleSpeed * dt),
                    this.rectangle.getUpperLeft().getY());
            //Checks that the paddle does not come out of the screen
            if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() >= 790) {
                centerNew = new Point(this.rectangle.getUpperLeft().getX(), this.rectangle.getUpperLeft().getY());
            }
            this.rectangle.setUpperLeft(centerNew);
        }
    }

    /**
     * Sprite - The function changes the shift of the paddle by the user click button.
     * @param dt - gets double dt.
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(biuoop.KeyboardSensor.LEFT_KEY)) {
            //System.out.println("the 'left arrow' key is pressed");
            moveLeft(dt);
        } else if (keyboard.isPressed(biuoop.KeyboardSensor.RIGHT_KEY)) {
            //System.out.println("the 'left arrow' key is pressed");
            moveRight(dt);
        }
        if (System.currentTimeMillis() - this.time >= 350) {
            if (this.keyboard.isPressed(biuoop.KeyboardSensor.SPACE_KEY)) {
                //this.stop = true;
                double x = this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() / 2;
                double y = this.rectangle.getUpperLeft().getY() + this.rectangle.getWidth() / 2 - 55;
                Ball ball = new Ball((int) x, (int) y, 5, java.awt.Color.YELLOW);
                Velocity v = new Velocity(0, -500);
                ball.setVelocity(v);
                ball.addToGame(this.game);
                ball.setGameEnvironment(this.environment);

            }
            this.time = System.currentTimeMillis();
        }
    }

    /**
     * The function draws the paddle.
     * @param d - is a drawsurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) (this.rectangle.getUpperLeft().getX()), (int) (this.rectangle.getUpperLeft().getY()),
                (int) (this.rectangle.getWidth()), (int) (this.rectangle.getHeight()));
        d.setColor(Color.black);
        d.drawRectangle((int) (this.rectangle.getUpperLeft().getX()), (int) (this.rectangle.getUpperLeft().getY()),
                (int) (this.rectangle.getWidth()), (int) (this.rectangle.getHeight()));
    }

    /**
     * Collidable.
     * @return return rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * The function returns the speed of the ball according to the injury it hit the paddle.
     *
     * @param collisionPoint  - point of collision
     * @param currentVelocity - speed of the current ball
     * @param hitter          - gets ball.
     * @return returns the speed of the ball according to the injury it hit the paddle.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        hitter.removeFromGame(this.game);
        this.live.decrease(1);
        Velocity velocityNew = currentVelocity;
        double xleft1 = this.rectangle.getUpperLeft().getX();
        double yleft1 = this.rectangle.getUpperLeft().getY();
        double xleft2 = this.rectangle.getUpperLeft().getX();
        double yleft2 = this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight();
        double xright1 = this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth();
        ;
        double yright1 = this.rectangle.getUpperLeft().getY();
        double xright2 = this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth();
        double yright2 = this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight();

        if (collisionPoint.getX() >= xleft1 && collisionPoint.getX() <= xright1 && collisionPoint.getY() >= yleft1
                && collisionPoint.getY() <= yright1) {
            if ((collisionPoint.getX() >= (xleft1 + ((this.rectangle.getWidth() / 5) * 2))) && (collisionPoint.getX()
                    <= (xleft1 + ((this.rectangle.getWidth() / 5) * 3)))) {
                velocityNew = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
            }
            if ((collisionPoint.getX() >= xleft1) && (collisionPoint.getX() <= (xleft1 + (this.rectangle.getWidth()
                    / 5)))) {
                velocityNew = Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            }
            if ((collisionPoint.getX() <= xright1) && (collisionPoint.getX() >= xleft1 + ((this.rectangle.getWidth()
                    / 5) * 4))) {
                velocityNew = Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            }
            if ((collisionPoint.getX() < (xleft1 + ((this.rectangle.getWidth() / 5) * 2))) && (collisionPoint.getX()
                    > (xleft1 + (this.rectangle.getWidth() / 5)))) {
                velocityNew = Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            }
            if ((collisionPoint.getX() < (xleft1 + ((this.rectangle.getWidth() / 5) * 4))) && (collisionPoint.getX()
                    > (xleft1 + ((this.rectangle.getWidth() / 5) * 3)))) {
                velocityNew = Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            }

        } else if (collisionPoint.getX() >= xleft1 && collisionPoint.getX() <= xleft2 && collisionPoint.getY()
                >= yleft1 && collisionPoint.getY() <= yleft2) {
            velocityNew = new Velocity(velocityNew.getDx() * (-1), velocityNew.getDy());
        } else if (collisionPoint.getX() >= xright1 && collisionPoint.getX() <= xright2 && collisionPoint.getY()
                >= yright1 && collisionPoint.getY() <= yright2) {
            velocityNew = new Velocity(velocityNew.getDx() * (-1), velocityNew.getDy());
        }
        return velocityNew;

    }

    /**
     * Add this paddle to the game.
     * @param g - get a game.
     */

    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * The initial velocity of each ball.
     * @return - velocity.
     */
    public Ball initialBallVelocities() {
        Ball ball = new Ball(400, 500, 7, java.awt.Color.YELLOW);
        Velocity v = new Velocity(0, -50);
        ball.setVelocity(v);
        if (this.keyboard.isPressed(biuoop.KeyboardSensor.SPACE_KEY)) {
            return ball;
        }
        return ball;
    }
    /**
     * A function that returns a boolean variable of true or false.
     * @return -  returns a boolean variable of true or false.
     */
    public boolean shouldStop() { return this.stop; }

    /**
     * return number of live of paddle.
     * @return - return number of live of paddle.
     */
    public Counter getLive() {
        return this.live;
    }
}