package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    /** Test BuggyAList.
     *  @author CuiYuxin
     */
    @Test
    public void testBuggyAList() {
        BuggyAList<Integer> list1 = new BuggyAList<>();
        AListNoResizing<Integer> list2 = new AListNoResizing<>();
        list1.addLast(4);
        list2.addLast(4);
        list1.addLast(5);
        list2.addLast(5);
        list1.addLast(6);
        list2.addLast(6);
        for (int i = 0; i < list1.size(); i++) {
            assertEquals(list1.get(i), list2.get(i));
        }
        list1.removeLast();
        list2.removeLast();
        for (int i = 0; i < list1.size(); i++) {
            assertEquals(list1.get(i), list2.get(i));
        }
        list1.removeLast();
        list2.removeLast();
        for (int i = 0; i < list1.size(); i++) {
            assertEquals(list1.get(i), list2.get(i));
        }
        list1.removeLast();
        list2.removeLast();
        assertEquals(list1.size(), list2.size());
    }

    @Test
    public void testBuggyAListRandom() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L1 = new BuggyAList<>();
        int N = 5000;
        for (int j = 0; j < N; j += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L1.addLast(randVal);
                for (int i = 0; i < L.size(); i++) {
                    assertEquals(L.get(i), L1.get(i));
                }
            } else if (operationNumber == 1) {
                // size
                assertEquals(L.size(), L1.size());
            } else if (operationNumber == 2) {
                // removeLast
                if (L.size() > 0) {
                    L.removeLast();
                    L1.removeLast();
                    for (int i = 0; i < L.size(); i++) {
                        assertEquals(L.get(i), L1.get(i));
                    }
                }
            }

        }
    }
}
