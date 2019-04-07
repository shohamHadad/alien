/**
 * Created by 1 on 22/06/2017.
 */


public class QuitGame implements Task<Void> {

    private AnimationRunner runner;

    /**
     * The building of the ShowHiScoresTask.
     * @param runner - gets a animationRunner.
     */
    public QuitGame(AnimationRunner runner) {
        this.runner = runner;
    }

    /**
     * return the appropriate action depending on the selected T type.
     * @return -  return the appropriate action depending on the selected T type.
     */
    public Void run() {
        System.exit(0);
        return null;
    }
}
