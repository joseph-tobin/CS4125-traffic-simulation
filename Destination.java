public class Destination extends Node{

	private int capacity;

	public Destination(float x, float y, int capacity){
		super(x,y);
		this.capacity = capacity;
	}

	public getCapacity(){
		return this.capacity;
	}

	public setCapacity(int capacity){
		this.capacity = capacity;
	}
}