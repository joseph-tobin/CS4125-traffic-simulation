package CS4125.View.UserInterface.Command;

import CS4125.Model.Utils.BasicLogger;
import CS4125.Model.Utils.LoggingAdapter;
import java.util.Stack;

public class CommandExecutor {
    private Stack<ICommand> history = new Stack<>();
    private Stack<ICommand> undoHistory = new Stack<>();
    private LoggingAdapter logger = LoggingAdapter.createLogger(BasicLogger .class);

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
            logger.info("No commands in the undo stack");
        }
    }

    public void redo() {
        if (!undoHistory.isEmpty()) {
            ICommand cmdToRedo = undoHistory.pop();
            cmdToRedo.redo();
            history.push(cmdToRedo);
        } else {
            logger.info("No commands in the redo stack");
        }
    }

    public Stack<ICommand> getHistory() {return history;}
    public Stack<ICommand> getUndoHistory() {return undoHistory;}

}
