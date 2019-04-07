import biuoop.KeyboardSensor;
import java.util.List;
import biuoop.GUI;

/**
 * Created by 1 on 04/06/2017.
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;
    private Counter live;
    private Counter currentScore;
    private HighScoresTable highScoresTable;
    private Counter aliens1;
    private Aliens aliens;
    private int num;
    private double newV = 1;
    /**
     * The building of the GameFlow.
     * @param ar gets AnimationRunner.
     * @param ks - gets KeyboardSensor.
     * @param gui - gets GUI.
     * @param highScoresTable gets a HighScoresTable.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks , GUI gui, HighScoresTable highScoresTable) {
        this.ar = ar;
        this.ks = ks;
        this.currentScore = new Counter(0);
        this.live = new Counter(3);
        this.gui = gui;
        this.highScoresTable = highScoresTable;
        this.aliens1 = new Counter(50);
    }

    /**
     * The function runs the game with the levels.
     * @param levels - gets list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        int levelNum = 1;
        // ...
      //  for (LevelInformation levelInfo : levels) {
        try {
            this.highScoresTable.load(new java.io.File("file"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
           // System.out.println("Value d");
        }
        while (this.live.getValue() > 0) {
           LevelInformation levelInfo = levels.get(0);
                GameLevel level = new GameLevel(levelInfo, this.ar, this.live, this.currentScore , this.gui,
                    this.aliens1, levelNum);
                level.initialize();
                if (this.aliens1.getValue() < 50) {
                    aliens1.increase(50);
                }
            while (this.aliens1.getValue() > 0) {
                level.playOneTurn();
                level.getSprites().removeSprite(level.getPaddle());
                level.getEnvironment().removeCollidable(level.getPaddle());
                //level.removeSprite(level.getPaddle());
                //level.removeCollidable(level.getPaddle());
                if (this.live.getValue() == 0) {
                    break;
                }
            }
            levelNum++;
        }
        Animation win = new Win(this.ks, this.currentScore);
        Animation lose = new Lose(this.ks, this.currentScore);
        Animation winPressed = new KeyPressStoppableAnimation(this.ks, this.ks.SPACE_KEY, win);
        Animation losePressed = new KeyPressStoppableAnimation(this.ks, this.ks.SPACE_KEY, lose);
        if (this.live.getValue() == 0) {
            this.ar.run(losePressed);
                //biuoop.DialogManager dialog = gui.getDialogManager();
                //String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                //System.out.println(name);
        } else if (this.live.getValue() != 0) {
            //break;
            this.ar.run(winPressed);
        }
       // this.ar.run(winPressed);
        if (this.highScoresTable.getRank(this.currentScore.getValue()) < 4) {
            if (this.highScoresTable.getRank(this.currentScore.getValue()) != -1) {
                biuoop.DialogManager dialog = gui.getDialogManager();
                String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                System.out.println(name);
                this.highScoresTable.add(new ScoreInfo(name, this.currentScore.getValue()));
                try {
                    this.highScoresTable.save(new java.io.File("file"));
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
                Animation highScoresAnimation = new HighScoresAnimation(this.highScoresTable, this.ks.SPACE_KEY, this
                        .ks);
                Animation highScoresAnimationPressed = new KeyPressStoppableAnimation(this.ks, this.ks.SPACE_KEY,
                        highScoresAnimation);
                this.ar.run(highScoresAnimation);
            }
        }
        //gui.close();
    }
}
