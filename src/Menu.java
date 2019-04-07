/**
 * Created by 1 on 20/06/2017.
 */

/**
 * The menu.
 * @param <T> - gets a type.
 */
public interface Menu<T> extends Animation {
    /**
     * The function add to menu.
     * @param key gets a string.
     * @param message - gets a string.
     * @param returnVal - gets a type.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * The function return the type of the menu.
     * @return - return the type of the menu.
     */
    T getStatus();

    /**
     * The function builds a secondary menu.
     * @param key gets a string.
     * @param message - gets a string.
     * @param subMenu - gets a subMenu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}