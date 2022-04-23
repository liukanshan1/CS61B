package flik;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestFilk {
    @Test
    public void test1() {
        int j = 0;
        for (int i = 0; i < 1000; i++, j++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(':');
            sb.append(j);
            assertTrue(sb.toString(), Flik.isSameNumber(i, j));
        }
    }

    @Test
    public void test2() {
        int j = 190;
        for (int i = 190; i < 1000; i++, j++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(':');
            sb.append(j);
            assertTrue(sb.toString(), Flik.isSameNumber(i, j));
        }
    }

    @Test
    public void test3() {
        Integer j = 190;
        for (Integer i = 190; i < 1000; i++, j++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(':');
            sb.append(j);
            assertTrue(sb.toString(), Flik.isSameNumber(i, j));
        }
    }
}
