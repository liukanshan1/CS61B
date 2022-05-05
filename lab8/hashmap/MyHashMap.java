package hashmap;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author CuiYuxin
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
    private int size = 0;
    private double maxLoad = 0.75;
    private int initialCapacity = 16;
    private Set<K> keySet = new HashSet<>();

    /**
     * Constructors
     */
    public MyHashMap() {
        buckets = createTable(initialCapacity);
    }

    public MyHashMap(int initialSize) {
        initialCapacity = initialSize;
        buckets = createTable(initialCapacity);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.maxLoad = maxLoad;
        this.initialCapacity = initialSize;
        buckets = createTable(initialCapacity);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<Node>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        return table;
    }

    /**
     * Removes all of the mappings from this map.
     * @author CuiYuxin
     */
    public void clear() {
        size = 0;
        buckets = createTable(initialCapacity);
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     * @author CuiYuxin
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     * @author CuiYuxin
     */
    public V get(K key) {
        int index = getIndex(key);
        if (buckets[index] == null) {
            return null;
        }
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    /**
     * Returns the index to which the specified key is mapped.
     * @author CuiYuxin
     */
    private int getIndex(K key) {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    /**
     * Returns the number of key-value mappings in this map.
     * @author CuiYuxin
     */
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     * @author CuiYuxin
     */
    public void put(K key, V value) {
        keySet.add(key);
        int index = getIndex(key);
        if (buckets[index] == null) {
            buckets[index] = createBucket();
        }
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        buckets[index].add(createNode(key, value));
        size++;
        if (size > buckets.length * maxLoad) {
            resize();
        }
    }

    /**
     * Resizes the table to a new size.
     * @author CuiYuxin
     */
    private void resize() {
        Collection<Node>[] oldBuckets = buckets;
        buckets = createTable(buckets.length * 2);
        size = 0;
        for (Collection<Node> bucket : oldBuckets) {
            if (bucket != null) {
                for (Node node : bucket) {
                    put(node.key, node.value);
                }
            }
        }
    }

    /**
     * Returns a Set view of the keys contained in this map.
     * @author CuiYuxin
     */
    public Set<K> keySet() {
        return this.keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     * @author CuiYuxin
     */
    public V remove(K key) {
        if (containsKey(key)) {
            int index = getIndex(key);
            for (Node node : buckets[index]) {
                if (node.key.equals(key)) {
                    V value = node.value;
                    buckets[index].remove(node);
                    keySet.remove(key);
                    size--;
                    return value;
                }
            }
        }
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     * @author CuiYuxin
     */
    public V remove(K key, V value) {
        if (containsKey(key)) {
            int index = getIndex(key);
            for (Node node : buckets[index]) {
                if (node.key.equals(key) && node.value.equals(value)) {
                    buckets[index].remove(node);
                    keySet.remove(key);
                    size--;
                    return value;
                }
            }
        }
        return null;
    }

    /**
     * Returns an iterator over the keys of this map.
     * @author CuiYuxin
     */
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<K> {
        private int currentIndex = 0;
        private Iterator it = keySet.iterator();

        public boolean hasNext() {
            return it.hasNext();
        }

        public K next() {
            if (this.hasNext()) {
                return (K) it.next();
            }
            return null;
        }
    }

}
