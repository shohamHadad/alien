/**
 *@author Shoham Hadad <shoham200296@gmail.com>
 * Id 207595620
 *@since 2017-05-01
 *class name- CollisionInfo.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor - Returns with which object the ball collided and where the collision point is.
     * @param collisionPoint - the point at which the collision occurs.
     * @param collisionObject - the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
        //System.out.println(collisionObject.getCollisionRectangle().getUpperLeft().getX());
        //System.out.println(collisionObject.getCollisionRectangle().getUpperLeft().getY());
        //System.out.println(collisionObject.getCollisionRectangle().getWidth());
        //System.out.println(collisionObject.getCollisionRectangle().getHeight());
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return - the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
