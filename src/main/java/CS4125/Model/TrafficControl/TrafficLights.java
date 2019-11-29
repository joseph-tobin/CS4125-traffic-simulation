package CS4125.Model.TrafficControl;

import CS4125.Model.Utils.IGraphable;
import CS4125.Model.Vehicle.Vehicle;

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
    public void updateState(int stateNum) {
        // TODO: 20-11-19 Update only relevant obserevrs of state change
    }

    @Override
    public float getX() {
        return super.getTcm().getX();
    }

    @Override
    public float getY() { return super.getTcm().getY(); }

    @Override
    public String getLabel() {return super.getTcm().getLabel();}

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
    public void setAdjacent(List<ITCM> adj) {
        super.getTcm().setAdjacent(adj);
    }

    @Override
    public boolean enterQueue(ITCM origin, Vehicle vehicle) {
        return super.getTcm().enterQueue(origin, vehicle);
    }

    @Override
    public int getCurrentQueue(ITCM dest) {
        return super.getTcm().getCurrentQueue(dest);
    }

    @Override
    public int getHeuristic() {
        return super.getTcm().getHeuristic();
    }

    @Override
    public List<IGraphable> getPossibleNext() {
        return super.getTcm().getPossibleNext();
    }

    /**
     * Modify the estimated cost of the ITCM that this is decorating
     * Traffic would decrease the throughput of a route due to the start stop nature, therefore should increase the estimated cost of this decorator's ITCM object
     * based on the number of adjacent nodes and the length of time spent in state GO
     * @return modified estimated cost of this decorator's ITCM object
     */
    @Override
    public float getEstimatedCost() {
        float beforeDecorator =  super.getTcm().getEstimatedCost();
        return beforeDecorator + (float)2 / super.getTcm().getAdjacent().size();
    }

    @Override
    public float distanceTo(IGraphable node) {
        return super.getTcm().distanceTo(node);
    }

    @Override
    public int compareTo(Object o) {
        return super.getTcm().compareTo(o);
    }
}
