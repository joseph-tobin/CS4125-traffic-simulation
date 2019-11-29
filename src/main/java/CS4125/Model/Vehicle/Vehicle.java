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
//	public Vehicle(ITCM curr, ITCM last, ITCM start, ITCM end, ArrayList<ITCM> route){
//		this.currentNode=curr;
//		this.lastNode=last;
//		this.startNode=start;
//		this.endNode=end;
//		this.route=route;
//	}
	public Vehicle copy(){
		return new Vehicle(this.startNode, this.endNode);
	}
	public ITCM getCurrentNode()		{return currentNode;}
	public ITCM getLastNode()			{return lastNode;}
	public ITCM getStartNode()			{return startNode;}
	public ITCM getEndNode()			{return endNode;}
	public List<IGraphable> getRoute()	{return route;}

//	private void routeFind(ArrayList<ITCM> in){
//		ITCM lastIn=in.get(in.size()-1);
//		//System.out.println("Vehicle.routeFind - lastIn: "+lastIn);
//		//System.out.println("Vehicle.routeFind - endNode: "+endNode);
//		//System.out.println("I am "+lastIn+". Looking at "+lastIn.getAdjacent().get(0));
//		if (lastIn==endNode) {
//			routeFound=true;
//			route=in;
//		}
//		ArrayList<ITCM> temp=in;
//		if(lastIn.getAdjacent().size()==1){
//			//System.out.println("Vehicle.routeFind - lastIn: "+lastIn+" has 1 adjacent");
//			temp.add((ITCM) lastIn.getAdjacent().get(0));
//			routeFind(temp);
//		} else if(lastIn.getAdjacent().size()==2) {
//			//System.out.println("Vehicle.routeFind - lastIn: "+lastIn+" has 2 adjacent");
//			if(lastIn.getAdjacent().get(0)!=in.get(in.size()-2))
//				temp.add((ITCM) lastIn.getAdjacent().get(0));
//			else
//				temp.add((ITCM) lastIn.getAdjacent().get(1));
//			routeFind(temp);
//		} else if(lastIn.getAdjacent().size()>2) {
//			for (int i=0; i<lastIn.getAdjacent().size(); i++) {
//				if (lastIn.getAdjacent().get(i)!=in.get(in.size()-2)) {
//					temp.add((ITCM) lastIn.getAdjacent().get(i));
//					routeFind(temp);
//					if(!routeFound) {
//						temp.remove(temp.size()-1);
//					}
//				}
//			}
//		}
//	}


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