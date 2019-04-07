import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Created by 1 on 01/06/2017.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * The building of PauseScreen.
     * @param k - gets KeyboardSensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    /**
     * The function draws the screen.
     * @param d - A screen to draw.
     * @param dt - gets double dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }
    /**
     * A function that returns a boolean variable of true or false.
     * @return -  returns a boolean variable of true or false.
     */
    public boolean shouldStop() { return this.stop; }
}