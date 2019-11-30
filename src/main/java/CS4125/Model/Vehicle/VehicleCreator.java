package CS4125.Model.Vehicle;

import CS4125.Controller.Sim.Simulation;
import CS4125.Model.TrafficControl.IEndpoint;
import CS4125.Model.TrafficControl.ITCM;
import CS4125.Model.Utils.IVehicleCreator;
import CS4125.Model.Utils.RandomCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VehicleCreator extends Thread implements IVehicleCreator {

    int timer;
    RandomCollection<IEndpoint> nodes;

    public VehicleCreator(RandomCollection<IEndpoint> nodes, int timer) {
        System.out.println("Starting vehicle creation thread");
        this.timer = timer;
        this.nodes = nodes;
    }

    @Override
    public void run() {
        while (!(Thread.interrupted())) {
            ITCM[] routeStartEnd = getRandom();
            Car v = new Car(routeStartEnd[0], routeStartEnd[1]);

            // causing null pointer- v.getNextNode == null
            Simulation.INSTANCE.addVehicleAnim(v,0);
            try {
                Thread.sleep(timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get random ITCM objects from a weighted collection
     * @return ITCM[2] where first elm = start, and second elm = end
     */
    public ITCM[] getRandom() { // possibility of start and end node being equal
        ITCM[] startEnd = new ITCM[2];
        startEnd[0] = nodes.next();

        while(startEnd[1] == null) {
            ITCM tempNode = nodes.next();
            if(tempNode != startEnd[0]) {
                startEnd[1] = tempNode;
            }
        }
        return startEnd;
    }

    @Override
    public void setTimer(int t) { this.timer = t;}
}
