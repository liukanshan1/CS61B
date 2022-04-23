package deque;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testCase1() {
        Deque<Double> buffer = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            buffer.addLast(0.0);
        }
        for (int i = 0; i < 500000; i++) {
            double first = buffer.removeFirst();
            double second = buffer.get(0);
            buffer.addLast(first);
        }
    }

    @Test
    public void testAddAndGet() {
        //Adding 0 through N-1 with addLast,
        // then checking that get returns the right number.
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            d.addLast(i);
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals((int)i, (int)d.get(i));
        }
    }

}
