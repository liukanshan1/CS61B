/**
 * Linked list based deque.
 * @author CuiYuxin
 */
package deque;
import java.util.Iterator;

public class LinkedListDeque<Item> implements Iterable<Item>, Deque<Item> {
    private static class Node<Item> {
        Item data;
        Node<Item> next;
        Node<Item> prev;
    }
    private int size;
    private Node<Item> sentinel;

    /**
     * Creates an empty deque.
     * @author CuiYuxin
     */
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node<Item>();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * Inserts an item at the front.*
     * @author CuiYuxin
     */
    @Override
    public void addFirst(Item item) {
        Node<Item> node = new Node<Item>();
        node.data = item;
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next = node;
        node.next.prev = node;
        size++;
    }

    /**
     * Inserts item into the back of the deque.
     * @author CuiYuxin
     */
    @Override
    public void addLast(Item item) {
        Node<Item> node = new Node<Item>();
        node.data = item;
        node.next = sentinel;
        node.prev = sentinel.prev;
        node.prev.next = node;
        sentinel.prev = node;
        size++;
    }

    /**
     * Returns the number of items in the deque.
     * @author CuiYuxin
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * @author CuiYuxin
     */
    @Override
    public void printDeque() {
        //string s;
        Node<Item> node = sentinel.next;
        while (node != sentinel) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * @author CuiYuxin
     */
    @Override
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<Item> node = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return node.data;
    }

    /**
     * Deletes item from back of the deque and returns deleted item.
     * @author CuiYuxin
     */
    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<Item> node = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return node.data;
    }

    /**
     * Gets the ith item in the deque, using iterate.
     * @author CuiYuxin
     */
    @Override
    public Item get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<Item> node = sentinel.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    /**
     * Gets the ith item in the deque, using recurse.
     * @author CuiYuxin
     */
    public Item getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }

    /**
     * Gets the ith item in the deque, using recurse(a helping method).
     * @author CuiYuxin
     */
    private Item getRecursive(int index, Node<Item> node) {
        if (index == 0) {
            return node.data;
        }
        return getRecursive(index - 1, node.next);
    }

    /** Judege if two deques are equal.
     *  @author CuiYuxin
     */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque other = (Deque) o;
        if (other.size() != size()) {
            return false;
        }
        for (int j = 0; j < size(); j++) {
            if (!this.get(j).equals(other.get(j))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the iterator.
     * @author CuiYuxin
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    /**
     * The iterator.
     * @author CuiYuxin
     */
    private class DequeIterator implements Iterator<Item> {
        private Node<Item> node;

        public DequeIterator() {
            node = sentinel.next;
        }

        public boolean hasNext() {
            return node != sentinel;
        }

        public Item next() {
            if (!hasNext()) {
                //throw new NoSuchElementException();
                return null;
            }
            Item item = node.data;
            node = node.next;
            return item;
        }
    }
}
