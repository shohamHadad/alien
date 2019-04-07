import biuoop.GUI;

/**
 * Created by 1 on 22/06/2017.
 */
public class Ass7Game {
    /**
     * The function runs the game with the final.
     *
     * @param args - gets args.
     */
    public static void main(String[] args) {
        java.util.List<LevelInformation> levels = new java.util.ArrayList<LevelInformation>(0);
        Level l1 = new Level();
        //levels.add(l1);
        GUI gui = new biuoop.GUI("game", 800, 600);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui);
        Counter aliens1 = new Counter(50);
        HighScoresTable highScoresTable = null;
        try {
            highScoresTable = HighScoresTable.loadFromFile(new java.io.File("file"));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (highScoresTable == null) {
            highScoresTable = new HighScoresTable(4);
        }
        if (levels.size() == 0) {
            levels.add(l1);
        GameFlow gameFlow = new GameFlow(runner, keyboard, gui, highScoresTable);
        //gameFlow.runLevels(levels);

        while (true) {
            Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboard, "Space Invaders");

            menu.addSelection("s", "Start Game", new StartGame(runner, keyboard, gui, highScoresTable,
                    levels));
            menu.addSelection("h", "HighScoresTable", new ShowHiScoresTask(runner,
                    new HighScoresAnimation(highScoresTable, keyboard.SPACE_KEY, keyboard)));
            menu.addSelection("q", "Quit", new QuitGame(runner));

            runner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }
}
}


