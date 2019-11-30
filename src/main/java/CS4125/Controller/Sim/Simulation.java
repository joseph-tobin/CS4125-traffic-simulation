package CS4125.Controller.Sim;


import CS4125.Model.Metrics.Metric;
import CS4125.Model.Utils.IVehicleCreator;
import CS4125.Model.Vehicle.Vehicle;
import CS4125.Model.TrafficControl.*;
import CS4125.Model.Vehicle.VehicleCreator;
import CS4125.View.EventHandlers.UIController;
import javafx.scene.shape.Circle;
import CS4125.Model.TrafficControl.SimpleJunction;
import sun.java2d.pipe.SpanShapeRenderer;

import java.lang.reflect.Array;
import java.util.*;

public enum Simulation{

	INSTANCE;

	public List<ITCM> nodeList; // Having public breaks encapsulation - cannot have final due to it not being initialized before simulation
	private HashMap<String, Vehicle> routeMap;
	private List<Circle> circles;
	private int vehicleQuantity;
	private List<Vehicle> vehicles;
	private static UIController controller;
	private IVehicleCreator vc;


	public void init(UIController controller) {
		this.nodeList = new ArrayList<ITCM>();
		this.routeMap = new HashMap<String, Vehicle>();
		this.vehicles = new ArrayList<>();
		this.vehicleQuantity = 0;
		this.controller = controller;
		this.circles = new ArrayList<>();
	}

	Simulation() {
	}

	public void run(){
		nodeList.clear();
		//vehicles.clear();
		routeMap.clear();

		defaultNodes();

		for (ITCM itcm : nodeList)
			controller.addNode(itcm);

		for (ITCM itcm : nodeList) {
			for (ITCM value : itcm.getAdjacent()) {
				controller.addEdge(itcm, value);
			}
		}
		createVehicles((ArrayList<ITCM>) nodeList, 1200); // this might be problem
	}

	public void pause(){

	}

	public void reset(){
		deleteNode("TrafficLights_a");
	}

	public void addNode(String type, String name, int x, int y) {
		ITCM n;
		switch (type) {
			case "SimpleJunction": n = new SimpleJunction(name,x,y); break;
			case "TrafficLights": n = new TrafficLights(new SimpleJunction(name,x,y)); break;
			//case "Roundabout": n = new SimpleJunction(new Roundabout(x,y,new ArrayList<ITCM>())); break;
			default: n = new SimpleJunction(name,x,y); break;
		}
		nodeList.add(n);
		controller.addNode(n);
	}

	public void deleteNode(String label) {
		ITCM n = findNode(label);
		nodeList.remove(n);
		controller.deleteNode(n);
	}

	public ITCM findNode(String label) {
		for (ITCM n: nodeList) {
			if (n.getLabel().equals(label)) {
				return n;
			}
		}
		return null;
	}

	public int findNodeIndex(String label, List<ITCM> list) {
		for (int i = 0; i < list.size(); i++) { // we need this for loop here so we can return index you cant get index from enhanced
			if (list.get(i).getLabel().equals(label)) {
				return i;
			}
		}
		return -1;
	}


	public void addEdge(String l1, String l2) {
		int l1index = -1;
		int l2index = -1;
		l1index = findNodeIndex(l1, nodeList); // NOTE!!!! STRING LABELS MUST BE UNIQUE OR THIS WILL NOT WORK
		l2index = findNodeIndex(l2, nodeList);
		if (l1index != -1 && l2index != -1) {
			addAdjacent(nodeList.get(l1index), nodeList.get(l2index));
			controller.addEdge(nodeList.get(l1index), nodeList.get(l2index));
		} else {
			System.out.println("Labels not found");
		}
	}

