import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: LinkedListDeque61B
 * Package: PACKAGE_NAME
 * Description: LinkedList implements Deque61B
 *
 * @Author harkjeans
 * @Create 2025/11/8 12:04
 * @Version 1.0
 */
public class LinkedListDeque61B<T> implements Deque61B<T> {

    private Node sentinel;
    private int size;

    private class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node newNode = new Node(x, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node currentNode = sentinel.next;
        while (currentNode != sentinel) {
            returnList.add(currentNode.item);
            currentNode = currentNode.next;
        }
        return returnList;
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
