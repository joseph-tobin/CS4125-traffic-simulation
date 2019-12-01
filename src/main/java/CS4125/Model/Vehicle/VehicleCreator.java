package CS4125.Model.Vehicle;

import CS4125.Controller.Sim.Simulation;
import CS4125.Model.TrafficControl.IEndpoint;
import CS4125.Model.TrafficControl.ITCM;
import CS4125.Model.Utils.IVehicleCreator;
import CS4125.Model.Vehicle.Car;

import java.util.List;
import java.util.Random;

/**
 * Thread that creates Vehicles objects with a random start and end points and a given interval.
 * Creation interval set in UI by changing the flow slider
 */
public class VehicleCreator extends Thread implements IVehicleCreator {

    private int timer;
    private List<ITCM> nodes;
    private Random rand;

    public VehicleCreator(List<ITCM> nodes, int timer) {
        System.out.println("Starting vehicle creation thread");
        this.timer = timer;
        this.nodes = nodes;
        rand = new Random(42);
        this.start();
    }

    @Override
    public void run() {
        while (true && !(Thread.interrupted())) {
            ITCM[] routeStartEnd = getRandom();
            IVehicle v = new Car(routeStartEnd[0], routeStartEnd[1]);

            v.move();
            try {
                Thread.sleep(timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            nodes = Simulation.INSTANCE.getEndpoints();
        }
    }

    /**
     * Get random ITCM objects from a weighted collection
     * @return ITCM[2] where first elm = start, and second elm = end
     */
    public ITCM[] getRandom() { // possibility of start and end node being equal
        ITCM[] startEnd = new ITCM[2];
        startEnd[0] = nodes.get(rand.nextInt(nodes.size()));

        while(startEnd[1] == null) {
            ITCM tempNode = nodes.get(rand.nextInt(nodes.size()));
            if(tempNode != startEnd[0]) {
                startEnd[1] = tempNode;
            }
        }
        return startEnd;
    }

    @Override
    public void setTimer(int t) { this.timer = t;}
}
