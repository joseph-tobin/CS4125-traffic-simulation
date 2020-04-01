package CS4125.View.UserInterface.Command;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommandExecutorTest {

    private CommandExecutor cmdExec;
    private ICommand cmd1;
    private ICommand cmd2;
    private ICommand cmd3;

    @BeforeAll
    void setUp() {
        cmdExec = new CommandExecutor();
        cmd1 = new TestCommand("1");
        cmd2 = new TestCommand("2");
        cmd3 = new TestCommand("3");
    }

    @Test
    void executeOp() {
        cmdExec.executeOp(cmd1);
        assertEquals(cmdExec.getHistory().peek(), cmd1);
        cmdExec.executeOp(cmd2);
        assertEquals(cmdExec.getHistory().peek(), cmd2);
        cmdExec.executeOp(cmd3);
        assertEquals(cmdExec.getHistory().peek(), cmd3);
    }

    @Test
    void undo() {
        assertEquals(cmdExec.getHistory().peek(), cmd3);
        cmdExec.undo();
        assertEquals(cmdExec.getHistory().peek(), cmd2);
        assertEquals(cmdExec.getUndoHistory().peek(), cmd3);
    }

    @Test
    void redo() {
        cmdExec.redo();
        assertEquals(cmdExec.getHistory().peek(), cmd3);
        assertEquals(cmdExec.getUndoHistory().isEmpty(), true);
    }


    public class TestCommand implements ICommand {
        private String id;

        public TestCommand(String id) {
            this.id = id;
        }

        public void execute() {}
        public void undo() {}
        public void redo() {}
    }
}