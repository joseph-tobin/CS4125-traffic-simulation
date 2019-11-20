package CS4125.Model.TrafficControl;

/**
 * Interface which describes the endpoint on a graph.
 */
public interface IEndpoint {
	public abstract int getCapacity();
	public abstract void setCapacity(int capacity);
}