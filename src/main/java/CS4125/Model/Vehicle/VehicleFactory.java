package CS4125.Model.Vehicle;

import CS4125.Model.TrafficControl.ITCM;

public class VehicleFactory {
    public IVehicle makeVehicle(String vehicleType, ITCM start, ITCM end){
        if (vehicleType.equals("car"))
            return new Car(start, end);
        return null;
    }
}
