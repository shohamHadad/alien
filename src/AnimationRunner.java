import biuoop.DrawSurface;
import biuoop.GUI;


/**
 * Created by 1 on 30/05/2017.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper;

    /**
     * The function runs the animations of the game.
     * @param gui - get gui.
     */
    public AnimationRunner(GUI gui) {
        this.framesPerSecond = 60;
        this.sleeper = new biuoop.Sleeper();
        this.gui = gui;
    }

    /**
     * The function runs the animations of the game.
     * @param animation - get animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, (double) 1 / this.framesPerSecond);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
