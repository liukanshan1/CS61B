package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    /** Test the case of 2.
     *  @author CuiYuxin
     */
    @Test
    public void testSquarePrimesCase2a() {
        IntList lst = IntList.of(2, 15, 16, 18, 20);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 15 -> 16 -> 18 -> 20", lst.toString());
        assertTrue(changed);
    }

    /** Test the case of 2.
     *  @author CuiYuxin
     */
    @Test
    public void testSquarePrimesCase2b() {
        IntList lst = IntList.of(15, 2, 16, 18, 20);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("15 -> 4 -> 16 -> 18 -> 20", lst.toString());
        assertTrue(changed);
    }

    /** Test the case of 1.
     *  @author CuiYuxin
     */
    @Test
    public void testSquarePrimesCase1() {
        IntList lst = IntList.of(1, 15, 16, 18, 20);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 15 -> 16 -> 18 -> 20", lst.toString());
        assertFalse(changed);
    }

    /** Test the case of 0.
     *  @author CuiYuxin
     */
    @Test
    public void testSquarePrimesCase0() {
        IntList lst = IntList.of(1, 15, 16, 18, 0);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 15 -> 16 -> 18 -> 0", lst.toString());
        assertFalse(changed);
    }

    /** Test the case of negative.
     *  @author CuiYuxin
     */
    @Test
    public void testSquarePrimesCaseNeg() {
        IntList lst = IntList.of(-1, -3, -16, -18, 0);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("-1 -> -3 -> -16 -> -18 -> 0", lst.toString());
        assertFalse(changed);
    }

    /** Test the case of many prime.
     *  @author CuiYuxin
     */
    @Test
    public void testSquarePrimesCaseMP() {
        IntList lst = IntList.of(2, 3, 5, 7, 20);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 9 -> 25 -> 49 -> 20", lst.toString());
        assertTrue(changed);
    }

}
