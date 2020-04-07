package CS4125.View.UserInterface.Command;

import CS4125.Controller.Sim.Simulation;

public class MementoRestoreCommand implements ICommand{

    private Simulation.Memento mem;

    public MementoRestoreCommand(Simulation.Memento mem) {
        this.mem = mem;
    }
    public void execute() {
        //Simulation.INSTANCE.getSavedSims().get(0).restoreFromMemento();
        mem.restoreFromMemento();
    }
    public void undo() {
        // TODO: Doesn't do anything ,user popup?
    }
    public void redo() {
        // TODO: Doesn't do anything ,user popup?
    }
}
