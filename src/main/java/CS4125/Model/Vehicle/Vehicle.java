package CS4125.Model.Vehicle;

import CS4125.Model.TrafficControl.ITCM;
import CS4125.Model.Utils.A_Star;
import CS4125.Model.Utils.IGraphable;
import CS4125.Model.Utils.Observer;

import java.util.*;

public class Vehicle extends Observer {
	private ITCM currentNode;
	private int currentNodeIndex = 0;
	private ITCM startNode;
	private ITCM endNode;
	private List<IGraphable> route;

	public Vehicle(ITCM start, ITCM end){
		startNode =start;
		currentNode =start;
		endNode=end;
		route= A_Star.findRoute(start, end);
	}

	public ITCM getCurrentNode()		{return currentNode;}
	public ITCM getNextNode() {
		return (route.get(currentNodeIndex+1) != null) ? (ITCM) route.get(currentNodeIndex+1) : null;
	}
	public ITCM getStarNode()			{return startNode;}
	public ITCM getEndNode()			{return endNode;}
	public List<IGraphable> getRoute()	{return route;}

	public void move() {
		if(getNextNode() != null) {
			currentNode = getNextNode();
			currentNodeIndex++;
		}
	}

	@Override
	public void update() {
		// TODO: 08-11-19 add implementation
	}
}