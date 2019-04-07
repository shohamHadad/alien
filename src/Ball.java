import biuoop.DrawSurface;

/**
 * @author Shoham Hadad <shoham200296@gmail.com>
 *         Id 207595620
 * @since 2017-04-02
 * class name- Ball.
 */
public class Ball implements Sprite, HitNotifier {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private Point end;
    private Point first;
    private Point end1;
    private Point first1;
    private GameEnvironment gameEnvironment;
    private Ball hitter;
    private java.util.List<HitListener> hitListeners;

    /**
     * .
     * constructor - Builds a ball with a point center, radius, and color
     *
     * @param center          - Ball center point
     * @param r               - The value of the radius
     * @param color           - Color ball
     * @param end1            - End point
     * @param first1          - starting point
     * @param gameEnvironment -
     */
    public Ball(Point center, int r, java.awt.Color color, Point end1, Point first1, GameEnvironment gameEnvironment) {
        this((int) center.getX(), (int) center.getY(), r, color, end1, first1, gameEnvironment);
        this.hitListeners = new java.util.ArrayList<HitListener>();
    }

    /**
     * .
     * constructor - Builds a ball with a point center, radius, color and border to ball
     *
     * @param x               - The value of X of the center of the ball
     * @param y               - The value of Y of the center of the ball0200
     * @param r               - The value of the radius
     * @param color           - Color ball
     * @param end1            - End point
     * @param first1          - starting point
     * @param gameEnvironment - Game environment
     */
    public Ball(int x, int y, int r, java.awt.Color color, Point end1, Point first1, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.v = new Velocity();
        this.end = end1;
        this.first = first1;
        this.gameEnvironment = gameEnvironment;
        this.hitListeners = new java.util.ArrayList<HitListener>();
    }

    /**
     * constructor - Builds a ball with a point center, radius, color and border to ball.
     *
     * @param x               - The value of X of the center of the ball
     * @param y               - The value of Y of the center of the ball0200
     * @param r               - The value of the radius
     * @param color           - Color ball
     * @param gameEnvironment - Game environment
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.v = new Velocity();
        this.gameEnvironment = gameEnvironment;
        this.hitListeners = new java.util.ArrayList<HitListener>();
    }

    /**
     * constructor - Builds a ball with a point center, radius, color and border to ball.
     *
     * @param x     - The value of X of the center of the ball
     * @param y     - The value of Y of the center of the ball0200
     * @param r     - The value of the radius
     * @param color - Color ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.gameEnvironment = new GameEnvironment();
        this.hitListeners = new java.util.ArrayList<HitListener>();
    }

    // accessors

    /**
     * .
     *
     * @return the x value of center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * .
     *
     * @return the y value of center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * .
     *
     * @return the radius value of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * .
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * .
     * draw the ball on the given DrawSurface
     *
     * @param surface surface a Ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.getX(), (int) this.getY(), r);

    }

    /**
     * .
     * The function defines the speed of the ball
     *
     * @param v1 -  velocity
     */
    public void setVelocity(Velocity v1) {
        this.v = v1;
    }

    /**
     * .
     * The function defines the velocity of the ball
     *
     * @param dx - The value of the X speed change
     * @param dy - The value of the Y speed change
     */
    public void setVelocity(double dx, double dy) {
        Velocity v1 = new Velocity(dx, dy);
        this.v = v1;
    }

    /**
     * .
     * The function return the velocity of the ball
     *
     * @return a velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * The function calculates the movement of the ball.
     *
     * @param dt - gets double dt.
     */
    public void moveOneStep(double dt) {
        Point nextPoint = new Point(center.getX() + v.getDx() * dt, center.getY() + v.getDy() * dt);
        // trajectory = The line represents the track of the ball.
        Line trajectory = new Line(this.center, nextPoint);
        // info = Represents the object where the ball is colliding and the point of collision.
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);

        //There is no collision between the ball and the object.
        if (info == null) {
            this.center = nextPoint;
        } else {
            //System.out.println(this.center.toString());
            //System.out.println(this.getVelocity().toString());

            double x = center.getX(), y = center.getY();
            if (v.getDx() != 0) {
                x = info.collisionPoint().getX() - getVelocity().getDx() / Math.abs(getVelocity().getDx());
            }
            if (v.getDy() != 0) {
                y = info.collisionPoint().getY() - getVelocity().getDy() / Math.abs(getVelocity().getDy());
            }

            if (x > 790) {
                System.out.println();
            }

            Velocity newV = info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity());
            if (newV.getDx() != v.getDx() || newV.getDy() != v.getDy()) {
                this.center = new Point(x, y);
                this.v = newV;
            } else {
                this.center = nextPoint;
            }

            if (center.getX() > 790) {
                System.out.println();
                newV = info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity());
            }
        }

        // The check deal with the x axis
        // The check for exact hit, on left border or right, and change direction if so
/**


 if ((this.center.getX() + this.r + Math.abs(this.v.getDx())) >= this.end.getX()) {
 //if we reach here, we are about to pass the bottom border
 if (this.v.getDx() > 0) {
 this.setVelocity(this.v.getDx() * (-1), this.v.getDY());
 }
 }
 //do the same with y axis as we did above with x.
 //The check for exact hit, on top border or bottom, and change direction if so

 if ((this.center.getY() + this.r + Math.abs(this.v.getDx())) >= this.end.getY()) {
 //if we reach here, we are about to pass the bottom border
 if (this.v.getDY() > 0) {
 this.setVelocity(this.v.getDx(), this.v.getDY() * (-1));
 }
 }
 // The check deal with the x axis
 if ((this.center.getX() - this.r + (-1) * Math.abs(this.v.getDx())) <= this.first.getX()) {
 //if we reach here, we are about to pass the bottom border
 if (this.v.getDx() < 0) {
 this.setVelocity(this.v.getDx() * (-1), this.v.getDY());
 }
 }
 //do the same with y axis as we did above with x.
 if ((this.center.getY() - this.r + (-1) * Math.abs(this.v.getDx())) <= this.first.getY()) {
 //if we reach here, we are about to pass the bottom border
 if (this.v.getDY() < 0) {
 this.setVelocity(this.v.getDx(), this.v.getDY() * (-1));
 }
 }
 this.center = this.getVelocity().applyToPoint(this.center);
 */

    }

    /**
     * @param dt - gets double dt.
     */
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * Add this paddle to the game.
     *
     * @param game - get a game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * The function updates the game environment.
     *
     * @param newG - Game environment
     */
    public void setGameEnvironment(GameEnvironment newG) {
        this.gameEnvironment = newG;
    }

    /**
     * The function removes the ball from the game.
     *
     * @param hl - get hl.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * The function adds the ball from the game.
     *
     * @param hl - get hl.
     */
    public void addHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.add(hl);
        }
    }

    /**
     * remove this ball to the game.
     * @param game - get a game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}