package CS4125.Model.Vehicle;

import CS4125.Model.TrafficControl.ITCM;
import CS4125.Model.Utils.IGraphable;
import CS4125.Model.Utils.Observer;

import java.util.List;

public interface IVehicle extends Observer {
    public abstract ITCM getCurrentNode();
    public abstract ITCM getNextNode();
    public abstract ITCM getStarNode();
    public abstract ITCM getEndNode();
    abstract List<IGraphable> getRoute();
    public abstract void update(int state);
}