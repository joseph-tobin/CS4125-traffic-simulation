package CS4125.View.UserInterface.Command;

import CS4125.Controller.Sim.Simulation;
import CS4125.Model.TrafficControl.ITCM;

public class AddTLightsCommand implements ICommand{

    private String thisTCM;
    private String x_inputText;
    private String y_inputText;
    private Boolean endpoint;
    private String thisType = "TrafficLights";
    

    public AddTLightsCommand(String thisTCM, String x_inputText, String y_inputText, Boolean endpoint) {
        this.thisTCM = thisTCM;
        this.x_inputText = x_inputText;
        this.y_inputText = y_inputText;
        this.endpoint = endpoint;
    }

    public void execute() {
        Simulation.INSTANCE.addNode(
                thisType, thisTCM,
                Integer.parseInt(x_inputText), Integer.parseInt(y_inputText), endpoint);
    }
}
