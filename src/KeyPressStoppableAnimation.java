import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Created by 1 on 18/06/2017.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * The building of the KeyPressStoppableAnimation.
     * @param sensor gets a keyboardSensor.
     * @param key gets a string key.
     * @param animation gets a Animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = false;
    }

    /**
     * The function draws the screen.
     * @param d - A screen to draw.
     * @param dt - gets double dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(this.key) && this.isAlreadyPressed) {
            this.stop = true;
        } else {
            this.isAlreadyPressed = true;
        }
    }

    /**
     * A function that returns a boolean variable of true or false.
     * @return -  returns a boolean variable of true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }

}
