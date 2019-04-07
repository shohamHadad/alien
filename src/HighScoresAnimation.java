import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Created by 1 on 15/06/2017.
 */
public class HighScoresAnimation implements Animation {

    private String endKey;
    private HighScoresTable scores;
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * The building of HighScoresAnimation.
     * @param scores - gets a HighScoresTable
     * @param endKey - gets a string.
     * @param k -  gets a KeyboardSensor
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey, KeyboardSensor k) {
        this.scores = scores;
        this.endKey = endKey;
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * The function draws the screen.
     * @param d - A screen to draw.
     * @param dt - gets double dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new java.awt.Color(203, 255, 220));
        d.fillRectangle(0, 20, 800, 580);
        d.setColor(new java.awt.Color(255, 107, 119));
        d.drawText(230, d.getHeight() / 4, " High Scores Table " , 36);
        for (int i = 0; i < this.scores.getHighScores().size(); i++) {
                d.drawText(220, 2 * d.getHeight() / 4 + (i * 20), "" + this.scores.getHighScores().get(i).getName(),
                        18);
                d.drawText(520, d.getHeight() / 2 + (i * 20), "" + this.scores.getHighScores().get(i).getScore(),
                        18);

        }
        if (this.keyboard.isPressed(biuoop.KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }

    /**
     * A function that returns a boolean variable of true or false.
     * @return -  returns a boolean variable of true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
