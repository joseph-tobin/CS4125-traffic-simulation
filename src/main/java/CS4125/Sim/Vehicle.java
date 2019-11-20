package CS4125.Sim;

import CS4125.TrafficControl.ITCM;
import CS4125.utils.IGraphable;
import CS4125.utils.Observer;

import java.util.*;

public class Vehicle extends Observer {
	private ITCM currentNode;
	private ITCM lastNode;
	private ITCM startNode;
	private ITCM endNode;
	private ArrayList<IGraphable> route;
	private boolean routeFound=false;

	public Vehicle(ITCM start, ITCM end){
		startNode=start;
		currentNode=start;
		endNode=end;
		ArrayList<IGraphable> routeArray=new ArrayList<IGraphable>();
		routeArray.add(start);
		route=routeArray;
		routeFind(routeArray);
	}
	public Vehicle(ITCM curr, ITCM last, ITCM start, ITCM end, ArrayList<IGraphable> route){
		this.currentNode=curr;
		this.lastNode=last;
		this.startNode=start;
		this.endNode=end;
		this.route=route;
	}
	public Vehicle copy(){
		return new Vehicle(this.currentNode, this.lastNode, this.startNode, this.endNode, this.route);
	}
	public ITCM getCurrentNode()		{return currentNode;}
	public ITCM getLastNode()			{return lastNode;}
	public ITCM getStartNode()			{return startNode;}
	public ITCM getEndNode()			{return endNode;}
	public ArrayList<IGraphable> getRoute()	{return route;}

	private void routeFind(ArrayList<IGraphable> in){
		ITCM lastIn=(ITCM)in.get(in.size()-1);
		//System.out.println("Vehicle.routeFind - lastIn: "+lastIn);
		//System.out.println("Vehicle.routeFind - endNode: "+endNode);
		//System.out.println("I am "+lastIn+". Looking at "+lastIn.getAdjacent().get(0));
		if (lastIn==endNode) {
			routeFound=true;
			route=in;
		}
		ArrayList<IGraphable> temp=in;
		if(lastIn.getAdjacent().size()==1){
			//System.out.println("Vehicle.routeFind - lastIn: "+lastIn+" has 1 adjacent");
			temp.add(lastIn.getAdjacent().get(0));
			routeFind(temp);
		} else if(lastIn.getAdjacent().size()==2) {
			//System.out.println("Vehicle.routeFind - lastIn: "+lastIn+" has 2 adjacent");
			if(lastIn.getAdjacent().get(0)!=in.get(in.size()-2))
				temp.add(lastIn.getAdjacent().get(0));
			else
				temp.add(lastIn.getAdjacent().get(1));
			routeFind(temp);
		} else if(lastIn.getAdjacent().size()>2) {
			for (int i=0; i<lastIn.getAdjacent().size(); i++) {
				if (lastIn.getAdjacent().get(i)!=in.get(in.size()-2)) {
					temp.add(lastIn.getAdjacent().get(i));
					routeFind(temp);
					if(!routeFound) {
						temp.remove(temp.size()-1);
					}
				}
			}
		}
	}


	public void move() {
		for (int i=0; i<this.route.size()-1; i++) {
			if (route.get(i)==currentNode) {
				lastNode=currentNode;
				currentNode=(ITCM)route.get(i+1);
			}
		}
	}

	@Override
	public void update() {
		// TODO: 08-11-19 add implementation
	}
}