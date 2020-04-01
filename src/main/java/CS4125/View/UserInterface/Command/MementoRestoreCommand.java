package CS4125.View.UserInterface.Command;

import CS4125.Controller.Sim.Simulation;

public class MementoRestoreCommand implements ICommand{
    public void execute() {
        Simulation.INSTANCE.getSavedSims().get(0).restoreFromMemento();
    }
    public void undo() {
        // TODO: Doesn't do anything ,user popup?
    }
    public void redo() {
        // TODO: Doesn't do anything ,user popup?
    }
}
