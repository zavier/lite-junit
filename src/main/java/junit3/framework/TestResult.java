package junit3.framework;

import java.util.Vector;

/**
 * 收集一个类中的测试结果
 */
public class TestResult {
    private int runTests;
    private Vector<TestFailure> failures;
    private Vector<TestFailure> errors;

    public TestResult() {
        this.runTests = 0;
        this.failures = new Vector<TestFailure>();
        this.errors = new Vector<TestFailure>();
    }

    public synchronized void statTest(Test test) {
        runTests++;
    }

    public synchronized void addFailure(Test test, AssertFailedError e) {
        failures.add(new TestFailure(test, e));
    }

    public synchronized void addError(Test test, Throwable e) {
        errors.add(new TestFailure(test, e));
    }

    public int getRunTests() {
        return runTests;
    }

    public Vector<TestFailure> getFailures() {
        return failures;
    }

    public Vector<TestFailure> getErrors() {
        return errors;
    }

    public void printResult() {
        printStatistics();
        for (TestFailure failure : failures) {
            failure.printResult();
        }
        for (TestFailure error : errors) {
            error.printResult();
        }
    }

    private void printStatistics() {
        System.out.format("run:%d\tfail:%d\terror:%d\n", runTests, failures.size(), errors.size());
    }
}
