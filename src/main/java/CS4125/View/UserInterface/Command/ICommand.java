package CS4125.View.UserInterface.Command;

public interface ICommand {
    abstract void execute();
    abstract void undo();
    abstract void redo();
}
