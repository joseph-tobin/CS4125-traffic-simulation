package CS4125.Model.Utils.Interceptor.Contexts;

public class Context {
    protected String message;

    public Context(String message) {
        this.message = message;
    }

    public Context() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void empty() {
        message = null;
    }
}