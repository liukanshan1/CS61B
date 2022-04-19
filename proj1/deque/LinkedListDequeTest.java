package deque;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Performs some basic linked list tests.
 */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     *  finally printing the results.
     *  @author CuiYuxin */
    public void addIsEmptySizeTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards.
     *  @author CuiYuxin */
    public void addRemoveTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

        lld1.addLast(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.addLast(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeLast();
        lld1.removeLast();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /** Tests removing from an empty deque
     *  @author CuiYuxin*/
    public void removeEmptyTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /** Check if you can create LinkedListDeques with different parameterized types and Check if the function
     *  get(index) and getRecursive(index) works well.
     *  @author CuiYuxin */
    public void multipleParamTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double> lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        assertEquals("string", lld1.get(0));
        assertEquals(3.14159, lld2.get(0), 0.00001); //第三个参数是允许误差
        assertEquals(true, lld3.get(0));

        assertEquals("string", lld1.getRecursive(0));
        assertEquals(3.14159, lld2.getRecursive(0), 0.00001);
        assertEquals(true, lld3.getRecursive(0));

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

        assertTrue("lld1 should be empty", lld1.isEmpty());
        assertTrue("lld2 should be empty", lld2.isEmpty());
        assertTrue("lld3 should be empty", lld3.isEmpty());
    }

    @Test
    /** check if null is return when removing from an empty LinkedListDeque.
     *  @author CuiYuxin */
    public void emptyNullReturnTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        //boolean passed1 = false;
        //boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /** Add large number of elements to deque; check if order is correct.
     *  @author CuiYuxin */
    public void bigLLDequeTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    /** Check if the function get(index) and getRecursive(index) works well.
     *  @author CuiYuxin */
    public void getItemTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addLast(3);
        lld1.addLast(4);
        //lld1.printDeque();
        assertEquals("Should return the correct value", 1, lld1.get(1), 0.0);
        assertEquals("Should return the correct value", 4, lld1.get(3), 0.0);
        assertEquals("Should return the correct value", 1, lld1.getRecursive(1), 0.0);
        assertEquals("Should return the correct value", 4, lld1.getRecursive(3), 0.0);
        assertEquals("Should return the correct value", null, lld1.getRecursive(-1));
        assertEquals("Should return the correct value", null, lld1.getRecursive(4));
        assertEquals("Should return the correct value", null, lld1.get(-1));
        assertEquals("Should return the correct value", null, lld1.get(4));
    }
}
