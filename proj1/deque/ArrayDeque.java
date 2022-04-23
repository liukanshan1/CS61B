/**
 * Array based deque.
 * @author CuiYuxin
 */
package deque;
import java.util.Iterator;


public class ArrayDeque<Item> implements Iterable<Item>, Deque<Item> {
    private int size;
    private Item[] arr;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty deque.
     *  @author CuiYuxin
     */
    public ArrayDeque() {
        size = 0;
        arr = (Item[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
    }

    /** Inserts an item at the front.*
     * @author CuiYuxin
     */
    @Override
    public void addFirst(Item item) {
        if (size == arr.length) {
            resize(size * 2);
        }
        arr[nextFirst] = item;
        size++;
        nextFirst = toPrev(nextFirst);
    }

    /** Inserts item into the back of the deque.
     * @author CuiYuxin
     */
    @Override
    public void addLast(Item item) {
        if (size == arr.length) {
            resize(size * 2);
        }
        arr[nextLast] = item;
        size++;
        nextLast = toNext(nextLast);
    }

    /** Returns the number of items in the deque.
     *  @author CuiYuxin
     */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     *  @author CuiYuxin
     */
    @Override
    public void printDeque() {
        int i = nextFirst + 1;
        while (i != nextLast) {
            System.out.print(arr[i] + " ");
            i = toNext(i);
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     *  @author CuiYuxin
     */
    @Override
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = toNext(nextFirst);
        Item item = arr[nextFirst];
        arr[nextFirst] = null;
        size--;
        if (arr.length >= 16 && size <= arr.length / 4) {
            resize(arr.length / 2);
        }
        return item;
    }

    /** Deletes item from back of the deque and returns deleted item.
     *  @author CuiYuxin
     */
    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = toPrev(nextLast);
        Item item = arr[nextLast];
        arr[nextLast] = null;
        size--;
        if (arr.length >= 16 && size <= arr.length / 4) {
            resize(arr.length / 2);
        }
        return item;
    }

    /** Gets the ith item in the deque.
     *  @author CuiYuxin
     */
    @Override
    public Item get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int i = toNext(nextFirst);
        for (int j = 0; j < index; j++) {
            i = toNext(i);
        }
        return arr[i];
    }

    /** Resizes the underlying array to the target capacity.
     *  @author CuiYuxin
     */
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        int i = nextFirst + 1;
        int j = 1;
        while (i != nextLast) {
            temp[j] = arr[i];
            j++;
            i = toNext(i);
        }
        nextFirst = 0;
        nextLast = j;
        arr = temp;
    }

    /** Returns the next index of the array.
     *  @author CuiYuxin
     */
    private int toNext(int index) {
        if (index == arr.length - 1) {
            index = 0;
        } else {
            index++;
        }
        return index;
    }

    /** Returns the previous index of the array.
     *  @author CuiYuxin
     */
    private int toPrev(int index) {
        if (index == 0) {
            index = arr.length - 1;
        } else {
            index--;
        }
        return index;
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

    /** Returns the iterator.
     *  @author CuiYuxin
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    /** The iterator.
     *  @author CuiYuxin
     */
    private class DequeIterator implements Iterator<Item> {
        private int index;

        /** Constructor.
         *  @author CuiYuxin
         */
        public DequeIterator() {
            index = nextFirst + 1;
        }

        /** Returns if there is a next item.
         *  @author CuiYuxin
         */
        public boolean hasNext() {
            return index != nextLast;
        }

        /** Returns the next item.
         *  @author CuiYuxin
         */
        public Item next() {
            if (!hasNext()) {
                //throw new NoSuchElementException();
                return null;
            }
            Item item = arr[index];
            index = toNext(index);
            return item;
        }
    }
}
