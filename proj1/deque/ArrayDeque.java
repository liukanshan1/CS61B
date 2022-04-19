package deque;

public class ArrayDeque<Item> {
    private Item size;
    private Item[] arr;

    public ArrayDeque() {
        size = 0;
        arr = (Item[]) new Object[8];
    }

    public void addFirst(Item item) {
        if (size == arr.length) {
            resize(size * 2);
        }
        for (int i = size; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = item;
        size++;
    }

    public void addLast(Item item) {
        if (size == arr.length) {
            resize(size * 2);
        }
        arr[size] = item;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Item item = arr[0];
        for (int i = 0; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        if (arr.length >= 16  && size <= arr.length / 4) {
            resize(arr.length / 4);
        }
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item item = arr[size - 1];
        arr[size - 1] = null;
        size--;
        if (arr.length >= 16  && size <= arr.length / 4) {
            resize(arr.length / 4);
        }
        return item;
    }

    public Item get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return arr[index];
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

}
