package CS4125;

import CS4125.Sim.Simulation;

public class Main {

    public static void main(String[] args) {
        //Simulation sim = new Simulation();
        Simulation.INSTANCE.run();
        //sim.run();
        Simulation.INSTANCE.getVehicles().get(0).move();

        System.out.println(Simulation.INSTANCE.getNodeList().get(0).getAdjacent());
        System.out.println(Simulation.INSTANCE.getNodeList().get(1).getAdjacent());
        //System.out.println(sim.getNodeList().get(2).getAdjacent());
        //System.out.println(sim.getVehicles().get(0).getStartNode());
        //System.out.println(sim.getVehicles().get(0).getEndNode());
    }
}
