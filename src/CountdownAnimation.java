import biuoop.DrawSurface;

/**
 * Created by 1 on 01/06/2017.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    /**
     * The CountdownAnimation will display the given gameScreen,
     * for numOfSeconds seconds, and on top of them it will show
     * a countdown from countFrom back to 1, where each number will
     * appear on the screen for (numOfSeconds / countFrom) secods, before.
     * it is replaced with the next one.
     * @param numOfSeconds - gets numOfSeconds.
     * @param countFrom - gets countFrom.
     * @param gameScreen - gets gameScreen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
      this.numOfSeconds = numOfSeconds;
      this.countFrom = countFrom;
      this.gameScreen = gameScreen;

    }

    /**
     * The function draws the game.
     * @param d - A screen to draw.
     * @param dt - gets double dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
       // int millisecondsPerFrame = 1000 / this.countFrom;
            biuoop.Sleeper sleeper = new biuoop.Sleeper();
            d.setColor(java.awt.Color.PINK);
            d.drawText(400, 300, this.countFrom-- + "", 150);
            long milliSecondLeftToSleep = 1000 * (long) this.numOfSeconds / (this.countFrom + 4);
            if (milliSecondLeftToSleep != this.numOfSeconds) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

    }

    /**
     * The function returns a fact that animation must stop.
     * @return - The function returns a fact that animation must stop
     */
    public boolean shouldStop() {
        if (this.countFrom < 0) {
            return true;
        }
            return false;
    }
}