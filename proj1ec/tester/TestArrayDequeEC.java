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
        for (int i = 0; i < 500; i++) {
            Integer actual = 1;
            Integer expected = 1;
            String temp = "";
            int rand = StdRandom.uniform(400);
            if (rand < 100) {
                stu.addLast(rand);
                sol.addLast(rand);
                temp += "addLast(" + rand + ")";
            } else if (rand < 200) {
                stu.addFirst(rand);
                sol.addFirst(rand);
                temp += "addFirst(" + rand + ")";
            } else if (rand < 300) {
                if (!stu.isEmpty() && !sol.isEmpty()) {
                    actual = stu.removeLast();
                    expected = sol.removeLast();
                    temp += "removeLast()";
                } else {
                    continue;
                }
            } else {
                if (!stu.isEmpty() && !sol.isEmpty()) {
                    actual = stu.removeFirst();
                    expected = sol.removeFirst();
                    temp += "removeFirst()";
                } else {
                    continue;
                }
            }
            if (info.size() < 3) {
                info.addLast(temp);
            } else {
                info.removeFirst();
                info.addLast(temp);
            }
            String message = "";
            for (int j = 0; j < info.size(); j++) {
                message += info.get(j) + "\n";
            }
            assertEquals(message, expected, actual);
        }

    }
}
