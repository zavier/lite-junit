package junit3.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 要测试的单个类需要继承此类
 */
public abstract class TestCase extends Assert implements Test {

    /**
     * 测试方法名
     */
    private String fName;

    public TestCase() {
        fName = null;
    }

    public TestCase(String fName) {
        this.fName = fName;
    }

    public TestResult run() {
        TestResult result = createResult();
        run(result);
        return result;
    }

    private TestResult createResult() {
        return new TestResult();
    }

    @Override
    public void run(TestResult result) {
        result.statTest(this);
        setUp();
        try {
            runTest();
        } catch (AssertFailedError e) { // 断言错误
            result.addFailure(this, e);
        } catch (Throwable e) { // 程序错误
            result.addError(this, e);
        } finally {
            tearDown();
        }
    }

    protected void setUp() {}

    protected void runTest() throws Throwable {
        Objects.requireNonNull(fName);
        Method runMethod = null;
        try {
            runMethod = this.getClass().getMethod(fName, new Class[0]);
        } catch (NoSuchMethodException e) {
            fail("Method " + fName + " not found");
        }
        try {
            runMethod.invoke(this, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // 获取反射调用过程中产生的真正异常
            throw e.getTargetException();
        }
    }

    protected void tearDown() {}

    public String getName() {
        return fName;
    }

    public void setName(String fName) {
        this.fName = fName;
    }
}
