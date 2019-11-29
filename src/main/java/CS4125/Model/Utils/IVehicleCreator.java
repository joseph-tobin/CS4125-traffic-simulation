package CS4125.Model.Utils;


import CS4125.Model.TrafficControl.ITCM;

public interface IVehicleCreator {
    public abstract void run();
    public abstract void setTimer(int timer);
    public abstract void setStart(ITCM start);
    public abstract void setEnd(ITCM end);
}
