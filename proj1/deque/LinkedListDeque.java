package deque;

public class LinkedListDeque<Item> {
    static class Node<Item> {
        Item data;
        Node<Item> next;
        Node<Item> prev;
    }

    private int size;
    private Node<Item> sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node<Item>();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(Item item) {
        Node<Item> node = new Node<Item>();
        node.data = item;
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next = node;
        node.next.prev = node;
        size++;
    }

    public void addLast(Item item) {
        Node<Item> node = new Node<Item>();
        node.data = item;
        node.next = sentinel;
        node.prev = sentinel.prev;
        node.prev.next = node;
        sentinel.prev = node;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        //string s;
        Node<Item> node = sentinel.next;
        while (node != sentinel) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

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

    public Item getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }

    private Item getRecursive(int index, Node<Item> node) {
        if (index == 0) {
            return node.data;
        }
        return getRecursive(index - 1, node.next);
    }
}
