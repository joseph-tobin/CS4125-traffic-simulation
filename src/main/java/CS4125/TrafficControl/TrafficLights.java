package CS4125.TrafficControl;

import CS4125.utils.IGraphable;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete TCM class that models an intersection with traffic lights
 */
public class TrafficLights extends TCMDecorator {

    TrafficLights(ITCM tcm) {
        super(tcm);
    }


    // Override ITCM methods
    @Override
    public void updateState() {
        // TODO: 18-11-19 Update state method here - State depending on the type of TCM decorators that are applied
    }

    @Override
    public float getX() {
        return super.getTcm().getX();
    }

    @Override
    public float getY() { return super.getTcm().getY(); }

    @Override
    public List<ITCM> getAdjacent() { return  super.getTcm().getAdjacent(); }

    @Override
    public void setX(float x) {
        super.getTcm().setX(x);
    }

    @Override
    public void setY(float y) {
        super.getTcm().setY(y);
    }

    @Override
    public void setAdjacent(List<ITCM> adj) { super.getTcm().setAdjacent(adj); }

    // Override IGraphable Methods
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
