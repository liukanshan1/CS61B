package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> maxComparator;

    /**
     * Constructor.
     *
     * @author CuiYuxin
     */
    public MaxArrayDeque(Comparator<T> c) {
        maxComparator = c;
    }

    /**
     * Return the max item in the deque, using Comparator given by constructor.
     *
     * @author CuiYuxin
     */
    public T max() {
        if (size() == 0) {
            return null;
        }
        T maxT = get(0);
        for (T i : this) {
            if (maxComparator.compare(maxT, i) < 0) {
                maxT = i;
            }
        }
        return maxT;
    }

    /**
     * Return the max item in the deque, using Comparator given by parameter.
     *
     * @author CuiYuxin
     */
    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        T maxT = get(0);
        for (T i : this) {
            if (c.compare(maxT, i) < 0) {
                maxT = i;
            }
        }
        return maxT;
    }
}
