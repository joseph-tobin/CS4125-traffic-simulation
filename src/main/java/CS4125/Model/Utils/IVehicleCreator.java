package CS4125.Model.Utils;


import CS4125.Model.TrafficControl.ITCM;

public interface IVehicleCreator {
    ITCM[] getRandom();
    void setTimer(int timer);

    void waitWhileAdding();
}
