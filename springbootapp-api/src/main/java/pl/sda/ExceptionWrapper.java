package pl.sda;
/**
 * Created by pzawa on 05.04.2017.
 */
public class ExceptionWrapper {

    private String exceptionName;
    private String message;

    public ExceptionWrapper(String exceptionName, String message) {
        super();
        this.exceptionName = exceptionName;
        this.message = message;
    }

    public ExceptionWrapper() {

    }

    public ExceptionWrapper(Exception ex) {
        this.exceptionName = ex.getClass().getName();
        this.message = ex.getMessage();
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
