package CS4125.TrafficControl;

import CS4125.utils.IGraphable;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete TCM class that models an intersection with traffic lights
 */
public class TrafficLights extends TCMDecorator {

    TrafficLights(ITCM tcm, int x, int y, ArrayList<ITCM> adj) {
        super(tcm);
    }

    @Override
    public void updateState() {
        // TODO: 18-11-19 Update state method here - State depending on the type of TCM decorators that are applied
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public List<ITCM> getAdjacent() {
        return null;
    }

    @Override
    public void setX(float x) {

    }

    @Override
    public void setY(float y) {

    }

    @Override
    public void setAdjacent(List<ITCM> adj) {

    }

    @Override
    public int getHeuristic() {
        return 0;
    }

    @Override
    public List<IGraphable> getPossibleNext() {
        return null;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
