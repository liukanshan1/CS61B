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
        String info = "";
        for (int i = 0; i < 500; i++) {
            Integer actual = 1;
            Integer expected = 1;
            int rand = StdRandom.uniform(600);
            if (rand < 200) {
                stu.addLast(rand);
                sol.addLast(rand);
                info += "addLast(" + rand + ")\n";
                continue;
            } else if (rand < 400) {
                stu.addFirst(rand);
                sol.addFirst(rand);
                info += "addFirst(" + rand + ")\n";
                continue;
            } else if (rand < 500) {
                if (!sol.isEmpty()) {
                    actual = stu.removeLast();
                    expected = sol.removeLast();
                    info += "removeLast()\n";
                } else {
                    continue;
                }
            } else {
                if (!sol.isEmpty()) {
                    actual = stu.removeFirst();
                    expected = sol.removeFirst();
                    info += "removeFirst()\n";
                } else {
                    continue;
                }
            }
            assertEquals(info, expected, actual);
        }
    }
}
