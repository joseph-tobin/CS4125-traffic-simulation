package CS4125.Model.Utils;

public class BasicLogger extends LoggingAdapter {
    StringBuilder sb = new StringBuilder();

    @Override
    public void info(String message) {
        formatLog("INFO");
        printLog(message);
    }

    @Override
    public void debug(String message) {
        formatLog("DEBUG");
        printLog(message);
    }

    @Override
    public void error(String message) {
        formatLog("ERROR");
        printLog(message);
    }

    private void formatLog(String logLevel) {
        sb.append("[").append(new java.util.Date()).append("] ");
        sb.append(logLevel).append(" - ");
    }

    private void printLog(String msg) {
        sb.append(msg);
        System.out.println(sb.toString());
        sb.setLength(0); // empty StringBuilder
    }
}
