/**
 * Created by 1 on 11/06/2017.
 */
public class ScoreInfo implements java.io.Serializable {

    private String name;
    private int score;

    /**
     * constructor - Builds us the final score and name of the player.
     *
     * @param name  - gets a name of the player.
     * @param score - gets a final score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * return name of the player.
     *
     * @return - return name of the player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * return a final score.
     *
     * @return - return a final score.
     */
    public int getScore() {
        return this.score;
    }
}
