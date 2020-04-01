package CS4125.Model.Utils.Interceptor.Interceptors;


import CS4125.Model.Utils.LoggingAdapter;
import CS4125.Model.Utils.BasicLogger;
import CS4125.Model.Utils.Interceptor.Contexts.Context;
import CS4125.Model.Utils.Interceptor.Contexts.RouteContext;


public class LoggingInterceptor implements Interceptor {
    private LoggingAdapter logging;

    private static LoggingInterceptor instance;

    private LoggingInterceptor(LoggingAdapter logging) {
        this.logging = logging;
    }

    public static LoggingInterceptor getInstance(String type) {
        if (instance == null) {
            switch (type) {
                case "BasicLogger":   instance = new LoggingInterceptor(LoggingAdapter.createLogger(BasicLogger.class));    break;
            }
        }
        return instance;
    }

    public Context onLogEvent(Context context) {
        logging.info(context.getMessage());
        if (context instanceof RouteContext) {
            logging.debug(((RouteContext)context).getRoute());
        }
        return context;
    }
}