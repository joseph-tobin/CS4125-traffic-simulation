package CS4125.Model.Vehicle;

import CS4125.Controller.Sim.Simulation;
import CS4125.Model.TrafficControl.ITCM;
import CS4125.Model.Utils.IVehicleCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VehicleCreator extends Thread implements IVehicleCreator{

    int timer;
    List<ITCM> nodes;

    public VehicleCreator(ArrayList<ITCM> nodes, int timer) {
        System.out.println("Starting vehicle creation thread");
        this.timer = timer;
        this.nodes = nodes;
        start();
    }

    @Override
    public void run() {
        while (true && !(Thread.interrupted())) {
            System.out.println("timer is " + timer);
            Vehicle v = new Vehicle(getRandom(nodes), getRandom(nodes));
            //Simulation.INSTANCE.addVehicleToVehicleList(v);
            //Simulation.INSTANCE.addVehicleToController(v);
            Simulation.INSTANCE.addVehicleAnim(v,0);
            try {
                Thread.sleep(timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            nodes = Simulation.INSTANCE.getNodeList(); // i dont like doing this line :(, ensures we have latest list
        }
    }

    public ITCM getRandom(List<ITCM> list) { // possibility of start and end node being equal
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
    @Override
    public void setTimer(int t) { this.timer = t;}
    @Override
    public void updateNodes(List<ITCM> nodes) {this.nodes = nodes;}
}
