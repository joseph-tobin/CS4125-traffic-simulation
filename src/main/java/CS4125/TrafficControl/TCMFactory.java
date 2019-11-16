package CS4125.TrafficControl;

import java.util.ArrayList;

public class TCMFactory {

    public enum TCMType {
        SIMPLE_JUNCTION,
        TRAFFIC_LIGHTS,
        ROUNDABOUT,
        CAR_PARK
    }

    /**
     * Overloaded Factory method which creates a TCM with an empty adjacency list
     * @param type Enumerated TCM type to createImage
     * @param x X Coordinate of TCM on UI
     * @param y Y Coordinate of TCM on UI
     * @return Concrete TCM object specified by type parameter
     */
    public TCM getTCM(TCMType type, int x, int y) {
        return getTCM(type, x, y, new ArrayList<Node>());
    }

    /**
     * Overloaded Factory method which creates a TCM with an adjacency list
     * @param type Enumerated TCM type to create
     * @param x X Coordinate of TCM on UI
     * @param y Y Coordinate of TCM on UI
     * @param adj List of adjacent TCMs in the Graph
     * @return Concrete TCM object specified by type parameter
     */
    public TCM getTCM(TCMType type, int x, int y, ArrayList<Node> adj) {
        switch (type) {
            // TODO: 08-11-19 create concrete TCM classes
            case SIMPLE_JUNCTION: return new SimpleJunction(x,y, adj);
            case TRAFFIC_LIGHTS: return new TrafficLights(x, y, adj);
            case ROUNDABOUT: ;
            case CAR_PARK: ;
            default: ; // Print error
        }
        // Did not return in Switch -> error and return null
        return null;
    }

}