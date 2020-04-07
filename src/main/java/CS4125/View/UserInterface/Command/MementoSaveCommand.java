package CS4125.View.UserInterface.Command;

import CS4125.Controller.Sim.Simulation;
import javafx.scene.control.Alert;

public class MementoSaveCommand implements ICommand{

    public void execute() {
        Simulation.INSTANCE.saveToMemento();
    }
    public void undo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Undo not supported for save");
        alert.show();
    }
    public void redo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Undo not supported for save");
        alert.show();
    }

}
