package CS4125.View.UserInterface.Command;

import CS4125.Controller.Sim.Simulation;
import CS4125.Model.TrafficControl.ITCM;

import java.util.Stack;

public class AddTCMCommand implements ICommand{

    private String thisTCM;
    private String x_inputText;
    private String y_inputText;
    private Boolean endpoint;
    private String thisType; // TrafficLights, SimpleJunction, Roundabout
    private Stack<ITCM> deletedNodes; // in order to maintain ITCM's after deletion so redo can be done
    

    public AddTCMCommand(String thisType, String thisTCM, String x_inputText, String y_inputText, Boolean endpoint) {
        this.thisTCM = thisTCM;
        this.x_inputText = x_inputText;
        this.y_inputText = y_inputText;
        this.endpoint = endpoint;
        this.deletedNodes = new Stack<>();
        this.thisType = thisType;
    }

    /**
     * Adds a node
     */
    public void execute() {
        Simulation.INSTANCE.addNode(
                thisType, thisTCM,
                Integer.parseInt(x_inputText), Integer.parseInt(y_inputText), endpoint);
    }

    /**
     * Removes the last added node, determined by the history stack in CommandExecutor
     * Adds the node to deletedNodes, allowing for redo when the node is deleted
     * As a deleted node has no reference in nodeList in Sim. We must maintain the reference here.
     */
    public void undo() {
        deletedNodes.add(Simulation.INSTANCE.getNode(thisTCM));
        Simulation.INSTANCE.deleteNode(thisTCM);
    }

    /**
     * Re adds the last undo'd removal. Retrieves the nodeToRedo from deletedNodes
     * Adds the node and readds the adjacencys
     */
    public void redo() {
        ITCM nodeToRedo = deletedNodes.pop();
        if (nodeToRedo != null) {
            Simulation.INSTANCE.addNode(
                    thisType, thisTCM,
                    Integer.parseInt(x_inputText), Integer.parseInt(y_inputText), endpoint);
            for (ITCM adjacentNode : nodeToRedo.getAdjacent()) {
                Simulation.INSTANCE.addEdge(nodeToRedo.getLabel(), adjacentNode.getLabel());
            }
        } else {
            Simulation.INSTANCE.logger.error("No node found to redo");
        }

    }

}
