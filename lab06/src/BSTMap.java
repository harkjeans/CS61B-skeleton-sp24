import java.util.*;

/**
 * ClassName: BSTMap
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author harkjeans
 * @Create 2025/11/17 18:56
 * @Version 1.0
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private Node root;
    private int size;
    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public BSTMap() {
        this.root = null;
        this.size = 0;
    }

    /** Associates the specified value with the specified key in this map.
     *  If the map already contains the specified key, replaces the key's mapping
     *  with the value specified. */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    /** Returns whether this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    private boolean containsKey(Node node, K key) {
        if (node == null) {
            return false;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return containsKey(node.left, key);
        } else if (cmp > 0) {
            return containsKey(node.right, key);
        } else {
            return true;
        }
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /** Removes every mapping from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new TreeSet<>();
        addKeys(root, keys);
        return keys;
    }

    private void addKeys(Node node, Set<K> keys) {
        if (node == null) {
            return;
        }

        addKeys(node.left, keys);
        keys.add(node.key);
        addKeys(node.right, keys);
    }

    /** Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping. */
    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        V value = get(key);
        root = remove(root, key);
        return value;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            size--;

            if (node.right == null) {
                return node.left;
            }

            if (node.left == null) {
                return node.right;
            }

            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        return node;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    /** Returns an iterator over elements of type {@code T}. */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
