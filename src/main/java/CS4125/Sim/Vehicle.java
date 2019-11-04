package CS4125.Sim;

import CS4125.TrafficControl.Node;
import java.util.*;

public class Vehicle {
	private Node currentNode;
	private Node lastNode;
	private Node startNode;
	private Node endNode;
	private ArrayList<Node> route;

	public Vehicle(Node start, Node end){
		startNode=start;
		currentNode=start;
		endNode=end;
		ArrayList<Node> routeArray=new ArrayList<Node>();
		routeArray.add(start);
		routeFind(routeArray);
	}
	public Vehicle(Node curr, Node last, Node start, Node end, ArrayList<Node> route){
		this.currentNode=curr;
		this.lastNode=last;
		this.startNode=start;
		this.endNode=end;
		this.route=route;
	}
	public Vehicle copy(){
		return new Vehicle(this.currentNode, this.lastNode, this.startNode, this.endNode, this.route);
	}
	public Node getCurrentNode()		{return currentNode;}
	public Node getLastNode()			{return lastNode;}
	public Node getStartNode()			{return startNode;}
	public Node getEndNode()			{return endNode;}
	public ArrayList<Node> getRoute()	{return route;}

	private void routeFind(ArrayList<Node> in){
		Node lastIn=in.get(in.size()-1);
		if (lastIn==lastNode) {
			route=in;
		}
		ArrayList<Node> temp=in;
		if(lastIn.getAdjacent().size()>1) {
			for (int i=0; i<lastIn.getAdjacent().size(); i++) {
				if (lastIn.getAdjacent().get(i)!=in.get(in.size()-2)) {
					temp.add(lastIn.getAdjacent().get(i));
					routeFind(temp);
				}
			}
		}
	}

	public void move() {
		for (int i=0; i<this.route.size()-1; i++) {
			if (route.get(i)==currentNode) {
				lastNode=currentNode;
				currentNode=route.get(i+1);
			}
		}
	}
}