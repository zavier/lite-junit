package junit3.runner;

import junit3.framework.Test;
import junit3.framework.TestResult;
import junit3.framework.TestSuite;

public class TestRunner {

    public static void main(String[] args) {
        TestRunner runner = new TestRunner();
//        String arg = args[0];
//        System.out.println("=======测试： " + arg + "========");
        try {
            TestResult result = runner.start("junit3.test.TestMain");
            result.printResult();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private TestResult start(String testClassName) throws ClassNotFoundException {
        Test suite = getTest(testClassName);
        TestResult result = createTestResult();
        suite.run(result);
        return result;
    }

    private TestResult createTestResult() {
        return new TestResult();
    }

    private Test getTest(String testClassName) {
        Class<?> aClass = null;
        try {
            aClass = Class.forName(testClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new TestSuite(aClass);
    }
}
