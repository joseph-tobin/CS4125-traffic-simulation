package CS4125.UserInterface;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class UserInterface extends Application {

	SplitPane split = new SplitPane();
	Pane pane = new Pane(new Label("Sim"));
	Pane control = new Pane(new Label("Control"));
	Scene scene;

	@Override
	public void start(Stage stage) throws Exception {
		setup(stage);

		Circle n1 = addNode(200, 200);
		Circle n2 = addNode(300, 400);

		addEdge(n1, n2);

		addCar(n1, n2);
		addCar(n2, n1);

	}

	private void setup(Stage stage) {
		split.setDividerPositions(0.75);
		split.getItems().addAll(pane, control);
		scene = new Scene(split, 900, 600);
		stage.setScene(scene);
		stage.show();
	}

	private Circle addNode(double x, double y) {
		Circle n1 = new Circle(x, y, 10);
		n1.setFill(Color.FORESTGREEN);
		pane.getChildren().add(n1);
		return n1;
	}

	private void addEdge(Circle n1, Circle n2) {
		Line edge = new Line();
		edge.startXProperty().bind(n1.centerXProperty());
		edge.startYProperty().bind(n1.centerYProperty());
		edge.endXProperty().bind(n2.centerXProperty());
		edge.endYProperty().bind(n2.centerYProperty());
		pane.getChildren().add(edge);
		edge.toBack();
	}

	private void addCar(Circle n1, Circle n2) {
		Circle c = new Circle(n1.getCenterX(), n1.getCenterY(), 5);
		c.setFill(Color.INDIANRED);
		pane.getChildren().add(c);
		c.toFront();

		Path p = new Path();
		p.getElements().add(new MoveTo(n1.getCenterX(), n1.getCenterY()));
		p.getElements().add(new LineTo(n2.getCenterX(), n2.getCenterY()));

		PathTransition t = new PathTransition();
		t.setNode(c);
		t.setDuration(Duration.millis(1000));
		t.setCycleCount(PathTransition.INDEFINITE);
		t.setPath(p);
		t.play();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
