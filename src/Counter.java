
/**
 * Created by 1 on 01/06/2017.
 */
public class Counter {
    private int value;

    /**
     * The building of the Counter.
     * @param value - gets value.
     */
    public Counter(int value) {
        this.value = value;
    }
    /**
     *  add number to current count.
     * @param number -  add number to current count.
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * subtract number from current count.
     * @param number - subtract number from current count.
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * get current count.
     * @return - get current count.
     */
    public int getValue() {
      return this.value;
    }
}