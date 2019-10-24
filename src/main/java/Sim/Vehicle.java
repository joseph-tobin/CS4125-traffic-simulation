package Sim;

import TrafficControl.Node;

public class Vehicle{

	private Node start;
	private Node end;

	public Vehicle(Node start, Node end){
		this.start = start;
		this.end = end;
	}

	public void routeFind(){

	}

	// TODO: 24-10-19 implement deep copy of vehicle
	public Vehicle copy() {
		return this;
	}
}