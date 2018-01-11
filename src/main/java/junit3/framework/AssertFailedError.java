package junit3.framework;

public class AssertFailedError extends Error {

    public AssertFailedError() {
    }

    public AssertFailedError(String message) {
        super(message);
    }
}
