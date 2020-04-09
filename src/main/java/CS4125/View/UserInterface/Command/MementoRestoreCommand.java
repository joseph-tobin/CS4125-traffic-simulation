package CS4125.View.UserInterface.Command;

import CS4125.Controller.Sim.Simulation;
import javafx.scene.control.Alert;

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
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Undo not supported for load");
        alert.show();
    }
    public void redo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Redo not supported for load");
        alert.show();
    }
}
