package CS4125.Model.TrafficControl;

import CS4125.Model.Utils.IGraphable;
import CS4125.Model.Vehicle.Vehicle;

import java.util.List;

public class Roundabout extends TCMDecorator {

    /**
     * Comstructor taking in a ITCM object and passing it as a reference to TCM decorator
     * @param tcm TCM to decorate
     */
    public Roundabout(ITCM tcm) { super(tcm); }

    @Override
    public void updateState(int stateNum) {
        // TODO: 28/11/2019 waiting on Observer patter implementation
    }

    @Override
    public float getX() {
        return super.getTcm().getX();
    }

    @Override
    public float getY() {
        return super.getTcm().getY();
    }

    @Override
    public String getLabel() {return super.getTcm().getLabel();}

    @Override
    public List<ITCM> getAdjacent() {
        return super.getTcm().getAdjacent();
    }

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
    public int getHeuristic() {
        return super.getTcm().getHeuristic();
    }

    @Override
    public List<IGraphable> getPossibleNext() {
        return super.getTcm().getPossibleNext();
    }

    /**
     * Modify the estimated cost of the ITCM that this is decorating
     * Roundabout would increase to throughput of a route due to the constant flow, therefore should reduce the estimated cost of this decorator's ITCM object
     * @return modified estimated cost of this decorator's ITCM object
     */
    @Override
    public float getEstimatedCost() {
        float beforeDecorator = super.getTcm().getEstimatedCost();
        return beforeDecorator / super.getTcm().getAdjacent().size();
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
