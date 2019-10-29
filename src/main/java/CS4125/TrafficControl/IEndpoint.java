package CS4125.TrafficControl;

public interface IEndpoint {

	int capacity = 0;

	public abstract int getCapacity();

	public abstract void setCapacity(int capacity);
}