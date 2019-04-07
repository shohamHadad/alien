/**
 *@author Shoham Hadad <shoham200296@gmail.com>
 * Id 207595620
 *@since 2017-05-03
 *class name- Collidable.
 */
public interface Collidable {
    /**
     * @return - Return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * @param hitter - gat ball hitter.
     * @param collisionPoint - point of collision
     * @param currentVelocity - speed of the current ball
     * @return - The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}