package CS4125.Model.Vehicle;

import CS4125.Controller.Sim.Simulation;
import CS4125.Model.TrafficControl.ITCM;
import CS4125.Model.Utils.A_Star;
import CS4125.Model.Utils.IGraphable;
import CS4125.Model.Utils.Observer;
import sun.awt.X11.XSystemTrayPeer;

import java.util.*;
import java.sql.Timestamp;

public class Car implements Runnable, IVehicle, Observer {
	private ITCM currentNode;
	private ITCM prevNode = null;
	private int currentNodeIndex = 0;
	private ITCM startNode;
	private ITCM endNode;
	private List<IGraphable> route;
	private Timestamp initialTime;
	private Timestamp endTime;

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
	@Override
	public Timestamp getEndTime() {return endTime;}

	public void move() {
		if(getNextNode() != null) {
			while (!(this.getNextNode().enterQueue(currentNode,this)))
				System.out.println("Waiting"); // wait until the node is available to enter#
			// leave previous queue

			prevNode = currentNode;
			currentNode = getNextNode();
			currentNodeIndex++;
			addToMoveQueue();
			System.out.println("Moving to " + currentNode.getLabel());

			if(currentNode != startNode)
				currentNode.exitQueue(prevNode);

			if (currentNode.equals(endNode)) {
				this.endTime = new Timestamp(System.currentTimeMillis());
			}
		}
	}

	/**
	 * Add Move object to moveQueue in Simulation
	 */
	private void addToMoveQueue() {
		Simulation.INSTANCE.getMoveQueue().offer(new Move(prevNode, currentNode));
	}

	@Override
	public void update(int state) {
		if(state==0) {
			this.move();
			subject.detach(this);
			subject.attach(this);
		}
	}

	@Override
	public void run() {
		while(currentNode != endNode) {
			move();
			try {
				long time = Simulation.INSTANCE.getJourneyTime(prevNode, currentNode);
				System.out.println("sleeping: " + time);
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}