package CS4125.Model.Vehicle;

import CS4125.Model.TrafficControl.ITCM;
import CS4125.Model.Utils.A_Star;
import CS4125.Model.Utils.IGraphable;
//import CS4125.Model.Utils.Observer;

import java.util.*;
import java.sql.Timestamp;

public class Car implements IVehicle {
	private ITCM currentNode;
	private ITCM prevNode = null;
	private int currentNodeIndex = 0;
	private ITCM startNode;
	private ITCM endNode;
	private List<IGraphable> route;
	private Timestamp initialTime;

	public Car(ITCM start, ITCM end){
		startNode = start;
		currentNode = start;
		endNode = end;
		route = A_Star.findRoute(start, end);
		initialTime = new Timestamp(System.currentTimeMillis());
	}

	public ITCM getCurrentNode()		{return currentNode;}
	public ITCM getNextNode() {
		return (route.get(currentNodeIndex+1) != null) ? (ITCM) route.get(currentNodeIndex+1) : null;
	}
	public ITCM getStarNode()			{return startNode;}
	public ITCM getEndNode()			{return endNode;}
	public List<IGraphable> getRoute()	{return route;}
	@Override
	public Timestamp getInitialTime() {return initialTime;}

	public void move() {
		if(getNextNode() != null) {
			while (!(this.getNextNode().enterQueue(currentNode,this)))
				System.out.println("Waiting"); // wait until the node is available to enter#
			// leave previous queue
			if(prevNode != null)
				currentNode.exitQueue(prevNode);

			prevNode = currentNode;
			currentNode = getNextNode();
			currentNodeIndex++;
		}
	}

//	@Override
//	public void update(Subject s, int state) {
//		if (state==0) {
//			this.move();
//			s.detach(this);
//			currentNode.attach(this);
//		}
//	}
}