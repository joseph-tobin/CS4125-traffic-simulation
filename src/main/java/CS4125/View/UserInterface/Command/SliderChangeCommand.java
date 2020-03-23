package CS4125.View.UserInterface.Command;

import CS4125.Controller.Sim.Simulation;

public class SliderChangeCommand implements ICommand {

    private Double sliderValue;

    public SliderChangeCommand(double sliderValue) {
        this.sliderValue = sliderValue;
    }
    public void execute() {
        Simulation.INSTANCE.setVCTimer(sliderValue.intValue());
    }
}
