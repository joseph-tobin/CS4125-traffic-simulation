package CS4125.UserInterface;

import CS4125.Sim.Simulation;
import com.sun.javafx.application.PlatformImpl;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UIView extends Application {

	private SplitPane split;
	private Pane pane;
	private Pane control;
	private Scene scene;

	private UIController controller;
	private UIController.ClickHandler clickController;
	private Simulation sim;


	/**
	 * Start point of the application
	 * @param args: null
	 */
	public static void main(String [] args){
		Application.launch();
	}

	/**
	 * Creating the sim and the controller from here
	 */
	@Override
	public void start(Stage stage) throws Exception {
		split = new SplitPane();
		pane = new Pane(new Label("Sim"));
		control = new Pane(new Label("Control"));
		setup(stage);

		controller = new UIController(this);
		clickController = controller.new ClickHandler(new Circle(-1));
		//sim = new UISim(controller);
		Simulation.INSTANCE.init(controller);

	}

	private void setup(Stage stage) {
		split.setDividerPositions(0.75);
		split.getItems().addAll(pane, control);
		scene = new Scene(split, 900, 600);
		stage.setScene(scene);
		stage.show();

		pane.addEventHandler(MouseEvent.ANY,
				new UIController(this).new ClickHandler(new Circle(-1)));
	}

	public Pane getPane() {
		return pane;
	}
}
