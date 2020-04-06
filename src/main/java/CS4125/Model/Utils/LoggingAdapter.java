package CS4125.Model.Utils;

import javax.lang.model.type.UnknownTypeException;

/**
 * Abstract class to be extended by all concrete adapters that wrap a specific logging framework
 */
abstract public class LoggingAdapter {

    public static LoggingAdapter createLogger(String loggerName, Class<?> adaptee) throws UnknownTypeException {
        // switch on (known) supported adaptable types
        switch(adaptee.getName()) {
            case "CS4125.Model.Utils.BasicLogger": {
                System.out.println(">>> BasicLogger.class passed to LoggingAdapter");
                return new BasicLogger(loggerName);
            }
            default: throw new UnknownTypeException(null, "Unsupported class " + adaptee.getName() + " passed to LoggingAdapter");
        }
    }

    // Abstract methods to be implemented by all concrete adapters
    abstract public void info(String message);
    abstract public void debug(String message);
    abstract public void error(String message);

}
