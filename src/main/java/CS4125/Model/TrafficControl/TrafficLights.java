package CS4125.Model.TrafficControl;

import CS4125.Model.Utils.IGraphable;

import java.util.List;

/**
 * Concrete TCM class that models an intersection with traffic lights
 */
public class TrafficLights extends TCMDecorator {

    public TrafficLights(ITCM tcm) {
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
    public List<NodePair> getAdjacent() { return  super.getTcm().getAdjacent(); }

    @Override
    public void setX(float x) {
        super.getTcm().setX(x);
    }

    @Override
    public void setY(float y) {
        super.getTcm().setY(y);
    }

    @Override
    public void setAdjacent(List<NodePair> adj) {
        super.getTcm().setAdjacent(adj);
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
