package CS4125.UserInterface;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class UserInterface extends Application {

	Pane pane = new Pane();
	Scene scene = new Scene(pane, 600, 600);

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.show();

		Circle n1 = addNode(200, 200);
		Circle n2 = addNode(300, 400);

		addEdge(n1, n2);

		addCar(n1, n2);
		addCar(n2, n1);

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
	}

	private void addCar(Circle n1, Circle n2) {
		Circle c1 = new Circle(n1.getCenterX(), n1.getCenterY(), 5);
		c1.setFill(Color.INDIANRED);
		pane.getChildren().add(c1);

		Path p1 = new Path();
		p1.getElements().add(new MoveTo(n1.getCenterX(), n1.getCenterY()));
		p1.getElements().add(new LineTo(n2.getCenterX(), n2.getCenterY()));

		PathTransition t1 = new PathTransition();
		t1.setNode(c1);
		t1.setDuration(Duration.millis(1000));
		t1.setCycleCount(PathTransition.INDEFINITE);
		t1.setPath(p1);
		t1.play();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
