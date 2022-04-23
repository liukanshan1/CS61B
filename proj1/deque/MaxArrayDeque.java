package deque;
import java.util.Comparator;
public class MaxArrayDeque<Item> extends ArrayDeque<Item> {
    private Comparator<Item> maxComparator;

    /** Constructor.
     *  @author CuiYuxin
     */
    public MaxArrayDeque(Comparator<Item> c) {
        maxComparator = c;
    }

    /** Return the max item in the deque, using Comparator given by constructor.
     *  @author CuiYuxin
     */
    public Item max() {
        if (size() == 0) {
            return null;
        }
        Item maxItem = get(0);
        for (Item i : this) {
            if (maxComparator.compare(maxItem, i) < 0) {
                maxItem = i;
            }
        }
        return maxItem;
    }

    /** Return the max item in the deque, using Comparator given by parameter.
     *  @author CuiYuxin
     */
    public Item max(Comparator<Item> c) {
        if (size() == 0) {
            return null;
        }
        Item maxItem = get(0);
        for (Item i : this) {
            if (c.compare(maxItem, i) < 0) {
                maxItem = i;
            }
        }
        return maxItem;
    }
}
