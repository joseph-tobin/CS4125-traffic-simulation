package CS4125.View.EventHandlers;

import CS4125.Model.TrafficControl.ITCM;
import CS4125.View.NodeDelay;
import CS4125.View.UserInterface.UIView;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class UIController{

	private UIView view;
	private List<String> nodesUI;

	public UIController(UIView view){
		this.view = view;
		nodesUI = new ArrayList<>();
	}

	/**
	 * Add a Node to the UI "map"
	 * @param n Node to be added
	 */
	public void addNode(ITCM n) {
		Circle c = new Circle(n.getX(), n.getY(), 10);
		Tooltip.install(c, new Tooltip(n.getLabel()));
		c.setFill(Color.FORESTGREEN);
		view.getSimPane().getChildren().add(c);
		nodesUI.add(n.getLabel());
	}

	/**
	/**
	 * Add an edge between two existing nodes, getting their X and Y co-ordinates
	 * @param n1: node 1
	 * @param n2: node 2
	 */
	public void addEdge(ITCM n1, ITCM n2) {
		Line edge = new Line();
		edge.setStartX(n1.getX());
		edge.setStartY(n1.getY());
		edge.setEndX(n2.getX());
		edge.setEndY(n2.getY());
		view.getSimPane().getChildren().add(edge);
		edge.toBack();
	}

	/**
	 * Add a vehicle animated along a path of nodes
	 * Each entry contains a node and the time it will take to reach that node from the previous one
	 *  (Calculated by A_Star: a function of TCM type, number of connected nodes, and their congestion levels)
	 * First element is start element, naturally with no delay
	 * @param path: array of NodeDelay objects: 2D array of node and the estimated time to reach it from prev. node
	 */
	public void addVehicle(NodeDelay[] path) {
		Circle c = new Circle(path[0].getX(), path[0].getY(), 5);
		c.setFill(Color.INDIANRED);
		view.getSimPane().getChildren().add(c);
		c.toFront();

		SequentialTransition seq = new SequentialTransition();
		for(int i = 0; i < path.length - 1; i++){
			Path p = new Path();
			p.getElements().add(new MoveTo(path[i].getX(), path[i].getY()));
			p.getElements().add(new LineTo(path[i+1].getX(), path[i+1].getY()));

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

	public UIController getUIC() {return this;}

	public List<String> getNodesUI() {
		return nodesUI;
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