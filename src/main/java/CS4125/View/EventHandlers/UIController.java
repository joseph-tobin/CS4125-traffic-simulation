package CS4125.View.EventHandlers;

import CS4125.Controller.Sim.Simulation;
import CS4125.Model.TrafficControl.ITCM;
import CS4125.Model.Vehicle.Car;
import CS4125.Model.Vehicle.IVehicle;
import CS4125.View.UserInterface.UIView;
import javafx.animation.PathTransition;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.*;

public class UIController{

    // For adding to the view
	private UIView view;
	// For adding/deleting/referencing nodes/edges on the UI
	private Map<String, Circle> nodeLabelCircles;
	private Map<Line, List<String> > nodeEdges;

	public UIController(UIView view){
		this.view = view;
		nodeLabelCircles = new HashMap<>();
		nodeEdges = new HashMap<>();
	}

	/**
	 * Add a Node to the UI "map"
	 * @param n Node to be added
	 */
	public void addNode(ITCM n) {
	    String name = n.getLabel();
		Circle c = new Circle(n.getX(), n.getY(), 10);
		Tooltip.install(c, new Tooltip(name));
		c.setFill(Color.FORESTGREEN);
		view.getSimPane().getChildren().add(c);

		nodeLabelCircles.put(name, c);
	}

	/**
	 * For deleting a node and the edges associated with it
	 * Cycle through the map of edges to the list of nodes (pair) it links
	 * Find instances of this node in that pair and remove the associated node
	 * @param n the node to be removed
	 */
	public void deleteNode(ITCM n){
		String name = n.getLabel();
	    Circle toRemove = nodeLabelCircles.get(name);
	    view.getSimPane().getChildren().remove(toRemove);

		// find all edges connected to this node
		// delete edges which are mapped to that node
		Iterator<Map.Entry<Line, List<String> > > iter = nodeEdges.entrySet().iterator();
		while (iter.hasNext()) {
			// iterate through list of linked nodes (pair)
			Map.Entry<Line, List<String> > pair = iter.next();
			for (String node : pair.getValue()) {
				if (node.equals(name)) {
					// if found, remove from UI and the map of edges
					view.getSimPane().getChildren().remove(pair.getKey());
					iter.remove();
				}
			}
		}
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

		// Add a new edge mapped to two nodes
        nodeEdges.put(edge, new ArrayList<String>(Arrays.asList(n1.getLabel(), n2.getLabel())));
	}

	/**
	 * Add a vehicle animated along a path of nodes
	 * This will be called for each portion of the journey, until they reach the end
	 *  (Calculated by A_Star: a function of TCM type, number of connected nodes, and their congestion levels)
	 * First element is start element, naturally with no delay
	 * @param v Vehicle being moved
	 * @param cost time taken for this part of the journey
	 */
	public void addAnimation(IVehicle v, int cost) {
		Circle c = new Circle(v.getCurrentNode().getX(), v.getCurrentNode().getY(), 5);
		c.setFill(Color.INDIANRED);
		view.getSimPane().getChildren().add(c);
		c.toFront();

		Path p = new Path();
		p.getElements().add(new MoveTo(v.getCurrentNode().getX(), v.getCurrentNode().getY()));
		p.getElements().add(new LineTo(v.getNextNode().getX(), v.getNextNode().getY()));

		PathTransition t = new PathTransition();
		t.setNode(c);
		t.setDuration(Duration.millis(cost));
		t.setCycleCount(1);
		t.setPath(p);
		t.play();

		t.setOnFinished(event -> {
			t.stop();
			view.getSimPane().getChildren().remove(c);
			v.update(0);
			if(v.getNextNode()!=null)
				addAnimation(v, Simulation.INSTANCE.getJourneyTime(v));
		});
        System.out.println("car finished journey");
	}

	public UIController getUIC() {return this;}

	/**
	 * Get the list of other nodes (apart from the one just created)
	 * @param selected the selected node that was just created
	 * @return list of nodes
	 */
	public List<String> getNodeLabels(String selected) {
		List<String> strOut = new ArrayList<>(Arrays.asList(nodeLabelCircles.keySet().toArray(new String[nodeLabelCircles.size()])));
		strOut.removeIf(node -> node.equals(selected));
		return strOut;
	}

}