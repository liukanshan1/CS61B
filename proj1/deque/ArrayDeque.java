/**
 * Array based deque.
 *
 * @author CuiYuxin
 */
package deque;

import java.util.Iterator;


public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private int size;
    private T[] arr;
    private int nextFirst;
    private int nextLast;

    /**
     * Creates an empty deque.
     *
     * @author CuiYuxin
     */
    public ArrayDeque() {
        size = 0;
        arr = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
    }

    /**
     * Inserts an item at the front.*
     *
     * @author CuiYuxin
     */
    @Override
    public void addFirst(T t) {
        if (size == arr.length) {
            resize(size * 2);
        }
        arr[nextFirst] = t;
        size++;
        nextFirst = toPrev(nextFirst);
    }

    /**
     * Inserts item into the back of the deque.
     *
     * @author CuiYuxin
     */
    @Override
    public void addLast(T t) {
        if (size == arr.length) {
            resize(size * 2);
        }
        arr[nextLast] = t;
        size++;
        nextLast = toNext(nextLast);
    }

    /**
     * Returns the number of items in the deque.
     *
     * @author CuiYuxin
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     *
     * @author CuiYuxin
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

    /**
     * Removes and returns the item at the front of the deque.
     *
     * @author CuiYuxin
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = toNext(nextFirst);
        T t = arr[nextFirst];
        arr[nextFirst] = null;
        size--;
        if (arr.length >= 16 && size <= arr.length / 4) {
            resize(arr.length / 2);
        }
        return t;
    }

    /**
     * Deletes item from back of the deque and returns deleted item.
     *
     * @author CuiYuxin
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = toPrev(nextLast);
        T t = arr[nextLast];
        arr[nextLast] = null;
        size--;
        if (arr.length >= 16 && size <= arr.length / 4) {
            resize(arr.length / 2);
        }
        return t;
    }

    /**
     * Gets the ith item in the deque.
     *
     * @author CuiYuxin
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int i = toNext(nextFirst);
        for (int j = 0; j < index; j++) {
            i = toNext(i);
        }
        return arr[i];
    }

    /**
     * Resizes the underlying array to the target capacity.
     *
     * @author CuiYuxin
     */
    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        int i = toNext(nextFirst);
        int j = 1;
        do {
            temp[j] = arr[i];
            j++;
            i = toNext(i);
        } while (i != nextLast);
        nextFirst = 0;
        nextLast = j;
        arr = temp;
    }

    /**
     * Returns the next index of the array.
     *
     * @author CuiYuxin
     */
    private int toNext(int index) {
        if (index == arr.length - 1) {
            index = 0;
        } else {
            index++;
        }
        return index;
    }

    /**
     * Returns the previous index of the array.
     *
     * @author CuiYuxin
     */
    private int toPrev(int index) {
        if (index == 0) {
            index = arr.length - 1;
        } else {
            index--;
        }
        return index;
    }

    /**
     * Judege if two deques are equal.
     *
     * @author CuiYuxin
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
     *
     * @author CuiYuxin
     */
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    /**
     * The iterator.
     *
     * @author CuiYuxin
     */
    private class DequeIterator implements Iterator<T> {
        private int index;

        DequeIterator() {
            index = nextFirst + 1;
        }

        /**
         * Returns if there is a next item.
         *
         * @author CuiYuxin
         */
        public boolean hasNext() {
            return index != nextLast;
        }

        /**
         * Returns the next item.
         *
         * @author CuiYuxin
         */
        public T next() {
            if (!hasNext()) {
                //throw new NoSuchElementException();
                return null;
            }
            T t = arr[index];
            index = toNext(index);
            return t;
        }
    }
}
