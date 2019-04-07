import java.util.ArrayList;

/**
 * @author Shoham Hadad <shoham200296@gmail.com>
 *         Id 207595620
 * @since 2017-04-26
 * class name- Rectangle.
 */
class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft - The left vertex of the rectangle
     * @param width     - the width of the rectangle
     * @param height    - the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
    }

    /**
     * Create a new rectangle with location and width.
     *
     * @param upperLeft - The left vertex of the rectangle
     * @param width     - the width of the rectangle
     */
    public Rectangle(Point upperLeft, double width) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
    }

    /**
     * The function returns the points that the line collides with in the rectangle.
     *
     * @param line - The line that collides with the rectangle
     * @return Return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List intersectionPoints(Line line) {
        double xleft1 = this.upperLeft.getX();
        double yleft1 = this.upperLeft.getY();
        double xleft2 = this.upperLeft.getX();
        double yleft2 = this.upperLeft.getY() + this.height;
        double xright1 = this.upperLeft.getX() + this.width;
        ;
        double yright1 = this.upperLeft.getY();
        double xright2 = this.upperLeft.getX() + this.width;
        double yright2 = this.upperLeft.getY() + this.height;

        Line r1 = new Line(xleft1, yleft1, xleft2, yleft2);
        Line r2 = new Line(xright1, yright1, xright2, yright2);
        Line r3 = new Line(xleft1, yleft1, xright1, yright1);
        Line r4 = new Line(xleft2, yleft2, xright2, yright2);

        Point intersect1 = r1.intersectionWith(line);
        Point intersect2 = r2.intersectionWith(line);
        Point intersect3 = r3.intersectionWith(line);
        Point intersect4 = r4.intersectionWith(line);
        java.util.List<Point> list = new ArrayList<Point>(4);

        if (intersect1 != null) {
            list.add(intersect1);
        }

        if (intersect2 != null) {
            list.add(intersect2);
        }

        if (intersect3 != null) {
            list.add(intersect3);
        }

        if (intersect4 != null) {
            list.add(intersect4);
        }
        return list;
    }

    /**
     * @return Return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return Return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return Returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @param point -
     */
    public void setUpperLeft(Point point) {
        this.upperLeft = point;
    }
}