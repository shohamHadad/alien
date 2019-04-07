/**
 * Created by 1 on 05/07/2017.
 */
public class Aliens implements Sprite {
    private java.util.ArrayList<Alien> alienList;
    private double v;
    private double newV;
    ///private java.util.ArrayList<java.util.List> list;

    /**
     * gets an Aliens.
     * @param v - gets a v.
     */
    public Aliens(double v) {
        this.v = v;
        this.newV = this.v;
        this.alienList = new java.util.ArrayList<Alien>();
        java.awt.Image image = null;
        try {
            image = javax.imageio.ImageIO.read(ClassLoader.getSystemResourceAsStream("alien.png"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                int x = 50 + i * (10 + 40);
                int y = 50 + j * (10 + 30);
                Rectangle rect = new Rectangle(new Point(x , y), 40, 30);
                Alien alien = new Alien(rect, image, 1);
                this.alienList.add(alien);
            }
        }
       // this.list = new java.util.ArrayList<java.util.List>();
    }

//    /**
//     * add a new alien to the list.
//     * @param a - Alien
//     */
//    public void addList(java.util.List a) {
//        if (a != null) {
//            this.list.add(a);
//        }
//    }
    /**
     * The function removes an Alien from the list.
     * @param a - kind of Alien.
     */
    public void removeAlien(Alien a) {
        this.alienList.remove(a);
    }

    /**
     * The function return the list of alien.
     * @return - return the list of alien.
     */
    public java.util.List getListAlien() {
        return this.alienList;
    }
//    /**
//     * The function removes an Alien from the list.
//     * @param a - kind of Alien.
//     */
//    public void removeList(java.util.List a) {
//        this.list.remove(a);
//    }

    /**
     * call timePassed() on all aliens.
     * @param dt - gets double dt.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.alienList.size(); i++) {
            this.alienList.get(i).timePassed(dt);
        }
    }

    /**
     * return Right Limit.
     * @return - return Right Limit.
     */
    public int getRightLimit() {
      boolean col = false;
      int right = 0;
        for (int j = 0; j <= 9; j++) {
            for (int i = 0; i < 5; i++) {
                    if (alienList.get(i * 10 + j).getHitPoints() != 0) {
                        col = true;
                        if (col) {
                            right = j;
                        }
                    }

            }
            col = false;
        }
        return right;
    }
    /**
     * return left Limit.
     * @return - return left Limit.
     */
    public int getLeftLimit() {
        boolean col = false;
        int left = 0;
        for (int j = 9; j >= 0; j--) {
            for (int i = 0; i < 5; i++) {
                if (alienList.get(i * 10 + j).getHitPoints() != 0) {
                    col = true;
                    if (col) {
                        left = i;
                    }
                }
                col = false;
            }
        }
        return left;
    }

    /**
     * The alien will move to the left.
     */
    public void moveLeft() {
        int left = getLeftLimit();
        Point centerNew = null;
        for (int i = 0; i < 50; i++) {
            centerNew = new Point(this.alienList.get(i).getRectangle().getUpperLeft().getX() + this.newV, this
                    .alienList.get(i).getRectangle().getUpperLeft().getY());
            this.alienList.get(i).getRectangle().setUpperLeft(centerNew);
        }
        //Checks that the paddle does not come out of the screen
        if (this.alienList.get(left).getRectangle().getUpperLeft().getX() <= 10) {
//            centerNew = new Point(this.alienList.get(left).getRectangle().getUpperLeft().getX(), this.alienList
//                    .get(i* 10).getRectangle().getUpperLeft().getY());
            this.newV = -1.1 * this.newV;
            for (int i = 0; i < 50; i++) {
                this.alienList.get(i).getRectangle().setUpperLeft(new Point(this.alienList.get(i)
                        .getCollisionRectangle().getUpperLeft().getX(),
                        this.alienList.get(i).getRectangle().getUpperLeft().getY() + 10));
            }
        }
    }

    /**
     * The alien will move to the right.
     */
    public void moveRight() {
        int right = getRightLimit();
        Point centerNew = null;
        for (int i = 0; i < 50; i++) {
            centerNew = new Point(this.alienList.get(i).getRectangle().getUpperLeft().getX() + this.newV, this
                    .alienList.get(i).getRectangle().getUpperLeft().getY());
            this.alienList.get(i).getRectangle().setUpperLeft(centerNew);
        }
        //Checks that the paddle does not come out of the screen
        if (this.alienList.get(right * 5).getRectangle().getUpperLeft().getX()
                + this.alienList.get(right * 5).getRectangle().getWidth() >= 790) {
//            centerNew = new Point(this.alienList.get(left).getRectangle().getUpperLeft().getX(), this.alienList
//                    .get(i* 10).getRectangle().getUpperLeft().getY());
            this.newV = -1.1 * this.newV;
            for (int i = 0; i < 50; i++) {
                this.alienList.get(i).getRectangle().setUpperLeft(new Point(this.alienList.get(i)
                        .getCollisionRectangle().getUpperLeft().getX(),
                        this.alienList.get(i).getRectangle().getUpperLeft().getY() + 10));
            }
        }
    }

    /**
     * check if we need move left or right.
     */
    public void checkMove() {
        if (this.newV > 0) {
            moveRight();
        } else {
            moveLeft();
        }
    }

//    /**
//     * check if have aliens.
//     * @return - return true if have a aliens.
//     */
//    public boolean check(){
//        boolean check = false;
//        for (int j = 0; j < 5; j++) {
//            for (int i = 0; i <= 49; i++) {
//                if ((this.alienList.get((49- j)- (i * 5)).getHitPoints() != 0)){
//                    check = true;
//                }
//            }
//        }
//        return check;
//    }

    /**
     * check if have aliens.
     * @return - return true if have a aliens.
     */
    public int[] check() {
        boolean check = false;
        int j, i, w;
        int[] arr = new int[3];
        for (j = 0; j < 5; j++) {
            for (i = 0; i <= 9; i++) {
                if ((this.alienList.get((49 - j) - (i * 5)).getHitPoints() != 0)) {
                    check = true;
                    arr[0] = j;
                    arr[1] = i;
                }
            }
        }
        if (check) {
            w = 0;
        } else {
            w = 1;
        }
        arr[2] = w;
        return arr;
    }
    @Override
    public void timePassed(double dt) {

    }

    @Override
    public void drawOn(biuoop.DrawSurface d) {
        for (int i = 0; i < this.alienList.size(); i++) {
            this.alienList.get(i).drawOn(d);
        }
    }

    /**
     *
     */
    public void reset() {
        int k = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                int x = 50 + i * (10 + 40);
                int y = 50 + j * (10 + 30);
                this.alienList.get(k).reset(x, y);
                k++;
            }
        }
        this.newV = this.v;
    }
}
