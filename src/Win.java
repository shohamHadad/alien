import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Created by 1 on 04/06/2017.
 */
public class Win implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter currentScore;

    /**
     * The building of win.
     *
     * @param k            -  gets a KeyboardSensor
     * @param currentScore - gets a Counter.
     */
    public Win(KeyboardSensor k, Counter currentScore) {
        this.currentScore = currentScore;
        this.keyboard = k;
        this.stop = false;
    }
    /**
     * The function draws the screen.
     * @param d - A screen to draw.
     * @param dt - gets double dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new java.awt.Color(255, 248, 161));
        d.fillRectangle(0, 20, 800, 580);
        d.setColor(new java.awt.Color(255, 109, 104));
        d.drawText(190, d.getHeight() / 2, "You Win! Your score is " + this.currentScore.getValue(), 40);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * A function that returns a boolean variable of true or false.
     *
     * @return -  returns a boolean variable of true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
