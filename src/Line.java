import java.util.ArrayList;

/**
 * @author Shoham Hadad <shoham200296@gmail.com>
 *         Id 207595620
 * @since 2017-04-02
 * class name- Line.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * .
     * constructors - The line constructed of two points, a start point and an end point.
     *
     * @param start Gets the value of start point
     * @param end   - Gets the value of end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * .
     * constructors - The line constructed of two points, a start point and an end point
     *
     * @param x1 - The X value is the start point
     * @param y1 - The Y value is the start point
     * @param x2 - The X value is the end point
     * @param y2 - The Y value is the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * .
     * The function calculates the function length
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * .
     * The function calculates the midpoint of the line
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double xmiddle;
        double ymiddle;
        xmiddle = (this.start.getX() + this.end.getX()) / 2;
        ymiddle = (this.start.getY() + this.end.getY()) / 2;
        Point p = new Point(xmiddle, ymiddle);
        return p;
    }

    /**
     * .
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * .
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * .
     * The function returns true if both lines intersect and false if they are not
     *
     * @param other - Gets the value of another line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        Point inter = intersectionWith(other);
        if (inter == null) {
            return false;
        }
        return true;
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.

    /**
     * .
     * The function calculates the intersection point
     *
     * @param other Gets the value of another line
     * @return Returns the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // calculate the determinate of the lines.
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double a2 = other.end().getY() - other.start().getY();
        double b2 = other.start().getX() - other.end().getX();
        double c1 = a1 * this.start.getX() + b1 * this.start.getY();
        double c2 = a2 * other.start().getX() + b2 * other.start().getY();
        double det = a1 * b2 - a2 * b1;

        if (det == 0) {
            // lines are parallel
            return null;
        } else {
            double x = (b2 * c1 - b1 * c2) / det;
            double y = (a1 * c2 - a2 * c1) / det;
            double round = Math.pow(10, 3);
            x = Math.round(x * round) / round;
            y = Math.round(y * round) / round;
//
//            if (x > this.end().getX() || x < this.start().getX()) {
//                return null;
//            }
//            if (y > this.end().getY() || y < this.start().getY()) {
//                return null;
//            }
//            if (x > other.end().getX() || x < other.start().getX()) {
//                return null;
//            }
//            if (y > other.end().getY() || y < other.start().getY()) {
//                return null;
//            }

            if (!this.checkRange(x, y) || !other.checkRange(x, y)) {
                return null;
            }

            return new Point(x, y);
        }
    }

    /**
     * The function checks whether the X and Y are in the middle.
     *
     * @param x - The X value of the point.
     * @param y - The y value of the point.
     * @return - The function returns to us if the X and Y are in the middle.
     */
    private boolean checkRange(double x, double y) {
        return checkRangeX(x) && checkRangeY(y);
    }

    /**
     * The function checks whether the X in the middle.
     *
     * @param x - The X value of the point.
     * @return The function returns to us if the X in the middle
     */
    private boolean checkRangeX(double x) {
        double minX = Math.min(start().getX(), end().getX());
        double maxX = Math.max(start().getX(), end().getX());

        return x >= minX && x <= maxX;
    }

    /**
     * The function checks whether the Y in the middle.
     *
     * @param y - The Y value of the point.
     * @return The function returns to us if the Y in the middle
     */
    private boolean checkRangeY(double y) {
        double minY = Math.min(start().getY(), end().getY());
        double maxY = Math.max(start().getY(), end().getY());

        return y >= minY && y <= maxY;
    }

//        double m1;
//        double m2;
//        double x = 0;
//        double y = 0;
//        // When I have two points with the same value of X then the slope is NEGATIVE INFINITY
//        if (this.end.getX() == this.start.getX()) {
//            m1 = Double.NEGATIVE_INFINITY;
//            // Calculates the x value of the intersection point
//            x = this.end.getX();
//            // Calculates the y value of the intersection point
//           // y = m1 * (x - this.end.getX()) + this.end.getY();
//            //Calculates th./e intersection point when there is a defined gradient
//        } else {
//            m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
//        }
//        // When I have two points with the same value of X then the slope is NEGATIVE INFINITY
//        if (other.end().getX() == other.start().getX()) {
//            m2 = Double.NEGATIVE_INFINITY;
//            // Calculates the x value of the intersection point
//            x = other.end.getX();
//            // Calculates the y value of the intersection point
//            //y = m2 * (x - other.end.getX()) + other.end.getY();
//
//            //Calculates the intersection point when there is a defined gradient
//        } else {
//            m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
//            // Calculates the x value of the intersection point
//           // x = (m1 * this.start.getX() - this.start.getY() - m2 * other.start.getX() + other.start.getY())
//             //       / (m1 - m2);
//            // Calculates the y value of the intersection point
//         //   y = m2 * (x - other.end.getX()) + other.end.getY();
//        }
//        //Checks if the two slopes of the two straight are equal and return null if yes
//        if (m1 == m2) {
//            return null;
//
//        } else {
//            //The value of X and Y if both slopes are defined
//            if (m1 != Double.NEGATIVE_INFINITY && m2 != Double.NEGATIVE_INFINITY) {
//                x = (m1 * this.start.getX() - this.start.getY() - m2 * other.start.getX() + other.start.getY())
//                        / (m1 - m2);
//                y = m1 * (x - this.start.getX()) + this.start.getY();
//            }
//
//            //The value of X and Y if one of the slopes is not defined
//            if (m2 == Double.NEGATIVE_INFINITY) {
//                y = m1 * (x - this.start.getX()) + this.start.getY();
//           // } else  {
//             //   y = m2 * (x - other.start.getX()) + other.start.getY();
//            }
//            if (m1 == Double.NEGATIVE_INFINITY){
//                y = m2 * (x - other.start.getX()) + other.start.getY();
//            }
//            Point intersection = new Point(this.roundOff(x, 3), this.roundOff(y, 3));
//
//            //The value of X and Y if one of the slopes is not defined
//            if ((x >= this.start.getX() && x <= this.end.getX())
//                    && (y >= this.start.getY() && y <= this.end.getY())) {
//                if ((x >= other.start.getX() && x <= other.end.getX())
//                        && (y >= other.start.getY() && y <= other.end.getY())
//                       ) {
//                    return intersection;
//                }
//            }
//        }
//        return null;
    //}

    /**
     * The function returns true if both lines are equal and false if not.
     *
     * @param other - Gets the value of another line
     * @return return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        } else if (this.start.equals(other.end) && this.end.equals(other.start)) {
            return true;
        }
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect - the function get block form of rectangle.
     * @return return the closest intersection point to the
     * start of the line and return null If this line does not intersect with the rectangle .
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        int count = 0;
        int distance;
        java.util.List<Point> list = new ArrayList<Point>(4);
        list = rect.intersectionPoints(this);
        if (list.size() == 0) {
            return null;
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            Point min = this.end();
            for (int i = 0; i < list.size(); i++) {
                if (this.start().distance(min) > this.start().distance(list.get(i))) {
                    min = list.get(i);
                }
            }
            if (min.equals(this.end())) {
                return null;
            }
            return min;
        }
    }

    /**
     * The function rounds the dots.
     *
     * @param x        - The X value of the point.
     * @param position - round.
     * @return - Returns a rounded point
     */
    public double roundOff(double x, int position) {
        double a = x;
        double temp = Math.pow(10.0, position);
        a *= temp;
        a = Math.round(a);
        return (a / (float) temp);
    }

}