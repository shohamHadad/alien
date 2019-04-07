/**
 * Created by 1 on 22/06/2017.
 */
public class ShowHiScoresTask implements Task<Void> {

    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * The building of the ShowHiScoresTask.
     * @param runner - gets a animationRunner.
     * @param highScoresAnimation - gets a animation.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    /**
     * return the appropriate action depending on the selected T type.
     * @return -  return the appropriate action depending on the selected T type.
     */
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}
