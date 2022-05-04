package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {
    /**
     * The node of the BST.
     * @author CuiYuxin
     */
    private class BSTNode {
        BSTNode parent;
        BSTNode leftChild;
        BSTNode rightChild;
        K key;
        V value;

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size = 0;
    private BSTNode root = null;

    /**
     * Removes all of the mappings from this map.
     * @author CuiYuxin
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     * @author CuiYuxin
     */
    public boolean containsKey(K key) {
        return find(root, key) != null;
    }

    /**
     * Find the specified key in the BSTMap.
     * @author CuiYuxin
     */
    private BSTNode find(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return find(node.leftChild, key);
        } else {
            return find(node.rightChild, key);
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     * @author CuiYuxin
     */
    public V get(K key) {
        BSTNode node = find(root, key);
        if (node == null) {
            return null;
        } else {
            return node.value;
        }
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
     * @author CuiYuxin
     */
    public void put(K key, V value) {
        if (root == null) {
            root = new BSTNode(key, value);
            size++;
            return;
        }
        put(root, key, value);
    }

    /**
     * Associates the specified value with the specified key in this map.
     * @author CuiYuxin
     */
    private void put(BSTNode node, K key, V value) {
        if (key.equals(node.key)) {
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {
            if (node.leftChild == null) {
                node.leftChild = new BSTNode(key, value);
                node.leftChild.parent = node;
                size++;
            } else {
                put(node.leftChild, key, value);
            }
        } else {
            if (node.rightChild == null) {
                node.rightChild = new BSTNode(key, value);
                node.rightChild.parent = node;
                size++;
            } else {
                put(node.rightChild, key, value);
            }
        }
    }

    /**
     * Print all values in this map.
     * @author CuiYuxin
     */
    public void printInOrder() {
        printInOrder(root);
    }

    /**
     * Print all values in this map.
     * @author CuiYuxin
     */
    private void printInOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        printInOrder(node.leftChild);
        System.out.println(node.key + ":" + node.value + " ");
        printInOrder(node.rightChild);
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     * @author CuiYuxin
     */
    public Set<K> keySet() {
        LinkedList<K> list = new LinkedList<>();
        keySet(root, list);
        return new HashSet<>(list);
    }

    /**
     * Returns a Set view of the keys contained in this map.
     * @author CuiYuxin
     */
    private void keySet(BSTNode node, LinkedList<K> set) {
        if (node == null) {
            return;
        }
        keySet(node.leftChild, set);
        set.add(node.key);
        keySet(node.rightChild, set);
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     * @author CuiYuxin
     */
    public V remove(K key) {
        BSTNode node = find(root, key);
        if (node == null) {
            return null;
        }
        V value = node.value;
        remove(node);
        size--;
        return value;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.
     * @author CuiYuxin
     */
    public V remove(K key, V value) {
        BSTNode node = find(root, key);
        if (node == null) {
            return null;
        }
        if (node.value.equals(value)) {
            remove(node);
            size--;
        } else {
            return null;
        }
        return value;
    }

    /**
     * Remove the node.
     * @author CuiYuxin
     */
    private void remove(BSTNode node) {
        // node has no child
        if (node.leftChild == null && node.rightChild == null) {
            if (node.parent == null) {
                root = null;
            } else {
                if (node.parent.leftChild == node) {
                    node.parent.leftChild = null;
                } else {
                    node.parent.rightChild = null;
                }
            }
            return;
        }
        // node has two child
        if (node.leftChild != null && node.rightChild != null) {
            // find the max node in the left subtree
            BSTNode newParent = node.leftChild;
            while (newParent.rightChild != null) {
                newParent = newParent.rightChild;
            }
            K key = newParent.key;
            V value = newParent.value;
            remove(newParent);
            // replace the node
            BSTNode rightChild = node.rightChild;
            BSTNode leftChild = node.leftChild;
            BSTNode newNode = new BSTNode(key, value);
            newNode.rightChild = rightChild;
            rightChild.parent = newNode;
            newNode.leftChild = leftChild;
            leftChild.parent = newNode;
            if (node.parent == null) {
                root = newNode;
            } else {
                if (node.parent.leftChild == node) {
                    node.parent.leftChild = newNode;
                } else {
                    node.parent.rightChild = newNode;
                }
            }
            return;
        }
        // node has one child
        BSTNode child = node.leftChild != null ? node.leftChild : node.rightChild;
        if (node.parent == null) {
            root = child;
            child.parent = null;
        } else {
            if (node.parent.leftChild == node) {
                node.parent.leftChild = child;
            } else {
                node.parent.rightChild = child;
            }
            child.parent = node.parent;
        }
        return;
    }

    /**
     * Returns an iterator of the keys in this map.
     * @author CuiYuxin
     */
    @Override
    public Iterator<K> iterator() {
        return new BSTKeyIterator();
    }

    /**
     * Iterator class of the keys in this map.
     * @author CuiYuxin
     */
    class BSTKeyIterator implements Iterator<K> {
        private LinkedList<K> list;
        private int index = 0;

        BSTKeyIterator() {
            list = new LinkedList<>();
            keySet(root, list);
        }

        @Override
        public boolean hasNext() {
            return index < list.size();
        }

        @Override
        public K next() {
            return list.get(index++);
        }
    }
}

