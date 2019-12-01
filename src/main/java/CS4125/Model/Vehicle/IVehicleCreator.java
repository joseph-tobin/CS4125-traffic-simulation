package CS4125.Model.Vehicle;


import CS4125.Model.TrafficControl.ITCM;

public interface IVehicleCreator {
    ITCM[] getRandom();
    void setTimer(int timer);
}
