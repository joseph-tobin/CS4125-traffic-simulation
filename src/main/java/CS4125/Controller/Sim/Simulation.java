package CS4125.Controller.Sim;


import CS4125.Model.Metrics.Metric;
import CS4125.Model.Utils.IVehicleCreator;
import CS4125.Model.Vehicle.Car;
import CS4125.Model.TrafficControl.*;
import CS4125.Model.Vehicle.IVehicle;
import CS4125.Model.Vehicle.VehicleCreator;
import CS4125.View.EventHandlers.UIController;
import javafx.application.Platform;
import javafx.scene.shape.Circle;
import CS4125.Model.TrafficControl.SimpleJunction;

import java.util.*;

public enum Simulation{

	INSTANCE;

	public List<ITCM> nodeList; // Having public breaks encapsulation - cannot have final due to it not being initialized before simulation
	private HashMap<String, IVehicle> routeMap;
	private List<Circle> circles;
	private int vehicleQuantity;
	private List<IVehicle> vehicles;
	private static UIController controller;
	private IVehicleCreator vc;


	public void init(UIController controller) {
		this.nodeList = new ArrayList<ITCM>();
		this.routeMap = new HashMap<String, IVehicle>();
		this.vehicles = new ArrayList<>();
		this.vehicleQuantity = 0;
		this.controller = controller;
		this.circles = new ArrayList<>();
	}

	Simulation() {
	}

	/**
	 * Called when UI starts, is the 'main' method of sim
	 */
	public void run(){
		nodeList.clear();
		vehicles.clear();
		routeMap.clear();

		defaultNodes();

		for (ITCM itcm : nodeList)
			controller.addNode(itcm);

		for (ITCM itcm : nodeList) {
			for (ITCM value : itcm.getAdjacent()) {
				controller.addEdge(itcm, value);
			}
		}
		createVehicles(getEndpoints(), 1200); // this might be problem
	}

	public void pause(){

	}

	public void reset(){
		deleteNode("TrafficLights_a");
	}

	/**
	 * Add a node
	 * @param type Node type
	 * @param name Label
	 * @param x xcoord
	 * @param y ycoord
	 */
	public void addNode(String type, String name, int x, int y, boolean endpoint) {
		ITCM n;
		switch (type) {
			case "SimpleJunction": n = new SimpleJunction(name,x,y); break;
			case "TrafficLights": n = new TrafficLights(new SimpleJunction(name,x,y)); break;
			//case "Roundabout": n = new SimpleJunction(new Roundabout(x,y,new ArrayList<ITCM>())); break;
			default: n = new SimpleJunction(name,x,y); break;
		}
		if(endpoint) // TODO: 30/11/2019 take boolean in param list, if boolean cast to IEndpoint
			n = (IEndpoint) n;
		nodeList.add(n);
		controller.addNode(n);
	}

	/**
	 * Remove node from node list given label
	 * @param label
	 */
	public void deleteNode(String label) {
		ITCM n = findNode(label);
		nodeList.remove(n);
		controller.deleteNode(n);
	}

	/**
	 * Finds node in nodeList given label
	 * @param label
	 * @return ITCM
	 */
	public ITCM findNode(String label) {
		for (ITCM n: nodeList) {
			if (n.getLabel().equals(label)) {
				return n;
			}
		}
		return null;
	}

	/**
	 * Finds the index of a node in a list given a label
	 * @param label
	 * @param list
	 * @return int
	 */
	public int findNodeIndex(String label, List<ITCM> list) {
		for (int i = 0; i < list.size(); i++) { // we need this for loop here so we can return index you cant get index from enhanced
			if (list.get(i).getLabel().equals(label)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Adds an edge between two node labels
	 * @param l1 node label 1
	 * @param l2 node label 2
	 */
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

	/**
	 * Removes edge between two node labels, not implemented yet hence the commented line
	 * @param l1 label of node 1
	 * @param l2 label of node 2
	 */
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

	/**
	 * Adds adjacency between n1 and n2
	 * @param n1 node 1
	 * @param n2 node 2
	 */
	public void addAdjacent(ITCM n1, ITCM n2) {
		if (n1.getAdjacent().isEmpty()) {
			n1.setAdjacent(new ArrayList<>(Arrays.asList(n2)));
		} else { // apparently we dont need this else statement but if things start breaking maybe we do lol
			//List<ITCM> test = n1.getAdjacent();
			//test.add(n2);
			//n1.setAdjacent(test);
		}
		if (n2.getAdjacent().isEmpty()) {
			n2.setAdjacent(new ArrayList<>(Arrays.asList(n2)));
		} else { // apparently we dont need this
//			List<ITCM> test = n2.getAdjacent();
//			test.add(n1);
//			n2.setAdjacent(test);
		}

	}

	/**
	 * Remove the adjacent nodes
	 * @param n1 first node
	 * @param n2 second node
	 */
	public void removeAdjacent(ITCM n1, ITCM n2) {
        List<ITCM> n1list = n1.getAdjacent(); n1list.remove(n2); // add n2 to adjacency list of n1
        List<ITCM> n2list = n2.getAdjacent(); n2list.remove(n1); // add n1 to adjacency list of n2
        n1.setAdjacent(n1list);
        n2.setAdjacent(n2list);
    }

	/**
	 * Create default nodes
	 */
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
			IVehicle newVehicle = new Car(start, end);
			//vehicles.add(newVehicle);
			routeMap.put(xyCoords, newVehicle);
		}
	}

	/**
	 * Get nodes of type IEndpoint from the nodeList
	 * @return subset of Node list where node instanceof IEndpoint
	 */
	public List<IEndpoint> getEndpoints() {
		List<IEndpoint> endpoints = new ArrayList<>();
		for(int i = 0; i < nodeList.size(); i++) {
			System.out.println("Node: " + nodeList.get(i) + ", type = " + nodeList.get(i).getClass());
			if(nodeList.get(i) instanceof IEndpoint) {
				endpoints.add((IEndpoint) nodeList.get(i));
			}
		}
		return endpoints;
	}

	/**
	 * Creates vehicle creator thread
	 * @param nodes list of nodes
	 * @param timer how often you want a vehicle created
	 * Use vc.setTimer(int) to change the timer
	 */
	public void createVehicles(List<IEndpoint> nodes, int timer) {
		for(IEndpoint e: nodes) {
			System.out.println(e.getLabel());
		}
		vc = new VehicleCreator(nodes, timer); // start vehicle creation with default timer and start end
	}

	/**
	 * Used to set the timer of the VehicleCreator thread to have vehicles created at different timings
	 * @param timer how often you want a vehicle created
	 */
	public void setVCTimer(int timer) {
		vc.setTimer(timer);
	}

	/**
	 * Returns metric, not complete
	 * @return
	 */
	public Metric getMetrics(){
		Metric newMetric = new Metric();
		return newMetric.generateMetrics();
	}

	/**
	 * Returns the list of nodes
	 * @return
	 */
	public List<ITCM> getNodeList(){
		return this.nodeList;
	}


	public List<IVehicle> getVehicleList() {return this.vehicles; };
	public void addVehicleToVehicleList(IVehicle v) {vehicles.add(v);}

	/**
	 * Adds the vehicle animation to UI Controller with the current index
	 * @param v
	 */
	public void addVehicleAnim(IVehicle v) {
		Platform.runLater(
            () -> {
                controller.addAnimation(v, 1000);
            }
		);
	}

	public HashMap<String, IVehicle> getRouteMap(){
		return this.routeMap;
	}
}