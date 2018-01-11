package junit3.framework;

public class Assert {
    protected Assert() {}

    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            fail(message);
        }
    }

    public static void assertTrue(boolean condition) {
        assertTrue(null, condition);
    }

    public static void assertFalse(String message, boolean condition) {
        assertTrue(message, !condition);
    }

    public static void assertFalse(boolean condition) {
        assertFalse(null, condition);
    }

    public static void fail(String message) {
        throw new AssertFailedError(message);
    }

    public static void fail() {
        fail(null);
    }

    public static void assertEquals(String message, Object expected, Object actual) {
        if (expected == null && actual == null) {
            return;
        }
        if (expected != null && expected.equals(actual)) {
            return;
        }
        failNotEquals(message, expected, actual);
    }

    public static void assertEquals(Object expected, Object actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, String expected, String actual) {
        if (expected == null && actual == null) {
            return;
        }
        if (expected != null && expected.equals(actual)) {
            return;
        }
        throw new AssertFailedError(message);
    }

    public static void assertEquals(String expected, String actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, double expected, double actual, double delta) {
        if (Double.isInfinite(expected)) {
            if (!(expected == actual)) {
                failNotEquals(message, new Double(expected), new Double(actual));
            }
        } else if (!(Math.abs(expected - actual) <= delta)) {
            failNotEquals(message, new Double(expected), new Double(actual));
        }
    }

    public static void assertEquals(double expected, double actual, double delta) {
        assertEquals(null, expected, actual, delta);
    }

    public static void assertEquals(String message, float expected, float actual, float delta) {
        if (Float.isInfinite(expected)) {
            if (!(expected == actual)) {
                failNotEquals(message, new Float(expected), new Float(actual));
            }
        } else if (!(Math.abs(expected-actual) <= delta)) {
            failNotEquals(message, new Float(expected), new Float(actual));
        }
    }

    public static void assertEquals(float expected, float actual, float delta) {
        assertEquals(null, expected, actual, delta);
    }

    public static void assertEquals(String message, long expected, long actual) {
        assertEquals(message, new Long(expected), new Long(actual));
    }

    public static void assertEquals(long expected, long actual) {
        assertEquals(null, expected, actual);
    }


    public static void assertEquals(String message, boolean expected, boolean actual) {
        assertEquals(message, new Boolean(expected), new Boolean(actual));
    }


    public static void assertEquals(boolean expected, boolean actual) {
        assertEquals(null, expected, actual);
    }


    public static void assertEquals(String message, byte expected, byte actual) {
        assertEquals(message, new Byte(expected), new Byte(actual));
    }


    public static void assertEquals(byte expected, byte actual) {
        assertEquals(null, expected, actual);
    }


    public static void assertEquals(String message, char expected, char actual) {
        assertEquals(message, new Character(expected), new Character(actual));
    }


    public static void assertEquals(char expected, char actual) {
        assertEquals(null, expected, actual);
    }


    public static void assertEquals(String message, short expected, short actual) {
        assertEquals(message, new Short(expected), new Short(actual));
    }


    public static void assertEquals(short expected, short actual) {
        assertEquals(null, expected, actual);
    }


    public static void assertEquals(String message, int expected, int actual) {
        assertEquals(message, new Integer(expected), new Integer(actual));
    }


    public static void assertEquals(int expected, int actual) {
        assertEquals(null, expected, actual);
    }


    public static void assertNotNull(Object object) {
        assertNotNull(null, object);
    }


    public static void assertNotNull(String message, Object object) {
        assertTrue(message, object != null);
    }


    public static void assertNull(Object object) {
        assertNull(null, object);
    }


    public static void assertNull(String message, Object object) {
        assertTrue(message, object == null);
    }

    private static void failNotEquals(String message, Object expected, Object actual) {
        fail(format(message, expected, actual));
    }

    static String format(String message, Object expected, Object actual) {
        String formatted= "";
        if (message != null) {
            formatted= message+" ";
        }
        return formatted+"expected:<"+expected+"> but was:<"+actual+">";
    }
}
