package validators;

public class ValidationException extends RuntimeException {
    public ValidationException(){}

    public ValidationException(String msg) { super(msg); }

    public ValidationException(String msg, Throwable cause) { super(msg, cause); }

    public ValidationException(Throwable cause) { super(cause); }

    public ValidationException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
