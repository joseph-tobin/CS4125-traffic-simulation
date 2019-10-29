package CS4125;

import CS4125.Sim.Simulation;

public class Main {

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        sim.run();

        System.out.println(sim.getNodeList().get(0).getX());
    }
}
