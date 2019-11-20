package CS4125.Controller.Sim;


import CS4125.Model.Metrics.Metric;
import CS4125.Model.Vehicle.Vehicle;
import CS4125.Model.TrafficControl.*;
//import CS4125.UserInterface.NodeDelay;
import CS4125.View.EventHandlers.UIController;
import CS4125.UserInterface.UIController;
import javafx.scene.shape.Circle;
import CS4125.Model.TrafficControl.IGraphable;
import CS4125.Model.TrafficControl.SimpleJunction;

import java.util.*;

public enum Simulation{

	INSTANCE;

	public ArrayList<ITCM> nodeList; // Having public breaks encapsulation - cannot have final due to it not being initialized before simulation
	private HashMap<String, Vehicle> routeMap;
	private List<Circle> circles;
	private int vehicleQuantity;
	private ArrayList<Vehicle> vehicles = new ArrayList<>();
	private static UIController controller;


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
		// HARCODED FOR NOW
		vehicleQuantity = 10;
		ITCM start = new SimpleJunction(10, 10, new ArrayList<NodePair>()) {};
		ITCM end = new SimpleJunction(10,5, new ArrayList<NodePair>());

		// HARDCODED FOR NOW
		// TODO: instantiate nodeList and routeMap
		createNodes();
		for (int i = 1; i < vehicleQuantity; i++) {
			// INITIALISE RANDOM START AND END NODE
			createVehicle(nodeList.get(new Random().nextInt(nodeList.size() - 1)), nodeList.get(new Random().nextInt(nodeList.size() - 1)));
		}

		for (int j = 0; j < nodeList.size(); j++) {
			Circle circle = controller.addNode(nodeList.get(j).getX(), nodeList.get(j).getY());
			circles.add(circle);
		}
		for (int j = 0; j < circles.size(); j++) {
			for (int k = 0; k < nodeList.get(j).getAdjacent().size(); k++) {
				//controller.addEdge(circles.get(j), nodeList.get(j).getAdjacent().get(k).getNode());
			}
		}

		Circle n1 = controller.addNode(100, 200); // Adding nodes
		Circle n2 = controller.addNode(300, 400);
		Circle n3 = controller.addNode(400, 200);

		controller.addEdge(n1, n2); // Adding roads between nodes
		controller.addEdge(n2, n3);

		// @Niall we want addEdge to take in type ITCM not type Circle
		//controller.addEdge(ITCM node, ITCM node);

		// Adding car animated along path in list of NodeDelay (
		//controller.addCar(new NodeDelay[]{new NodeDelay(n1, 100
		// 0),new NodeDelay(n2, 1000),new NodeDelay(n3, 5000)});
	}

	public void pause(){

	}

	public void reset(){

	}

	public void createNodes(){
		// Existing Nodes & Adjacency lists - In future change to allow passing in a graph topology (e.g. CSV adjacency matrix)

		List<ITCM> nodes = new ArrayList<ITCM>();
		TrafficLights flagpoles = new TrafficLights(new SimpleJunction(1,2));	nodeList.add(flagpoles);
		TrafficLights libRoundabout = new TrafficLights(new SimpleJunction(1,2));	nodeList.add(libRoundabout);
		TrafficLights leroRoundabout = new TrafficLights(new SimpleJunction(1,2));	nodeList.add(leroRoundabout);
		TrafficLights stables = new TrafficLights(new SimpleJunction(1,2));	nodeList.add(stables);
		TrafficLights eastGate = new TrafficLights(new SimpleJunction(1,2));	nodeList.add(eastGate);

//		// Setup adjacency lists for each of the original nodes
		flagpoles.setAdjacent(Arrays.asList(new NodePair(libRoundabout, 3)));
		libRoundabout.setAdjacent(Arrays.asList(new NodePair(flagpoles, 3), new NodePair(leroRoundabout, 1), new NodePair(stables, 2)));
		leroRoundabout.setAdjacent(Arrays.asList(new NodePair(libRoundabout, 1)));
		stables.setAdjacent(Arrays.asList(new NodePair(libRoundabout, 2), new NodePair(eastGate, 4)));
		eastGate.setAdjacent(Arrays.asList(new NodePair(stables, 4)));
	}

	public void createVehicle(ITCM start, ITCM end){
		float xCoord = 1; // TODO:start.getX + start.getY
		float yCoord = 2; // TODO: end.getX + end.getY
		String xyCoords = String.valueOf(xCoord) + String.valueOf(yCoord);
		if (routeMap.containsKey(xyCoords)) {
			vehicles.add(routeMap.get(xyCoords).copy());
		} else {
			Vehicle newVehicle = new Vehicle(start, end);
			vehicles.add(newVehicle);
			routeMap.put(xyCoords, newVehicle);
		}
	}

	public void updateGraph(){

	}

	public Metric getMetrics(){
		Metric newMetric = new Metric();
		return newMetric.generateMetrics();
	}

	public ArrayList<ITCM> getNodeList(){
		return this.nodeList;
	}

	public ArrayList<Vehicle> getVehicles(){
		return this.vehicles;
	}

	public HashMap<String, Vehicle> getRouteMap(){
		return this.routeMap;
	}
}