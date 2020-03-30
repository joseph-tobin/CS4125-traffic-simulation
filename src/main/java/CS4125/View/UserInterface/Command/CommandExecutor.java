package CS4125.View.UserInterface.Command;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CommandExecutor {
    private Stack<ICommand> history = new Stack<>();
    private Stack<ICommand> undoHistory = new Stack<>();

    public void executeOp(ICommand command) {
        history.push(command);
        command.execute();
    }

    public void undo() {
        if (!history.isEmpty()) {
            ICommand cmdToUndo = history.pop();
            cmdToUndo.undo();
            undoHistory.push(cmdToUndo);
        } else {
            //TODO: Nothing to undo, show user popup?
        }
    }

    public void redo() {
        if (!undoHistory.isEmpty()) {
            ICommand cmdToRedo = undoHistory.pop();
            executeOp(cmdToRedo);
        } else {
            //TODO: Nothing to redo, show user popup?
        }
    }

    public Stack<ICommand> getHistory() {return history;}
    public Stack<ICommand> getUndoHistory() {return undoHistory;}

}
