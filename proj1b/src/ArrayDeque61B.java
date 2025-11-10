import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ArrayDeque61B
 * Package: PACKAGE_NAME
 * Description: Array implements Deque61B
 *
 * @Author harkjeans
 * @Create 2025/11/9 21:26
 * @Version 1.0
 */
public class ArrayDeque61B<T> implements Deque61B<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private static final int INITIAL_CAPACITY = 8;

    public ArrayDeque61B() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, items.length);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();

        if (isEmpty()) {
            return returnList;
        }

        int current = Math.floorMod(nextFirst + 1, items.length);
        for (int i = 0; i < size; i++) {
            returnList.add(items[current]);
            current = Math.floorMod(current + 1, items.length);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        int firstIndex = Math.floorMod(nextFirst + 1, items.length);
        T returnItem = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;
        size--;

        if (items.length >= 2 * INITIAL_CAPACITY && size < items.length / 4) {
            resize(items.length / 2);
        }

        return returnItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        int lastIndex = Math.floorMod(nextLast - 1, items.length);
        T returnItem = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size--;

        if (items.length >= 2 * INITIAL_CAPACITY && size < items.length / 4) {
            resize(items.length / 2);
        }

        return returnItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int current = Math.floorMod(nextFirst + 1 + index, items.length);
        return items[current];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];

        int current = Math.floorMod(nextFirst + 1, items.length);
        for (int i = 0; i < size; i++) {
            newItems[i] = items[current];
            current = Math.floorMod(current + 1, items.length);
        }

        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }
}
