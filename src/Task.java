/**
 * Created by 1 on 22/06/2017.
 */

/**
 * The task.
 * @param <T> - gets a type.
 */
public interface Task<T> {
    /**
     * return the appropriate action depending on the selected T type.
     * @return -  return the appropriate action depending on the selected T type.
     */
    T run();
}
