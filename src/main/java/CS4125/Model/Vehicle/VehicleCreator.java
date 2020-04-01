package CS4125.Model.Vehicle;

import CS4125.Controller.Sim.Simulation;
import CS4125.Model.TrafficControl.ITCM;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Thread that creates Vehicles objects with a random start and end points and a given interval.
 * Creation interval set in UI by changing the flow slider
 */
public class VehicleCreator extends Thread implements IVehicleCreator {

    private int timer;
    private List<ITCM> nodes;
    private Random rand;
    int ctr = 0;
    private Map<String, IVehicle> premade;
    private Map<IVehicle, Integer> premade_count;
    private VehicleFactory vFactory;

    public VehicleCreator(List<ITCM> nodes, int timer) {
        Simulation.INSTANCE.logger.info("Starting vehicle creation thread");
        this.timer = timer;
        this.nodes = nodes;
        rand = new Random(42);
        premade_count = new HashMap<>();
        premade = new HashMap<>();
        vFactory = new VehicleFactory();
        this.start();
    }

    @Override
    public void run() {
        while (!(Thread.interrupted())) {
            ITCM[] routeStartEnd = getRandom();
            String routeString = routeStartEnd[0].toString() + routeStartEnd[1].toString();
            IVehicle v = null;

            /*
              Prototype Design Pattern Implementation
              checking if we already made a vehicle with these start & end points
             */
            if(premade.containsKey(routeString)) {
                Simulation.INSTANCE.logger.info("~~~~~~~~~~~~~~~~~~~~~~~copying~~~~~~~~~~~~~~~~~~~~~~");
                // checking if this vehicle has been used to copy more than 5 times, remove after this
                IVehicle toCopy = premade.get(routeString);
                int count = premade_count.get(toCopy);
                if(count > 5) {
                    Simulation.INSTANCE.logger.info("///////////////-deleting-/////////////////");
                    premade_count.remove(toCopy);
                    premade.remove(routeString);
                }
                v = toCopy.makeCopy();
                premade_count.replace(toCopy, count + 1);
                toCopy = null;
            }
            else {
                v = vFactory.makeVehicle("car", routeStartEnd[0], routeStartEnd[1]);
                premade.put(routeStartEnd[0].toString() + routeStartEnd[1].toString(), v);
                premade_count.put(v, 1);
            }

            new Thread(v).start();

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

    @Override
    public void waitWhileAdding() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
