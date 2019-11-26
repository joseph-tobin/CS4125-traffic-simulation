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
        return false;
    }

    @Override
    public int getHeuristic() {
        return 0;
    }

    @Override
    public List<IGraphable> getPossibleNext() {

        // TODO: 20-11-19 create and return new lsit
        return null;
    }

    @Override
    public int getEstimatedCost() {
        // TODO: 26-11-19 modify the SimpleJunctions cost to be relevant to TrafficLights
        return 0;
    }

    @Override
    public int compareTo(Object o) {
        return super.getTcm().compareTo(o);
    }
}
