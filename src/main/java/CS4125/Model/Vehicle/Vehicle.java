package CS4125.Model.Vehicle;

import CS4125.Model.TrafficControl.ITCM;
import CS4125.Model.Utils.A_Star;
import CS4125.Model.Utils.IGraphable;
import CS4125.Model.Utils.Observer;

import java.util.*;

public class Vehicle extends Observer {
	private ITCM currentNode;
	private ITCM lastNode;
	private ITCM startNode;
	private ITCM endNode;
	private List<IGraphable> route;
	private boolean routeFound=false;

	public Vehicle(ITCM start, ITCM end){
		startNode=start;
		currentNode=start;
		endNode=end;
		route= A_Star.findRoute(start, end);
	}

	public Vehicle copy(){
		return new Vehicle(this.startNode, this.endNode);
	}
	public ITCM getCurrentNode()		{return currentNode;}
	public ITCM getLastNode()			{return lastNode;}
	public ITCM getStartNode()			{return startNode;}
	public ITCM getEndNode()			{return endNode;}
	public List<IGraphable> getRoute()	{return route;}

	public void move() {
		for (int i=0; i<this.route.size()-1; i++) {
			if (route.get(i)==currentNode) {
				lastNode=currentNode;
				currentNode= (ITCM) route.get(i+1);
			}
		}
	}

	@Override
	public void update() {
		// TODO: 08-11-19 add implementation
	}
}