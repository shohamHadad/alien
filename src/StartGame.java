import biuoop.KeyboardSensor;
import biuoop.GUI;
import java.util.List;

/**
 * Created by 1 on 22/06/2017.
 */
public class StartGame implements Task<Void> {

    private AnimationRunner runner;
    private KeyboardSensor ks;
    private GUI gui;
    private Counter live;
    private Counter currentScore;
    private HighScoresTable highScoresTable;
    private List<LevelInformation> levelInformationList;
    private Counter aliens1;

    /**
     * The building of the StartGame.
     * @param runner - gets a animationRunner.
     * @param ks - gets KeyboardSensor.
     * @param gui - gets GUI.
     * @param highScoresTable gets a HighScoresTable.
     * @param levelInformationList - gets a List<LevelInformation>.
     */
    public StartGame(AnimationRunner runner, KeyboardSensor ks , GUI gui, HighScoresTable highScoresTable,
                     List<LevelInformation> levelInformationList) {
        this.runner = runner;
        this.ks = ks;
        this.gui = gui;
        this.highScoresTable = highScoresTable;
        this.levelInformationList = levelInformationList;
    }

    /**
     * return the appropriate action depending on the selected T type.
     * @return -  return the appropriate action depending on the selected T type.
     */
    public Void run() {
//        java.util.List<LevelInformation> levels = new java.util.ArrayList<LevelInformation>(0);
//        Level l1 = new Level();
//        levels.add(l1);
        GameFlow gameFlow = new GameFlow(runner, ks, gui, highScoresTable);
        gameFlow.runLevels(levelInformationList);
        return null;
    }
}
