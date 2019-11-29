package CS4125.Model.Vehicle;

import CS4125.Model.TrafficControl.ITCM;
import CS4125.Model.Utils.IVehicleCreator;

public class VehicleCreator extends Thread implements IVehicleCreator {

    int timer;
    ITCM start;
    ITCM end;

    public VehicleCreator(int timer, ITCM start, ITCM end) {
        System.out.println("Starting vehicle creation thread");
        this.timer = timer;
        this.start = start;
        this.end = end;
        start();
    }

    public void run() {
        while (true && !(Thread.interrupted())) {
            System.out.println("timer is " + timer);
            Vehicle v = new Vehicle(start, end);
            try {
                Thread.sleep(timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setTimer(int t) { this.timer = t;}
    public void setStart(ITCM start) {this.start = start;}
    public void setEnd(ITCM end) {this.end = end;}
}
