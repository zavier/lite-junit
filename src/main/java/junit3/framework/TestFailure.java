package junit3.framework;

public class TestFailure {
    private Test test;
    private Throwable throwable;

    public TestFailure(Test test, Throwable throwable) {
        this.test = test;
        this.throwable = throwable;
    }

    public void printResult() {
        System.out.println(test.getClass().getName() + " : " + ((TestCase)test).getName());
        throwable.printStackTrace();
    }
}
