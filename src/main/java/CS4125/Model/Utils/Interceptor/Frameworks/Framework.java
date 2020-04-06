package CS4125.Model.Utils.Interceptor.Frameworks;

import CS4125.Model.Utils.Interceptor.Contexts.Context;
import CS4125.Model.Utils.Interceptor.Contexts.RouteContext;
import CS4125.Model.Utils.Interceptor.Dispatchers.LoggingDispatcher;
import CS4125.Model.Utils.Interceptor.Interceptors.LoggingInterceptor;

public class Framework
{
    private LoggingDispatcher loggingDispatcher;

    private static Framework instance;

    private Framework() {
        loggingDispatcher = LoggingDispatcher.getInstance();
    }

    public static Framework getInstance() {
        if (instance == null)
            instance = new Framework();
        return instance;
    }

    public boolean registerLoggingInterceptor(LoggingInterceptor interceptor) {
        return loggingDispatcher.register(interceptor);
    }

    public boolean removeLoggingInterceptor(LoggingInterceptor interceptor) {
        return loggingDispatcher.remove(interceptor);
    }

    public void onLogEvent(Context context) {
        loggingDispatcher.onLogEvent(context);
    }
}