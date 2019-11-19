package CS4125.TrafficControl;

import CS4125.utils.IGraphable;
import CS4125.utils.Subject;

import java.util.ArrayList;
import java.util.HashMap;


public class Node extends Subject implements IEndpoint, IGraphable {

	private float x;
	private float y;
	private int capacity;
	private ArrayList<NodePair> adjacent;


	public Node(float x, float y) {
		this(x, y, new ArrayList<>());
	}
	public Node(float x, float y, ArrayList<NodePair> adj){
		this.x = x;
		this.y = y;
		this.adjacent = adj;
	}


	// *** Accessor Methods ***
	public float getX(){
		return this.x;
	}
	public float getY(){
		return this.y;
	}
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	public ArrayList<NodePair> getAdjacent() {return this.adjacent; }
	public void setAdjacent(ArrayList<NodePair> adj) { this.adjacent = adj; }


	/**
	 * Implementation of IEndpoint Interface
	 * @return capacity set for this Endpoint on the graph
	 */
	@Override
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Implementation of the IEndpoint Interface
	 * @param capacity Capacity of this Endpoint
	 */
	@Override
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Implementaton of IGraphable, calculate this Node's heuristic value for use in route finding
	 * @return Heuristic value as an integer
	 */
	@Override
	public int getHeuristic() {
		return 0;
	}

	/**
	 * Override compareTo Method, compares the Heuristic of two given nodes
	 * @param o Object which is being compared
	 * @return 1 if this > o, 0 if this == o, -1 if this < o
	 */
	@Override
	public int compareTo(Object o) {
		Node n = (Node)o;
		if(this.getHeuristic() < ((Node) o).getHeuristic())	return -1;
		else if(this.getHeuristic() > ((Node) o).getHeuristic()) return 1;
		return 0; // else this.heuristic() == o.getHeuristic
	}
}
