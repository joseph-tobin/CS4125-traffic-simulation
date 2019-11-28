package CS4125.Controller.Sim;


import CS4125.Model.Metrics.Metric;
import CS4125.Model.Vehicle.Vehicle;
import CS4125.Model.TrafficControl.*;
import CS4125.View.EventHandlers.UIController;
import javafx.scene.shape.Circle;
import CS4125.Model.TrafficControl.SimpleJunction;

import java.util.*;

public enum Simulation{

	INSTANCE;

	public List<ITCM> nodeList; // Having public breaks encapsulation - cannot have final due to it not being initialized before simulation
	private HashMap<String, Vehicle> routeMap;
	private List<Circle> circles;
	private int vehicleQuantity;
	private List<Vehicle> vehicles = new ArrayList<>();
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

		// HARDCODED FOR NOW
		// TODO: instantiate nodeList and routeMap
		createNodes();

		for (int i = 1; i < vehicleQuantity; i++) {
			createVehicle(nodeList.get(new Random().nextInt(nodeList.size() - 1)), nodeList.get(new Random().nextInt(nodeList.size() - 1)));
		}

		for (int j = 0; j < nodeList.size(); j++) {
			controller.addNode(nodeList.get(j));
			List<ITCM> adj = new ArrayList<ITCM>();
			adj = nodeList.get(j).getAdjacent();
			for (int k = 0; k < adj.size(); k++) {
				controller.addEdge(nodeList.get(j), adj.get(k));
			}
		}
	}

	public void pause(){

	}

	public void reset(){

	}

	public void createNodes(){
		//Existing Nodes & Adjacency lists - In future change to allow passing in a graph topology (e.g. CSV adjacency matrix)

		// adding to nodeList
		List<ITCM> adj = new ArrayList<ITCM>();
		TrafficLights flagpoles = new TrafficLights(new SimpleJunction(1,20, adj));	nodeList.add(flagpoles); adj.clear();
		adj.add(flagpoles);
		TrafficLights libRoundabout = new TrafficLights(new SimpleJunction(15,2, adj));	nodeList.add(libRoundabout); adj.clear();
		adj.add(libRoundabout); adj.add(flagpoles);
		TrafficLights leroRoundabout = new TrafficLights(new SimpleJunction(8,23, adj));	nodeList.add(leroRoundabout);
//		TrafficLights stables = new TrafficLights(new SimpleJunction(1,2));	nodeList.add(stables);
//		TrafficLights eastGate = new TrafficLights(new SimpleJunction(1,2));	nodeList.add(eastGate);
//
//		// Setup adjacency lists for each of the original nodes
//		flagpoles.setAdjacent(Arrays.asList(new NodePair(libRoundabout, 3)));
//		libRoundabout.setAdjacent(Arrays.asList(new NodePair(flagpoles, 3), new NodePair(leroRoundabout, 1), new NodePair(stables, 2)));
//		leroRoundabout.setAdjacent(Arrays.asList(new NodePair(libRoundabout, 1)));
//		stables.setAdjacent(Arrays.asList(new NodePair(libRoundabout, 2), new NodePair(eastGate, 4)));
//		eastGate.setAdjacent(Arrays.asList(new NodePair(stables, 4)));

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

	public List<ITCM> getNodeList(){
		return this.nodeList;
	}

	public List<Vehicle> getVehicles(){
		return this.vehicles;
	}

	public HashMap<String, Vehicle> getRouteMap(){
		return this.routeMap;
	}
}