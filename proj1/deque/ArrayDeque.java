/** Array based deque.
 *  @author CuiYuxin
 */
package deque;

public class ArrayDeque<Item> {
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
    public void addFirst(Item item) {
        if (size == arr.length) {
            resize(size * 2);
        }
        arr[nextFirst] = item;
        size++;
        nextFirst= toPrev(nextFirst);
    }

    /** Inserts item into the back of the deque.
     * @author CuiYuxin
     */
    public void addLast(Item item) {
        if (size == arr.length) {
            resize(size * 2);
        }
        arr[nextLast] = item;
        size++;
        nextLast= toNext(nextLast);
    }

    /** Returns if the deque is empty.
     *  @author CuiYuxin
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque.
     *  @author CuiYuxin
     */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     *  @author CuiYuxin
     */
    public void printDeque() {
        int i = nextFirst+1;
        while(i != nextLast) {
            System.out.print(arr[i] + " ");
            i= toNext(i);
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     *  @author CuiYuxin
     */
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

    /** Deletes item from back of the deque and returns deleted item.
     *  @author CuiYuxin
     */
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

    /** Gets the ith item in the deque.
     *  @author CuiYuxin
     */
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

    /** Resizes the underlying array to the target capacity.
     *  @author CuiYuxin
     */
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
}