	public void deleteEdge(String l1, String l2) {
        int l1index = -1;
        int l2index = -1;
        l1index = findNodeIndex(l1, nodeList); // NOTE!!!! STRING LABELS MUST BE UNIQUE OR THIS WILL NOT WORK
        l2index = findNodeIndex(l2, nodeList);
        if (l1index != -1 && l2index != -1) {
            removeAdjacent(nodeList.get(l1index), nodeList.get(l2index));
            //controller.removeEdge(nodeList.get(l1index), nodeList.get(l2index));
        } else {
            System.out.println("Labels not found");
        }
    }

	public void addAdjacent(ITCM n1, ITCM n2) {
		List<ITCM> n1list = n1.getAdjacent(); n1list.add(n2); // add n2 to adjacency list of n1
		List<ITCM> n2list = n2.getAdjacent(); n2list.add(n1); // add n1 to adjacency list of n2
		n1.setAdjacent(n1list);
		n2.setAdjacent(n2list);
	}

	public void removeAdjacent(ITCM n1, ITCM n2) {
        List<ITCM> n1list = n1.getAdjacent(); n1list.remove(n2); // add n2 to adjacency list of n1
        List<ITCM> n2list = n2.getAdjacent(); n2list.remove(n1); // add n1 to adjacency list of n2
        n1.setAdjacent(n1list);
        n2.setAdjacent(n2list);
    }


	public void defaultNodes(){
		//Existing Nodes & Adjacency lists - In future change to allow passing in a graph topology (e.g. CSV adjacency matrix)

		// adding to nodeList
		TrafficLights flagpoles = new TrafficLights(new SimpleJunction("TrafficLights_flag",300,200));
		TrafficLights a = new TrafficLights(new SimpleJunction("TrafficLights_a",400,300));
		TrafficLights b = new TrafficLights(new SimpleJunction("TrafficLights_b",200,400));
		flagpoles.setAdjacent(new ArrayList<>(Arrays.asList(a,b)));
		a.setAdjacent(new ArrayList<>(Arrays.asList(flagpoles,b)));
		b.setAdjacent(new ArrayList<>(Arrays.asList(flagpoles,a)));

		nodeList.addAll(Arrays.asList(flagpoles, a, b));
	}

	// Don't use
	public void createVehicle(ITCM start, ITCM end){

		float xCoord = 1; // TODO: start.getX + start.getY
		float yCoord = 2; // TODO: end.getX + end.getY
		String xyCoords = String.valueOf(xCoord) + String.valueOf(yCoord);
		if (routeMap.containsKey(xyCoords)) {
			//vehicles.add(routeMap.get(xyCoords).copy());
		} else {
			Vehicle newVehicle = new Vehicle(start, end);
			//vehicles.add(newVehicle);
			routeMap.put(xyCoords, newVehicle);
		}
	}

	// Use this to start thread to create vehicles use methods below to set things
	public void createVehicles(ArrayList<ITCM> nodes, int timer) {
		vc = new VehicleCreator(nodes, timer); // start vehicle creation with default timer and start end
		// use vc.setTimer(int)  to change the timer
	}

	public void setVCTimer(int timer) {
		vc.setTimer(timer);
	}

	public void updateNodes(List<ITCM> nodes) {
		vc.updateNodes(nodes);
	}

	public void updateGraph(){

	}

	public void addVehicleToController(Vehicle v){
		controller.addAnimation(v);
	}

	public Metric getMetrics(){
		Metric newMetric = new Metric();
		return newMetric.generateMetrics();
	}

	public List<ITCM> getNodeList(){
		return this.nodeList;
	}
	public List<Vehicle> getVehicleList() {return this.vehicles; };
	public void addVehicleToVehicleList(Vehicle v) {vehicles.add(v);}

	public void addVehicleAnim(Vehicle v, int index) {
		controller.addAnimation(v,index,v.getCurrentNode().getCurrentQueue((v.getNextNode())));
	}

	//public List<Vehicle> getVehicles(){
		//return this.vehicles;
		//return this.vehicles;
	//}

	public HashMap<String, Vehicle> getRouteMap(){
		return this.routeMap;
	}
}