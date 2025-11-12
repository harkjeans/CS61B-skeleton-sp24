package deque;

import java.util.Comparator;

/**
 * ClassName: MaxArrayDeque61B
 * Package: deque
 * Description:
 *
 * @Author harkjeans
 * @Create 2025/11/12 14:58
 * @Version 1.0
 */
public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {

    private final Comparator<T> defaultComparator;

    public MaxArrayDeque61B(Comparator<T> c) {
        super();
        this.defaultComparator = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }

        T maxItem = get(0);
        for (int i = 0; i < size(); i++) {
            if (defaultComparator.compare(get(i), maxItem) > 0) {
                maxItem = get(i);
            }
        }

        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }

        T maxItem = get(0);
        for (int i = 0; i < size(); i++) {
            if (c.compare(get(i), maxItem) > 0) {
                maxItem = get(i);
            }
        }

        return maxItem;
    }

}
