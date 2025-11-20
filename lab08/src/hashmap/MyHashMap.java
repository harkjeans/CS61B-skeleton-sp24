package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author harkjeans
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size;
    private double loadFactor;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    @SuppressWarnings("unchecked")
    public MyHashMap(int initialCapacity, double loadFactor) {
        if (initialCapacity < 1 || loadFactor <= 0) {
            throw new IllegalArgumentException();
        }

        this.buckets = (Collection<Node>[]) new Collection[initialCapacity];
        this.loadFactor = loadFactor;
        this.size = 0;

        for (int i = 0; i < initialCapacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    // Helper method to calculate bucket index
    private int getBucketIndex(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Null keys are not allowed");
        }
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    // Helper method to find node in a bucket
    private Node findNodeInBucket(Collection<Node> bucket, K key) {
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Collection<Node>[] oldBuckets = buckets;
        int newCapacity = buckets.length * 2;
        buckets = (Collection<Node>[]) new Collection[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            buckets[i] = createBucket();
        }

        size = 0;
        for (Collection<Node> bucket : oldBuckets) {
            for (Node node : bucket) {
                put(node.key, node.value);
            }
        }
    }

    @Override
    public void put(K key, V value) {
        if ((double) size / buckets.length > loadFactor) {
            resize();
        }

        int index = getBucketIndex(key);
        Collection<Node> bucket = buckets[index];

        Node existingNode = findNodeInBucket(bucket, key);
        if (existingNode != null) {
            existingNode.value = value;
        } else {
            bucket.add(new Node(key, value));
            size++;
        }
    }

    @Override
    public V get(K key) {
        int index = getBucketIndex(key);
        Collection<Node> bucket = buckets[index];

        Node node = findNodeInBucket(bucket, key);
        return (node != null) ? node.value : null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = getBucketIndex(key);
        Collection<Node> bucket = buckets[index];

        return findNodeInBucket(bucket, key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                keys.add(node.key);
            }
        }
        return keys;
    }

    @Override
    public V remove(K key) {
        int index = getBucketIndex(key);
        Collection<Node> bucket = buckets[index];

        Iterator<Node> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node.key.equals(key)) {
                V value = node.value;
                iterator.remove();
                size--;
                return value;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

}
