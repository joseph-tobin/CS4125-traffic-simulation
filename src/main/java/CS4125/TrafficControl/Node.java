package CS4125.TrafficControl;

import CS4125.utils.Subject;

import java.util.ArrayList;

public class Node extends Subject implements IEndpoint{
	private float x;
	private float y;
	private int capacity;
	private ArrayList<Node> adjacent;

	public Node(float x, float y) {
		this(x, y, new ArrayList<>());
	}
	public Node(float x, float y, ArrayList<Node> adj){
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
	public ArrayList<Node> getAdjacent() {return this.adjacent; }
	public void setAdjacent(ArrayList<Node> adj) { this.adjacent = adj; }


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
}