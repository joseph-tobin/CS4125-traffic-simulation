package CS4125;

import CS4125.Model.Utils.Interceptor.Frameworks.Framework;
import CS4125.Model.Utils.Interceptor.Interceptors.LoggingInterceptor;
import CS4125.View.UserInterface.UIView;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        // Create Framework, Interceptor, and Register Interceptor with Framework
        Framework framework = Framework.getInstance();
        LoggingInterceptor loggingInterceptor = LoggingInterceptor.getInstance("BasicLogger");
        framework.registerLoggingInterceptor(loggingInterceptor);

        UIView.main(args);
    }
}
