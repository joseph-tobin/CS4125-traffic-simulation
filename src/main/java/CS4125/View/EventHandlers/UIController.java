package CS4125.View.EventHandlers;

import CS4125.Controller.Sim.Simulation;
import CS4125.Model.TrafficControl.ITCM;
import CS4125.View.UserInterface.UIView;
import javafx.animation.PathTransition;
import javafx.scene.Cursor;
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

	private List<PathTransition> animations;

	private List<Circle> circles;

	public UIController(UIView view){
		this.view = view;
		nodeLabelCircles = new HashMap<>();
		nodeEdges = new HashMap<>();
		animations = new ArrayList<>();
		circles = new ArrayList<>();
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

		// delete node on click
		c.setOnMouseClicked(event -> {
			Simulation.INSTANCE.deleteNode(name);
		});
		c.setCursor(Cursor.HAND);

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
                    removeEdgeFromUI(iter, pair);
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

		// delete node on click
		edge.setOnMouseClicked(event -> {
			Simulation.INSTANCE.deleteEdge(n1.getLabel(), n2.getLabel());
		});
		edge.setCursor(Cursor.HAND);

		// Add a new edge mapped to two nodes
        nodeEdges.put(edge, new ArrayList<String>(Arrays.asList(n1.getLabel(), n2.getLabel())));
	}

	/**
	 * For deleting an edge
     * @param node1 one of two nodes connected by the edge
     * @param node2 the second node
	 */
	public void deleteEdge (ITCM node1, ITCM node2) {
		String n1 = node1.getLabel();
		String n2 = node2.getLabel();

		// find and delete all edges connected by these nodes
		Iterator<Map.Entry<Line, List<String> > > iter = nodeEdges.entrySet().iterator();
		while (iter.hasNext()) {
			// iterate through list of linked nodes (pair)
			Map.Entry<Line, List<String> > pair = iter.next();
			String brother = pair.getValue().get(0);
			String sister = pair.getValue().get(1);

			if (n1.equals(brother) && n2.equals(sister)) {
                // if found, remove from UI and the map of edges
                removeEdgeFromUI(iter, pair);
            }
			else if (n2.equals(brother) && n1.equals(sister)) {
                // if found, remove from UI and the map of edges
                removeEdgeFromUI(iter, pair);
            }
		}
	}

    /**
     * Removes an edge connected by two nodes – to avoid code replication
     * @param iter iterator from the loop which called this
     * @param pair the pair of nodes connected by this edge
     */
    private void removeEdgeFromUI(Iterator<Map.Entry<Line, List<String>>> iter, Map.Entry<Line, List<String>> pair) {
        view.getSimPane().getChildren().remove(pair.getKey());
        iter.remove();
    }

    /**
	 * Add a vehicle animated along a path of nodes
	 * This will be called for each portion of the journey, until they reach the end
	 *  (Time calculated by A_Star: a function of TCM type, number of connected nodes, and their congestion levels)
	 * @param start beginning of the animation
     * @param end end of the animation
	 * @param time time taken for this part of the journey
	 */
	public void addAnimation(ITCM start, ITCM end, int time) {
		Circle c = new Circle(start.getX(), start.getY(), 5);
		c.setFill(Color.INDIANRED);
		view.getSimPane().getChildren().add(c);
		c.toFront();

		Path p = new Path();
		p.getElements().add(new MoveTo(start.getX(), start.getY()));
		p.getElements().add(new LineTo(end.getX(), end.getY()));

		PathTransition t = new PathTransition();
		t.setNode(c);
		t.setDuration(Duration.millis(time));
		t.setCycleCount(1);
		t.setPath(p);
		t.play();

		animations.add(t);
		circles.add(c);

		t.setOnFinished(event -> {
			t.stop();
			view.getSimPane().getChildren().remove(c);
		});
	}

	/**
	 * Get the list of other nodes (apart from the one just created)
	 * @param selected the selected node that was just created
	 * @return list of nodes
	 */
	public List<String> getNodeLabels(String selected) {
		//List<String> strOut = new ArrayList<>(Arrays.asList(nodeLabelCircles.keySet().toArray(new String[nodeLabelCircles.size()])));
		List<String> strOut = new ArrayList<>();
		for (ITCM i : Simulation.INSTANCE.getNodeList()) {
			strOut.add(i.getLabel());
		}
		strOut.removeIf(node -> node.equals(selected));
		return strOut;
	}

	public List<PathTransition> getAnimations() {
		return animations;
	}

	public List<Circle> getCircles() {
		return circles;
	}
}