package CS4125.Model.Utils.Interceptor.Contexts;

import java.util.List;

public class RouteContext extends Context {
    private List route;

    public RouteContext(String message, List route) {
        this.message = message;
        this.route = route;
    }

    public String getRoute() {
        return route.toString().replace("CS4125.Model.TrafficControl.", "");
    }
}
