package CS4125.Model.TrafficControl;


import CS4125.Model.Utils.IGraphable;
import CS4125.Model.Vehicle.Car;

import java.util.List;

/**
 * Interface that all Traffic Control Management classes will implement
 * Defines methods that all types of TCMs require
 */
public interface ITCM extends IGraphable {
    // TODO: 17-11-19 See if saving all possible states as an enum here is possible/ worth while

    abstract void updateState(int stateNum);
    abstract float getX();
    abstract float getY();
    abstract String getLabel();
    abstract List<ITCM> getAdjacent();
    abstract void setX(float x);
    abstract void setY(float y);
    abstract void setAdjacent(List<ITCM> adj);
    abstract boolean enterQueue(ITCM origin, Car vehicle);
    abstract int getCurrentQueue(ITCM dest);
}