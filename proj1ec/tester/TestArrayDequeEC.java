package tester;

import static org.junit.Assert.*;

import org.junit.Test;
import student.StudentArrayDeque;
import edu.princeton.cs.introcs.StdRandom;

/**
 * A test of ArrayDeque.
 *
 * @author CuiYuxin
 */
public class TestArrayDequeEC {
    /**
     * Testing for adding items.
     * @author CuiYuxin
     */
    @Test
    public void testAdd() {
        StudentArrayDeque<Integer> stu = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<Integer>();
        ArrayDequeSolution<String> info = new ArrayDequeSolution<String>();
        for (int i = 0; i < 100; i++) {
            String temp = "";
            int rand = StdRandom.uniform(300);
            if (rand < 150) {
                stu.addLast(rand);
                sol.addLast(rand);
                temp += "addLast(" + rand + ")";
            } else {
                stu.addFirst(rand);
                sol.addFirst(rand);
                temp += "addFirst(" + rand + ")";
            }
            if (info.size() < 3) {
                info.addLast(temp);
            } else {
                info.removeFirst();
                info.addLast(temp);
            }
        }
        String message = "";
        for (int i = 0; i < info.size(); i++) {
            message += info.get(i) + "\n";
        }
        assertEquals(message, sol.size(), stu.size());
        //System.out.print(message);
        for (int i = 0; i < sol.size(); i++) {
            assertEquals(message, sol.get(i), stu.get(i));
        }
    }

    /**
     * Testing for removing items.
     * @author CuiYuxin
     */
    @Test
    public void testRemove() {
        StudentArrayDeque<Integer> stu = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<Integer>();
        ArrayDequeSolution<String> info = new ArrayDequeSolution<String>();
        for (int i = 0; i < 100; i++) {
            String temp = "";
            int rand = StdRandom.uniform(300);
            if (rand < 150) {
                stu.addLast(rand);
                sol.addLast(rand);
                temp += "addLast(" + rand + ")";
            } else {
                stu.addFirst(rand);
                sol.addFirst(rand);
                temp += "addFirst(" + rand + ")";
            }
            if (info.size() < 3) {
                info.addLast(temp);
            } else {
                info.removeFirst();
                info.addLast(temp);
            }
        }
        String message = "";
        for (int i = 0; i < info.size(); i++) {
            message += info.get(i) + "\n";
        }
        assertEquals(message, sol.size(), stu.size());
        while (sol.size() > 0) {
            String temp = "";
            int rand = StdRandom.uniform(20);
            Integer actual, expected;
            if (rand % 2 == 0) {
                actual = stu.removeLast();
                expected = sol.removeLast();
                temp += "removeLast()";
            } else {
                actual = stu.removeFirst();
                expected = sol.removeFirst();
                temp += "removeFirst()";
            }
            if (info.size() < 3) {
                info.addLast(temp);
            } else {
                info.removeFirst();
                info.addLast(temp);
            }
            String message2 = "";
            for (int i = 0; i < info.size(); i++) {
                message2 += info.get(i) + "\n";
            }
            assertEquals(message2, expected, actual);
        }
    }
}
