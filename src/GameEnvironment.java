import java.util.ArrayList;

/**
 *@author Shoham Hadad <shoham200296@gmail.com>
 * Id 207595620
 *@since 2017-05-01
 *class name- CollisionInfo.
 */

public class GameEnvironment {

    private java.util.ArrayList<Collidable> collidableList;

    /**
     *The function creates an array of Collidable.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();

    }

    /**
     * @return - The function returns List of collidable.
     */
    public ArrayList getList() {
        return this.collidableList;
    }

    /**
     * add the collidable to the environment.
     * @param c - Kind of collidable.
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
        this.collidableList.add(c);
        }
    }

    /**
     * The function removes an collidable from the list.
     * @param c - kind of collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory - The line represents the track of the ball.
     * @return - CollisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point min = trajectory.end(), temp;
        int indexObject = 0;
        //// System.out.print(this.collidableList.get(0).getCollisionRectangle().getHeight());
        ////System.out.println(collidableList);
        ////min = trajectory.closestIntersectionToStartOfLine(this.collidableList.get(0).getCollisionRectangle());
        // Checks which of the points is the first point of collision
        for (int i = 0; i < this.collidableList.size(); i++) {
            //min = trajectory.closestIntersectionToStartOfLine(this.collidableList.get(i).getCollisionRectangle());
           //s System.out.print(min);
            temp = trajectory.closestIntersectionToStartOfLine(this.collidableList.get(i).getCollisionRectangle());
            if (temp == null) { continue; }
            if (trajectory.start().distance(min) > trajectory.start().distance(temp)) {
                min = temp;
                indexObject = i;
            }
        }
        if (min.equals(trajectory.end()) && indexObject != this.collidableList.size() - 1) {
            return null;
        }
        CollisionInfo info = new CollisionInfo(min, collidableList.get(indexObject));
        return info;
    }
}