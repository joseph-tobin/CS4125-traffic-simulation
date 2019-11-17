package CS4125.UserInterface;

import CS4125.Sim.Simulation;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class UIController{

	private UIView view;

	UIController(UIView view){
		this.view = view;
	}

	Circle addNode(double x, double y) {
		Circle n = new Circle(x, y, 10);
		n.setFill(Color.FORESTGREEN);
		view.getPane().getChildren().add(n);

		n.addEventHandler(MouseEvent.ANY,
				new UIController.ClickHandler(n));

		return n;
	}

	void addEdge(Circle n1, Circle n2) {
		Line edge = new Line();
		edge.startXProperty().bind(n1.centerXProperty());
		edge.startYProperty().bind(n1.centerYProperty());
		edge.endXProperty().bind(n2.centerXProperty());
		edge.endYProperty().bind(n2.centerYProperty());
		view.getPane().getChildren().add(edge);
		edge.toBack();
	}

	void addCar(NodeDelay [] path) {
		Circle c = new Circle(path[0].getNode().getCenterX(), path[0].getNode().getCenterY(), 5);
		c.setFill(Color.INDIANRED);
		view.getPane().getChildren().add(c);
		c.toFront();

		SequentialTransition seq = new SequentialTransition();
		for(int i = 0; i < path.length - 1; i++){
			Path p = new Path();
			p.getElements().add(new MoveTo(path[i].getNode().getCenterX(), path[i].getNode().getCenterY()));
			p.getElements().add(new LineTo(path[i+1].getNode().getCenterX(), path[i+1].getNode().getCenterY()));

			PathTransition t = new PathTransition();
			t.setNode(c);
			t.setDuration(Duration.millis(path[i+1].getDelay()));
			t.setPath(p);

			seq.getChildren().add(t);
		}
		seq.setCycleCount(SequentialTransition.INDEFINITE);
		seq.setCycleCount(SequentialTransition.INDEFINITE);
		seq.play();
	}

	static class Delta { double x, y; }

	public class ClickHandler implements EventHandler<MouseEvent> {

		private boolean dragging = false;

		/*
		 * Dragging and Dropping Nodes
		 */
		ClickHandler(Circle n) {
			if(n.getRadius() == -1)
				return;
			final Delta dragDelta = new Delta();

			n.setOnMousePressed(mouseEvent -> {
				// record a delta distance for the drag and drop operation.
				dragDelta.x = n.getCenterX() - mouseEvent.getX();
				dragDelta.y = n.getCenterY() - mouseEvent.getY();
				n.getScene().setCursor(Cursor.MOVE);
			});
			n.setOnMouseReleased(mouseEvent -> {
				n.getScene().setCursor(Cursor.HAND);
			});
			n.setOnMouseDragged(mouseEvent -> {
				n.setCenterX(mouseEvent.getX() + dragDelta.x);
				n.setCenterY(mouseEvent.getY() + dragDelta.y);
			});
			n.setOnMouseEntered(mouseEvent -> {
				if (!mouseEvent.isPrimaryButtonDown()) {
					n.getScene().setCursor(Cursor.HAND);
				}
			});
			n.setOnMouseExited(mouseEvent -> {
				if (!mouseEvent.isPrimaryButtonDown()) {
					n.getScene().setCursor(Cursor.DEFAULT);
				}
			});
		}

		/*
		 * Adding and Removing Nodes
		 */
		@Override
		public void handle(MouseEvent e) {
			if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
				dragging = false;
			}
			else if (e.getEventType() == MouseEvent.DRAG_DETECTED) {
				dragging = true;
			}
			else if (e.getEventType() == MouseEvent.MOUSE_CLICKED) {
				if (!dragging) {
					if (e.getButton() == MouseButton.PRIMARY) {
						double x = e.getX(); // remove pane's coordinates
						double y = e.getY(); // remove pane's coordinates
						Circle c = addNode(x, y);

						view.getPane().getChildren().add(c);
					}
					else if (e.getButton() == MouseButton.SECONDARY) {
						// if circle was clicked, remove it
						Circle picked = (Circle) e.getPickResult().getIntersectedNode();
						if (picked instanceof Circle) {
							view.getPane().getChildren().remove(picked);
						}
					}
				}
			}
		}
	}
//	private Simulation sim;
//
//	public void save(){
//
//	}
//
//	public void load(){
//
//	}
//
//	public void display(){
//
//	}
//
//	public void updateSim(){
//
//	}
//
//	public void getMetrics(){
//		sim.getMetrics();
//	}
}