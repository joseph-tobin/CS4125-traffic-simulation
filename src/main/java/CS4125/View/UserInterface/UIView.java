package CS4125.View.UserInterface;

import CS4125.Controller.Sim.Simulation;
import CS4125.View.EventHandlers.UIController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class UIView extends Application {

	private SplitPane split;
	private Pane pane;
	private Scene scene;
	private VBox controls;
	private Slider slider;
	private GridPane tcms;
	private BorderPane root;
	private HBox ioButtons;
	private Button saveBtn;

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
	 * Initialise UI elements
	 */
	@Override
	public void start(Stage stage) throws Exception {
		split = new SplitPane();
		split.setDividerPositions(0.75);
		pane = new Pane(new Label("Sim"));

		controls = new VBox();
		controls.setSpacing(10);

		root = new BorderPane();
		root.setPadding(new Insets(10)); // margin around controls panel
		ioButtons = new HBox();
		root.setCenter(controls);
		root.setBottom(ioButtons);

		tcms = new GridPane();
		tcms.setHgap(10); // padding
		tcms.setVgap(10); // padding
		tcms.setPadding(new Insets(10, 10, 10, 10)); // margin

		slider = new Slider(0, 100, 20);
		slider.setShowTickMarks(true);

		setup(stage);
	}

	/**
	 * Build UI, Create Simulation, Controller
	 * @param stage javafx Stage
	 */
	private void setup(Stage stage) {
		controls.getChildren().add(new Label("Controls"));

		controls.getChildren().add(new Label("Traffic Level:"));
		controls.getChildren().add(slider);

		controls.getChildren().add(new Label("TCMs:"));
		for (int i = 0; i < 3; i++) {
			double r = 15;
			Button btn = new Button();
			switch(i) {
				case 0: btn.setText("S"); break;
				case 1: btn.setText("T"); break;
				case 2: btn.setText("R"); break;
			}
			btn.setShape(new Circle(r));
			btn.setMinSize(2 * r, 2 * r);
			btn.setMaxSize(2 * r, 2 * r);

			tcms.add(btn, i, 0);
		}
		tcms.getStyleClass().add("button-container");
		controls.getChildren().add(tcms);

		saveBtn = new Button("Save");
		ioButtons.getChildren().add(saveBtn);
		ioButtons.setAlignment(Pos.CENTER_RIGHT);

		split.getItems().addAll(pane, root);
		scene = new Scene(split, 900, 600);
		stage.setScene(scene);
		stage.show();

		controller = new UIController(this);
		clickController = controller.new ClickHandler(new Circle(-1));
		pane.addEventHandler(MouseEvent.ANY,
				new UIController(this).new ClickHandler(new Circle(-1)));

		Simulation.INSTANCE.init(controller);
//		Simulation.INSTANCE.run();
	}

	public Pane getPane() {
		return pane;
	}
}
