package CS4125.Model.Utils;


import CS4125.Model.TrafficControl.ITCM;

import java.util.List;

public interface IVehicleCreator {
    public abstract void run();
    public abstract void setTimer(int timer);
    public abstract void updateNodes(List<ITCM> nodes);
}
