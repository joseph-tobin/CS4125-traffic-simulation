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
		allNodes=new ArrayList<Node>(Arrays.asList(allNodes));
		private ArrayList<Node> routeArray=new ArrayList<Node>();
		routeArray.add(start);
		routeFind(routeArray);
	}
	public Vehicle(Vehicle in){
		this.currentNode=in.getCurrentNode();
		this.lastNode=in.getLastNode();
		this.startNode=in.getStartNode();
		this.endNode=in.getEndNode();
		this.route=in.routeFind();
	}

	public Node getCurrentNode()		{return currentNode;}
	public Node getLastNode()			{return lastNode;}
	public Node getStartNode()			{return startNode;}
	public Node getEndNode()			{return endNode;}
	public ArrayList<Node> routeFind()	{return route;}

	private void routeFind(ArrayList<Node> in){
		Node lastIn=in.get(in.size()-1);
		if (lastIn=lastNode) {
			route=in;
		}
		ArrayList<Node> temp=in;
		if(in.getAdjacent().size()>1) {
			for (int i=0; i<lastIn.getAdjacent.size(); i++) {
				if (lastIn.getAdjacent.get(i)!=in.get(in.size()-2)) {
					temp.add(lastIn.getAdjacent.get(i));
					routeFind(temp);
				}
			}
		}
	}

	public void move() {
		for (int i=0; i<route.size()-1; i++) {
			if (route.get(i)==currentNode) {
				lastNode=currentNode;
				currentNode=route.get(i+1);
			}
		}
	}
}