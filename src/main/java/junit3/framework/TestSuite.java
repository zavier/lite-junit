package junit3.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

/**
 * 为测试类中的每个测试方法生成一个对应的类，运行时依次调用执行
 */
public class TestSuite implements Test{

    /**
     * 保存要测试的类（如果类中有多个要测试的方法，会为每个方法生成一个类）
     */
    private Vector<Test> fTests = new Vector<Test>();

    private String fName;

    public TestSuite() {
    }

    public TestSuite(Class theClass) {
        fName = theClass.getName();
        try {
            getTestConstructor(theClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Class superClass = theClass;
        if (Test.class.isAssignableFrom(superClass)) {
            Method[] methods = superClass.getDeclaredMethods();
            for (Method method : methods) {
                addTestMethod(method, theClass);
            }
        }
    }

    /**
     * 判断此方法是否是需要测试的方法，如果是则添加到测试列表中
     * @param method
     * @param theClass
     */
    private void addTestMethod(Method method, Class theClass) {
        String name = method.getName();
        if (!isPublicTestMethod(method)) {
            return;
        }
        addTest(createTest(theClass, name));
    }

    private void addTest(Test test) {
        fTests.add(test);
    }

    private Test createTest(Class theClass, String name) {
        Constructor constructor = null;
        try {
            constructor = getTestConstructor(theClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instanceTestConstruct(constructor, name);
    }

    /**
     * 实例化测试类，并设置其测试的具体的方法名字
     * @param constructor
     * @param name
     */
    private Test instanceTestConstruct(Constructor constructor, String name) {
        Object test = null;
        try {
            if (constructor.getParameterTypes().length == 0) { // 无参构造器，需要调用setfName方法设置其测试的方法名字
                test = constructor.newInstance(new Object[0]);
                if (test instanceof TestCase) {
                    ((TestCase)test).setName(name);
                }
            } else { // 使用有参构造器
                test = constructor.newInstance(new Object[]{name});
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return (Test) test;
    }

    /**
     * 判断此方法是否是公共的需要进行测试的方法
     * @param method
     * @return
     */
    private boolean isPublicTestMethod(Method method) {
        String name = method.getName();
        Class<?>[] parameters = method.getParameterTypes();
        Class<?> returnType = method.getReturnType();
        return parameters.length == 0 && name.startsWith("test") && returnType.equals(Void.TYPE);

    }

    /**
     * 获取测试类的构造器，如果没有String参数的构造器，就获取无参构造器
     * @param theClass
     * @return
     * @throws NoSuchMethodException
     */
    private Constructor getTestConstructor(Class theClass) throws NoSuchMethodException {
        try {
            return theClass.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            // 如果没有String参数的构造器，就获取无参构造器
        }
        return theClass.getConstructor(new Class[0]);
    }


    @Override
    public void run(TestResult result) {
        for (Test test : fTests) {
            runTest(test, result);
        }
    }

    private void runTest(Test test, TestResult result) {
        test.run(result);
    }
}
