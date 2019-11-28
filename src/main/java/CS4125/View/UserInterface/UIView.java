package CS4125.View.UserInterface;

//import CS4125.Controller.Sim.Simulation;
import CS4125.Controller.Sim.Simulation;
import CS4125.View.EventHandlers.UIController;
import CS4125.View.UISim;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UIView extends Application {

	private SplitPane split;
	private Pane simPane;
	private Scene scene;
	private VBox controls;
	private Slider slider;
	private GridPane tcms;
	private BorderPane root;
	private HBox ioButtons;

	private UIController controller;
	private Stage stage;
//	private UIController.ClickHandler clickController;


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
		simPane = new Pane(new Label("Sim"));
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

		this.stage = stage;
		setup(stage);
	}

	/**
	 * Build UI, Create Simulation, Controller
	 * @param stage javafx Stage
	 */
	private void setup(Stage stage) {
		controls.getChildren().add(new Label("Controls"));

		controls.getChildren().add(new Label("Traffic Level:"));
		slider.valueProperty().addListener(event-> {
			System.out.println(slider.getValue());
			// reduce interval for createVehicle thread
		});
		controls.getChildren().add(slider);

		controls.getChildren().add(new Label("TCMs:"));
		for (int i = 0; i < 3; i++) {
			double r = 15;
			Button btn = new Button();
			switch(i) {
				case 0: btn.setText("S"); btn.setTooltip(new Tooltip("Simple Junction")); break;
				case 1: btn.setText("T"); btn.setTooltip(new Tooltip("Traffic Lights")); break;
				case 2: btn.setText("R"); btn.setTooltip(new Tooltip("Roundabout")); break;
			}
			btn.setOnAction(event -> {
				addTCMPane(btn.getTooltip().getText(), "A"); // prompt user to enter co-ords and nodes connected to it
			});
			btn.setShape(new Circle(r));
			btn.setMinSize(2 * r, 2 * r);
			btn.setMaxSize(2 * r, 2 * r);

			tcms.add(btn, i, 0);
		}
		tcms.getStyleClass().add("button-container");
		controls.getChildren().add(tcms);

		Button saveBtn = new Button("Save");
		saveBtn.setOnAction(event -> {
			// save setup to database
		});

		Button loadBtn = new Button("Load");
		loadBtn.setOnAction(event -> {
			// load setup from database
			// *** run sim with those metrics
		});

		Button refreshBtn = new Button("Refresh");
		refreshBtn.setOnAction(event -> {
			// reset to default sim
			Simulation.INSTANCE.run();
		});

		Button playPauseBtn = new Button("Play");
		playPauseBtn.setOnAction(event -> {
			// if play, set to pause and stop animations
			// if pause, set to play and restart animations
		});

		ioButtons.getChildren().addAll(playPauseBtn, refreshBtn, saveBtn, loadBtn);
		ioButtons.setAlignment(Pos.CENTER_RIGHT);

		split.getItems().addAll(simPane, root);
		scene = new Scene(split, 900, 600);
		stage.setScene(scene);
		stage.show();

		controller = new UIController(this);

		Simulation.INSTANCE.init(controller);
		Simulation.INSTANCE.run();
	}

	/**
	 * For adding a TCM to the UI, via clicking on one of the menu items
	 * @param tcmType
	 * @param name
	 */
	private void addTCMPane(String tcmType, String name) {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(stage);
		dialog.setTitle("Add a " + tcmType);
		VBox dialogVbox = new VBox(20);

		Label coordTitle = new Label("Co-ordinates:");
		TextField x_input = new TextField();
		x_input.setPromptText("x co-ord");
		TextField y_input = new TextField();
		y_input.setPromptText("y co-ord");
		Button coordsBtn = new Button("Save");
		coordsBtn.setOnAction(event -> {
			System.out.print(x_input.getText() + "; " + y_input.getText());
			// grab x & y, add node
			 Simulation.INSTANCE.addNode(
			 		tcmType, name, Integer.parseInt(x_input.getText()), Integer.parseInt(y_input.getText()), 1000);
		});

		Label nodeTitle = new Label("Connect to a node:");
		TextField node_input = new TextField("node");
		Button nodeBtn = new Button("Add");
		nodeBtn.setOnAction(event -> {
			System.out.println(node_input.getText());
			node_input.clear();
//			sim.addEdge();
		});

		dialogVbox.getChildren().addAll(coordTitle, x_input, y_input, coordsBtn, nodeTitle, node_input, nodeBtn);

		Scene dialogScene = new Scene(dialogVbox, 300, 300);
		dialog.setScene(dialogScene);
		dialog.show();
	}

	public Pane getSimPane() {
		return simPane;
	}
}
