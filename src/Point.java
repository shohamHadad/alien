/**
 *@author Shoham Hadad <shoham200296@gmail.com>
 * Id 207595620
 *@since 2017-04-02
 *class name- Point.
 */
public class Point {
    private double x;
    private double y;
    /**.
     * constructor - The point is constructed from an X and a Y values.
     * @param x  - The X value is the point
     * @param y - The y value is the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**.
     * The function calculates the distance between points
     * @param other - Gets the value of another point
     * @return return the distance of this point to the other point
     */
    public double distance(Point other) {
        double distance;
        distance = Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
                + ((this.y - other.getY()) * (this.y - other.getY())));
        return distance;
    }

    /**.
     *The function will return true if both points are equal and false if the points are not equal
     * @param other - Gets the value of another point
     * @return return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
        return true;
        }
        return false;
    }

    /**.
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**.
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}