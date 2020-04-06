package CS4125.Controller.Sim;


import CS4125.Model.Metrics.Metric;
import CS4125.Model.Utils.BasicLogger;
import CS4125.Model.Utils.LoggingAdapter;
import CS4125.Model.Vehicle.IVehicleCreator;
import CS4125.Model.Vehicle.Car;
import CS4125.Model.TrafficControl.*;
import CS4125.Model.Vehicle.IVehicle;
import CS4125.Model.Vehicle.Move;
import CS4125.Model.Vehicle.VehicleCreator;
import CS4125.View.EventHandlers.UIController;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.shape.Circle;
import CS4125.Model.TrafficControl.SimpleJunction;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public enum Simulation{

	INSTANCE;

	public List<ITCM> nodeList; // Having public breaks encapsulation - cannot have final due to it not being initialized before simulation
	public LoggingAdapter logger;

    private List<Memento> savedSims;
	private HashMap<String, IVehicle> routeMap;
	private List<Circle> circles;
	private int vehicleQuantity;
	private List<IVehicle> vehicles;
	private Queue<Move> moveQueue;
	private Queue<IVehicle> waitingQueue;
	private static UIController controller;
	private IVehicleCreator vc;





	public void init(UIController controller) {
		this.nodeList = new ArrayList<ITCM>();
		this.routeMap = new HashMap<String, IVehicle>();
		this.vehicles = new ArrayList<IVehicle>();
		this.vehicleQuantity = 0;
		this.controller = controller;
		this.circles = new ArrayList<Circle>();
		moveQueue = new ArrayBlockingQueue<Move>(10000);
		savedSims = new ArrayList<Memento>();
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

		logger = LoggingAdapter.createLogger("sim-inst", BasicLogger.class);

		defaultNodes();

		for (ITCM itcm : nodeList)
			controller.addNode(itcm);

		for (ITCM itcm : nodeList) {
			for (ITCM value : itcm.getAdjacent()) {
				controller.addEdge(itcm, value);
			}
		}
		createVehicles(getEndpoints(), 1200); // this might be problem
		new MoveConsumer(moveQueue).start();
	}

	public void pause(){

	}

	public void reset(){
	}

	/**
	 * Add a node
	 * @param type Node type
	 * @param name Label
	 * @param x xcoord
	 * @param y ycoord
	 */
	public void addNode(String type, String name, int x, int y, boolean endpoint)  {
		// Block the Vehicle creator thread while node and it's adjacent list are being updated

		ITCM n;
		switch (type) {
			case "TrafficLights": n = new TrafficLights(new SimpleJunction(name, x, y, false)); break;
			case "Roundabout": n = new Roundabout(new SimpleJunction(name, x, y, false)); break;
			default: n = new SimpleJunction(name,x,y, false); break;
		}
		if(endpoint) // TODO: 30/11/2019 take boolean in param list, if boolean cast to IEndpoint
			n.setEndpoint(true);
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
			logger.debug("Labels not found");
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
            controller.deleteEdge(nodeList.get(l1index), nodeList.get(l2index));
        } else {
            logger.debug("Labels not found");
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
		}
		if (n2.getAdjacent().isEmpty()) {
			n2.setAdjacent(new ArrayList<>(Arrays.asList(n2)));
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

		TrafficLights f = new TrafficLights(new SimpleJunction("TrafficLights_f",300,200, true));
		TrafficLights e = new TrafficLights(new SimpleJunction("TrafficLights_e",100,200, false));
		TrafficLights d = new TrafficLights(new SimpleJunction("TrafficLights_d",55,300, false));
		TrafficLights c = new TrafficLights(new SimpleJunction("TrafficLights_c",300,125, true));
		TrafficLights b = new TrafficLights(new SimpleJunction("TrafficLights_b",400,300, true));
		TrafficLights a = new TrafficLights(new SimpleJunction("TrafficLights_a",200,400, true));
		f.setAdjacent(new ArrayList<ITCM>(Arrays.asList(e)));
		e.setAdjacent(new ArrayList<ITCM>(Arrays.asList(c,d,f)));
		d.setAdjacent(new ArrayList<ITCM>(Arrays.asList(a,c,b,e)));
		c.setAdjacent(new ArrayList<ITCM>(Arrays.asList(a,d,e)));
		b.setAdjacent(new ArrayList<ITCM>(Arrays.asList(d)));
		a.setAdjacent(new ArrayList<ITCM>(Arrays.asList(c,d)));

		nodeList.addAll(Arrays.asList(a, b, c, d, e, f));
	}

	/**
	 * Get nodes of type IEndpoint from the nodeList
	 * @return subset of Node list where node instanceof IEndpoint
	 */
	public List<ITCM> getEndpoints() {
		List<ITCM> endpoints = new ArrayList<>();
		for(int i = 0; i < nodeList.size(); i++) {
			if(nodeList.get(i).isEndpoint()) {
				endpoints.add(nodeList.get(i));
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
	public void createVehicles(List<ITCM> nodes, int timer) {
		vc = new VehicleCreator(nodes, timer); // start vehicle creation with default timer and start end
	}

	/**
	 * Used to set the timer of the VehicleCreator thread to have vehicles created at different timings
	 * @param timer how often you want a vehicle created
	 */
	public void setVCTimer(int timer) {
		vc.setTimer(1300 - (timer * 12));
	}

	/**
	 * Returns metric, not complete
	 * @return
	 */
	public int getMetrics(IVehicle v){
		Timestamp initial = v.getInitialTime();
		Timestamp current = new Timestamp(System.currentTimeMillis());
		return initial.compareTo(current);
	}

	/**
	 * Returns the list of nodes
	 * @return
	 */
	public List<ITCM> getNodeList(){
		return this.nodeList;
	}

	public int getVehicleJourneyTime(IVehicle v) {
		return v.getInitialTime().compareTo(v.getEndTime());
	}

	public int getJourneyTime(ITCM current, ITCM next) {
		float dist = current.distanceTo(next);
		float queue = next.getCurrentQueue(current);
		int maxQueue = next.getMaxQueue(current) / 10;
		float percentQueue = queue / maxQueue;
		//logger.debug("Queue size: " + queue);
		//logger.debug("Queue max: " + maxQueue);
		//logger.debug("Queue full: " + percentQueue);
		float time = (percentQueue + 1) * (dist / 5);
		return (int) time * 100;
	}

	public List<IVehicle> getVehicleList() {return this.vehicles; };
	public Queue<Move> getMoveQueue() { return this.moveQueue; }
	public void addVehicleToVehicleList(IVehicle v) {vehicles.add(v);}
	public List<Memento> getSavedSims() { return savedSims; }

	/**
	 * Adds the vehicle animation to UI Controller with the current index
	 * @param m Move animation
	 */
	public void addMoveAnimimation(Move m) {

		Platform.runLater(
            () -> {
                controller.addAnimation(m.getStart(), m.getEnd(), getJourneyTime(m.getStart(), m.getEnd()));
            }
		);
	}

	public HashMap<String, IVehicle> getRouteMap(){
		return this.routeMap;
	}

	public void saveToMemento() {
		savedSims.add(new Memento(new ArrayList<ITCM>(getNodeList()), new HashMap<String, IVehicle>(getRouteMap()), new ArrayList<IVehicle>(getVehicleList()), getMoveQueue(), this.controller, this.vc));
	}

	public class Memento {

		public List<ITCM> nodeList; // Having public breaks encapsulation - cannot have final due to it not being initialized before simulation
		private HashMap<String, IVehicle> routeMap;
		private List<IVehicle> vehicles;
		private Queue<Move> moveQueue;
		private UIController controller;
		private IVehicleCreator vc;


		public Memento(List<ITCM> nodeList, HashMap<String, IVehicle> routeMap, List<IVehicle> vehicles, Queue<Move> moveQueue, UIController controller, IVehicleCreator
				vc) {
			this.nodeList = nodeList;
			this.routeMap = routeMap;
			this.vehicles = vehicles;
			this.controller = controller;
			this.moveQueue = moveQueue;
			this.vc = vc;
		}

		public void restoreFromMemento() {
			// delete all old nodes
			for (ITCM n : Simulation.INSTANCE.nodeList) {
				controller.deleteNode(n);
			}

			// re-add memento nodes
			for (ITCM n : nodeList) {
				controller.addNode(n);
				for (ITCM value : n.getAdjacent()) {
					controller.addEdge(n, value);
				}
			}

			// TODO: MoveConsumer changed, need to be able to save the move consumer state and re start
			// createVehicles(getEndpoints(), 1200); // this might be problem
			// new MoveConsumer(moveQueue).start();

		}


		public UIController getController() {
			return controller;
		}

		public List<ITCM> getNodeList() {
			return this.nodeList;
		}

		public HashMap<String, IVehicle> getRouteMap() {
			return this.routeMap;
		}

		public List<IVehicle> getVehicles() {
			return vehicles;
		}

		public Queue<Move> getMoveQueue() {
			return moveQueue;
		}

		public IVehicleCreator getVehicleCreator() {
			return vc;
		}
	}

}