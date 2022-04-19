package deque;

public class ArrayDeque<Item> {
    private int size;
    private Item[] arr;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        arr = (Item[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
    }

    public void addFirst(Item item) {
        if (size == arr.length) {
            resize(size * 2);
        }
        arr[nextFirst] = item;
        size++;
        nextFirst= toPrev(nextFirst);
    }

    public void addLast(Item item) {
        if (size == arr.length) {
            resize(size * 2);
        }
        arr[nextLast] = item;
        size++;
        nextLast= toNext(nextLast);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = nextFirst+1;
        while(i != nextLast) {
            System.out.print(arr[i] + " ");
            i= toNext(i);
        }
        System.out.println();
    }

    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = toNext(nextFirst);
        Item item = arr[nextFirst];
        arr[nextFirst] = null;
        size--;
        if (arr.length >= 16  && size <= arr.length / 4) {
            resize(arr.length / 2);
        }
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = toPrev(nextLast);
        Item item = arr[nextLast];
        arr[nextLast] = null;
        size--;
        if (arr.length >= 16  && size <= arr.length / 4) {
            resize(arr.length / 2);
        }
        return item;
    }


    public Item get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int i = nextFirst+1;
        for (int j = 0; j < index; j++) {
            i = toNext(i);
        }
        return arr[i];
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        int i = nextFirst + 1;
        int j = 1;
        while(i != nextLast) {
            temp[j] = arr[i];
            j++;
            i= toNext(i);
        }
        nextFirst = 0;
        nextLast = j;
        arr = temp;
    }

    private int toNext(int index) {
        if (index == arr.length - 1) {
            index = 0;
        } else {
            index++;
        }
        return index;
    }

    private int toPrev(int index) {
        if (index == 0) {
            index = arr.length - 1;
        } else {
            index--;
        }
        return index;
    }

}
