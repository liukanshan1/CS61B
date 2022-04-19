/**
 * Linked list based deque.
 *
 * @author CuiYuxin
 */
package deque;

public class LinkedListDeque<Item> {
    static class Node<Item> {
        Item data;
        Node<Item> next;
        Node<Item> prev;
    }

    private int size;
    private Node<Item> sentinel;

    /**
     * Creates an empty deque.
     *
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
     *
     * @author CuiYuxin
     */
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
     *
     * @author CuiYuxin
     */
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
     * Returns if the deque is empty.
     *
     * @author CuiYuxin
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     *
     * @author CuiYuxin
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     *
     * @author CuiYuxin
     */
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
     *
     * @author CuiYuxin
     */
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
     *
     * @author CuiYuxin
     */
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
     *
     * @author CuiYuxin
     */
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
     *
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
     *
     * @author CuiYuxin
     */
    private Item getRecursive(int index, Node<Item> node) {
        if (index == 0) {
            return node.data;
        }
        return getRecursive(index - 1, node.next);
    }
}
