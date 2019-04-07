/**
 * @author Shoham Hadad <shoham200296@gmail.com>
 *         Id 207595620
 * @since 2017-04-02
 * class name- Velocity.
 */

public class Velocity {
    private double dx;
    private double dy;

    // constructor

    /**
     * .
     * Initial speed
     */
    public Velocity() {
        this.dx = 1;
        this.dy = 1;
    }

    /**
     * .
     * Velocity specifies the change in position on the `x` and the `y` axes.
     *
     * @param dx - The value of the X speed change
     * @param dy - The value of the Y speed change
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * .
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy)
     *
     * @param p - the point the need to be changed.
     * @return - a new point
     */
    public Point applyToPoint(Point p) {
        double x = (p.getX() + this.dx);
        double y = (p.getY() + this.dy);
        return new Point(x, y);
    }

    /**
     * .
     * Take a angle with position angles and return a new velocity
     * with angle and speed
     *
     * @param angle The value of the angle change
     * @param speed The value of the speed change
     * @return a new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angle1 = Math.toRadians(angle);
        double dx = Math.sin(angle1) * speed;
        double dy = Math.cos(angle1) * speed * (-1);
        return new Velocity(dx, dy);
    }

    /**
     * .
     *
     * @return the dx value of this velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * .
     *
     * @return the dy value of this velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * return a speed.
     * @return -return a speed.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
}
