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
public class ArrayDeque61B<T> implements Deque61B<T>{

    private T[] item;
    private int size;
    private int nextFirst;
    private int nextLast;

    private static final int INITIAL_CAPACITY = 8;

    public ArrayDeque61B() {
        item = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    @Override
    public void addFirst(T x) {

    }

    @Override
    public void addLast(T x) {

    }

    @Override
    public List<T> toList() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
