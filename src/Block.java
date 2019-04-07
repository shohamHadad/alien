import biuoop.DrawSurface;

import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Shoham Hadad <shoham200296@gmail.com>
 *         Id 207595620
 * @since 2017-05-01
 * class name- CollisionInfo.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rectangle;
    private java.awt.Color color;
    private int counterHit, maxHits;
    private List<HitListener> hitListeners;
    private java.awt.Image image;
    private java.awt.Color stroke;
    private ArrayList<java.awt.Color> color1;
    private ArrayList<java.awt.Image> image1;
    private Color stroke1;

    /**
     * constructor - Builds block by rectangle and by image.
     * @param rect       - The rectangle that builds the block.
     * @param image      - The image of the block.
     * @param counterHit - The number of hits  of the ball in the block.
     */
    public Block(Rectangle rect, java.awt.Image image, int counterHit) {
        this.counterHit = counterHit;
        this.maxHits = counterHit;
        this.rectangle = rect;
        this.image = image;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * constructor - Builds block by rectangle and by color.
     *
     * @param rect       - The rectangle that builds the block.
     * @param color      - The color of the block.
     * @param counterHit - The number of hits  of the ball in the block.
     */
    public Block(Rectangle rect, java.awt.Color color, int counterHit) {
        this.counterHit = counterHit;
        this.maxHits = counterHit;
        this.rectangle = rect;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();

    }
    /**
     * constructor - Builds block by rectangle and by color.
     *
     * @param rect  - The rectangle that builds the block.
     * @param color - The color of the block.
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.rectangle = rect;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * The function returns the rectangle of the block.
     *
     * @return - return rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * The function changes the speed of the ball depending on the ball's hit in the block.
     *
     * @param collisionPoint  - point of collision
     * @param currentVelocity - speed of the current ball
     * @param hitter          - gets ball hitter.
     * @return - The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        boolean moveRight = currentVelocity.getDx() > 0;
        boolean moveDown = currentVelocity.getDy() > 0;
        // The number of hits of the ball in the block.
        if (this.counterHit != 0) {
            counterHit--;
        }
        this.notifyHit(hitter);
        /*
        if ((collisionPoint.getX() >= getCollisionRectangle().getUpperLeft().getX() && moveRight) || (collisionPoint
                .getX() <= getCollisionRectangle().getUpperLeft().getX() + getCollisionRectangle().getWidth()
                && !moveRight)) {
            Velocity currentVelocity1 = new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
          //  this.notifyHit(hitter);
            return currentVelocity1;
        }
        if ((collisionPoint.getY() >= getCollisionRectangle().getUpperLeft().getY() && moveDown) || (collisionPoint
                .getY() <= getCollisionRectangle().getUpperLeft().getY() + getCollisionRectangle().getHeight()
                && !moveDown)) {
            Velocity currentVelocity1 = new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
            //this.notifyHit(hitter);
            return currentVelocity1;
        }*/
        Rectangle rect = getCollisionRectangle();
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        if (rect.getUpperLeft().getY() >= collisionPoint.getY() && currentVelocity.getDy() > 0) {
            newVelocity = new Velocity(newVelocity.getDx(), -newVelocity.getDy());
        }
        if (rect.getUpperLeft().getY() + rect.getHeight() <= collisionPoint.getY() && currentVelocity.getDy() < 0) {
            newVelocity = new Velocity(newVelocity.getDx(), -newVelocity.getDy());
        }
        if (rect.getUpperLeft().getX() >= collisionPoint.getX() && currentVelocity.getDx() > 0) {
            newVelocity = new Velocity(-newVelocity.getDx(), newVelocity.getDy());
        }
        if (rect.getUpperLeft().getX() + rect.getWidth() <= collisionPoint.getX() && currentVelocity.getDx() < 0) {
            newVelocity = new Velocity(-newVelocity.getDx(), newVelocity.getDy());
        }
        return newVelocity;
    }

    /**
     * The function draws a block.
     *
     * @param d - surface a block.
     */
    public void drawOn(DrawSurface d) {
        if (this.image != null) {
            d.drawImage((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                    this.image);
        }
        if (this.color != null) {
                d.setColor(this.color);
                d.fillRectangle((int) (this.rectangle.getUpperLeft().getX()),
                        (int) (this.rectangle.getUpperLeft().getY()), (int) (this.rectangle.getWidth()),
                        (int) (this.rectangle.getHeight()));
        }
        int i = this.maxHits - this.counterHit;
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt - gets double dt.
     */
    public void timePassed(double dt) {
        return;
    }

    /**
     * Add this paddle to the game.
     *
     * @param game - get a game.
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * remove this paddle to the game.
     *
     * @param game - get a game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    // ... implementation

    /**
     * Notify all listeners about a hit event.
     *
     * @param hitter - gets ball hitter.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * The function removes the block from the game.
     *
     * @param hl - get hl.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * The function adds the block from the game.
     *
     * @param hl - get hl.
     */
    public void addHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.add(hl);
        }
    }

    /**
     * The function returns the number of hits in the block.
     * @return - Returns the number of hits in the block
     */
    public int getHitPoints() {
        return this.counterHit;
    }

    /**
     * The function returns the of rectangle the block.
     * @return - Returns the of rectangle the block.
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    /**
     * The function returns the image of the block.
     * @return - Returns the image of the block.
     */
    public java.awt.Image getImage() {
        return this.image;
    }

    /**
     *  Do reset of the game.
     * @param x - gets a x.
     * @param y - gets a y.
     */
    public void reset(double x,  double y) {
        Point p = new Point(x, y);
        this.rectangle.setUpperLeft(p);
    }

}