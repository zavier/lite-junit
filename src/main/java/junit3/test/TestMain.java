package junit3.test;

import junit3.framework.TestCase;

public class TestMain extends TestCase {

    public void testA() {
        assertEquals(5, 5);
    }

    public void testB() {
        assertEquals("hello", "hello");
    }

    public void testC() {
        assertEquals(1, 2);
    }
}
