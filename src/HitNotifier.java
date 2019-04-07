/**
 * Created by 1 on 23/05/2017.
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.

    /**
     * Add hl as a listener to hit events.
     * @param hl - gets HitListener
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - gets HitListener
     */
    void removeHitListener(HitListener hl);
}