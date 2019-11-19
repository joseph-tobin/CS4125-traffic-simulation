package CS4125.Sim;


import CS4125.TrafficControl.*;
//import CS4125.UserInterface.NodeDelay;
import CS4125.UserInterface.UIController;
import javafx.scene.shape.Circle;
import CS4125.TrafficControl.ITCM;
import CS4125.TrafficControl.SimpleJunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public enum Simulation{

	INSTANCE;

	public ArrayList<ITCM> nodeList; // Having public breaks encapsulation - cannot have final due to it not being initialized before simulation
	private HashMap<String, Vehicle> routeMap;
	private int vehicleQuantity;
	private ArrayList<Vehicle> vehicles = new ArrayList<>();
	private static UIController controller;


	public void init(UIController controller) {
		this.nodeList = new ArrayList<ITCM>();
		this.routeMap = new HashMap<String, Vehicle>();
		this.vehicles = new ArrayList<>();
		this.vehicleQuantity = 0;
		this.controller = controller;
	}
	Simulation() {
	}

	public void run(){
		// HARCODED FOR NOW
		vehicleQuantity = 10;
		ITCM start = new SimpleJunction(10, 10, new ArrayList<ITCM>()) {};
		ITCM end = new SimpleJunction(10,5, new ArrayList<ITCM>());

		// HARDCODED FOR NOW
		// TODO: instantiate nodeList and routeMap
		createNodes();
		for (int i = 1; i < vehicleQuantity; i++) {
			// INITIALISE RANDOM START AND END NODE
			createVehicle(nodeList.get(new Random().nextInt(nodeList.size() - 1)), nodeList.get(new Random().nextInt(nodeList.size() - 1)));
		}

		Circle n1 = controller.addNode(200, 200); // Adding nodes
		Circle n2 = controller.addNode(300, 400);
		Circle n3 = controller.addNode(400, 200);

		controller.addEdge(n1, n2); // Adding roads between nodes
		controller.addEdge(n2, n3);

		// Adding car animated along path in list of NodeDelay (
		//controller.addCar(new NodeDelay[]{new NodeDelay(n1, 1000),new NodeDelay(n2, 1000),new NodeDelay(n3, 5000)});
	}

	public void pause(){

	}

	public void reset(){

	}

	public void createNodes(){
		// Existing Nodes & Adjacency lists - In future change to allow passing in a graph topology (e.g. CSV adjacency matrix)

//		TCMFactory factory = new TCMFactory();
//		ArrayList<Node> nodes = new ArrayList<Node>();
//		TrafficLights flagpoles = (TrafficLights) factory.getTCM(TCMFactory.TCMType.TRAFFIC_LIGHTS, 0, 0);			nodeList.add(flagpoles);
//		SimpleJunction libRoundabout = (SimpleJunction) factory.getTCM(TCMFactory.TCMType.SIMPLE_JUNCTION, 0, 0);		nodeList.add(libRoundabout);
//		SimpleJunction leroRoundabout = (SimpleJunction) factory.getTCM(TCMFactory.TCMType.SIMPLE_JUNCTION, 0, 0);	nodeList.add(leroRoundabout);
//		SimpleJunction stables = (SimpleJunction) factory.getTCM(TCMFactory.TCMType.SIMPLE_JUNCTION, 0, 0);			nodeList.add(stables);
//		SimpleJunction stablesCarpark = (SimpleJunction) factory.getTCM(TCMFactory.TCMType.SIMPLE_JUNCTION, 0, 0);	nodeList.add(stablesCarpark);
//		TrafficLights eastGate = (TrafficLights) factory.getTCM(TCMFactory.TCMType.TRAFFIC_LIGHTS, 0, 0);				nodeList.add(eastGate);
//
//		// Setup adjacency lists for each of the original nodes
//		flagpoles.setAdjacent(new ArrayList<Node>(Arrays.asList(libRoundabout)));
//		libRoundabout.setAdjacent(new ArrayList<Node>(Arrays.asList(flagpoles, leroRoundabout, stables)));
//		leroRoundabout.setAdjacent(new ArrayList<Node>(Arrays.asList(libRoundabout)));
//		stables.setAdjacent(new ArrayList<Node>(Arrays.asList(libRoundabout, stablesCarpark, eastGate)));
//		stablesCarpark.setAdjacent(new ArrayList<Node>(Arrays.asList(stables)));
//		eastGate.setAdjacent(new ArrayList<Node>(Arrays.asList(stables)));
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