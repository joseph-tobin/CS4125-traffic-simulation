package CS4125.View.UserInterface;

//import CS4125.Controller.Sim.Simulation;
import CS4125.Controller.Sim.Simulation;
import CS4125.View.EventHandlers.UIController;
import CS4125.View.UserInterface.Command.*;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
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
	private CommandExecutor commandExecutor = new CommandExecutor();

	/**
	 * Start point of the application
	 * @param args: null
	 */
	public static void main(String [] args){
		Application.launch();
	}

	/**
	 * Initialise UI elements
	 * Main "sim" pane which displays your nodes, edges, vehicles
	 * Sidebar pane which contains various options and TCMs to add
	 * @param stage javafx Stage
	 */
	@Override
	public void start(Stage stage) throws Exception {

		// shut down all threads when closing the application
		stage.setOnCloseRequest(event ->  {
				Platform.exit();
				System.exit(0);
			}
		);

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
		sideMenu(stage);
	}

	/**
	 * Build the default sidebar menu, Create Simulation, Create Controller
	 * Menu contains a slider for adjusting the traffic level (rate at which vehicles are added to the sim),
	 * 	buttons for adding new TCMs to the sim,
	 * 	buttons for saving/loading from a database (not yet implemented)
	 * 	buttons to play/pause or refresh the simulation to its default state
	 * @param stage javafx Stage
	 */
	private void sideMenu(Stage stage) {
		controls.getChildren().add(new Label("Controls"));

		controls.getChildren().add(new Label("Traffic Level:"));
		slider.valueProperty().addListener(event-> {
			System.out.println(slider.getValue());
			commandExecutor.executeOp(new SliderChangeCommand(slider.getValue()));
		});
		controls.getChildren().add(slider);

		controls.getChildren().add(new Label("TCMs:"));
		for (int i = 0; i < 3; i++) {
			double r = 15;
			Button btn = new Button();
			switch(i) {
				case 0: btn.setText("S"); btn.setTooltip(new Tooltip("SimpleJunction")); break;
				case 1: btn.setText("T"); btn.setTooltip(new Tooltip("TrafficLights")); break;
				case 2: btn.setText("R"); btn.setTooltip(new Tooltip("Roundabout")); break;
			}
			btn.setOnAction(event -> {
				addTCMPane(btn.getTooltip().getText()); // prompt user to enter co-ords and nodes connected to it
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
			commandExecutor.executeOp(new MementoSaveCommand());
		});

		Button loadBtn = new Button("Load");
		loadBtn.setOnAction(event -> {
			// TODO: Change this to maybe open a dialog with all saved sims and click it defaults 0th saved sim
			// stop current paths
			// TODO: Stop and delete
			for (PathTransition t: controller.getAnimations()) {
				t.stop();
				for (Circle c: controller.getCircles()) {
					getSimPane().getChildren().remove(c);
				}
			}
			commandExecutor.executeOp(new MementoRestoreCommand());
		});

		Button refreshBtn = new Button("Undo");
		refreshBtn.setOnAction(event -> {
			commandExecutor.undo();
		});

		Button playPauseBtn = new Button("Redo");
		playPauseBtn.setOnAction(event -> {
			commandExecutor.redo();
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
	 * Popup dialog for adding a TCM to the UI, via clicking on one of the menu items
	 * User is prompted to enter a name, x and y coordinates
	 * @param tcmType simple junction / roundabout / traffic lights...
	 */
	private void addTCMPane(String tcmType) {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(stage);
		dialog.setTitle("Add a " + tcmType);
		VBox dialogItems = new VBox(20);

		Label nameTitle = new Label("Name:");
		TextField name_input = new TextField();
		name_input.setPromptText("Name");
		Label coordTitle = new Label("Co-ordinates:");
		TextField x_input = new TextField();
		x_input.setPromptText("x co-ord");
		TextField y_input = new TextField();
		y_input.setPromptText("y co-ord");
		CheckBox endpoint_cb = new CheckBox("Endpoint");

		Button coordsBtn = new Button("Save");
		coordsBtn.setOnAction(event -> {
			Boolean endpoint = endpoint_cb.isSelected();
			System.out.println(x_input.getText() + "; " + y_input.getText());
			// grab x & y, add node
			// prefixing label with tcmType for different representation in UI
			String thisTCM = tcmType + "_" + name_input.getText();
			commandExecutor.executeOp(new AddTCMCommand(tcmType, thisTCM, x_input.getText(), y_input.getText(), endpoint));
			connectTCMPane(dialog, thisTCM);
		});

		dialogItems.getChildren().addAll(nameTitle, name_input, coordTitle,  x_input, y_input, endpoint_cb, coordsBtn);

		Scene dialogScene = new Scene(dialogItems, 300, 300);
		dialog.setScene(dialogScene);
		dialog.show();
	}

	/**
	 * Following from the previous dialog, now you may connect your TCMs to others
	 * Gets the list of labels from UIController and displays them in Multi-Select ListView
	 * @param dialog use the previous dialog window
	 * @param tcm the TCM that was just added
	 */
	private void connectTCMPane(Stage dialog, String tcm){
		dialog.setTitle("Connect " + tcm);
		Label nodeTitle = new Label("Connect to a node:");
		VBox dialogItems = new VBox(20);

		ObservableList<String> observableList = FXCollections.observableList(controller.getNodeLabels(tcm));
		ListView<String> nodeOptions = new ListView<>(observableList);
		nodeOptions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		Button connect_btn = new Button("Connect");

		connect_btn.setOnAction(event -> {
			dialog.close();
			ObservableList<String> selectedNodes = nodeOptions.getSelectionModel().getSelectedItems();
			for(String n : selectedNodes){
				System.out.println("You = " + tcm + ",  node = " + n);
				Simulation.INSTANCE.addEdge(tcm, n);
			}
		});

		dialogItems.getChildren().addAll(nodeTitle, nodeOptions, connect_btn);
		Scene dialogScene = new Scene(dialogItems, 300, 300);
		dialog.setScene(dialogScene);
	}

	/**
	 * Getter for the main Sim pane, so the controller may add elements to it
	 * @return Pane simPane
	 */
	public Pane getSimPane() {
		return simPane;
	}
}
