package TrafficControl;

import java.util.ArrayList;

public class Node{
	private float x;
	private float y;
	private ArrayList<Node> adjacent;

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

}