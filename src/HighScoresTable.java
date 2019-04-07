import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by 1 on 11/06/2017.
 */
public class HighScoresTable implements java.io.Serializable {

    private int size;
    private List<ScoreInfo> highScoresTable;

    /**
     * constructor - Create an empty high-scores tabe with the specified size.
     * The size means that the table hollds up to size top scores.
     *
     * @param size - gets a size of table.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.highScoresTable = new java.util.LinkedList<ScoreInfo>();
    }

    /**
     * Add a high-score.
     *
     * @param score - gets a score.
     */
    public void add(ScoreInfo score) {
        int indxs = -1;
        if (this.highScoresTable.size() != 0) {
            for (int i = 0; i < this.highScoresTable.size(); i++) {
                if (score.getScore() >= this.highScoresTable.get(i).getScore()) {
                    indxs = i;
                    this.highScoresTable.add(i, score);
                    break;
                }
            }
        } else {
            this.highScoresTable.add(score);
        }
        if (this.highScoresTable.size() > this.size()) {
            this.highScoresTable.remove(this.highScoresTable.size() - 1);
        }
    }

    /**
     * Return table size.
     *
     * @return - Return table size.
     */
    public int size() {
        return this.size;
    }

    /**
     * The list is sorted such that the highest scores come first.
     *
     * @return - Return the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScoresTable;
    }

    /**
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     *
     * @param score - gets score of game.
     * @return - return the rank of the current score: where will it
     * be on the list if added?
     */
    public int getRank(int score) {
        if (this.highScoresTable.size() == 0) {
            return 1;
        }
        for (int i = 0; i < this.highScoresTable.size(); i++) {
            if (score >= this.highScoresTable.get(i).getScore()) {
                return i;
            }
        }
        return 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.highScoresTable.clear();
    }

    /**
     * Load table data from file.
     * Current table data is cleared.
     *
     * @param filename - gets file filename.
     * @throws IOException - exception.
     */
    public void load(File filename) throws IOException {

        ObjectInputStream objectInputStream = null;
        try {
            HighScoresTable highScoresTable1 = new HighScoresTable(this.size);

            objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            highScoresTable1 = (HighScoresTable) objectInputStream.readObject();
            this.highScoresTable = highScoresTable1.highScoresTable;
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            return;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename);
            return;
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename - gets file filename.
     * @throws IOException - exception.
     */
    public void save(File filename) throws IOException {

        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename - gets file filename.
     * @return - return table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        ObjectInputStream objectInputStream = null;
        try {

            objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            return (HighScoresTable) objectInputStream.readObject();
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            return null;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename);
            return null;
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return null;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }
}
