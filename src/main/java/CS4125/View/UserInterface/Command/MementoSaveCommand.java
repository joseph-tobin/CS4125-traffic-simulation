package CS4125.View.UserInterface.Command;

import CS4125.Controller.Sim.Simulation;

public class MementoSaveCommand implements ICommand{

    public void execute() {
        Simulation.INSTANCE.saveToMemento();
    }

}
